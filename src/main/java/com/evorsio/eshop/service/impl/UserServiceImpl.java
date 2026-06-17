package com.evorsio.eshop.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.evorsio.eshop.common.BizCode;
import com.evorsio.eshop.common.BizConstants;
import com.evorsio.eshop.common.BizException;
import com.evorsio.eshop.common.BizProperties;
import com.evorsio.eshop.domain.User;
import com.evorsio.eshop.mapper.UserMapper;
import com.evorsio.eshop.service.UserService;
import com.evorsio.eshop.vo.LoginVo;
import com.evorsio.eshop.vo.TokenVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.concurrent.TimeUnit;

/**
 * @author Admin
 * @description 针对表【user】的数据库操作Service实现
 * @createDate 2026-06-16 00:58:31
 */
@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    private final StringRedisTemplate redisTemplate;
    private final BizProperties bizProperties;

    @Override
    public void sendCode(String phone) {
        long expire = bizProperties.getSms().getExpire();
        long interval = bizProperties.getSms().getInterval();
        // 1. 查看Redis中是否已有验证码
        String key = BizConstants.REDIS_KEY_SMS_CODE_PREFIX + phone;
        String existCode = redisTemplate.opsForValue().get(key);

        // 2. 已有验证码
        if (existCode != null) {
            Long ttl = redisTemplate.getExpire(key, TimeUnit.SECONDS);
            if (ttl > expire - interval) {
                throw new BizException(BizCode.SMS_CODE_FREQUENTLY);
            }
        }

        // 3. 没有，生成验证码
        String code = RandomUtil.randomNumbers(6);

        // 4. 存入redis
        redisTemplate.opsForValue().set(key, code, expire, TimeUnit.SECONDS);

        // TODO: 5. 发送短信
        log.info("用户: {}的验证码: {}", phone, code);
    }

    @Override
    public TokenVo login(LoginVo vo) {
        String phone = vo.getPhone();
        String code = vo.getCode();

        // 1. 从 Redis 取验证码
        String key = BizConstants.REDIS_KEY_SMS_CODE_PREFIX + phone;
        String cachedCode = redisTemplate.opsForValue().get(key);

        // 2. 校验验证码
        if (cachedCode == null || !cachedCode.equals(code)) {
            throw new BizException(BizCode.SMS_CODE_ERROR);
        }

        // 3. 删除验证码
        redisTemplate.delete(key);

        // 4. 查询用户，不存在自动注册
        User user = lambdaQuery().eq(User::getPhone, phone).one();
        if (user == null) {
            user = new User();
            user.setPhone(phone);
            user.setNickname(BizConstants.USER_NAME_PREFIX + phone.substring(phone.length() - 4));
            this.save(user);
        }

        // 5. Sa-Token登录，生成Token
        StpUtil.login(user.getId());
        SaTokenInfo tokenInfo = StpUtil.getTokenInfo();

        // 6. 返回Token
        TokenVo tokenVo = new TokenVo();
        tokenVo.setAccessToken(tokenInfo.getTokenValue());
        tokenVo.setExpireAt(LocalDateTime.now().plusSeconds(tokenInfo.getTokenTimeout()));
        return tokenVo;
    }

}




