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

    @TableId(type = IdType.ASSIGN_ID)
    @TableField("DICT_ID")
//	@ExportConfig(value = "字典ID")
    private Long dictId;

    @TableField("KEY")
//	@ExportConfig(value = "字典Key")
    private String key;

    @TableField("VALUE")
//	@ExportConfig(value = "字典Value")
    private String value;

    @TableField("TABLE_NAME")
//	@ExportConfig(value = "表名")
    private String tableName;

    @TableField("FIELD_NAME")
//	@ExportConfig(value = "列名")
    private String fieldName;

}