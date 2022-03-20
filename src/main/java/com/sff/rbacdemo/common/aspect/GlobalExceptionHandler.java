package com.sff.rbacdemo.common.aspect;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.sff.rbacdemo.common.model.APIResponse;
import com.sff.rbacdemo.common.properties.GlobalConstant;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authz.AuthorizationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Frankie Fan
 * @date 2022-03-05 18:51
 * spring boot rest请求统一的异常处理
 */

@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Shiro Authentication 异常
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthenticationException.class)
    public APIResponse<Object> handleAuthenticationError(AuthenticationException e) {
        return APIResponse.ERROR(HttpStatus.UNAUTHORIZED.value(), e.getMessage(), e.getCause());
    }

    /**
     * Shiro Authorization 异常
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(AuthorizationException.class)
    public APIResponse<Object> handleAuthorizationError(AuthorizationException e) {
        return APIResponse.ERROR(HttpStatus.UNAUTHORIZED.value(), e.getMessage(), e.getCause());
    }

    /**
     * JWT Token 过期异常
     * @param e
     * @return
     */
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(TokenExpiredException.class)
    public APIResponse<Object> handleTokenExpired(TokenExpiredException e){
        return APIResponse.ERROR(GlobalConstant.TOKEN_EXPIRE, e.getMessage(), e.getCause());
    }

    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(JWTVerificationException.class)
    public APIResponse<Object> handleInvalidToken(JWTVerificationException e){
        return APIResponse.ERROR(GlobalConstant.TOKEN_INVALID, e.getMessage(), e.getCause());
    }

    // 捕捉其他所有异常
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public APIResponse globalException(HttpServletRequest request, Throwable ex) {
        return APIResponse.ERROR(getStatus(request).value(), ex.getMessage(), ex);
    }

    /**
     * get tomcat error
     *
     * @param request
     * @return
     */
    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
