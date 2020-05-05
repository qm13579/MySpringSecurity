package cn.people.utils.common;

/**
 * @author apple
 */

public enum ResultCode {
    SUCCESS(true,10000,"success"),
    FAIL(false,9999,"fail"),
    ERROR(false,9998,"error");

    boolean success;
    int code;
    String message;

    ResultCode(boolean success, int code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
