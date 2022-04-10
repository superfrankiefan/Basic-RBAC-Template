package com.sff.rbacdemo.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sff.rbacdemo.common.model.PageResponseDTO;
import com.sff.rbacdemo.system.dto.UserInfoDTO;
import com.sff.rbacdemo.system.entity.User;
import org.springframework.transaction.annotation.Transactional;

public interface UserService extends IService<User> {

    /**
     * 获取当前登陆用户信息
     * @return
     */
    UserInfoDTO getCurrentUserInfo();

    /**
     * 分页查询用户信息
     * @param page
     * @param count
     * @param deptCode
     * @param username
     * @param realName
     * @return
     */
    PageResponseDTO<UserInfoDTO> queryUserList(int page, int count, String deptCode, String username, String realName);

    /**
     * 根据用户名查询用户信息
     * @param userName
     * @return
     */
    User findByUserName(String userName);

    /**
     * 添加用户
     * @param userInfoDTO
     */
    void addUser(UserInfoDTO userInfoDTO);

    /**
     * 更新用户信息
     * @param userInfoDTO
     */
    void updateUser(UserInfoDTO userInfoDTO);

    /**
     * 删除用户
     * @param userNames
     */
    void deleteUsers(String userNames);

    void updateLoginTime(String userName);

    void updatePassword(String password);
}
