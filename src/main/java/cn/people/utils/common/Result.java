package cn.people.utils.common;

import lombok.Data;

/**
 * @author : FENGZHI
 * create at:  2020/5/5  下午9:18
 * @description:
 */
@Data
public class Result {

    private boolean success;
    private String message;
    private int code;
    private Object data;



    public Result(ResultCode code,Object data) {
        this.success = code.success;
        this.message = code.message;
        this.code = code.code;
        this.data = data;
    }

    public Result(boolean success, String message, int code) {
        this.success = success;
        this.message = message;
        this.code = code;
    }
    public Result(ResultCode code) {
        this.success = code.success;
        this.message = code.message;
        this.code = code.code;
    }
    public static Result SUCCESS(){
        return new Result(ResultCode.SUCCESS);
    }
    public static Result FAIL(){
        return new Result(ResultCode.FAIL);
    }
    public static Result ERROR(){
        return new Result(ResultCode.ERROR);
    }

}
