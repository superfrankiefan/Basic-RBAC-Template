package com.sff.rbacdemo.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sff.rbacdemo.common.model.BaseEntity;
import lombok.Data;

@Data
@TableName("t_dept")
public class Dept extends BaseEntity {

    private static final long serialVersionUID = -7790334862410409053L;

    @TableId(value = "DEPT_ID", type = IdType.ASSIGN_ID)
    private Long deptId;

    @TableField("PARENT_ID")
    private Long parentId;

    @TableField("DEPT_CODE")
    private String deptCode;

    @TableField("DEPT_NAME")
    private String deptName;

    @TableField("STATUS")
    private int status;

    @TableField("ORDER_NO")
    private int orderNo;

    @TableField("REMARK")
    private String remark;

}