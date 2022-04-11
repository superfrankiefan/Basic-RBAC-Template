package com.sff.rbacdemo.biz.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sff.rbacdemo.biz.entity.Case;
import com.sff.rbacdemo.biz.mapper.CaseMapper;
import com.sff.rbacdemo.common.controller.BaseController;
import com.sff.rbacdemo.common.model.APIResponse;
import com.sff.rbacdemo.common.properties.GlobalConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Frankie Fan
 * @date 2022-04-10 20:56
 */

@Slf4j
@RestController
@RequestMapping("/biz/case")
public class CaseMgmt extends BaseController {

    @Autowired
    private CaseMapper caseMapper;

    @PostMapping("addOrUpdateCase")
    @ResponseBody
    @RequiresAuthentication
    public APIResponse addOrUpdateCase(@RequestBody Case caseForm){
        if(caseForm != null & caseForm.getCaseCode() != null) {
            QueryWrapper queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("CASE_CODE", caseForm.getCaseCode());
            List custIns = this.caseMapper.selectList(queryWrapper);
            if(custIns.isEmpty()) {
                this.caseMapper.insert(caseForm);
                return APIResponse.OK("Add Case", null);
            }else{
                this.caseMapper.updateById(caseForm);
                return APIResponse.OK("Update Case", null);
            }
        } else {
            return APIResponse.ERROR(GlobalConstant.REQ_PARAM_ERROR,"案件数据非法",caseForm);
        }
    }

    @GetMapping("getCaseList")
    @ResponseBody
    @RequiresAuthentication
    public APIResponse getCaseList(@RequestParam(required = false, value = "caseCode") String caseCode,
                                   @RequestParam(required = false, value = "externalCode") String externalCode) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        if (caseCode != null) {
            queryWrapper.like("CASE_CODE", caseCode);
        }
        if (externalCode != null) {
            queryWrapper.like("EXTERNAL_CODE", externalCode);
        }
        return APIResponse.OK("customers", this.caseMapper.selectList(queryWrapper));
    }

    @DeleteMapping("deleteCases")
    @ResponseBody
    @RequiresAuthentication
    public APIResponse deleteCustomers(@RequestBody Map<String, String> caseIds) {
        this.caseMapper.deleteById(caseIds.get("caseIds"));
        return APIResponse.OK("Delete Cases", null);
    }
}
