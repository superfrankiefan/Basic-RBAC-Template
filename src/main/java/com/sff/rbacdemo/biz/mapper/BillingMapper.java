package com.sff.rbacdemo.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sff.rbacdemo.biz.entity.Billing;

/**
 * @author Frankie Fan
 * @date 2022-04-10 14:59
 */

public interface BillingMapper extends BaseMapper<Billing> {

    String getSequence(String caseCode);

}
