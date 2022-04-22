package com.sff.rbacdemo.biz.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.sff.rbacdemo.biz.entity.Customer;
import com.sff.rbacdemo.biz.mapper.CustomerMapper;
import com.sff.rbacdemo.common.controller.BaseController;
import com.sff.rbacdemo.common.model.APIResponse;
import com.sff.rbacdemo.common.model.PageResponseDTO;
import com.sff.rbacdemo.common.properties.GlobalConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author Frankie Fan
 * @date 2022-04-10 20:45
 */

@Slf4j
@RestController
@RequestMapping("/biz/customer")
public class CustomerMgmt extends BaseController {

    @Autowired
    private CustomerMapper customerMapper;

    @PostMapping("addOrUpdateCustomer")
    @ResponseBody
    @RequiresAuthentication
    public APIResponse addOrUpdateCustomer(@RequestBody Customer customer){
        if(customer != null) {
            if(customer.getCustomerId() != null){
                Customer custIns = this.customerMapper.selectById(customer.getCustomerId());
                if(custIns != null) {
                    this.customerMapper.updateById(customer);
                    return APIResponse.OK("Update Customer", null);
                }
            }
            createCustomerInstance(customer);
            return APIResponse.OK("Add Customer", null);
        }
        return APIResponse.ERROR(GlobalConstant.REQ_PARAM_ERROR,"Customer Data Invalid",customer);
    }

    @GetMapping("getCustomerList")
    @ResponseBody
    @RequiresAuthentication
    public APIResponse getCustomerList(@RequestParam(required = false, value = "customerCode") String customerCode,
                                       @RequestParam(required = false, value = "customerName") String customerName,
                                       @RequestParam(required = false, value = "country") String country) {
        QueryWrapper queryWrapper = new QueryWrapper<>();
        if (customerCode != null) {
            queryWrapper.eq("CUSTOMER_CODE", customerCode);
        }
        if (customerName != null) {
            queryWrapper.like("CUSTOMER_NAME", customerName);
        }
        if (country != null) {
            queryWrapper.like("COUNTRY", country);
        }
        return APIResponse.OK("customers", this.customerMapper.selectList(queryWrapper));
    }

    @GetMapping("getCustomerListByPage")
    @ResponseBody
    @RequiresAuthentication
    public APIResponse<PageResponseDTO> getCustomerListByPage(@RequestParam(required = false, value = "customerCode") String customerCode,
                                                              @RequestParam(required = false, value = "customerName") String customerName,
                                                              @RequestParam(required = false, value = "country") String country,
                                                              @RequestParam(required = false, defaultValue = "0") Integer page,
                                                              @RequestParam(required = false, defaultValue = "10") Integer pageSize) {
        Page<Customer> pager = new Page<>(page, pageSize);
        QueryWrapper queryWrapper = new QueryWrapper();
        if (customerCode != null) {
            queryWrapper.eq("CUSTOMER_CODE", customerCode);
        }
        if (customerName != null) {
            queryWrapper.like("CUSTOMER_NAME", customerName);
        }
        if (country != null) {
            queryWrapper.like("COUNTRY", country);
        }
        IPage<Customer> paging = this.customerMapper.selectPage(pager, queryWrapper);
        PageResponseDTO pageResponseDTO = new PageResponseDTO();
        pageResponseDTO.setPage(paging.getCurrent());
        pageResponseDTO.setCount(paging.getSize());
        pageResponseDTO.setTotal(paging.getTotal());
        pageResponseDTO.setItems(paging.getRecords());
        return APIResponse.OK("Get Customers by Page", pageResponseDTO);
    }

    @DeleteMapping("deleteCustomers")
    @ResponseBody
    @RequiresAuthentication
    public APIResponse deleteCustomers(@RequestBody Map<String, String> customerIds) {
        this.customerMapper.deleteById(customerIds.get("customerIds"));
        return APIResponse.OK("Delete Customers", null);
    }

    synchronized void createCustomerInstance (Customer customer) {
        String countryCode = customer.getCountry();
        String seq = this.customerMapper.getSequence(countryCode);
        if(seq == null){
            countryCode += "00001";
            customer.setCustomerCode(countryCode);
        }else{
            int num =  Integer.valueOf(seq).intValue() + 1;
            countryCode += String.format("%05d", num);
            customer.setCustomerCode(countryCode);
        }
        this.customerMapper.insert(customer);
    }

}
