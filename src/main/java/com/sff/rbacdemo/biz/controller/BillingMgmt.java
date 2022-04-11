package com.sff.rbacdemo.biz.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sff.rbacdemo.biz.entity.Billing;
import com.sff.rbacdemo.biz.mapper.BillingMapper;
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
 * @date 2022-04-11 16:44
 */

@Slf4j
@RestController
@RequestMapping("/biz/billing")
public class BillingMgmt extends BaseController {

    @Autowired
    private BillingMapper billingMapper;

    @PostMapping("addOrUpdateBilling")
    @ResponseBody
    @RequiresAuthentication
    public APIResponse addOrUpdateBilling(@RequestBody Billing billing){
        if(billing != null & billing.getBillingCode() != null) {
            QueryWrapper queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("BILLING_CODE", billing.getBillingCode());
            List billings = this.billingMapper.selectList(queryWrapper);
            if(billings.isEmpty()) {
                this.billingMapper.insert(billing);
                return APIResponse.OK("Add Billing", null);
            }else{
                this.billingMapper.updateById(billing);
                return APIResponse.OK("Update Billing", null);
            }
        } else {
            return APIResponse.ERROR(GlobalConstant.REQ_PARAM_ERROR,"账单数据非法",billing);
        }
    }

    @GetMapping("getBillingList")
    @ResponseBody
    @RequiresAuthentication
    public APIResponse getBillingList(@RequestParam(required = false, value = "caseCode") String caseCode,
                                      @RequestParam(required = false, value = "customerCode") String customerCode) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        if (caseCode != null) {
            queryWrapper.like("CASE_CODE", caseCode);
        }
        if (customerCode != null) {
            queryWrapper.like("CUSTOMER_CODE", customerCode);
        }
        return APIResponse.OK("customers", this.billingMapper.selectList(queryWrapper));
    }

    @DeleteMapping("deleteBillings")
    @ResponseBody
    @RequiresAuthentication
    public APIResponse deleteBillings(@RequestBody Map<String, String> billingIds) {
        this.billingMapper.deleteById(billingIds.get("billingIds"));
        return APIResponse.OK("Delete Billings", null);
    }

}
