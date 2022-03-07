package com.sff.rbacdemo.common.aspect;

import com.sff.rbacdemo.common.model.ResponseDTO;
import com.sff.rbacdemo.common.exceptions.UnauthorizedException;
import com.sff.rbacdemo.common.model.Result;
import org.apache.shiro.ShiroException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Frankie Fan
 * @date 2022-03-05 18:51
 * @description spring boot rest请求统一的异常处理
 */

@RestControllerAdvice
public class ExceptionController {

    // 捕捉shiro的异常
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(ShiroException.class)
    public Result<Object> handle401(ShiroException e) {
        return Result.error(e.getMessage(), e);
    }

    // 捕捉UnauthorizedException
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnauthorizedException.class)
    public ResponseDTO handle401() {
        return new ResponseDTO(401, "Unauthorized", null);
    }

    // 捕捉其他所有异常
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDTO globalException(HttpServletRequest request, Throwable ex) {
        return new ResponseDTO(getStatus(request).value(), ex.getMessage(), null);
    }

    private HttpStatus getStatus(HttpServletRequest request) {
        Integer statusCode = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (statusCode == null) {
            return HttpStatus.INTERNAL_SERVER_ERROR;
        }
        return HttpStatus.valueOf(statusCode);
    }
}
