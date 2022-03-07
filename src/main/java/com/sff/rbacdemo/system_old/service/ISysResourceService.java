package com.sff.rbacdemo.system_old.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sff.rbacdemo.common.exceptions.BaseException;
import com.sff.rbacdemo.system_old.dto.CommonTreeDTO;
import com.sff.rbacdemo.system_old.entity.SysResource;

import java.util.List;

/**
 * <p>
 * 菜单权限表 服务类
 * </p>
 */
public interface ISysResourceService extends IService<SysResource> {

    public List<CommonTreeDTO> queryListByParentId(String parentId);

    /**
     * 真实删除
     */
    public void deleteResource(String id);

    /**
     * 逻辑删除
     */
    public void deleteResourceLogical(String id) throws BaseException;

    public void addResource(SysResource SysResource) throws BaseException;

    public void editResource(SysResource SysResource) throws BaseException;

    public List<SysResource> queryByUser(String username);

    /**
     * 根据ResourceId删除其关联的SysResourceDataRule表中的数据
     *
     * @param id
     * @return
     */
//    public void deletePermRuleByPermId(String id);

    /**
     * 查询出带有特殊符号的菜单地址的集合
     *
     * @return
     */
//    public List<String> queryResourceUrlWithStar();

    /**
     * 判断用户否拥有权限
     *
     * @param username
     * @param SysResource
     * @return
     */
    public boolean hasResource(String username, SysResource SysResource);

    /**
     * 根据用户和请求地址判断是否有此权限
     *
     * @param username
     * @param url
     * @return
     */
    public boolean hasResource(String username, String url);
}
