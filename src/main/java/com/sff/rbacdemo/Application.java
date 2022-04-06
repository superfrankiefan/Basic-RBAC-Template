package com.sff.rbacdemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@MapperScan("com.sff.rbacdemo.system.mapper")
@EnableTransactionManagement //开启事务管理，保证多笔数据库交易的原子性
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

}
