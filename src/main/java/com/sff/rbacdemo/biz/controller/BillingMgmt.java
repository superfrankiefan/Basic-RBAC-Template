package com.sff.rbacdemo.biz.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sff.rbacdemo.biz.entity.Billing;
import com.sff.rbacdemo.biz.mapper.BillingMapper;
import com.sff.rbacdemo.biz.mapper.CaseMapper;
import com.sff.rbacdemo.biz.mapper.TaskMapper;
import com.sff.rbacdemo.common.controller.BaseController;
import com.sff.rbacdemo.common.model.APIResponse;
import com.sff.rbacdemo.common.model.PageResponseDTO;
import com.sff.rbacdemo.common.properties.GlobalConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
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

    @Autowired
    private CaseMapper caseMapper;

    @Autowired
    private TaskMapper taskMapper;

    @PostMapping("addOrUpdateBilling")
    @ResponseBody
    @RequiresAuthentication
    public APIResponse addOrUpdateBilling(@RequestBody Billing billing){
        if(billing != null) {
            if(billing.getBillingId() != null){
                Billing billingIns= this.billingMapper.selectById(billing.getBillingId());
                if(billingIns != null) {
                    this.billingMapper.updateById(billing);
                    return APIResponse.OK("Update Billing", null);
                }
            }
            createBillingInstance(billing);
            return APIResponse.OK("Add Billing", null);
        }
        return APIResponse.ERROR(GlobalConstant.REQ_PARAM_ERROR,"Billing Data Invalid",billing);
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

    @GetMapping("getBillingListByPage")
    @ResponseBody
    @RequiresAuthentication
    public APIResponse<PageResponseDTO> getBillingListByPage(@RequestParam(required = false, value = "billingStatus") String billingStatus,
                                                             @RequestParam(required = false, value = "receiveStatus") String receiveStatus,
                                                             @RequestParam(required = false, value = "extPayStatus") String extPayStatus,
                                                             @RequestParam(required = false, value = "billingCode") String billingCode,
                                                             @RequestParam(required = false, value = "caseCode") String caseCode,
                                                             @RequestParam(required = false, value = "customerCode") String customerCode,
                                                             @RequestParam(required = false, value = "billingDate[]") String[] billingDate,
                                                             @RequestParam(required = false, value = "receiveDate[]") String[] receiveDate,
                                                             @RequestParam(required = false, defaultValue = "0") Integer page,
                                                             @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        Page<Billing> pager = new Page<>(page, pageSize);
        QueryWrapper queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("BILLING_DATE");
        if (billingStatus != null) {
            queryWrapper.eq("BILLING_STATUS", billingStatus);
        }
        if (receiveStatus != null) {
            queryWrapper.eq("RECEIVE_STATUS", receiveStatus);
        }
        if (extPayStatus != null) {
            queryWrapper.eq("EXT_PAY_STATUS", extPayStatus);
        }
        if (billingCode != null) {
            queryWrapper.like("BILLING_CODE", billingCode);
        }
        if (caseCode != null) {
            queryWrapper.eq("CASE_CODE", caseCode);
        }
        if (customerCode != null) {
            queryWrapper.eq("CUSTOMER_CODE", customerCode);
        }
        if(billingDate != null){
            queryWrapper.between("BILLING_DATE", billingDate[0], billingDate[1]);
        }
        if(receiveDate != null){
            queryWrapper.between("RECEIVE_DATE", receiveDate[0], receiveDate[1]);
        }
        IPage<Billing> paging = this.billingMapper.selectPage(pager, queryWrapper);
        PageResponseDTO pageResponseDTO = new PageResponseDTO();
        pageResponseDTO.setPage(paging.getCurrent());
        pageResponseDTO.setCount(paging.getSize());
        pageResponseDTO.setTotal(paging.getTotal());
        pageResponseDTO.setItems(paging.getRecords());
        return APIResponse.OK("Get Billings by Page", pageResponseDTO);
    }




    @DeleteMapping("deleteBillings")
    @ResponseBody
    @RequiresAuthentication
    public APIResponse deleteBillings(@RequestBody Map<String, String> billingIds) {
        this.billingMapper.deleteById(billingIds.get("billingIds"));
        return APIResponse.OK("Delete Billings", null);
    }

    synchronized void createBillingInstance(Billing billing) {
        billing.setBillingDate(new Date(new java.util.Date().getTime()));
        String caseCode = billing.getCaseCode();
        float totalHours = this.taskMapper.getCaseTotalHours(caseCode);
        billing.setTotalWorkHours(totalHours);
        String seq = this.billingMapper.getSequence(caseCode);
        if(billing.getCustomerCode() == null){
            billing.setCustomerCode(caseCode.substring(0,8));
        }
        caseCode += "B";
        if(seq == null){
            caseCode += "01";
            billing.setBillingCode(caseCode);
        }else{
            int num =  Integer.valueOf(seq).intValue() + 1;
            caseCode += String.format("%02d", num);
            billing.setBillingCode(caseCode);
        }
        this.billingMapper.insert(billing);
    }
}
