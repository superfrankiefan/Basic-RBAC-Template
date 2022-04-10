package com.sff.rbacdemo.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sff.rbacdemo.common.model.TreeModel;
import com.sff.rbacdemo.common.properties.GlobalConstant;
import com.sff.rbacdemo.common.utils.TreeUtils;
import com.sff.rbacdemo.system.dto.Meta;
import com.sff.rbacdemo.system.dto.RouteDTO;
import com.sff.rbacdemo.system.entity.Resource;
import com.sff.rbacdemo.system.mapper.ResourceMapper;
import com.sff.rbacdemo.system.service.ResourceService;
import com.sff.rbacdemo.system.service.RoleResourceServie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Service("resourceService")
public class ResourceServiceImpl extends ServiceImpl<ResourceMapper, Resource> implements ResourceService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ResourceMapper resourceMapper;

    @Autowired
    private RoleResourceServie roleResourceService;

    @Autowired
    private WebApplicationContext applicationContext;

    @Override
    public List<RouteDTO> getUserRoutes(String userName) {
        List<Resource> resources = this.resourceMapper.findUserResources(userName);
        List<RouteDTO> trees = new ArrayList<>();
        buildRouteTrees(trees, resources);
        return buildList(trees, GlobalConstant.ROOT_ID);
    }

    private void buildRouteTrees(List<RouteDTO> trees, List<Resource> resources) {
        resources.forEach(resource -> {
            RouteDTO routeDTO = new RouteDTO();
            routeDTO.setId(resource.getResourceId());
            routeDTO.setParentId(resource.getParentId());
            routeDTO.setName(resource.getResourceName());
            routeDTO.setAlias(resource.getPath());
            routeDTO.setRedirect(resource.getRedirect());
            routeDTO.setPath(resource.getPath());
            routeDTO.setCaseSensitive(false);
            routeDTO.setComponent(resource.getComponent());
            Meta meta = new Meta();
            meta.setIcon(resource.getIcon());
            meta.setTitle(resource.getResourceName());
            meta.setOrderNo(resource.getOrderNo());
            meta.setHideMenu(!resource.isShow());
            meta.setCurrentActiveMenu(resource.getCurrentActiveMenu());
            meta.setRealPath(resource.getRealPath());
            meta.setIgnoreKeepAlive(resource.isKeepalive());
            routeDTO.setMeta(meta);
            trees.add(routeDTO);
        });
    }

    public static List<RouteDTO> buildList(List<RouteDTO> nodes, String idParam) {
        if (nodes == null) {
            return new ArrayList<>();
        }
        List<RouteDTO> topNodes = new ArrayList<>();
        nodes.forEach(child -> {
            String pid = child.getParentId().toString();
            if (pid == null || idParam.equals(pid)) {
                topNodes.add(child);
                return;
            }
            nodes.forEach(parent -> {
                String id = parent.getId().toString();
                if (id != null && id.equals(pid)) {
                    if (parent.getChildren() == null){
                        parent.setChildren(new ArrayList<>());
                    }
                    parent.getChildren().add(child);
                }
            });
        });
        return topNodes;
    }

    @Override
    public List<TreeModel<Resource>> getResourceTree(String manuName, int status) {
        QueryWrapper queryWrapper = new QueryWrapper();
        if(manuName != null && !manuName.isEmpty()){
            queryWrapper.like("RESOURCE_NAME", manuName);
        }
        queryWrapper.eq("STATUS", status);
        queryWrapper.orderByAsc("ORDER_NO");
        List<Resource> resources = this.resourceMapper.selectList(queryWrapper);
        List<TreeModel<Resource>> trees = new ArrayList<>();
        buildTrees(trees, resources);
        return TreeUtils.buildList(trees, GlobalConstant.ROOT_ID);
    }

    private void buildTrees(List<TreeModel<Resource>> trees, List<Resource> resources) {
        resources.forEach(resource -> {
            TreeModel<Resource> tree = new TreeModel<>();
            tree.setId(resource.getResourceId().toString());
            tree.setParentId(resource.getParentId().toString());
            tree.setStatus(resource.getStatus());
            tree.setPath(resource.getPath());
            tree.setComponent(resource.getComponent());
            tree.setPerms(resource.getPerms());
            tree.setOrderNo(resource.getOrderNo());
            tree.setIcon(resource.getIcon());
            tree.setText(resource.getResourceName());
            tree.setType(resource.getType());
            tree.setCurrentActiveMenu(resource.getCurrentActiveMenu());
            tree.setShow(resource.isShow());
            tree.setRedirect(resource.getRedirect());
            tree.setRealPath(resource.getRealPath());
            tree.setExt(resource.isExt());
            tree.setKeepalive(resource.isKeepalive());
            trees.add(tree);
        });
    }

    @Override
    @Transactional
    public void addResource(Resource resource) {
        resource.setCreateTime(new Date());
        if (resource.getParentId() == null)
            resource.setParentId(Long.valueOf(GlobalConstant.ROOT_ID));
        if (GlobalConstant.RES_TYPE_BTN == resource.getType()) {
            resource.setPath(null);
            resource.setIcon(null);
        }
        this.save(resource);
    }

    @Override
    @Transactional
    public void deleteMenus(String resourceIds) {
        List<String> list = Arrays.asList(resourceIds.split(","));
        list.stream().forEach(s -> this.resourceMapper.deleteById(Long.valueOf(s)));
        this.roleResourceService.deleteRoleResourcesByResourceIds(resourceIds);
        this.resourceMapper.changeToTop(list);
    }

    @Override
    public Resource findByResourceId(Long resourceId) {
        return this.resourceMapper.selectById(resourceId);
    }

    @Override
    @Transactional
    public void updateResource(Resource resource) {
        if (resource.getParentId() == null)
            resource.setParentId(Long.valueOf(GlobalConstant.ROOT_ID));
        if (GlobalConstant.RES_TYPE_BTN == resource.getType()) {
            resource.setPath(null);
            resource.setIcon(null);
        }
        this.resourceMapper.updateById(resource);
    }

    @Override
    public List<Resource> findUserPermissions(String userName) {
        return this.resourceMapper.findUserPermissions(userName);
    }
