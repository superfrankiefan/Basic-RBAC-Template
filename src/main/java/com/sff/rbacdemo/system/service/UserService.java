package com.sff.rbacdemo.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sff.rbacdemo.common.model.PageResponseDTO;
import com.sff.rbacdemo.system.dto.UserInfoDTO;
import com.sff.rbacdemo.system.dto.UserWithRole;
import com.sff.rbacdemo.system.entity.User;

public interface UserService extends IService<User> {

    UserInfoDTO getCurrentUserInfo();

    PageResponseDTO<User> getUserListByDept(String deptId, String username, String realName, int page, int count);

    User findByName(String userName);

    /**
     * 添加用户
     * @param user
     * @param roles
     */
    void addUser(User user, Long[] roles);

    /**
     * 更新用户信息
     * @param user
     * @param roles
     */
    void updateUser(User user, Long[] roles);

    /**
     * 删除用户
     * @param userIds
     */
    void deleteUsers(String userIds);

    void updateLoginTime(String userName);

    void updatePassword(String password);
}
