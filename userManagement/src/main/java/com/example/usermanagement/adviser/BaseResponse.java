package com.example.usermanagement.adviser;

import lombok.Data;

@Data
public class BaseResponse<T> {
    private int retCode;
    private String retMsg;
    private T data;
    private int dataSize;

    public BaseResponse(){super();}

    public BaseResponse(int retCode, String retMsg){
        super();
        this.retCode = retCode;
        this.retMsg = retMsg;
    }

    public boolean isSuccess(){return 0 == this.getRetCode();}

    /*
        <T>:为表名这个方法为泛型方法。

        BaseResponse<T>:表示返回的类型。
     */
    public static <T> BaseResponse<T> success(T data){
        BaseResponse<T> res = new BaseResponse<>();
        res.setRetCode(ErrorCode.SUCCESS.getCode());
        res.setRetMsg(ErrorCode.SUCCESS.getMsg());
        res.setData(data);
        return res;
    }

    public static BaseResponse<?> error(BaseException e){
        BaseResponse<?> res = new BaseResponse<>();
        res.setRetCode(e.getCode());
        res.setRetMsg(e.getMsg());
        return res;
    }

    public static BaseResponse<?> success(){
        BaseResponse<?> res = new BaseResponse<>();
        res.setRetCode(ErrorCode.SUCCESS.getCode());
        res.setRetMsg(ErrorCode.SUCCESS.getMsg());
        return res;
    }
}
