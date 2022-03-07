package com.sff.rbacdemo.common.exceptions;

/**
 * @author Frankie Fan
 * @date 2022-03-05 18:38
 */

public class UnauthorizedException extends RuntimeException {
    public UnauthorizedException(String msg) {
        super(msg);
    }

    public UnauthorizedException() {
        super();
    }
}
