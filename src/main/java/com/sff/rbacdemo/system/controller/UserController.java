package com.sff.rbacdemo.system.controller;

import com.sff.rbacdemo.common.controller.BaseController;
import com.sff.rbacdemo.common.model.Result;
import com.sff.rbacdemo.system.annotation.Log;
import com.sff.rbacdemo.system.entity.User;
import com.sff.rbacdemo.system.service.UserService;
import com.sff.rbacdemo.system.utils.MD5Utils;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class UserController extends BaseController {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserService userService;

    private static final String ON = "on";

    @RequestMapping("user")
    @RequiresPermissions("user:list")
    public String index(Model model) {
        User user = super.getCurrentUser();
        model.addAttribute("user", user);
        return "system/user/user";
    }

    @RequestMapping("user/checkUserName")
    @ResponseBody
    public boolean checkUserName(String username, String oldusername) {
        if (StringUtils.isNotBlank(oldusername) && username.equalsIgnoreCase(oldusername)) {
            return true;
        }
        User result = this.userService.findByName(username);
        return result == null;
    }

    @RequestMapping("user/getUser")
    @ResponseBody
    public Result getUser(Long userId) {
        try {
            User user = this.userService.findById(userId);
            return Result.OK(user);
        } catch (Exception e) {
            log.error("获取用户失败", e);
            return Result.error("获取用户失败，请联系网站管理员！");
        }
    }

    @RequestMapping("user/regist")
    @ResponseBody
    public Result regist(User user) {
        try {
            User result = this.userService.findByName(user.getUsername());
            if (result != null) {
                return Result.error("该用户名已被使用！");
            }
            this.userService.registUser(user);
            return Result.OK();
        } catch (Exception e) {
            log.error("注册失败", e);
            return Result.error("注册失败，请联系网站管理员！");
        }
    }

    @Log("新增用户")
    @RequiresPermissions("user:add")
    @RequestMapping("user/add")
    @ResponseBody
    public Result addUser(User user, Long[] roles) {
        try {
            if (ON.equalsIgnoreCase(user.getStatus()))
                user.setStatus(User.STATUS_VALID);
            else
                user.setStatus(User.STATUS_LOCK);
            this.userService.addUser(user, roles);
            return Result.OK("新增用户成功！");
        } catch (Exception e) {
            log.error("新增用户失败", e);
            return Result.error("新增用户失败，请联系网站管理员！");
        }
    }

    @Log("修改用户")
    @RequiresPermissions("user:update")
    @RequestMapping("user/update")
    @ResponseBody
    public Result updateUser(User user, Long[] rolesSelect) {
        try {
            if (ON.equalsIgnoreCase(user.getStatus()))
                user.setStatus(User.STATUS_VALID);
            else
                user.setStatus(User.STATUS_LOCK);
            this.userService.updateUser(user, rolesSelect);
            return Result.OK("修改用户成功！");
        } catch (Exception e) {
            log.error("修改用户失败", e);
            return Result.error("修改用户失败，请联系网站管理员！");
        }
    }

    @Log("删除用户")
    @RequiresPermissions("user:delete")
    @RequestMapping("user/delete")
    @ResponseBody
    public Result deleteUsers(String ids) {
        try {
            this.userService.deleteUsers(ids);
            return Result.OK("删除用户成功！");
        } catch (Exception e) {
            log.error("删除用户失败", e);
            return Result.error("删除用户失败，请联系网站管理员！");
        }
    }

    @RequestMapping("user/checkPassword")
    @ResponseBody
    public boolean checkPassword(String password) {
        User user = getCurrentUser();
        String encrypt = MD5Utils.encrypt(user.getUsername().toLowerCase(), password);
        return user.getPassword().equals(encrypt);
    }

    @RequestMapping("user/updatePassword")
    @ResponseBody
    public Result updatePassword(String newPassword) {
        try {
            this.userService.updatePassword(newPassword);
            return Result.OK("更改密码成功！");
        } catch (Exception e) {
            log.error("修改密码失败", e);
            return Result.error("更改密码失败，请联系网站管理员！");
        }
    }
}
