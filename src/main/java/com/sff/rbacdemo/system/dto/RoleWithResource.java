package com.sff.rbacdemo.system.dto;

import com.sff.rbacdemo.system.entity.Role;
import lombok.Data;

import java.util.List;

@Data
public class RoleWithResource extends Role {

	private static final long serialVersionUID = 2013847071068967187L;
	
	private Long resourceId;
	
	private List<Long> resourceIds;

}
