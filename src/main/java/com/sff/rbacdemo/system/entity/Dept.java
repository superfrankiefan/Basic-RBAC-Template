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

    @TableId(type = IdType.ASSIGN_ID)
    @TableField("DEPT_ID")
//	@ExportConfig(value = "编号")
    private Long deptId;

    @TableField("PARENT_ID")
    private Long parentId;

    @TableField("DEPT_NAME")
//	@ExportConfig(value = "部门名称")
    private String deptName;

    @TableField("ORDER_NUM")
    private Long orderNum;

}