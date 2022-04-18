package com.sff.rbacdemo.system.entity;

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
 * @date 2022-04-14 10:16
 * 字典配置表
 */

@Data
@TableName("t_dict_detail")
public class DictDetail extends BaseEntity {

    private static final long serialVersionUID = 7780820231535870010L;

    @TableId(value = "DETAIL_ID", type = IdType.ASSIGN_ID)
    @JsonSerialize(using = ToStringSerializer.class)
    private Long detailId;

    @TableField("DICT_CODE")
    private String dictCode;

    @TableField("DETAIL_KEY")
    private String detailKey;

    @TableField("DETAIL_VALUE")
    private String detailValue;

}
