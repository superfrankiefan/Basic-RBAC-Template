package com.sff.rbacdemo.system.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.sff.rbacdemo.system.entity.Dict;
import com.sff.rbacdemo.system.entity.DictDetail;

/**
 * @author Frankie Fan
 * @date 2022-04-14 10:30
 */

public interface DictService extends IService<Dict> {

    public DictDetail getDictDetailByCodeAndValue(String dictCode, String value);

}
