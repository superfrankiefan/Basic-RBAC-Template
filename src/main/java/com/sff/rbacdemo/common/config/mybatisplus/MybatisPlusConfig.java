package com.sff.rbacdemo.common.config.mybatisplus;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author Frankie Fan
 * @date 2022-03-14 16:28
 * Mybatis Plus 配置类
 */

@Configuration
public class MybatisPlusConfig {

    /**
     * 分页插件(PaginationInterceptor)
     * 不配置此项返回的是全部记录
     *
     * @return
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }

}
