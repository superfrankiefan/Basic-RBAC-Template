package com.sff.rbacdemo.system.controller;

import com.sff.rbacdemo.common.controller.BaseController;
import com.sff.rbacdemo.common.model.Result;
import com.sff.rbacdemo.common.utils.JWTUtil;
import com.sff.rbacdemo.system.service.UserService;
import com.sff.rbacdemo.system.utils.MD5Utils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;

@Controller
public class LoginController extends BaseController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ResponseBody
    public Result login(String username, String password, Boolean rememberMe) {
        // 密码 MD5 加密
        password = MD5Utils.encrypt(username.toLowerCase(), password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        try {
            Subject subject = getSubject();
            if (subject != null)
                subject.logout();
            super.login(token);
            this.userService.updateLoginTime(username);
            return Result.OK(JWTUtil.sign(username,password));
        } catch (UnknownAccountException | IncorrectCredentialsException | LockedAccountException e) {
            return Result.error(e.getMessage());
        } catch (AuthenticationException e) {
            return Result.error("认证失败！");
        } catch (UnsupportedEncodingException e) {
            return Result.error(e.getMessage());
        }
    }
}
