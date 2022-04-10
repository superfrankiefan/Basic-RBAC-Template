package com.sff.rbacdemo.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sff.rbacdemo.common.model.TreeModel;
import com.sff.rbacdemo.system.dto.RouteDTO;
import com.sff.rbacdemo.system.entity.Resource;

import java.util.List;

public interface ResourceService extends IService<Resource> {

    /**
     * 获取资源树
     * @param manuName
     * @param status
     * @return
     */
    List<TreeModel<Resource>> getResourceTree(String manuName, int status);

    /**
     * 根据ID获取资源
     * @param resourceId
     * @return
     */
    Resource findByResourceId(Long resourceId);

    /**
     * 新增资源
     * @param resource
     */
    void addResource(Resource resource);

    /**
     * 更新资源
     * @param resource
     */
    void updateResource(Resource resource);

    /**
     * 删除资源
     * @param resourceIds
     */
    void deleteMenus(String resourceIds);

    /**
     * 获取用户的权限
     * @param userName
     * @return
     */
    List<Resource> findUserPermissions(String userName);

    /**
     * 获取用户前端路由
     * @param userName
     * @return
     */
    List<RouteDTO> getUserRoutes(String userName);
    //    List<Resource> findUserResources(String userName);
    //    List<Resource> findAllResources(Resource resource);
    //    TreeModel<Resource> getUserResource(String userName);
    //    List<Map<String, String>> getAllUrl(String p1);
}
