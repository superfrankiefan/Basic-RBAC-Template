package com.sff.rbacdemo.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sff.rbacdemo.system.entity.Dict;

public interface DictService extends IService<Dict> {

    Dict findById(Long dictId);

    void addDict(Dict dict);

    void deleteDicts(String dictIds);

    void updateDict(Dict dicdt);
}
