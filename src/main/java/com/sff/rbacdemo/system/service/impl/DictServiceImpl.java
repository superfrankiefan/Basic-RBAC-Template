package com.sff.rbacdemo.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sff.rbacdemo.system.entity.Dict;
import com.sff.rbacdemo.system.entity.DictDetail;
import com.sff.rbacdemo.system.mapper.DictDetailMapper;
import com.sff.rbacdemo.system.mapper.DictMapper;
import com.sff.rbacdemo.system.service.DictService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Frankie Fan
 * @date 2022-04-14 11:14
 */

@Service("dictService")
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    @Autowired
    private DictDetailMapper dictDetailMapper;

    @Override
    public DictDetail getDictDetailByCodeAndValue(String dictCode, String value) {
        return null;
    }
}
