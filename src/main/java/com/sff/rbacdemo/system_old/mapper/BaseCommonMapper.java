package com.sff.rbacdemo.system_old.mapper;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.sff.rbacdemo.system_old.dto.LogDTO;
import org.apache.ibatis.annotations.Param;

public interface BaseCommonMapper {

    /**
     * 保存日志
     * @param dto
     */
    @InterceptorIgnore(illegalSql = "true", tenantLine = "true")
    void saveLog(@Param("dto") LogDTO dto);

}
