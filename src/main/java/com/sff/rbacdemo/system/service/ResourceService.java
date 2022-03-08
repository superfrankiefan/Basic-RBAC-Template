package com.sff.rbacdemo.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sff.rbacdemo.common.model.TreeModel;
import com.sff.rbacdemo.system.entity.Resource;

import java.util.List;
import java.util.Map;

public interface ResourceService extends IService<Resource> {

    List<Resource> findUserPermissions(String userName);

    List<Resource> findUserResources(String userName);

    List<Resource> findAllResources(Resource resource);

    TreeModel<Resource> getResourceButtonTree();

    TreeModel<Resource> getResourceTree();

    TreeModel<Resource> getUserResource(String userName);

    Resource findById(Long resourceId);

    Resource findByNameAndType(String resourceName, String type);

    void addResource(Resource resource);

    void updateResource(Resource resource);

    void deleteMeuns(String resourceIds);

    List<Map<String, String>> getAllUrl(String p1);
}
