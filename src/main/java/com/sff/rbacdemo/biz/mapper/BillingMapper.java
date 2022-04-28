package com.sff.rbacdemo.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sff.rbacdemo.biz.dto.OverdueBillingsDTO;
import com.sff.rbacdemo.biz.entity.Billing;

import java.util.List;

/**
 * @author Frankie Fan
 * @date 2022-04-10 14:59
 */

public interface BillingMapper extends BaseMapper<Billing> {

    /**
     * Obtain max number of case's billing
     * e.x: C0001B0010, then get 10
     * @param caseCode
     * @return
     */
    String getSequence(String caseCode);

    List<OverdueBillingsDTO> getOverdueBillings();

}
