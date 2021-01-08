package com.tengfei.fairy.touch;

/**
 * @ Description :公共properties 属性
 * @ Author 李腾飞
 * @ Time 2021/1/8   11:08
 * @ Version :
 */
public class CommonProperties {

    private static CommonProperties instance;
    private String _app_name;
    private String _app_version;
    private String _carrier;
    private String _lib;
    private String _lib_version;
    private String _ip;
    private String _model;
    private String _os;
    private String _os_version;
    private String _geo;
    private String _network_type;
    private String _device_id;

    private CommonProperties() {
    }

    public static CommonProperties getInstance() {
        if (instance == null) {
            synchronized (CommonProperties.class) {
                if (instance == null) {
                    instance = new CommonProperties();
                }
            }
        }
        return instance;
    }



    public String get_app_name() {
        return _app_name;
    }

    public void set_app_name(String _app_name) {
        this._app_name = _app_name;
    }

    public String get_app_version() {
        return _app_version;
    }

    public void set_app_version(String _app_version) {
        this._app_version = _app_version;
    }

    public String get_carrier() {
        return _carrier;
    }

    public void set_carrier(String _carrier) {
        this._carrier = _carrier;
    }

    public String get_lib() {
        return _lib;
    }

    public void set_lib(String _lib) {
        this._lib = _lib;
    }

    public String get_lib_version() {
        return _lib_version;
    }

    public void set_lib_version(String _lib_version) {
        this._lib_version = _lib_version;
    }

    public String get_ip() {
        return _ip;
    }

    public void set_ip(String _ip) {
        this._ip = _ip;
    }

    public String get_model() {
        return _model;
    }

    public void set_model(String _model) {
        this._model = _model;
    }

    public String get_os() {
        return _os;
    }

    public void set_os(String _os) {
        this._os = _os;
    }

    public String get_os_version() {
        return _os_version;
    }

    public void set_os_version(String _os_version) {
        this._os_version = _os_version;
    }

    public String get_geo() {
        return _geo;
    }

    public void set_geo(String _geo) {
        this._geo = _geo;
    }

    public String get_network_type() {
        return _network_type;
    }

    public void set_network_type(String _network_type) {
        this._network_type = _network_type;
    }

    public String get_device_id() {
        return _device_id;
    }

    public void set_device_id(String _device_id) {
        this._device_id = _device_id;
    }


}
