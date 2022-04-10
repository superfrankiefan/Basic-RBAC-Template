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
 * @date 2022-04-10 14:50
 */
@Data
@TableName("b_task")
public class Task extends BaseEntity {

    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(value = "TASK_ID", type = IdType.ASSIGN_ID)
    private Long taskId;

    @TableField("TYPE")
    private String type;

    @TableField("WORKING_HOUR")
    private int workingHour;

    @TableField("DESCRIPTION")
    private String description;

    @TableField("CASE_CODE")
    private String caseCode;
}
