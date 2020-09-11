package com.tengfei.fairy.constant;

/**
 * @ Description :
 * @ Author 李腾飞
 * @ Time 2020-08-04   10:22
 * @ Version :
 */
public class Constants {

    public static Boolean isNeedLog=false;//是否需要日志
    public static String appkey="";//项目ID
    public static String appsecret="";//
    public static String packageName="";
    public static String appVersion="";
    public static String appName="";
    public static String ip="";
    public static String locatLatitude="";
    public static String locatLongitude="";
    public static String CITYNAME="";
    public static String ADDRESS="";
    public static Boolean isGPS=false;//gps 是否开启
    public static String TEXTDATA="textdata";//测试请求cmd
    public static String ENCRYPTION="encryption";//加密请求cmd
    public static String USERID="";//用户登录后标识符（哈行为身份证号）
    public static String distinct_id="";//用户登录后标识符（哈行为身份证号）
    public static Boolean is_login_id;//用户是否为登录状态
    public static long timeDifference;//时钟差值
    public static String altitude;//GPS 经度
    public static String latitude;//GPS 经度
    public static String trackView="";//GPS 经度

    public static Boolean isLogined=false;//是否是登录状态




    public static final class Code {
        //未知来源权限requestCode
        public static final int GET_UNKNOW_APP_SOURCE = 0x01;
        public static final int REQUEST_CODE_SELECT_CONTRACT_NO = 0x02;
        public static final int PERMISSION_CAMERA_REQUEST_CODE = 0x03;
        public static final int PERMISSION_SETING_CODE = 0x04;
        public static final int FACE_PAGE_AGAIN_CODE = 0x05;
        public static final int SELECT_TYPE_UPLOAD_PIC = 0x06;
        public static final int PERMISSION_CALL_PHONE = 0x07;
        public static final int PERMISSION_LOCATION = 0x08;
        //小微企业续贷
        public static final String BUSINESS_LOAN_CODE = "016";
        //征信查询 组织机构代码
        public static final String CREDIT_ORGANIZATION_CODE = "30";
        //征信查询 工商注册号
        public static final String CREDIT_BUSINESS_REGISTER = "01";
        //征信查询 统一社会信用代码
        public static final String CREDIT_SOCIAL_CODE = "20";
        //个人征信查询，身份证
        public static final String CREDIT_PERSONAL_CERT_ID_CODE = "10";

        /**
         * 客户选项
         */
        public static final String OPTION_CUSTOMER="CustomerConfig.json";
        /**
         * 业务类型
         */
        public static final String OPTION_BUSSINESS="HandleBusiConfig.json";


    }

    public static class SPKey {
        public static final String TOKEN = "token";
        public static final String USER_NAME = "user_name";
        public static final String AES_DYNAMICKEY = "aes_dynamickey";
    }

    //公共事件属性
    /**
     * •   "_app_name":"手机银行",//项目应用名称
     *     "_app_version":"1.0.1",//项目应用版本
     *     "_lib":"andriod",
     *     "_lib_version":"1.0",//埋点SDK版本
     *     "_os":"andriod",//操作系统
     *     "_os_version":"andriod 8",//操作系统
     *     "_model":"huawei p30 pro",//手机型号
     *     "_geo":"120.33143,34.56985",//坐标
     *     "_carrier":"中国联通",//运营商
     */

    public static String _app_name="";
    public static String _app_version="";
    public static String _lib="";
    public static String _lib_version="";
    public static String _os="";
    public static String _os_version="";
    public static String _model="";
    public static String _geo="";
    public static String _carrier="";




    public static String os="android";
    public static String version="1.0";//20200707-12:27

}

