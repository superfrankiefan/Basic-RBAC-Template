package com.sff.rbacdemo.system.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sff.rbacdemo.common.model.PageResponseDTO;
import com.sff.rbacdemo.common.properties.GlobalConstant;
import com.sff.rbacdemo.common.utils.MD5Utils;
import com.sff.rbacdemo.system.dto.RoleInfoDTO;
import com.sff.rbacdemo.system.dto.UserInfoDTO;
import com.sff.rbacdemo.system.dto.UserQueryParam;
import com.sff.rbacdemo.system.entity.User;
import com.sff.rbacdemo.system.entity.UserRole;
import com.sff.rbacdemo.system.mapper.UserMapper;
import com.sff.rbacdemo.system.mapper.UserRoleMapper;
import com.sff.rbacdemo.system.service.UserRoleService;
import com.sff.rbacdemo.system.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Slf4j(topic = "UserServiceImpl.class")
@Service("userService")
public class UserServiceImpl extends ServiceImpl<UserMapper,User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public User findByUserName(String userName) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", userName);
        User user = userMapper.selectOne(queryWrapper);
        return user;
    }

    @Override
    @Transactional
    public void addUser(UserInfoDTO userInfoDTO) {
        User user = new User();
        user.setUserStatus(GlobalConstant.STATUS_VALID);
        user.setEmail(userInfoDTO.getEmail());
        user.setUsername(userInfoDTO.getUserName());
        user.setRealName(userInfoDTO.getRealName());
        user.setWorkNo(userInfoDTO.getWorkNo());
        user.setDeptCode(userInfoDTO.getDeptCode());
        user.setPassword(MD5Utils.encrypt(userInfoDTO.getUserName().toLowerCase(), userInfoDTO.getPassword()));
        user.setAvatar(User.DEFAULT_AVATAR);
        this.userMapper.insert(user);
        setUserRoles(userInfoDTO.getUserName(), userInfoDTO.getRoleCodes());
    }

    @Override
    @Transactional
    public void updateUser(UserInfoDTO userInfoDTO) {
        User user = findByUserName(userInfoDTO.getUserName());
        user.setRealName(userInfoDTO.getRealName());
        user.setWorkNo(userInfoDTO.getWorkNo());
        user.setDeptCode(userInfoDTO.getDeptCode());
        user.setEmail(userInfoDTO.getEmail());
        this.userMapper.updateById(user);
        this.userRoleMapper.deleteByUserName(userInfoDTO.getUserName());
        setUserRoles(userInfoDTO.getUserName(), userInfoDTO.getRoleCodes());
    }

    private void setUserRoles(String userName, String roleCodes) {
        Arrays.asList(roleCodes.split(",")).stream().forEach(roleCode -> {
            UserRole ur = new UserRole();
            ur.setUserName(userName);
            ur.setRoleCode(roleCode);
            this.userRoleMapper.insert(ur);
        });
    }

    @Override
    @Transactional
    public void deleteUsers(String userNames) {
        List<String> list = Arrays.asList(userNames.split(","));
        list.stream().forEach(s -> this.userMapper.deleteByUsername(s));
        this.userRoleService.deleteUserRolesByUserName(userNames);
    }

    @Override
    @Transactional
    public void updateLoginTime(String userName) {
        User userIns = this.findByUserName(userName);
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
    public UserInfoDTO getCurrentUserInfo() {
        User currentUser = (User) SecurityUtils.getSubject().getPrincipal();
        try{
            List<UserInfoDTO> userInfos = this.userMapper.getUserInfo(currentUser.getUserId());
            List<RoleInfoDTO> roleInfos = new ArrayList<>();
            StringBuilder roleCodes = new StringBuilder();
            userInfos.forEach(ui -> {
                RoleInfoDTO roleInfo = new RoleInfoDTO();
                roleInfo.setRoleCode(ui.getRoleCode());
                roleInfo.setRemark(ui.getRemark());
                roleInfo.setRoleName(ui.getRoleName());
                roleInfos.add(roleInfo);
                roleCodes.append(ui.getRoleCodes());
                roleCodes.append(",");
            });
            UserInfoDTO userInfo = userInfos.get(0);
            userInfo.setRoles(roleInfos);
            userInfo.setRoleCodes(roleCodes.deleteCharAt(roleCodes.length()-1).toString());
            return userInfo;
        }catch (Exception e){
            throw e;
        }
    }

    @Override
    public PageResponseDTO<UserInfoDTO> queryUserList(int page, int count, String deptCode, String username, String realName) {
        UserQueryParam userQueryParam = new UserQueryParam();
        userQueryParam.setRealName(realName);
        userQueryParam.setDeptCode(deptCode);
        userQueryParam.setUsername(username);
        IPage<UserInfoDTO> iPage = this.userMapper.findUserWithRoleAndDept(new Page<>(page, count), userQueryParam);
        PageResponseDTO pageResponseDTO = new PageResponseDTO();
        pageResponseDTO.setPage(iPage.getCurrent());
        pageResponseDTO.setCount(iPage.getSize());
        pageResponseDTO.setTotal(iPage.getTotal());
        pageResponseDTO.setItems(iPage.getRecords());
        return pageResponseDTO;
    }

}
