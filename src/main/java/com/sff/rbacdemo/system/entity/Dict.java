package com.sff.rbacdemo.system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sff.rbacdemo.common.model.BaseEntity;
import lombok.Data;

@Data
@TableName("t_dict")
public class Dict extends BaseEntity {

    private static final long serialVersionUID = 7780820231535870010L;

    @TableId(value = "DICT_ID", type = IdType.ASSIGN_ID)
    private Long dictId;

    @TableField("KEY")
    private String key;

    @TableField("VALUE")
    private String value;

    @TableField("TABLE_NAME")
    private String tableName;

    @TableField("FIELD_NAME")
    private String fieldName;

}