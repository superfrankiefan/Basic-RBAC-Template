package com.sff.rbacdemo.system.controller;

import com.sff.rbacdemo.common.annotation.Log;
import com.sff.rbacdemo.common.controller.BaseController;
import com.sff.rbacdemo.common.model.APIResponse;
import com.sff.rbacdemo.common.model.PageResponseDTO;
import com.sff.rbacdemo.common.properties.GlobalConstant;
import com.sff.rbacdemo.common.utils.MD5Utils;
import com.sff.rbacdemo.system.entity.User;
import com.sff.rbacdemo.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Frankie Fan
 * @date 2022-03-21 12:12
 * Account Management
 */

@Slf4j
@RestController
@RequestMapping("/system/account")
public class UserMgmt extends BaseController {

    @Autowired
    private UserService userService;

    private static final String ON = "on";

    @GetMapping("getAccountList")
    @ResponseBody
    @RequiresAuthentication
    public APIResponse getAccountListByDept(@RequestParam(required = false) String deptId,
                                            @RequestParam(required = false) String username,
                                            @RequestParam(required = false) String realName,
                                            @RequestParam(required = false, defaultValue = "0") Integer page,
                                            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        PageResponseDTO<User> accounts = this.userService.getUserListByDept(deptId, username, realName, page, pageSize);
        return APIResponse.OK("accounts", accounts);
    }

    @Log("新增用户")
    @RequiresAuthentication
    @RequestMapping("add")
    @ResponseBody
    public APIResponse addUser(User user, Long[] roles) {
        try {
            if (ON.equalsIgnoreCase(user.getUserStatus()))
                user.setUserStatus(GlobalConstant.STATUS_VALID);
            else
                user.setUserStatus(GlobalConstant.STATUS_LOCK);
            this.userService.addUser(user, roles);
            return APIResponse.OK("新增用户成功！", null);
        } catch (Exception e) {
            log.error("新增用户失败", e);
            return APIResponse.ERROR(GlobalConstant.HTTP_500, "新增用户失败，请联系网站管理员！", e);
        }
    }

    @Log("修改用户")
    @RequiresAuthentication
    @RequestMapping("update")
    @ResponseBody
    public APIResponse updateUser(User user, Long[] rolesSelect) {
        try {
            if (ON.equalsIgnoreCase(user.getUserStatus()))
                user.setUserStatus(GlobalConstant.STATUS_VALID);
            else
                user.setUserStatus(GlobalConstant.STATUS_LOCK);
            this.userService.updateUser(user, rolesSelect);
            return APIResponse.OK("修改用户成功！", null);
        } catch (Exception e) {
            log.error("修改用户失败", e);
            return APIResponse.ERROR(GlobalConstant.HTTP_500, "修改用户失败，请联系网站管理员！", e);
        }
    }

    @Log("删除用户")
    @RequiresAuthentication
    @RequestMapping("deleteAccounts")
    @ResponseBody
    public APIResponse deleteUsers(@RequestBody Map<String, String> userIds) {
        try {
            this.userService.deleteUsers(userIds.get("userIds"));
            return APIResponse.OK("删除用户成功！", null);
        } catch (Exception e) {
            log.error("删除用户失败", e);
            return APIResponse.ERROR(GlobalConstant.HTTP_500, "删除用户失败，请联系网站管理员！", e);
        }
    }

    @Log("校验密码")
    @RequestMapping("checkPassword")
    @ResponseBody
    public boolean checkPassword(String password) {
        User user = getCurrentUser();
        String encrypt = MD5Utils.encrypt(user.getUsername().toLowerCase(), password);
        return user.getPassword().equals(encrypt);
    }

    @Log("校验用户名")
    @RequestMapping("accountExist")
    @ResponseBody
    public APIResponse accountExist(@RequestBody Map<String, String> username) {
        String userName = username.get("username");
        User user = this.userService.findByName(userName);
        if(user == null) {
            return APIResponse.OK("User Name is OK.", null);
        }
        return APIResponse.ERROR(Integer.valueOf(GlobalConstant.REQ_PARAM_ERROR), "User Name Exit.", user);
    }

    @Log("修改密码")
    @RequestMapping("updatePassword")
    @ResponseBody
    public APIResponse updatePassword(String newPassword) {
        try {
            this.userService.updatePassword(newPassword);
            return APIResponse.OK("更改密码成功！", null);
        } catch (Exception e) {
            log.error("修改密码失败", e);
            return APIResponse.ERROR(GlobalConstant.HTTP_500, "更改密码失败，请联系网站管理员！", e);
        }
    }

}
