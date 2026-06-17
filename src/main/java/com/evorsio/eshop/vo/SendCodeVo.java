package com.evorsio.eshop.vo;

import cn.hutool.core.lang.RegexPool;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * @author Evorsio
 * @since 2026/6/16
 */
@Data
public class SendCodeVo {
    @NotBlank(message = "手机号不能为空")
    @Pattern(regexp = RegexPool.MOBILE, message = "手机号格式错误")
    private String phone;
}
