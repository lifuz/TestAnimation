package com.lifuz.map.utils;

/**
 *
 * 百度地图 定位返回码
 *
 * @author: 李富
 * @email: lifuzz@163.com
 * @time: 2016/5/8 13:29
 */
public class BaiDuLocationError {

    /**
     * GPS定位结果，GPS定位成功。
     */
    public static final int GPS_LOCATION_RESULT = 61;

    /**
     *  无法获取有效定位依据，定位失败，请检查运营商网络或者wifi网络是否正常开启，尝试重新请求定位。
     */
    public static final int LOCATION_ERROR = 62;

    /**
     *  网络异常，没有成功向服务器发起请求，请确认当前测试手机网络是否通畅，尝试重新请求定位。
     */
    public static final int INTERNET_EXCEPTION = 63;

    /**
     *  定位缓存的结果。
     */
    public static final int LOCATION_CACHE = 65;

    /**
     *  离线定位结果。通过requestOfflineLocaiton调用时对应的返回结果。
     */
    public static final int OFFLINE_LOCATION_RESULT = 66;

    /**
     *  离线定位失败。通过requestOfflineLocaiton调用时对应的返回结果。
     */
    public static final int OFFLINE_LOCATION_FAILED = 62;

    /**
     *  网络连接失败时，查找本地离线定位时对应的返回结果。
     */
    public static final int INTERNET_EXCEPTION_OFFLINE_LOCATION_RESULT = 68;

    /**
     *  网络定位结果，网络定位定位成功。
     */
    public static final int INTERNET_LOCATION_RESULT = 161;

    /**
     *  请求串密文解析失败。
     */
    public static final int REQUEST_ANALYSIS_FAILED = 162;

    /**
     *  服务端定位失败，请您检查是否禁用获取位置信息权限，尝试重新请求定位。
     */
    public static final int SERVER_LOCATION_FAILED = 167;

    /**
     *  key参数错误，请按照说明文档重新申请KEY。
     */
    public static final int KEY_PARAMETER_FAILED = 502;

    /**
     * key不存在或者非法，请按照说明文档重新申请KEY。
     */
    public static final int KEY_NOT_EXIST = 505;

    /**
     *  key服务被开发者自己禁用，请按照说明文档重新申请KEY。
     */
    public static final int KEY_DISABLED = 601;

    /**
     *  key mcode不匹配，您的ak配置过程中安全码设置有问题，请确保：sha1正确，“;”分号是英文状态；
     *  且包名是您当前运行应用的包名，请按照说明文档重新申请KEY。
     */
    public static final int KEY_NO_MATCH = 602;




}
