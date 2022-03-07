package com.sff.rbacdemo.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sff.rbacdemo.system.entity.SysLog;

import java.util.List;

public interface LogMapper extends BaseMapper<SysLog> {

    List<SysLog> findAll();
}