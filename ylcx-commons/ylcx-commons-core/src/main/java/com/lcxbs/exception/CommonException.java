package com.lcxbs.exception;

/**
 * 自定义异常（支持异常编码）
 */
public class CommonException extends  RuntimeException {

    private int code=0;

    public CommonException() {
        super();
    }

    public CommonException(int code) {
        super();
        this.code=code;
    }

    public CommonException(int code,String message) {
        super(message);
        this.code=code;
    }

    public CommonException(int code,String message, Throwable cause) {
        super(message, cause);
        this.code=code;
    }

    public CommonException(int code,Throwable cause) {
        super(cause);
        this.code=code;
    }



    /**
     * 异常编码
     * @return
     */
    public int getCode() {
        return code;
    }

    /**
     * 异常编码
     * @param code
     */
    public void setCode(int code) {
        this.code = code;
    }
}
