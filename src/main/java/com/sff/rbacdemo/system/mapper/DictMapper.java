package com.sff.rbacdemo.system.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sff.rbacdemo.system.entity.Dict;

import java.util.List;

public interface DictMapper extends BaseMapper<Dict> {

    List<Dict> findAll();
}