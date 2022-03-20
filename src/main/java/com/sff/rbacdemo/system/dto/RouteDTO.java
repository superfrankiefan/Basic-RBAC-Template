package com.sff.rbacdemo.system.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Frankie Fan
 * @date 2022-03-14 10:32
 */

@Data
public class RouteDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private String name;

    private String path;

    private String component;

    private String alias;

    private String redirect;

    private boolean caseSensitive;

    private Meta meta;

    private List<RouteDTO> children;

}
