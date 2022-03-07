package com.sff.rbacdemo.system_old.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sff.rbacdemo.common.utils.ObjectConvertUtils;
import com.sff.rbacdemo.system_old.entity.SysRoleResource;
import com.sff.rbacdemo.system_old.mapper.SysRoleResourceMapper;
import com.sff.rbacdemo.system_old.service.ISysRoleResourceService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * <p>
 * 角色权限表 服务实现类
 * </p>
 */
@Service
@Primary
public class SysRoleResourceServiceImpl extends ServiceImpl<SysRoleResourceMapper, SysRoleResource> implements ISysRoleResourceService {

    @Override
    public void saveRoleResource(String roleId, String ResourceIds) {
        LambdaQueryWrapper<SysRoleResource> query = new QueryWrapper<SysRoleResource>().lambda().eq(SysRoleResource::getRoleId, roleId);
        this.remove(query);
        List<SysRoleResource> list = new ArrayList<SysRoleResource>();
        String[] arr = ResourceIds.split(",");
        for (String p : arr) {
            if (ObjectConvertUtils.isNotEmpty(p)) {
                SysRoleResource rolepms = new SysRoleResource(roleId, p);
                list.add(rolepms);
            }
        }
        this.saveBatch(list);
    }

    @Override
    public void saveRoleResource(String roleId, String ResourceIds, String lastResourceIds) {
        List<String> add = getDiff(lastResourceIds, ResourceIds);
        if (add != null && add.size() > 0) {
            List<SysRoleResource> list = new ArrayList<SysRoleResource>();
            for (String p : add) {
                if (ObjectConvertUtils.isNotEmpty(p)) {
                    SysRoleResource rolepms = new SysRoleResource(roleId, p);
                    list.add(rolepms);
                }
            }
            this.saveBatch(list);
        }

        List<String> delete = getDiff(ResourceIds, lastResourceIds);
        if (delete != null && delete.size() > 0) {
            for (String ResourceId : delete) {
                this.remove(new QueryWrapper<SysRoleResource>().lambda().eq(SysRoleResource::getRoleId, roleId).eq(SysRoleResource::getResourceId, ResourceId));
            }
        }
    }

    /**
     * 从diff中找出main中没有的元素
     *
     * @param main
     * @param diff
     * @return
     */
    private List<String> getDiff(String main, String diff) {
        if (ObjectConvertUtils.isEmpty(diff)) {
            return null;
        }
        if (ObjectConvertUtils.isEmpty(main)) {
            return Arrays.asList(diff.split(","));
        }

        String[] mainArr = main.split(",");
        String[] diffArr = diff.split(",");
        Map<String, Integer> map = new HashMap<>();
        for (String string : mainArr) {
            map.put(string, 1);
        }
        List<String> res = new ArrayList<String>();
        for (String key : diffArr) {
            if (ObjectConvertUtils.isNotEmpty(key) && !map.containsKey(key)) {
                res.add(key);
            }
        }
        return res;
    }

}
