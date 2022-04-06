package com.sff.rbacdemo.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sff.rbacdemo.common.model.PageResponseDTO;
import com.sff.rbacdemo.system.dto.RoleAndMenus;
import com.sff.rbacdemo.system.dto.RoleWithResource;
import com.sff.rbacdemo.system.entity.Role;
import com.sff.rbacdemo.system.entity.RoleResource;
import com.sff.rbacdemo.system.mapper.RoleMapper;
import com.sff.rbacdemo.system.mapper.RoleResourceMapper;
import com.sff.rbacdemo.system.service.RoleResourceServie;
import com.sff.rbacdemo.system.service.RoleService;
import com.sff.rbacdemo.system.service.UserRoleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j(topic = "RoleServiceImpl")
@Service("roleService")
@Transactional
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private RoleResourceMapper roleResourceMapper;

    @Autowired
    private UserRoleService userRoleService;

    @Autowired
    private RoleResourceServie roleResourceService;

    @Override
    public List<Role> findUserRole(String userName) {
        return this.roleMapper.findUserRole(userName);
    }

    @Override
    public PageResponseDTO<Role> getRoleByPage(String roleName, Integer page, Integer count) {
        Page<Role> pager = new Page<>(page, count);
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.orderByAsc("ROLE_CODE");
        if(roleName != null && !roleName.isEmpty()){
            queryWrapper.like("ROLE_NAME", roleName);
        }
        IPage<Role> paging = this.roleMapper.selectPage(pager, queryWrapper);
        PageResponseDTO pageResponseDTO = new PageResponseDTO();
        pageResponseDTO.setPage(paging.getCurrent());
        pageResponseDTO.setCount(paging.getSize());
        pageResponseDTO.setTotal(paging.getTotal());
        pageResponseDTO.setItems(paging.getRecords());
        return pageResponseDTO;
    }

    @Override
    public List<Role> getAllRole(int status) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("STATUS", status);
        queryWrapper.orderByAsc("ROLE_CODE");
        List<Role> roles = this.roleMapper.selectList(queryWrapper);
        return roles;
    }

    @Override
    public void setRoleStatus(String roleCode, String status) {
        QueryWrapper queryWrapper = new QueryWrapper();
        Role role = this.roleMapper.selectOne(queryWrapper);
        if (role != null) {
            role.setStatus(status);
            this.roleMapper.updateById(role);
        }
    }

    @Override
    public Role findByRoleCode(String roleCode) {
        QueryWrapper<Role> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("ROLE_CODE", roleCode);
        Role role = this.roleMapper.selectOne(queryWrapper);
        return role;
    }

    @Override
    @Transactional
    public void addRole(RoleAndMenus roleAndMenus) {
        Role role = (Role) roleAndMenus;
        this.roleMapper.insert(role);
        String[] menus = roleAndMenus.getMenuIds().split(",");
        setRoleResources(role, menus);
    }

    @Override
    @Transactional
    public void updateRole(RoleAndMenus roleAndMenus) {
        Role role = (Role) roleAndMenus;
        this.roleMapper.updateById(role);
        this.roleResourceMapper.deleteByRoleId(role.getRoleId());
        setRoleResources(role, roleAndMenus.getMenuIds().split(","));
    }

    private void setRoleResources(Role role, String[] resourceIds) {
        Arrays.stream(resourceIds).forEach(resourceId -> {
            RoleResource rm = new RoleResource();
            rm.setResourceId(Long.valueOf(resourceId));
            rm.setRoleId(role.getRoleId());
            this.roleResourceMapper.insert(rm);
        });
    }

    @Override
    @Transactional
    public void deleteRoles(String roleIds) {
        List<String> list = Arrays.asList(roleIds.split(","));
        list.stream().forEach(s -> this.roleMapper.deleteById(Long.valueOf(s)));
        this.roleResourceService.deleteRoleResourcesByRoleIds(roleIds);
        this.userRoleService.deleteUserRolesByRoleId(roleIds);
    }

    @Override
    public RoleWithResource findRoleWithResources(Long roleId) {
        List<RoleWithResource> list = this.roleMapper.findById(roleId);
        List<Long> resourceList = list.stream().map(RoleWithResource::getResourceId).collect(Collectors.toList());
        if (list.isEmpty())
            return null;
        RoleWithResource roleWithResource = list.get(0);
        roleWithResource.setResourceIds(resourceList);
        return roleWithResource;
    }

}
