package com.sff.rbacdemo.system.controller;

import com.sff.rbacdemo.common.controller.BaseController;
import com.sff.rbacdemo.common.model.APIResponse;
import com.sff.rbacdemo.common.properties.GlobalConstant;
import com.sff.rbacdemo.common.utils.MD5Utils;
import com.sff.rbacdemo.common.annotation.Log;
import com.sff.rbacdemo.system.entity.User;
import com.sff.rbacdemo.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/user")
public class UserController extends BaseController {

    @Autowired
    private UserService userService;

    private static final String ON = "on";

    @PostMapping("regist")
    public APIResponse<User> regist(@RequestBody User user) {
        APIResponse<User> result = new APIResponse<>();
        try {
            User userIns = this.userService.findByName(user.getUsername());
            if (userIns != null) {
                return result.internalError("该用户名已被使用！");
            }
            this.userService.registUser(user);
            return result.success("注册成功！");
        } catch (Exception e) {
            log.error("注册失败", e);
            return result.internalError("注册失败，请联系网站管理员！");
        }
    }

    @Log("新增用户")
    @RequiresPermissions("user:add")
    @RequestMapping("add")
    @ResponseBody
    public APIResponse addUser(User user, Long[] roles) {
        try {
            if (ON.equalsIgnoreCase(user.getStatus()))
                user.setStatus(User.STATUS_VALID);
            else
                user.setStatus(User.STATUS_LOCK);
            this.userService.addUser(user, roles);
            return APIResponse.OK("新增用户成功！", null);
        } catch (Exception e) {
            log.error("新增用户失败", e);
            return APIResponse.ERROR(GlobalConstant.HTTP_500, "新增用户失败，请联系网站管理员！", e);
        }
    }

    @Log("修改用户")
    @RequiresPermissions("user:update")
    @RequestMapping("update")
    @ResponseBody
    public APIResponse updateUser(User user, Long[] rolesSelect) {
        try {
            if (ON.equalsIgnoreCase(user.getStatus()))
                user.setStatus(User.STATUS_VALID);
            else
                user.setStatus(User.STATUS_LOCK);
            this.userService.updateUser(user, rolesSelect);
            return APIResponse.OK("修改用户成功！", null);
        } catch (Exception e) {
            log.error("修改用户失败", e);
            return APIResponse.ERROR(GlobalConstant.HTTP_500, "修改用户失败，请联系网站管理员！", e);
        }
    }

    @Log("删除用户")
    @RequiresPermissions("user:delete")
    @RequestMapping("delete")
    @ResponseBody
    public APIResponse deleteUsers(String ids) {
        try {
            this.userService.deleteUsers(ids);
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
