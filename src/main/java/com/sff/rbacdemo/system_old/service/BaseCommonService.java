package com.sff.rbacdemo.system_old.service;

import com.sff.rbacdemo.system_old.dto.LogDTO;
import com.sff.rbacdemo.system_old.entity.SysUser;

/**
 * common接口
 */
public interface BaseCommonService {

    /**
     * 保存日志
     *
     * @param logDTO
     */
    void addLog(LogDTO logDTO);

    /**
     * 保存日志
     *
     * @param LogContent
     * @param logType
     * @param operateType
     * @param user
     */
    void addLog(String LogContent, Integer logType, Integer operateType, SysUser user);

    /**
     * 保存日志
     *
     * @param LogContent
     * @param logType
     * @param operateType
     */
    void addLog(String LogContent, Integer logType, Integer operateType);

}
