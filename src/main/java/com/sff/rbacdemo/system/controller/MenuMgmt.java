package com.sff.rbacdemo.system.controller;

import com.sff.rbacdemo.common.model.APIResponse;
import com.sff.rbacdemo.common.properties.GlobalConstant;
import com.sff.rbacdemo.system.service.ResourceService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
}
