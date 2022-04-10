package com.sff.rbacdemo.biz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.sff.rbacdemo.common.model.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * @author Frankie Fan
 * @date 2022-04-10 14:52
 */

@Data
@TableName("b_billing")
public class Billing extends BaseEntity {

    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "BILLING_ID", type = IdType.ASSIGN_ID)
    private Long billingId;

    @TableField("BILLING_CODE")
    private String billingCode;

    @TableField("BILLING_STATUS")
    private int billingStatus;

    @TableField("BILLING_DATE")
    private Date billingDate;

    @TableField("BILLING_DAYS")
    private int billingDays;

    @TableField("RECEIVE_DATE")
    private Date receiveDate;

    @TableField("EXT_PAY_DATE")
    private Date extPayDate;

    @TableField("EXT_PAY_STATUS")
    private int extPayStatus;

    @TableField("STD_FEE")
    private float stdFee;

    @TableField("WH_PRICE")
    private float whPrice;

    @TableField("EXT_FEE")
    private float extFee;

    @TableField("OTHER_FEE")
    private float otherFee;

    @TableField("CASE_CODE")
    private String caseCode;
}
