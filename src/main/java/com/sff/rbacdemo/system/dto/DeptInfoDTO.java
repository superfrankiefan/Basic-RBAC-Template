package com.sff.rbacdemo.system.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Frankie Fan
 * @date 2022-03-14 19:55
 * 部门信息
 */

@Data
public class DeptInfoDTO implements Serializable {

    private static final long serialVersionUID = -7790334862410409053L;

    private String id;

    private String parentId;

    private String deptName;

    private String remark;

    private String createTime;

    private String status;

    private int orderNo;

    private List<DeptInfoDTO> children;

}
