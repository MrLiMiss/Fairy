package com.tengfei.fairy.touch;


import android.content.Context;
import android.os.Build;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.RequiresApi;

import com.bjrxtd.sdk.Touch;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

@RequiresApi(api = Build.VERSION_CODES.N)
public class TouchUtils {
    private Touch touch = null;
    public Context context = null;

    public TouchUtils(Context context) {
        this.context = context;
    }


    /**
     * 获取 测试 Touch
     *
     * @return
     */
    public Touch getDebugTouch() {
        if (touch == null) {
            Properties touchProp = new Properties();
            touchProp.setProperty(Touch.TOUCH_SDK_APP_URL, "http://130.1.10.158:8788/data/encryption");
            touchProp.setProperty(Touch.TOUCH_SDK_APP_KEY, "default");
            touchProp.setProperty(Touch.TOUCH_SDK_APP_SECRET, "1qweasd!");
            touchProp.setProperty(Touch.TOUCH_SDK_APP_ENABLE, Boolean.TRUE.toString());
            touchProp.setProperty(Touch.TOUCH_SDK_PUBLIC_APP_NAME, "手机银行");
            touchProp.setProperty(Touch.TOUCH_SDK_PUBLIC_APP_VERSION, "1.0.1");
            Touch.init((Map) touchProp, msg -> Log.e("TouchInit", msg));
            touch = Touch.getInstance();
        }
        return touch;
    }

    /**
     * 获取 Release  Touch
     *
     * @return
     */
    public Touch getRelaeseTouch() {
        if (touch == null) {
            Properties touchProp = new Properties();
            touchProp.setProperty(Touch.TOUCH_SDK_APP_URL, "https://touch.hrbb.com.cn:443/data/encryption");
            touchProp.setProperty(Touch.TOUCH_SDK_APP_KEY, "mpAndroid");
            touchProp.setProperty(Touch.TOUCH_SDK_APP_SECRET, "QLr5jJn!85j7!");
            touchProp.setProperty(Touch.TOUCH_SDK_APP_ENABLE, Boolean.TRUE.toString());
            touchProp.setProperty(Touch.TOUCH_SDK_PUBLIC_APP_NAME, "手机银行");
            touchProp.setProperty(Touch.TOUCH_SDK_PUBLIC_APP_VERSION, "1.0.1");
            Touch.init((Map) touchProp, msg -> Log.e("TouchInit", msg));
            touch = Touch.getInstance();
        }
        return touch;
    }

    /**
     * 注册事件 公共 属性
     *
     * @param properties
     */
    public void register(CommonProperties properties) {
        if (touch == null) {
            Toast.makeText(context, "register:touch==null !", Toast.LENGTH_SHORT);
        } else {
            Map<String, Object> superPropertiesMap = new HashMap<>();
            superPropertiesMap.put("_app_name", properties.get_app_name());
            superPropertiesMap.put("_app_version", properties.get_app_version());
            superPropertiesMap.put("_carrier", properties.get_carrier());
            superPropertiesMap.put("_lib", properties.get_lib());
            superPropertiesMap.put("_lib_version", properties.get_lib_version());
            superPropertiesMap.put("_ip", properties.get_ip());
            superPropertiesMap.put("_model", properties.get_model());
            superPropertiesMap.put("_os", properties.get_os());
            superPropertiesMap.put("_os_version", properties.get_os_version());
            superPropertiesMap.put("_geo", properties.get_geo());
            superPropertiesMap.put("_network_type", properties.get_network_type());
            superPropertiesMap.put("_device_id", properties.get_device_id());
            touch.registerSuperProperties(superPropertiesMap);
        }

    }


    /**
     * 事件埋点
     *
     * @param distinctId 标识符
     * @param isLoginId  是否登录
     * @param eventCode  eventCode  "_AppView/_AppClick/_AppSignUp(trackSiginUp中已定义)"
     * @param properties 事件properties属性
     */
    public void track(String distinctId, boolean isLoginId, String eventCode, HashMap<String, Object> properties) {


        if (touch == null) {
            Toast.makeText(context, "track :touch==null !", Toast.LENGTH_SHORT);
        } else {
            touch.track(distinctId, isLoginId, eventCode, properties);
        }

    }

    /**
     * 用户登录绑定
     *
     * @param loginId     客户身份证号MD5 值
     * @param anonymousId 客户标识符
     * @param properties  事件properties属性
     */
    public void trackSiginUp(String loginId, String anonymousId, Map<String, Object> properties) {
        if (touch == null) {
            Toast.makeText(context, "trackSiginUp :touch==null !", Toast.LENGTH_SHORT);
        } else {
            touch.trackSignUp(loginId, anonymousId, properties);
        }


    }
}
