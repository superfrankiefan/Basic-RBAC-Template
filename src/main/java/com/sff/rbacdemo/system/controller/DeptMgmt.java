package com.sff.rbacdemo.system.controller;

import com.sff.rbacdemo.common.model.APIResponse;
import com.sff.rbacdemo.common.properties.GlobalConstant;
import com.sff.rbacdemo.system.entity.Dept;
import com.sff.rbacdemo.system.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author Frankie Fan
 * @date 2022-03-20 10:16
 * department management
 */

@Slf4j
@RestController
@RequestMapping("/system/dept")
public class DeptMgmt {

    @Autowired
    private DeptService deptService;

    @PostMapping("addDept")
    @ResponseBody
    @RequiresAuthentication
    public APIResponse addDept(@RequestBody Dept dept){
        this.deptService.addDept(dept);
        return APIResponse.OK("OK", null);
    }

    @GetMapping("getDeptList")
    @ResponseBody
    @RequiresAuthentication
    public APIResponse getDeptList(@RequestParam(required = false, value = "code") String deptCode,
                                   @RequestParam(required = false, value = "text") String deptName,
                                   @RequestParam(required = false, defaultValue = GlobalConstant.STATUS_VALID) int status) {
        return APIResponse.OK("depts", this.deptService.getDeptTree(deptCode, deptName, status));
    }

}
