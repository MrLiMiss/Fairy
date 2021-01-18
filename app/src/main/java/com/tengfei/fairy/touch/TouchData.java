package com.tengfei.fairy.touch;


import android.content.Context;
import android.os.Build;
import android.util.Log;
import androidx.annotation.RequiresApi;

import com.bjrxtd.sdk.LogConsumer;
import com.bjrxtd.sdk.Touch;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 数据 埋点
 */
@RequiresApi(api = Build.VERSION_CODES.N)
public class TouchData {
    private static TouchData instance;
    private Touch touch = null;
    public Context context = null;
    public static String distinctId;

    public TouchData(Context context) {
        this.context = context;
    }

    public static TouchData getInstance(Context context) {
        if (instance == null) {
            synchronized (TouchData.class) {
                if (instance == null) {
                    instance = new TouchData(context);
                }
            }
        }
        return instance;
    }


    /**
     * 获取 测试 Touch（Touch为单例）
     *
     * @return
     */
    public Touch getTouch() {
        if (touch == null) {
            Properties touchProp = new Properties();
            touchProp.setProperty(Touch.TOUCH_SDK_APP_URL, "http://130.1.10.158:8788/data/encryption");
            touchProp.setProperty(Touch.TOUCH_SDK_APP_KEY, "default");
            touchProp.setProperty(Touch.TOUCH_SDK_APP_SECRET, "1qweasd!");
            touchProp.setProperty(Touch.TOUCH_SDK_APP_ENABLE, Boolean.TRUE.toString());
//            touchProp.setProperty(Touch.TOUCH_SDK_APP_ENABLE, Boolean.FALSE.toString());
            touchProp.setProperty(Touch.TOUCH_SDK_PUBLIC_APP_NAME, "手机银行");
            touchProp.setProperty(Touch.TOUCH_SDK_PUBLIC_APP_VERSION, "1.0.1");
//             发送规则：10s发送一次（不足10个也发送），每次发送十个。
            touchProp.setProperty(Touch.TOUCH_SDK_APP_QUEUE_MAX_SIZE, "100");//内存队列长度
            touchProp.setProperty(Touch.TOUCH_SDK_APP_PACK_TASK_NUM, "10");//每次发送默认条数
            touchProp.setProperty(Touch.TOUCH_SDK_APP_ADD_STEP_NUM, "10");//发送失败后，追加的步长值，默认10个
            touchProp.setProperty(Touch.TOUCH_SDK_APP_SEND_INTERVAL, "10000");//发送时间间隔 10000ms
          Touch.init((Map) touchProp, new LogConsumer().setCallBackFunction(new LogConsumer.CallBack() {
              @Override
              public void log(String msg) {
                  Log.e("TouchData",msg);
              }
          }));
            touch = Touch.getInstance();
        }
        return touch;
    }




    /**
     * 注册事件 公共 属性
     *
     * •//公共事件属性
     *     "_app_name":"手机银行",//项目应用名称
     *     "_app_version":"1.0.1",//项目应用版本
     *     "_lib":"andriod",
     *     "_lib_version":"1.0",//埋点SDK版本
     *     "_os":"andriod",//操作系统
     *     "_os_version":"andriod 8",//操作系统
     *     "_model":"huawei p30 pro",//手机型号
     *     "_geo":"120.33143,34.56985",//坐标
     *     "_carrier":"中国联通",//运营商
     *     "_device_id"："123123123123" 设备唯一标识符
     * @param properties
     */
    public void trackRegister(CommonProperties properties) {
        if (touch == null) {
            Log.e("TouchData-Register", "track==null ！");
        } else {
            Map<String, Object> superPropertiesMap = new HashMap<>();
            //预置属性（Track中也有预置属性，则以track中为准）
            superPropertiesMap.put("_ip", null);
            superPropertiesMap.put("_network_type", properties.get_network_type());
            //公共属性
            superPropertiesMap.put("_app_name", properties.get_app_name());
            superPropertiesMap.put("_app_version", properties.get_app_version());
            superPropertiesMap.put("_carrier", properties.get_carrier());
            superPropertiesMap.put("_lib", properties.get_lib());
            superPropertiesMap.put("_lib_version", properties.get_lib_version());
            superPropertiesMap.put("_model", properties.get_model());
            superPropertiesMap.put("_os", properties.get_os());
            superPropertiesMap.put("_os_version", properties.get_os_version());
            superPropertiesMap.put("_geo", properties.get_geo());
            superPropertiesMap.put("_device_id", properties.get_device_id());
            touch.registerSuperProperties(superPropertiesMap);
        }

    }

