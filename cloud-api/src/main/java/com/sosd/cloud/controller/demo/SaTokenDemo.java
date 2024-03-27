package com.sosd.cloud.controller.demo;

import cn.dev33.satoken.annotation.SaCheckLogin;
import cn.dev33.satoken.annotation.SaIgnore;
import cn.dev33.satoken.stp.StpUtil;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SaCheckLogin
@RequestMapping("/demo")
public class SaTokenDemo {
    @SaIgnore
    @RequestMapping("/login/{id}")
    String getToken(@PathVariable Integer id) {
        StpUtil.login(id);
        StpUtil.logout();
        return StpUtil.getTokenInfo().tokenValue;
    }

    @RequestMapping("/logout")
    String getToken() {
        StpUtil.logout();
        return "logout success!";
    }

    @PostMapping("/auth/{id}")
    String auth(@PathVariable Integer id) {
        return "success!";

    }
}
