package com.sff.rbacdemo.system.controller;

import com.sff.rbacdemo.common.controller.BaseController;
import com.sff.rbacdemo.common.model.APIResponse;
import com.sff.rbacdemo.common.model.PageResponseDTO;
import com.sff.rbacdemo.common.properties.GlobalConstant;
import com.sff.rbacdemo.system.dto.RoleAndMenus;
import com.sff.rbacdemo.system.entity.Role;
import com.sff.rbacdemo.system.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Frankie Fan
 * @date 2022-03-21 11:59
 */

@Slf4j
@RestController
@RequestMapping("/system/role")
public class RoleMgmt extends BaseController {

    @Autowired
    private RoleService roleService;

    @GetMapping("getRoleListByPage")
    @ResponseBody
    @RequiresAuthentication
    public APIResponse<PageResponseDTO> getRoleListByPage(@RequestParam(required = false) String roleName,
                                                          @RequestParam(required = false, defaultValue = GlobalConstant.STATUS_VALID) String status,
                                                          @RequestParam(required = false, defaultValue = "0") Integer page,
                                                          @RequestParam(required = false, defaultValue = "10") Integer pageSize) {

        PageResponseDTO pageRoles = roleService.getRoleByPage(roleName, status, page, pageSize);
        return APIResponse.OK("Get Roles by Page", pageRoles);
    }

    @GetMapping("getAllRoleList")
    @ResponseBody
    @RequiresAuthentication
    public APIResponse getAllRoleList(@RequestParam(required = false, defaultValue = "1") int status) {
        List<Role> roles = roleService.getAllRole(status);
        return APIResponse.OK("Get Roles", roles);
    }

    @PostMapping("setRoleStatus")
    @ResponseBody
    @RequiresAuthentication
    public APIResponse setRoleStatus(@RequestBody Role role) {
        roleService.setRoleStatus(role.getRoleCode(), role.getStatus());
        return APIResponse.OK("Set Role Code", null);
    }

    @PostMapping("addOrUpdateRole")
    @ResponseBody
    @RequiresAuthentication
    public APIResponse addOrUpdateRole(@RequestBody RoleAndMenus roleAndMenus) {
        if(roleAndMenus != null & roleAndMenus.getRoleCode() != null) {
            Role role = roleService.findByRoleCode(roleAndMenus.getRoleCode());
            if(role == null) {
                this.roleService.addRole(roleAndMenus);
                return APIResponse.OK("Add Role", null);
            }else{
                this.roleService.updateRole(roleAndMenus);
                return APIResponse.OK("Update Role", null);
            }
        } else {
          return APIResponse.ERROR(GlobalConstant.REQ_PARAM_ERROR,"角色数据非法",roleAndMenus);
        }
    }

    @DeleteMapping("deleteRoles")
    @ResponseBody
    @RequiresAuthentication
    public APIResponse deleteRole(@RequestBody Map<String, String> roleIds) {
        this.roleService.deleteRoles(roleIds.get("roleIds"));
        return APIResponse.OK("Delete Roles", null);
    }

    @GetMapping("getRoleMenus")
    @ResponseBody
    @RequiresAuthentication
    public APIResponse getRoleMenus(@RequestParam(required = true) String roleId) {
        return APIResponse.OK("Get Role Menus", this.roleService.getRoleMenus(roleId));
    }
}
