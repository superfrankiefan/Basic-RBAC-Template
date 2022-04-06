package com.sff.rbacdemo.system.dto;

import com.sff.rbacdemo.system.entity.Role;
import lombok.Data;

/**
 * @author Frankie Fan
 * @date 2022-04-04 18:46
 */

@Data
public class RoleAndMenus extends Role {

    private static final long serialVersionUID = 2013847071068967187L;

    private String menuIds;
}
