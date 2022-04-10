package com.sff.rbacdemo.biz.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.sff.rbacdemo.common.model.BaseEntity;
import lombok.Data;

/**
 * @author Frankie Fan
 * @date 2022-04-10 14:25
 */

@Data
@TableName("b_customer")
public class Customer extends BaseEntity {

    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "CUSTOMER_ID", type = IdType.ASSIGN_ID)
    private Long customerId;

    @TableField("CUSTOMER_CODE")
    private String customerCode;

    @TableField("CUSTOMER_NAME")
    private String customerName;

    @TableField("ABBR")
    private String abbr;

    @TableField("COUNTRY")
    private String country;

}
