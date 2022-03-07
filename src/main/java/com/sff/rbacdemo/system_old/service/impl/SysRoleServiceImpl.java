package com.sff.rbacdemo.system_old.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sff.rbacdemo.system_old.entity.SysRole;
import com.sff.rbacdemo.system_old.mapper.SysRoleMapper;
import com.sff.rbacdemo.system_old.mapper.SysUserMapper;
import com.sff.rbacdemo.system_old.service.ISysRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 */
@Service
@Primary
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements ISysRoleService {
    @Autowired
    SysRoleMapper sysRoleMapper;
    @Autowired
    SysUserMapper sysUserMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteRole(String roleid) {
        //1.删除角色和用户关系
        sysRoleMapper.deleteRoleUserRelation(roleid);
        //2.删除角色和权限关系
        sysRoleMapper.deleteRolePermissionRelation(roleid);
        //3.删除角色
        this.removeById(roleid);
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean deleteBatchRole(String[] roleIds) {
        //1.删除角色和用户关系
        sysUserMapper.deleteBathRoleUserRelation(roleIds);
        //2.删除角色和权限关系
        sysUserMapper.deleteBathRolePermissionRelation(roleIds);
        //3.删除角色
        this.removeByIds(Arrays.asList(roleIds));
        return true;
    }
}
