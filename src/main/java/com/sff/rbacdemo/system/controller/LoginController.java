package com.sff.rbacdemo.system.controller;

import com.sff.rbacdemo.common.controller.BaseController;
import com.sff.rbacdemo.common.properties.GlobalConstant;
import com.sff.rbacdemo.common.model.APIResponse;
import com.sff.rbacdemo.common.utils.JWTUtil;
import com.sff.rbacdemo.common.utils.MD5Utils;
import com.sff.rbacdemo.common.annotation.Log;
import com.sff.rbacdemo.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;

@Slf4j
@Controller
public class LoginController extends BaseController {

    @Autowired
    private UserService userService;

    @Log("用户登陆")
    @PostMapping("/login")
    @ResponseBody
    public APIResponse login(String username, String password) {
        // 密码 MD5 加密
        password = MD5Utils.encrypt(username.toLowerCase(), password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password);
        try {
            Subject subject = getSubject();
            if (subject != null)
                subject.logout();
            super.login(token);
            this.userService.updateLoginTime(username);
            return APIResponse.OK("登陆成功", JWTUtil.sign(username, password));
        } catch (UnsupportedEncodingException e) {
            return APIResponse.ERROR(GlobalConstant.HTTP_500, e.getMessage(), e);
        }
    }
}
