package com.sff.rbacdemo.common.config.shiro;

import com.sff.rbacdemo.common.utils.JWTUtil;
import com.sff.rbacdemo.system.entity.User;
import com.sff.rbacdemo.system.mapper.UserMapper;
import com.sff.rbacdemo.system.service.UserService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author Frankie Fan
 * @date 2022-03-05 18:54
 */

@Service
public class AuthRealm extends AuthorizingRealm {

    private static final Logger LOGGER = LogManager.getLogger(AuthRealm.class);

    private UserService userService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
//    @Override
//    public boolean supports(AuthenticationToken token) {
//        return token instanceof JWTToken;
//    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = JWTUtil.getUsername(principals.toString());
        User user = userService.findByName(username);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.addRole(user.getRoleName());
//        Set<String> permission = new HashSet<>(Arrays.asList(user.getPermission().split(",")));
//        simpleAuthorizationInfo.addStringPermissions(permission);
        return simpleAuthorizationInfo;
    }

    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        // 获取用户输入的用户名和密码
        String userName = (String) auth.getPrincipal();
        if (userName == null) {
            throw new AuthenticationException("token invalid");
        }
        String password = new String((char[]) auth.getCredentials());

        // 通过用户名到数据库查询用户信息
        User user = userService.findByName(userName);
        if (!password.equals(user.getPassword()))
            throw new IncorrectCredentialsException("用户名或密码错误！");
        return new SimpleAuthenticationInfo(user, password, getName());
    }
}
