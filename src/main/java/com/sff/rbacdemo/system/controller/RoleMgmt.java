package com.sff.rbacdemo.system.controller;

import com.sff.rbacdemo.common.model.APIResponse;
import com.sff.rbacdemo.common.model.PageResponseDTO;
import com.sff.rbacdemo.common.properties.GlobalConstant;
import com.sff.rbacdemo.system.entity.Role;
import com.sff.rbacdemo.system.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Frankie Fan
 * @date 2022-03-21 11:59
 */

@Slf4j
@RestController
@RequestMapping("/system/role")
public class RoleMgmt {

    @Autowired
    private RoleService roleService;

    @GetMapping("getRoleListByPage")
    @ResponseBody
    @RequiresAuthentication
    public APIResponse<PageResponseDTO> getRoleListByPage(@RequestParam(required = false) String roleName,
                                                          @RequestParam(required = false, defaultValue = GlobalConstant.STATUS_VALID) int status,
                                                          @RequestParam(required = false, defaultValue = "0") Integer page,
                                                          @RequestParam(required = false, defaultValue = "10") Integer pageSize) {

        PageResponseDTO pageRoles = roleService.getRoleByPage(status, roleName, page, pageSize);
        return APIResponse.OK("roles", pageRoles);
    }

    @PostMapping("setRoleStatus")
    @ResponseBody
    @RequiresAuthentication
    public APIResponse setRoleStatus(@RequestBody Role role) {
        return null;
    }
}
