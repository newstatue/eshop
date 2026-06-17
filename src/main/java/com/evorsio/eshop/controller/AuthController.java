package com.evorsio.eshop.controller;

import com.evorsio.eshop.common.R;
import com.evorsio.eshop.service.UserService;
import com.evorsio.eshop.vo.LoginVo;
import com.evorsio.eshop.vo.SendCodeVo;
import com.evorsio.eshop.vo.TokenVo;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Evorsio
 * @since 2026/6/16
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/auth")
public class AuthController {
    private final UserService userService;

    @PostMapping("/send-code")
    public R<TokenVo> sendCode(@Validated @RequestBody SendCodeVo vo) {
        userService.sendCode(vo.getPhone());
        return R.ok();
    }

    @PostMapping("/login")
    public R<TokenVo> login(@RequestBody LoginVo vo) {
        TokenVo r = userService.login(vo);
        return R.ok(r);
    }
}
