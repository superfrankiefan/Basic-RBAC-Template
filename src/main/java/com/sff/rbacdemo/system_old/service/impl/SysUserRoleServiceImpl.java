package com.sff.rbacdemo.system_old.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sff.rbacdemo.system_old.entity.SysUserRole;
import com.sff.rbacdemo.system_old.mapper.SysUserRoleMapper;
import com.sff.rbacdemo.system_old.service.ISysUserRoleService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户角色表 服务实现类
 * </p>
 */
@Service
@Primary
public class SysUserRoleServiceImpl
        extends ServiceImpl<SysUserRoleMapper, SysUserRole>
        implements ISysUserRoleService {

}
