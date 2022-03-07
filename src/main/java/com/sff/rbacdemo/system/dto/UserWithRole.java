package com.sff.rbacdemo.system.dto;

import com.sff.rbacdemo.system.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class UserWithRole extends User {
	
	private static final long serialVersionUID = -5680235862276163462L;
	
	private Long roleId;
	
	private List<Long> roleIds;
	
}