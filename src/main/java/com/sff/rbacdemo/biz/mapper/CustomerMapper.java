package com.sff.rbacdemo.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.sff.rbacdemo.biz.entity.Customer;
import org.apache.ibatis.annotations.Param;

/**
 * @author Frankie Fan
 * @date 2022-04-10 14:58
 */

public interface CustomerMapper extends BaseMapper<Customer> {

    String getSequence(@Param("countryCode") String countryCode);

}
