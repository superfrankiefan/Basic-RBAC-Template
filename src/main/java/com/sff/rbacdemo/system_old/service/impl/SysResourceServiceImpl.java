package com.sff.rbacdemo.system_old.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sff.rbacdemo.common.enums.CommonConstant;
import com.sff.rbacdemo.common.exceptions.BaseException;
import com.sff.rbacdemo.common.utils.ObjectConvertUtils;
import com.sff.rbacdemo.system_old.dto.CommonTreeDTO;
import com.sff.rbacdemo.system_old.entity.SysResource;
import com.sff.rbacdemo.system_old.mapper.SysResourceMapper;
import com.sff.rbacdemo.system_old.mapper.SysRoleResourceMapper;
import com.sff.rbacdemo.system_old.service.ISysResourceService;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 菜单权限表 服务实现类
 * </p>
 */
@Service
@Primary
public class SysResourceServiceImpl extends ServiceImpl<SysResourceMapper, SysResource> implements ISysResourceService {

    @Resource
    private SysResourceMapper sysResourceMapper;

    @Resource
    private SysRoleResourceMapper sysRoleResourceMapper;

    @Override
    public List<CommonTreeDTO> queryListByParentId(String parentId) {
        return sysResourceMapper.queryListByParentId(parentId);
    }

    /**
     * 真实删除
     */
    @Override
    @Transactional
    public void deleteResource(String id) throws BaseException {
        SysResource sysResource = this.getById(id);
        if (sysResource == null) {
            throw new BaseException("未找到菜单信息");
        }
        String pid = sysResource.getParentId();
        if (ObjectConvertUtils.isNotEmpty(pid)) {
            long count = this.count(new QueryWrapper<SysResource>().lambda().eq(SysResource::getParentId, pid));
            if (count == 1) {
                //若父节点无其他子节点，则该父节点是叶子节点
                this.sysResourceMapper.setMenuLeaf(pid, 1);
            }
        }
        sysResourceMapper.deleteById(id);
        // 该节点可能是子节点但也可能是其它节点的父节点,所以需要级联删除
        this.removeChildrenBy(sysResource.getId());
        //关联删除
        Map map = new HashMap<>();
        map.put("Resource_id", id);
        //删除角色授权表
        sysRoleResourceMapper.deleteByMap(map);
    }

    /**
     * 根据父id删除其关联的子节点数据
     *
     * @return
     */
    public void removeChildrenBy(String parentId) {
        LambdaQueryWrapper<SysResource> query = new LambdaQueryWrapper<>();
        // 封装查询条件parentId为主键,
        query.eq(SysResource::getParentId, parentId);
        // 查出该主键下的所有子级
        List<SysResource> ResourceList = this.list(query);
        if (ResourceList != null && ResourceList.size() > 0) {
            String id = ""; // id
            long num = 0; // 查出的子级数量
            // 如果查出的集合不为空, 则先删除所有
            this.remove(query);
            // 再遍历刚才查出的集合, 根据每个对象,查找其是否仍有子级
            for (int i = 0, len = ResourceList.size(); i < len; i++) {
                id = ResourceList.get(i).getId();
                Map map = new HashMap<>();
                map.put("Resource_id", id);
                //删除角色授权表
                sysRoleResourceMapper.deleteByMap(map);
                num = this.count(new LambdaQueryWrapper<SysResource>().eq(SysResource::getParentId, id));
                // 如果有, 则递归
                if (num > 0) {
                    this.removeChildrenBy(id);
                }
            }
        }
    }

    /**
     * 逻辑删除
     */
    @Override
    //@CacheEvict(value = CacheConstant.SYS_DATA_ResourceS_CACHE,allEntries=true,condition="#sysResource.menuType==2")
    public void deleteResourceLogical(String id) throws BaseException {
        SysResource sysResource = this.getById(id);
        if (sysResource == null) {
            throw new BaseException("未找到菜单信息");
        }
        String pid = sysResource.getParentId();
        long count = this.count(new QueryWrapper<SysResource>().lambda().eq(SysResource::getParentId, pid));
        if (count == 1) {
            //若父节点无其他子节点，则该父节点是叶子节点
            this.sysResourceMapper.setMenuLeaf(pid, 1);
        }
        sysResource.setDelFlag(1);
        this.updateById(sysResource);
    }

    @Override
//	@CacheEvict(value = CacheConstant.SYS_DATA_ResourceS_CACHE,allEntries=true)
    public void addResource(SysResource sysResource) throws BaseException {
        //----------------------------------------------------------------------
        //判断是否是一级菜单，是的话清空父菜单
        if (CommonConstant.MENU_TYPE_0.equals(sysResource.getResourceType())) {
            sysResource.setParentId(null);
        }
        //----------------------------------------------------------------------
        String pid = sysResource.getParentId();
        if (ObjectConvertUtils.isNotEmpty(pid)) {
            //设置父节点不为叶子节点
            this.sysResourceMapper.setMenuLeaf(pid, 0);
        }
        sysResource.setCreateTime(new Date());
        sysResource.setDelFlag(0);
        sysResource.setLeaf(true);
        this.save(sysResource);
    }

    @Override
//	@CacheEvict(value = CacheConstant.SYS_DATA_ResourceS_CACHE,allEntries=true)
    public void editResource(SysResource sysResource) throws BaseException {
        SysResource p = this.getById(sysResource.getId());
        //TODO 该节点判断是否还有子节点
        if (p == null) {
            throw new BaseException("未找到菜单信息");
        } else {
            sysResource.setUpdateTime(new Date());
            //----------------------------------------------------------------------
            //Step1.判断是否是一级菜单，是的话清空父菜单ID
            if (CommonConstant.MENU_TYPE_0.equals(sysResource.getResourceType())) {
                sysResource.setParentId("");
            }
            //Step2.判断菜单下级是否有菜单，无则设置为叶子节点
            long count = this.count(new QueryWrapper<SysResource>().lambda().eq(SysResource::getParentId, sysResource.getId()));
            if (count == 0) {
                sysResource.setLeaf(true);
            }
            //----------------------------------------------------------------------
            this.updateById(sysResource);

            //如果当前菜单的父菜单变了，则需要修改新父菜单和老父菜单的，叶子节点状态
            String pid = sysResource.getParentId();
            if ((ObjectConvertUtils.isNotEmpty(pid) && !pid.equals(p.getParentId())) || ObjectConvertUtils.isEmpty(pid) && ObjectConvertUtils.isNotEmpty(p.getParentId())) {
                //a.设置新的父菜单不为叶子节点
                this.sysResourceMapper.setMenuLeaf(pid, 0);
                //b.判断老的菜单下是否还有其他子菜单，没有的话则设置为叶子节点
                long cc = this.count(new QueryWrapper<SysResource>().lambda().eq(SysResource::getParentId, p.getParentId()));
                if (cc == 0) {
                    if (ObjectConvertUtils.isNotEmpty(p.getParentId())) {
                        this.sysResourceMapper.setMenuLeaf(p.getParentId(), 1);
                    }
                }

            }
        }

    }

    @Override
    public List<SysResource> queryByUser(String username) {
        return this.sysResourceMapper.queryByUser(username);
    }

    @Override
    public boolean hasResource(String username, SysResource sysResource) {
        int count = baseMapper.queryCountByUsername(username, sysResource);
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public boolean hasResource(String username, String url) {
        SysResource sysResource = new SysResource();
        sysResource.setUrl(url);
        int count = baseMapper.queryCountByUsername(username, sysResource);
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

}
