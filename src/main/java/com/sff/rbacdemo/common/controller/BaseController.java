package com.sff.rbacdemo.common.controller;

import com.sff.rbacdemo.system.entity.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;

public class BaseController {

    protected static Subject getSubject() {
        return SecurityUtils.getSubject();
    }

    protected User getCurrentUser() {
        return (User) getSubject().getPrincipal();
    }

    protected Session getSession() {
        return getSubject().getSession();
    }

    protected Session getSession(Boolean flag) {
        return getSubject().getSession(flag);
    }

    protected void login(AuthenticationToken token) {
        getSubject().login(token);
    }

//    private Map<String, Object> getDataTable(PageInfo<?> pageInfo) {
//        Map<String, Object> rspData = new HashMap<>();
//        rspData.put("rows", pageInfo.getList());
//        rspData.put("total", pageInfo.getTotal());
//        return rspData;
//    }
//
//    protected Map<String, Object> selectByPageNumSize(QueryRequest request, Supplier<?> s) {
//        PageHelper.startPage(request.getPageNum(), request.getPageSize());
//        PageInfo<?> pageInfo = new PageInfo<>((List<?>) s.get());
//        PageHelper.clearPage();
//        return getDataTable(pageInfo);
//    }
}
