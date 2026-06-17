package com.evorsio.eshop.vo;

import cn.hutool.core.lang.RegexPool;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

/**
 * @author Evorsio
 * @since 2026/6/16
 * 用户登录视图
 */
@Data
public class LoginVo {
    // 手机号
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = RegexPool.MOBILE, message = "手机号格式错误")
    private String phone;
    // 验证码
    @NotBlank(message = "验证码不能为空")
    @Pattern(regexp = RegexPool.NUMBERS, message = "验证码必须是数字")
    @Size(min = 6, max = 6, message = "验证码必须是6位")
    private String code;
}
