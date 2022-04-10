package com.sff.rbacdemo.system.controller;

import com.sff.rbacdemo.common.controller.BaseController;
import com.sff.rbacdemo.common.model.APIResponse;
import com.sff.rbacdemo.common.properties.GlobalConstant;
import com.sff.rbacdemo.system.entity.Dept;
import com.sff.rbacdemo.system.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Frankie Fan
 * @date 2022-03-20 10:16
 * department management
 */

@Slf4j
@RestController
@RequestMapping("/system/dept")
public class DeptMgmt extends BaseController {

    @Autowired
    private DeptService deptService;

    @PostMapping("addOrUpdateDept")
    @ResponseBody
    @RequiresAuthentication
    public APIResponse addOrUpdateDept(@RequestBody Dept dept){
        if(dept != null & dept.getDeptCode() != null) {
            Dept department = deptService.findByDeptCode(dept.getDeptCode());
            if(department == null) {
                this.deptService.addDept(dept);
                return APIResponse.OK("Add Dept", null);
            }else{
                this.deptService.updateDept(dept);
                return APIResponse.OK("Update Dept", null);
            }
        } else {
            return APIResponse.ERROR(GlobalConstant.REQ_PARAM_ERROR,"部门数据非法",dept);
        }
    }

    @GetMapping("getDeptList")
    @ResponseBody
    @RequiresAuthentication
    public APIResponse getDeptList(@RequestParam(required = false, value = "code") String deptCode,
                                   @RequestParam(required = false, value = "text") String deptName,
                                   @RequestParam(required = false, defaultValue = GlobalConstant.STATUS_VALID) int status) {
        return APIResponse.OK("depts", this.deptService.getDeptTree(deptCode, deptName, status));
    }

    @DeleteMapping("deleteDepts")
    @ResponseBody
    @RequiresAuthentication
    public APIResponse deleteDept(@RequestBody Map<String, String> deptIds) {
        this.deptService.deleteDepts(deptIds.get("deptIds"));
        return APIResponse.OK("Delete Depts", null);
    }

}
