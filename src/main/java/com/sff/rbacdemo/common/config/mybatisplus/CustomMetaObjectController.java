//package com.sff.rbacdemo.common.config.mybatisplus;
//
//import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
//import com.sff.rbacdemo.system.entity.User;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.ibatis.reflection.MetaObject;
//import org.apache.shiro.SecurityUtils;
//import org.springframework.stereotype.Component;
//
//import java.time.LocalDateTime;
//
///**
// * @author Frankie Fan
// * @date 2022-03-06 12:52
// * @description 自动填充审计字段
// */
//
//@Slf4j
//@Component
//public class CustomMetaObjectController implements MetaObjectHandler {
//    @Override
//    public void insertFill(MetaObject metaObject) {
//        log.info("start insert fill......");
//        this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
//        User currentUser = (User) SecurityUtils.getSubject().getPrincipal();
//        this.strictInsertFill(metaObject, "createBy", Long.class, currentUser.getUserId());
//    }
//
//    @Override
//    public void updateFill(MetaObject metaObject) {
//        log.info("start update fill......");
//        this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
//        User currentUser = (User) SecurityUtils.getSubject().getPrincipal();
//        this.strictUpdateFill(metaObject, "updateBy", Long.class, currentUser.getUserId());
//    }
//}
