package com.sff.rbacdemo.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.enums.SqlLike;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sff.rbacdemo.common.model.PageResponseDTO;
import com.sff.rbacdemo.common.properties.GlobalConstant;
import com.sff.rbacdemo.system.dto.RoleInfoDTO;
import com.sff.rbacdemo.system.dto.UserInfoDTO;
import com.sff.rbacdemo.system.dto.UserWithRole;
import com.sff.rbacdemo.system.entity.Role;
import com.sff.rbacdemo.system.entity.User;
import com.sff.rbacdemo.system.entity.UserRole;
import com.sff.rbacdemo.system.mapper.UserMapper;
import com.sff.rbacdemo.system.mapper.UserRoleMapper;
import com.sff.rbacdemo.system.service.UserRoleService;
import com.sff.rbacdemo.system.service.UserService;
import com.sff.rbacdemo.common.utils.MD5Utils;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j(topic = "UserServiceImpl.class")
@Service("userService")
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true, rollbackFor = Exception.class)
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public User findByName(String userName) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userName);
        User user = userMapper.selectOne(queryWrapper);
        return user;
    }

    @Override
    @Transactional
    public void registUser(User user) {
        user.setAvatar(User.DEFAULT_AVATAR);
        user.setGender(GlobalConstant.SEX_UNKNOW);
        user.setPassword(MD5Utils.encrypt(user.getUsername().toLowerCase(), user.getPassword()));
        this.save(user);
//        UserRole ur = new UserRole();
//        ur.setUserId(user.getUserId());
//        ur.setRoleId(3L);
//        this.userRoleMapper.insert(ur);
    }

    @Override
    @Transactional
    public void addUser(User user, Long[] roles) {
        user.setCreateTime(new Date());
        user.setAvatar(User.DEFAULT_AVATAR);
        user.setPassword(MD5Utils.encrypt(user.getUsername(), user.getPassword()));
        this.save(user);
        setUserRoles(user, roles);
    }

    private void setUserRoles(User user, Long[] roles) {
        Arrays.stream(roles).forEach(roleId -> {
            UserRole ur = new UserRole();
            ur.setUserId(user.getUserId());
            ur.setRoleId(roleId);
            this.userRoleMapper.insert(ur);
        });
    }

    @Override
    @Transactional
    public void updateUser(User user, Long[] roles) {
        user.setPassword(null);
        user.setUsername(null);
        user.setUpdateTime(new Date());
        this.userMapper.updateById(user);
        this.userRoleMapper.deleteByUserId(user.getUserId());
        setUserRoles(user, roles);
    }

    @Override
    @Transactional
    public void deleteUsers(String userIds) {
        List<String> list = Arrays.asList(userIds.split(","));
        this.userMapper.deleteBatchIds(list);
        this.userRoleService.deleteUserRolesByUserId(userIds);
    }

    @Override
    @Transactional
    public void updateLoginTime(String userName) {
        User userIns = this.findByName(userName);
        if(userIns != null){
            userIns.setLastLoginTime(new Date());
            userMapper.updateById(userIns);
        }
    }

    @Override
    @Transactional
    public void updatePassword(String password) {
        User user = (User) SecurityUtils.getSubject().getPrincipal();
        User ins = this.userMapper.selectById(user.getUserId());
        String newPassword = MD5Utils.encrypt(user.getUsername().toLowerCase(), password);
        ins.setPassword(newPassword);
        this.userMapper.updateById(ins);
    }

    @Override
    public UserWithRole findById(Long userId) {
        List<UserWithRole> list = this.userMapper.findUserWithRole(userId);
        List<Long> roleList = list.stream().map(UserWithRole::getRoleId).collect(Collectors.toList());
        if (list.isEmpty())
            return null;
        UserWithRole userWithRole = list.get(0);
        userWithRole.setRoleIds(roleList);
        return userWithRole;
    }

    @Override
    public UserInfoDTO getCurrentUserInfo() {
        User currentUser = (User) SecurityUtils.getSubject().getPrincipal();
        try{
            List<UserInfoDTO> userInfos = this.userMapper.getUserInfo(currentUser.getUserId());
            List<RoleInfoDTO> roleInfos = new ArrayList<>();
            userInfos.forEach(userInfo -> {
                RoleInfoDTO roleInfo = new RoleInfoDTO();
                roleInfo.setRoleCode(userInfo.getRoleCode());
                roleInfo.setRemark(userInfo.getRemark());
                roleInfo.setRoleName(userInfo.getRoleName());
                roleInfos.add(roleInfo);
            });
            UserInfoDTO userInfo = userInfos.get(0);
            userInfo.setRoles(roleInfos);
            return userInfo;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public PageResponseDTO<User> getUserListByDept(String deptId, String username, String realName, int page, int count) {
        Page<User> pager = new Page<>(page, count);
        QueryWrapper queryWrapper = new QueryWrapper();
        if (deptId != null && !deptId.isEmpty()){
            queryWrapper.eq("deptId", deptId);
        }
        if (username != null && !username.isEmpty()){
            queryWrapper.like("USERNAME", username);
        }
        if (realName != null && !realName.isEmpty()){
            queryWrapper.like("REAL_NAME", realName);
        }
        IPage<User> paging = this.userMapper.selectPage(pager, queryWrapper);
        PageResponseDTO pageResponseDTO = new PageResponseDTO();
        pageResponseDTO.setPage(paging.getCurrent());
        pageResponseDTO.setCount(paging.getSize());
        pageResponseDTO.setTotal(paging.getTotal());
        pageResponseDTO.setItems(paging.getRecords());
        return pageResponseDTO;
    }

    @Override
    public User findUserProfile(User user) {
        return this.userMapper.findUserProfile(user);
    }

    @Override
    @Transactional
    public void updateUserProfile(User user) {
        user.setUsername(null);
        user.setPassword(null);
        if (user.getDeptId() == null)
            user.setDeptId(0L);
        userMapper.updateById(user);
    }

}
