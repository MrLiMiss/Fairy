package com.tengfei.fairy.retrofit;

/**
 * @ Description :
 * @ Author 李腾飞
 * @ Time 2020-11-17   10:41
 * @ Version :
 */

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * @ Description :埋点request
 * @ Author 李腾飞
 * @ Time 2020-04-16   11:24
 * @ Version :
 */
public class OnEventRequest  extends BaseReq implements Serializable {


    @SerializedName("EventList")
    public List<EventBean> eventList;
//    public  static List<EventBean> eventList;

    public static class EventBean implements Serializable{
        @SerializedName("is_login_id")
        public Boolean is_login_id;
        @SerializedName("distinct_id")
        public String distinct_id;
        @SerializedName("original_id")
        public String original_id;
        @SerializedName("_datetime")
        public long _datetime;
        @SerializedName("type")
        public String type;
        @SerializedName("event_code")
        public String event_code;

        @SerializedName("properties")
        public PropertiesBean properties;

        public static class PropertiesBean implements Serializable{
            @SerializedName("_element_name")
            public String _element_name;
            @SerializedName("_element_target_url")
            public String _element_target_url;
            @SerializedName("_title")
            public String _title;
            @SerializedName("_url")
            public String _url;
            @SerializedName("_ip")
            public String _ip;
            @SerializedName("_network_type")
            public String _network_type;
            @SerializedName("_app_name")
            public String _app_name;
            @SerializedName("_app_version")
            public String _app_version;
            @SerializedName("_lib")
            public String _lib;
            @SerializedName("_lib_version")
            public String _lib_version;
            @SerializedName("_os")
            public String _os;
            @SerializedName("_os_version")
            public String _os_version;
            @SerializedName("_model")
            public String _model;
            @SerializedName("_geo")
            public String _geo;
            @SerializedName("_carrier")
            public String _carrier;

        }



    }







}

