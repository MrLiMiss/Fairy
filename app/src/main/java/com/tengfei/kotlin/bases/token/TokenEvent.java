package com.tengfei.kotlin.bases.token;

/**
 * Created by wangxiulong on 16/11/3.
 */

public class TokenEvent {

    int code;
    String msg;

    public TokenEvent(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }
}
