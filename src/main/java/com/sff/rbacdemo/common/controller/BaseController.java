package com.sff.rbacdemo.common.controller;

import com.sff.rbacdemo.system.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.subject.Subject;

/**
 * @author frankie fan
 * @date 2022-03-07
 * 控制器基类
 */
public class BaseController {

    /**
     * 获取Shiro Subject
     * @return
     */
    protected static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    /**
     * 获取当前登陆用户
     * @return
     */
    protected User getCurrentUser() {
        return (User) getSubject().getPrincipal();
    }

    /**
     * 用户登陆
     * @param token
     */
    protected void login(AuthenticationToken token) {
        getSubject().login(token);
    }

}
