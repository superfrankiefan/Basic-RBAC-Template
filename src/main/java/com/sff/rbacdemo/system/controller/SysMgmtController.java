package com.sff.rbacdemo.system.controller;

import com.sff.rbacdemo.common.model.APIResponse;
import com.sff.rbacdemo.common.model.PageResponseDTO;
import com.sff.rbacdemo.system.entity.Role;
import com.sff.rbacdemo.system.entity.User;
import com.sff.rbacdemo.system.service.RoleService;
import com.sff.rbacdemo.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Frankie Fan
 * @date 2022-03-14 16:15
 * 系统管理组件
 */

@Slf4j
@RestController
@RequestMapping("/system")
public class SysMgmtController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @GetMapping("getAccountList")
    @ResponseBody
    @RequiresAuthentication
    public APIResponse getAccountListByDept(@RequestParam(required = false, defaultValue = "%") String deptId,
                                            @RequestParam(required = false, defaultValue = "0") Integer page,
                                            @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        PageResponseDTO<User> accounts = this.userService.getUserListByDept(deptId,page,pageSize);
        return APIResponse.OK("accounts", accounts);
    }

    @GetMapping("getRoleListByPage")
    @ResponseBody
    @RequiresAuthentication
    public APIResponse<PageResponseDTO> getRoleListByPage(@RequestParam(required = false, defaultValue = "0") Integer page,
                                                          @RequestParam(required = false, defaultValue = "10") Integer pageSize) {

        PageResponseDTO pageRoles = roleService.getRoleByPage(page, pageSize);
        return APIResponse.OK("roles", pageRoles);
    }

    @PostMapping("setRoleStatus")
    @ResponseBody
    @RequiresAuthentication
    public APIResponse setRoleStatus(@RequestBody Role role) {
        return null;
    }

}
