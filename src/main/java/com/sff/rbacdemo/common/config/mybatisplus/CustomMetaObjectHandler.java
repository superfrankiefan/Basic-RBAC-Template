package com.sff.rbacdemo.common.config.mybatisplus;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import com.sff.rbacdemo.system.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.shiro.SecurityUtils;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * @author Frankie Fan
 * @date 2022-03-06 12:52
 * 自动填充审计字段
 */

@Slf4j
@Component
public class CustomMetaObjectHandler implements MetaObjectHandler {
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("start insert fill......");
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
        this.strictInsertFill(metaObject, "updateTime", Date.class, new Date());
        User currentUser = (User) SecurityUtils.getSubject().getPrincipal();
        if(currentUser != null) {
            this.strictInsertFill(metaObject, "createBy", Long.class, currentUser.getUserId());
            this.strictInsertFill(metaObject, "updateBy", Long.class, currentUser.getUserId());
        }else{
            this.strictInsertFill(metaObject, "createBy", Long.class, Long.valueOf(10000000));
            this.strictInsertFill(metaObject, "updateBy", Long.class, Long.valueOf(10000000));
        }
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("start update fill......");
        this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
        User currentUser = (User) SecurityUtils.getSubject().getPrincipal();
        if(currentUser != null) {
            this.strictUpdateFill(metaObject, "updateBy", Long.class, currentUser.getUserId());
        }else{
            this.strictUpdateFill(metaObject, "updateBy", Long.class, Long.valueOf(10000000));
        }
    }
}
