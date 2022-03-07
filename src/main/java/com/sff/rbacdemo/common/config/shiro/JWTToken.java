package com.sff.rbacdemo.common.config.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author Frankie Fan
 * @date 2022-03-05 18:54
 */

public class JWTToken implements AuthenticationToken {

    // 密钥
    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
