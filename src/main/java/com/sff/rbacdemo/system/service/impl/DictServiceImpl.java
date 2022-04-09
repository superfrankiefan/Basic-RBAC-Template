package com.sff.rbacdemo.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.sff.rbacdemo.system.entity.Dict;
import com.sff.rbacdemo.system.mapper.DictMapper;
import com.sff.rbacdemo.system.service.DictService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * @author frankie fan
 */
@Service("dictService")
public class DictServiceImpl extends ServiceImpl<DictMapper, Dict> implements DictService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private DictMapper dictMapper;

    @Override
    @Transactional
    public void addDict(Dict dict) {
        this.save(dict);
    }

    @Override
    @Transactional
    public void deleteDicts(String dictIds) {
        List<String> list = Arrays.asList(dictIds.split(","));
        this.dictMapper.deleteBatchIds(list);
    }

    @Override
    @Transactional
    public void updateDict(Dict dict) {
        this.dictMapper.updateById(dict);
    }

    @Override
    public Dict findById(Long dictId) {
        return this.dictMapper.selectById(dictId);
    }

}
