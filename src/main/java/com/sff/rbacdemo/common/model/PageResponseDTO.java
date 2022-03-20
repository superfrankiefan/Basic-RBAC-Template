package com.sff.rbacdemo.common.model;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Frankie Fan
 * @date 2022-03-14 16:25
 * 统一的分页查询结果返回格式
 */

@Data
public class PageResponseDTO<T> implements Serializable {

    private static final long serialVersionUID = 1L;

    private long total;

    private List<T> items;

    private long page;

    private long count;

}