    /**
     * 点击事件埋点
     * @param distinctId          isLoginId为false时，distinct_id代表的是匿名标示，true时distinct_id代表的是登录账号
     * @param isLoginId           是否登录
     * @param _element_name        点击元素name
     * @param _element_target_url  点击元素链接
     * @param _title               点击时页面title
     * @param _url                 点击时页面url
     */
    public void trackClick(String distinctId, boolean isLoginId, String _element_name, String _element_target_url, String _title, String _url) {

        Map<String, Object> properties = new HashMap<>();
//        long timeStamp = new Date().getTime();
        //事件属性
        properties.put("_datetime", new Date());
        properties.put("is_login_id", isLoginId);
        properties.put("_element_name",_element_name );//元素名称
        properties.put("_element_target_url", _element_target_url);//元素链接地址
        properties.put("_title",_title);//页面标题
        properties.put("_url", _url);//页面地址
        //事件预置属性
        properties.put("_ip","1.1.1.1");
        properties.put("_network_type","wifi");
        if (touch == null) {
            Log.e("TouchData-trackClick ", "track==null ！");
        } else {
            touch.track(distinctId, isLoginId, "_APPClick", properties);
        }

    }

    /**
     *  浏览事件埋点
     * @param distinctId          isLoginId为false时，distinct_id代表的是匿名标示，true时distinct_id代表的是登录账号
     * @param isLoginId           是否登录
     * @param _title               点击时页面title
     * @param _url                 点击时页面url
     */
    public void trackView(String distinctId, boolean isLoginId, String _title, String _url) {

        Map<String, Object> properties = new HashMap<>();
//        long timeStamp = new Date().getTime();
        properties.put("_datetime", new Date());
        properties.put("is_login_id", isLoginId);
        properties.put("_title",_title);//页面标题
        properties.put("_url", _url);//页面地址
        //事件预置属性
        properties.put("_ip","1.1.1.1");
        properties.put("_network_type","wifi");
        if (touch == null) {
            Log.e("TouchData-trackView ", "track==null ！");
        } else {
            touch.track(distinctId, isLoginId, "_APPView", properties);
        }

    }


    /**
     * 启动退出事件埋点
     * @param distinctId          isLoginId为false时，distinct_id代表的是匿名标示，true时distinct_id代表的是登录账号
     * @param isLoginId           是否登录
     * @param eventCode   _AppStart/_AppEnd（启动/退出）
     */
    public void trackEvent(String distinctId, boolean isLoginId, String eventCode) {

        Map<String, Object> properties = new HashMap<>();
//        long timeStamp = new Date().getTime();
        properties.put("_datetime", new Date());
        properties.put("is_login_id", isLoginId);
        //事件预置属性
        properties.put("_ip","1.1.1.1");
        properties.put("_network_type","wifi");
        if (touch == null) {
            Log.e("TouchData-trackEvent ", "track==null ！");
        } else {
            touch.track(distinctId, isLoginId, eventCode, properties);
        }

    }




    /**
     * 用户登录绑定
     *
     * @param loginId     客户身份证号MD5 值
     * @param anonymousId 客户标识符
     */
    public void trackSiginUp(String loginId, String anonymousId) {
        Map<String, Object> properties = new HashMap<>();
        long timeStamp = new Date().getTime();
//        properties.put("_datetime", timeStamp);
        properties.put("_datetime",  new Date());
        //事件预置属性
        properties.put("_ip","1.1.1.1");
        properties.put("_network_type","wifi");
        if (touch == null) {
            Log.e("TouchData-trackSiginUp ", "track==null ！");
        } else {
            touch.trackSignUp(loginId, anonymousId, properties);
        }


    }
}
