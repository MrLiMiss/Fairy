package com.tengfei.fairy.config;
import com.tengfei.fairy.application.FairyApplication;

/**
 * @ Description :配置类
 * @ Author 李腾飞
 * @ Time 2021/6/28   13:46
 * @ Version :
 */
public class Constans {
    //电子签名保存地址
    public static String SIGN_FILE_PATH = FairyApplication.getInstance().getExternalCacheDir().getPath();
    /**
     * inteng跳转flag
     */
    public static String INTENG_FLAG = "intengFlag";
    public static String INTENG_CALLBACK_FLAG = "intengCallBackFlag";
}
