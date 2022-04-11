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
 * @date 2022-04-10 14:47
 */

@Data
@TableName("b_case")
public class Case extends BaseEntity {

    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "CASE_ID", type = IdType.ASSIGN_ID)
    private Long caseId;

    @TableField("CASE_CODE")
    private String caseCode;

    @TableField("EXTERNAL_CODE")
    private String externalCode;

    @TableField("DESCRIPTION")
    private String description;

    @TableField("CUSTOMER_CODE")
    private String customerCode;
}
