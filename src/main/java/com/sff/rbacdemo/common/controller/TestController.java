package com.sff.rbacdemo.common.controller;

import com.sff.rbacdemo.common.model.ResponseDTO;
import com.sff.rbacdemo.common.model.Result;
import com.sff.rbacdemo.common.utils.JWTUtil;
import com.sff.rbacdemo.common.utils.MD5Utils;
import com.sff.rbacdemo.system.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

/**
 * @author Frankie Fan
 * @date 2022-03-05 18:39
 */
@RestController
@RequestMapping("/test")
@Slf4j
public class TestController extends BaseController{
    private UserMapper userService;

    @Autowired
    public void setService(UserMapper userService) {
        this.userService = userService;
    }

    @PostMapping("login")
    public ResponseDTO login(@RequestParam("username") String username,
                             @RequestParam("password") String password,
                             @RequestParam("rememberMe") Boolean rememberMe) throws UnsupportedEncodingException {
        password = MD5Utils.encrypt(username.toLowerCase(), password);
        UsernamePasswordToken token = new UsernamePasswordToken(username, password, rememberMe);
        try {
            Subject subject = getSubject();
            if (subject != null)
                subject.logout();
            super.login(token);
//            this.userService.updateLoginTime(username);
            return new ResponseDTO(200, "Login success", JWTUtil.sign(username, password));
        } catch (UnknownAccountException | IncorrectCredentialsException | LockedAccountException e) {
            return new ResponseDTO(400, e.getMessage(), e);
        } catch (AuthenticationException e) {
            return new ResponseDTO(400, e.getMessage(), e);
        }
    }

    @GetMapping("article")
    public ResponseDTO article() {
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            return new ResponseDTO(200, "You are already logged in", null);
        } else {
            return new ResponseDTO(200, "You are guest", null);
        }
    }

    @GetMapping("/require_auth")
    @RequiresAuthentication
    public Result<Object> requireAuth() {
        return Result.OK("You are authenticated", null);
    }

    @GetMapping("/require_role")
    @RequiresRoles("admin")
    public ResponseDTO requireRole() {
        return new ResponseDTO(200, "You are visiting require_role", null);
    }

    @GetMapping("/require_permission")
    @RequiresPermissions(logical = Logical.AND, value = {"view", "edit"})
    public ResponseDTO requirePermission() {
        return new ResponseDTO(200, "You are visiting permission require edit,view", null);
    }

    @RequestMapping(path = "/401")
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseDTO unauthorized() {
        return new ResponseDTO(401, "Unauthorized", null);
    }
}
