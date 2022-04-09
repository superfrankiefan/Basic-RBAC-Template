package com.sff.rbacdemo.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sff.rbacdemo.system.entity.Dict;
import org.springframework.transaction.annotation.Transactional;

public interface DictService extends IService<Dict> {

    Dict findById(Long dictId);

    void addDict(Dict dict);

    void deleteDicts(String dictIds);

    void updateDict(Dict dicdt);
}
