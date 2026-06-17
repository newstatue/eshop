package com.evorsio.eshop.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.evorsio.eshop.domain.User;
import com.evorsio.eshop.vo.LoginVo;
import com.evorsio.eshop.vo.TokenVo;

/**
 * @author Admin
 * @description 针对表【user】的数据库操作Service
 * @createDate 2026-06-16 00:58:31
 */
public interface UserService extends IService<User> {
    void sendCode(String phone);

    TokenVo login(LoginVo vo);
}
