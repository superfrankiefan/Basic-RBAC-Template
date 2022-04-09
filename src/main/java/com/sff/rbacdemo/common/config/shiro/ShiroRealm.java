package com.sff.rbacdemo.common.config.shiro;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.sff.rbacdemo.common.utils.JWTUtil;
import com.sff.rbacdemo.system.entity.Resource;
import com.sff.rbacdemo.system.entity.Role;
import com.sff.rbacdemo.system.entity.User;
import com.sff.rbacdemo.system.service.ResourceService;
import com.sff.rbacdemo.system.service.RoleService;
import com.sff.rbacdemo.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author Frankie Fan
 * @date 2022-03-05 18:54
 * 自定义认证和授权规则
 */

@Slf4j(topic = "ShiroRealm.class")
@Service
public class ShiroRealm extends AuthorizingRealm {

    private UserService userService;

    private RoleService roleService;

    private ResourceService resourceService;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setRoleService(RoleService roleService) {
        this.roleService = roleService;
    }

    @Autowired
    public void setResourceService(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    /**
     * 大坑！，必须重写此方法，不然Shiro会报错
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }

    /**
     * 只有当需要检测用户权限的时候才会调用此方法，例如checkRole,checkPermission之类的
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String userName = JWTUtil.getUsername(principals.toString());
        User user = userService.findByUserName(userName);
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
//        simpleAuthorizationInfo.addRole(user.getRoleName());
        // 获取用户角色集
        List<Role> roleList = this.roleService.findUserRole(userName);
        Set<String> roleSet = roleList.stream().map(Role::getRoleName).collect(Collectors.toSet());
        simpleAuthorizationInfo.setRoles(roleSet);

        // 获取用户权限集
        List<Resource> permissionList = this.resourceService.findUserPermissions(userName);
        Set<String> permissionSet = permissionList.stream().map(Resource::getPerms).collect(Collectors.toSet());
        simpleAuthorizationInfo.setStringPermissions(permissionSet);
        return simpleAuthorizationInfo;
    }

    /**
     * 默认使用此方法进行用户名正确与否验证，错误抛出异常即可。
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException, JWTVerificationException {
        String token = (String) auth.getCredentials();
        //Decrypt to get the user name, which is used to compare with the database
        String username = JWTUtil.getUsername(token);
        if (username == null) {
            throw new AuthenticationException("token invalid");
        }
        // 通过用户名到数据库查询用户信息
        User user = userService.findByUserName(username);
        if (user == null) {
            throw new AuthenticationException("User didn't existed!");
        }
        if (!JWTUtil.verify(token, username, user.getPassword())) {
            throw new JWTVerificationException("Invalid Token!");
        }
        return new SimpleAuthenticationInfo(user, token, getName());
    }
}
