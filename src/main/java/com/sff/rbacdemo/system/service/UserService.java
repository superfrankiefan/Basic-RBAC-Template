package com.sff.rbacdemo.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sff.rbacdemo.common.model.PageResponseDTO;
import com.sff.rbacdemo.system.dto.UserInfoDTO;
import com.sff.rbacdemo.system.dto.UserWithRole;
import com.sff.rbacdemo.system.entity.User;

public interface UserService extends IService<User> {

    UserWithRole findById(Long userId);

    UserInfoDTO getCurrentUserInfo();

    PageResponseDTO<User> getUserListByDept(String deptId, String username, String realName, int page, int count);

    User findByName(String userName);

    void registUser(User user);

    void addUser(User user, Long[] roles);

    void updateUser(User user, Long[] roles);

    void deleteUsers(String userIds);

    void updateLoginTime(String userName);

    void updatePassword(String password);

    User findUserProfile(User user);

    void updateUserProfile(User user);
}
