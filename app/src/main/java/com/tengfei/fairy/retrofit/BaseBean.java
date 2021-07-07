package com.tengfei.fairy.retrofit;

import java.io.Serializable;

/**
 * @ Description :
 * @ Author 李腾飞
 * @ Time 2020-04-08   16:26
 * @ Version :
 */
public class BaseBean<T> implements Serializable {
    public String code;
    public String msg;
    //    public String cmd;
//    public String fail;
    public boolean success;
    public T data;

}