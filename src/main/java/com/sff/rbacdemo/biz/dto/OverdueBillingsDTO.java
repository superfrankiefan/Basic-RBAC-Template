package com.sff.rbacdemo.biz.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import lombok.Data;

import java.io.Serializable;

/**
 * @author Frankie Fan
 * @date 2022-04-28 15:29
 */

@Data
public class OverdueBillingsDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @Excel(name = "Billing Code")
    private String billingCode;

    @Excel(name = "Standard Fees")
    private float stdFee;

    @Excel(name = "Service Price")
    private float whPrice;

    @Excel(name = "Service Hours")
    private float totalWorkHours;

    @Excel(name = "Service Fees")
    private float serviceFee;

    @Excel(name = "External Fees")
    private float extFee;

    @Excel(name = "Other Fees")
    private float otherFee;

    @Excel(name = "Total Cost")
    private float totalCost;

}
