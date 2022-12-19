package com.lcxbs.exception;

/**
 * 数据源未找到异常
 */
public class DataSourceNotFoundException extends CommonException{
    public DataSourceNotFoundException() {
        super();
    }

    public DataSourceNotFoundException(int code) {
        super(code);
    }

    public DataSourceNotFoundException(int code, String message) {
        super(code, message);
    }

    public DataSourceNotFoundException(int code, String message, Throwable cause) {
        super(code, message, cause);
    }

    public DataSourceNotFoundException(int code, Throwable cause) {
        super(code, cause);
    }
}