//
//    @Override
//    public List<Resource> findUserResources(String userName) {
//        return this.resourceMapper.findUserResources(userName);
//    }
//
//    @Override
//    public List<Resource> findAllResources(Resource resource) {
//        try {
//            return this.resourceMapper.findAll();
//        } catch (NumberFormatException e) {
//            log.error("error", e);
//            return new ArrayList<>();
//        }
//    }

//    @Override
//    public TreeModel<Resource> getUserResource(String userName) {
//        List<TreeModel<Resource>> trees = new ArrayList<>();
//        List<Resource> resources = this.findUserResources(userName);
//        resources.forEach(resource -> {
//            TreeModel<Resource> tree = new TreeModel<>();
//            tree.setId(resource.getResourceId().toString());
//            tree.setParentId(resource.getParentId().toString());
//            tree.setText(resource.getResourceName());
//            tree.setIcon(resource.getIcon());
//            tree.setPath(resource.getPath());
//            trees.add(tree);
//        });
//        return TreeUtils.build(trees);
//    }

//    @Override
//    public List<Map<String, String>> getAllUrl(String p1) {
//        RequestMappingHandlerMapping mapping = applicationContext.getBean(RequestMappingHandlerMapping.class);
//        //获取 url与类和方法的对应信息
//        Map<RequestMappingInfo, HandlerMethod> map = mapping.getHandlerMethods();
//        List<Map<String, String>> urlList = new ArrayList<>();
//        for (Map.Entry<RequestMappingInfo, HandlerMethod> entry : map.entrySet()) {
//            RequestMappingInfo info = entry.getKey();
//            HandlerMethod handlerMethod = map.get(info);
//            RequiresPermissions permissions = handlerMethod.getMethodAnnotation(RequiresPermissions.class);
//            String perms = "";
//            if (null != permissions) {
//                perms = StringUtils.join(permissions.value(), ",");
//            }
//            Set<String> patterns = info.getPatternsCondition().getPatterns();
//            for (String url : patterns) {
//                Map<String, String> urlMap = new HashMap<>();
//                urlMap.put("url", url.replaceFirst("\\/", ""));
//                urlMap.put("perms", perms);
//                urlList.add(urlMap);
//            }
//        }
//        return urlList;
//    }
}
