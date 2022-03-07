package com.sff.rbacdemo.common.exceptions;

/**
 * @author Frankie Fan
 * @date 2022-03-06 18:36
 */

public class BaseException extends RuntimeException{
    private static final long serialVersionUID = 1L;

    public BaseException(String message){
        super(message);
    }

    public BaseException(Throwable cause)
    {
        super(cause);
    }

    public BaseException(String message,Throwable cause)
    {
        super(message,cause);
    }
}
