package com.sff.rbacdemo.system.controller;

import com.sff.rbacdemo.common.annotation.Log;
import com.sff.rbacdemo.common.controller.BaseController;
import com.sff.rbacdemo.common.model.APIResponse;
import com.sff.rbacdemo.common.properties.GlobalConstant;
import com.sff.rbacdemo.common.utils.JWTUtil;
import com.sff.rbacdemo.common.utils.MD5Utils;
import com.sff.rbacdemo.system.dto.*;
import com.sff.rbacdemo.system.entity.User;
import com.sff.rbacdemo.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author frankie fan
 * @date 2022-03-09
 */
@Slf4j
@Controller
public class LogInOutMgmt extends BaseController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    @ResponseBody
    public APIResponse login(@RequestBody LoginFormDTO loginForm) {
        String username = loginForm.getUsername();
        String password = loginForm.getPassword();
        // 密码 MD5 加密
        password = MD5Utils.encrypt(username.toLowerCase(), password);
        User user = this.userService.findByName(username);
        if (user != null) {
            if (password.equals(user.getPassword())) {
                try {
                    LoginResultDTO result = new LoginResultDTO();
                    result.setToken(JWTUtil.sign(username, password));
                    result.setUserId(user.getUserId());
                    this.userService.updateLoginTime(username);
                    return APIResponse.OK("登陆成功", result);
                } catch (UnsupportedEncodingException e) {
                    return APIResponse.ERROR(GlobalConstant.HTTP_500, e.getMessage(), e);
                }
            } else {
                throw new AuthenticationException("User & password didn't match!");
            }
        } else {
            throw new AuthenticationException("User didn't existed!");
        }
    }

    @PostMapping("logout")
    @ResponseBody
    public APIResponse logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
        return APIResponse.OK("登出成功", null);
    }

    @Log("获取用户信息")
    @RequestMapping("getUserInfo")
    @RequiresAuthentication
    @ResponseBody
    public APIResponse<UserInfoDTO> getUserInfo() {
        UserInfoDTO userInfo = this.userService.getCurrentUserInfo();
        return APIResponse.OK("userInfo", userInfo);
    }

    @Log("获取用户权限")
    @RequestMapping("getPermCode")
    @RequiresAuthentication
    @ResponseBody
    public APIResponse<List> getPermCode() {
        List<String> permList = new ArrayList<>();
        permList.add("100");
        permList.add("200");
        permList.add("300");
        return APIResponse.OK("permCode", permList);
    }

    @Log("获取用户菜单")
    @RequestMapping("getRouteList")
    @RequiresAuthentication
    @ResponseBody
    public APIResponse<List> getUserRouteList() {
        // dashboard
        Meta dashboardMeta = new Meta();
        dashboardMeta.setTitle("routes.dashboard.dashboard");
        dashboardMeta.setHideChildrenInMenu(false);
        dashboardMeta.setIcon("bx:bx-home");
        RouteDTO dashboardRoute = new RouteDTO();
        dashboardRoute.setPath("/dashboard");
        dashboardRoute.setName("Dashboard");
        dashboardRoute.setComponent("LAYOUT");
        dashboardRoute.setAlias("/dashboard");
        dashboardRoute.setRedirect("/dashboard/analysis");
        dashboardRoute.setMeta(dashboardMeta);
        // dashboard analysis
        Meta analysisMeta = new Meta();
        analysisMeta.setHideMenu(false);
        analysisMeta.setHideBreadcrumb(false);
        analysisMeta.setTitle("routes.dashboard.analysis");
        analysisMeta.setCurrentActiveMenu("/dashboard");
        analysisMeta.setIcon("bx:bx-home");
        RouteDTO analysisRoute = new RouteDTO();
        analysisRoute.setPath("analysis");
        analysisRoute.setName("Analysis");
        analysisRoute.setAlias("analysis");
        analysisRoute.setComponent("/dashboard/analysis/index");
        analysisRoute.setMeta(analysisMeta);
        // dashboard workbench
        Meta workbenchMeta = new Meta();
        workbenchMeta.setHideMenu(false);
        workbenchMeta.setHideBreadcrumb(false);
        workbenchMeta.setTitle("routes.dashboard.workbench");
        workbenchMeta.setCurrentActiveMenu("/dashboard");
        workbenchMeta.setIcon("bx:bx-home");
        RouteDTO workbenchRoute = new RouteDTO();
        workbenchRoute.setPath("workbench");
        workbenchRoute.setName("Workbench");
        workbenchRoute.setAlias("workbench");
        workbenchRoute.setComponent("/dashboard/workbench/index");
        workbenchRoute.setMeta(workbenchMeta);
        List<RouteDTO> dashboardList  = new ArrayList<>();
        dashboardList.add(analysisRoute);
        dashboardList.add(workbenchRoute);
        dashboardRoute.setChildren(dashboardList);

        // system management
        Meta sysMeta = new Meta();
        sysMeta.setIcon("ion:settings-outline");
        sysMeta.setTitle("routes.biz.system.moduleName");
        RouteDTO sysRoute = new RouteDTO();
        sysRoute.setComponent("LAYOUT");
        sysRoute.setPath("/system");
        sysRoute.setName("System");
        sysRoute.setAlias("/system");
        sysRoute.setRedirect("/system/account");
        sysRoute.setMeta(sysMeta);
        // system account mgmt
        Meta accountMeta = new Meta();
        accountMeta.setIgnoreKeepAlive(true);
        accountMeta.setTitle("routes.biz.system.account");
        RouteDTO accountRoute = new RouteDTO();
        accountRoute.setComponent("/biz/system/account/index");
        accountRoute.setPath("account");
        accountRoute.setName("AccountManagement");
        accountRoute.setAlias("account");
        accountRoute.setMeta(accountMeta);
        // system account detail
        Meta accountDetailMeta = new Meta();
        accountDetailMeta.setHideMenu(true);
        accountDetailMeta.setTitle("routes.biz.system.account_detail");
        accountDetailMeta.setCurrentActiveMenu("/system/account");
        accountDetailMeta.setIgnoreKeepAlive(true);
        RouteDTO accountDetailRoute = new RouteDTO();
        accountDetailRoute.setComponent("/biz/system/account/AccountDetail");
        accountDetailRoute.setPath("account_detail/:id");
        accountDetailRoute.setName("AccountDetail");
        accountDetailRoute.setAlias("account_detail/:id");
        accountDetailRoute.setMeta(accountDetailMeta);
        // system role mgmt
        Meta roleMeta = new Meta();
        roleMeta.setIgnoreKeepAlive(true);
        roleMeta.setTitle("routes.biz.system.role");
        RouteDTO roleRoute = new RouteDTO();
        roleRoute.setComponent("/biz/system/role/index");
        roleRoute.setPath("role");
        roleRoute.setName("RoleManagement");
        roleRoute.setAlias("role");
        roleRoute.setMeta(roleMeta);
        // system menu mgmt
        Meta menuMeta = new Meta();
        menuMeta.setIgnoreKeepAlive(true);
        menuMeta.setTitle("routes.biz.system.menu");
        RouteDTO menuRoute = new RouteDTO();
        menuRoute.setComponent("/biz/system/menu/index");
        menuRoute.setPath("/menu");
        menuRoute.setName("MenuManagement");
        menuRoute.setAlias("/menu");
        menuRoute.setMeta(menuMeta);
        // system dept mgmt
        Meta deptMeta = new Meta();
        deptMeta.setIgnoreKeepAlive(true);
        deptMeta.setTitle("routes.biz.system.dept");
        RouteDTO deptRoute = new RouteDTO();
        deptRoute.setComponent("/biz/system/dept/index");
        deptRoute.setPath("/dept");
        deptRoute.setName("DeptManagement");
        deptRoute.setAlias("/dept");
        deptRoute.setMeta(deptMeta);
        List<RouteDTO> sysChildren = new ArrayList<>();
        sysChildren.add(accountRoute);
        sysChildren.add(accountDetailRoute);
        sysChildren.add(roleRoute);
        sysChildren.add(menuRoute);
        sysChildren.add(deptRoute);
        sysRoute.setChildren(sysChildren);
        List<RouteDTO> menus = new ArrayList<>();
        menus.add(dashboardRoute);
        menus.add(sysRoute);
        return APIResponse.OK("menus", menus);
    }
}
