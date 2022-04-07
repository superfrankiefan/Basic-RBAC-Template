package com.sff.rbacdemo.system.controller;

import com.sff.rbacdemo.common.model.APIResponse;
import com.sff.rbacdemo.common.properties.GlobalConstant;
import com.sff.rbacdemo.system.entity.Dept;
import com.sff.rbacdemo.system.entity.Resource;
import com.sff.rbacdemo.system.service.ResourceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Frankie Fan
 * @date 2022-03-20 21:30
 */

@Slf4j
@RestController
@RequestMapping("/system/menu")
public class MenuMgmt {

    @Autowired
    private ResourceService resourceService;

    @GetMapping("getMenuList")
    @ResponseBody
    @RequiresAuthentication
    public APIResponse getMenuList(@RequestParam(required = false, value = "text") String menuName,
                                   @RequestParam(required = false, defaultValue = GlobalConstant.STATUS_VALID) int status) {
        return APIResponse.OK("menus", this.resourceService.getResourceTree(menuName, status));
    }

    @PostMapping("addOrUpdateMenu")
    @ResponseBody
    @RequiresAuthentication
    public APIResponse addOrUpdateMenu(@RequestBody Resource resource){
        if(resource != null) {
            if (resource.getResourceId() != null) {
                Resource resourceIns = resourceService.findById(resource.getResourceId());
                if (resourceIns != null) {
                    this.resourceService.updateResource(resource);
                    return APIResponse.OK("Update Resource", null);
                }
            }
            this.resourceService.addResource(resource);
            return APIResponse.OK("Add Resource", null);
        } else {
            return APIResponse.ERROR(GlobalConstant.REQ_PARAM_ERROR,"资源数据非法",resource);
        }
    }

    @DeleteMapping("deleteMenus")
    @ResponseBody
    @RequiresAuthentication
    public APIResponse deleteMenus(@RequestBody Map<String, String> menuIds) {
        this.resourceService.deleteMenus(menuIds.get("menuIds"));
        return APIResponse.OK("Delete Menus", null);
    }

}
