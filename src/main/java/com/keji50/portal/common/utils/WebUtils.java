package com.keji50.portal.common.utils;

import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import com.alibaba.fastjson.JSONObject;
import com.keji50.portal.common.utils.constants.ResponseCode;

/**
 * web 请求帮助类
 * 
 * @author chao.li
 * @version
 * @since Ver 1.1
 * @Date 2015年12月12日 下午1:02:33
 * 
 * @see
 */
public class WebUtils {

    public static final String KEY_CODE = "code";

    public static final String KEY_MESSAGE = "message";

    private static final String KEY_DATA = "data";

    private static final String KEY_CONTEXT_PATH = "contextpath";

    private static JSONObject RESPONSE_FAILED;
    private static JSONObject RESPONSE_SUCCEED;
    
    private static final Pattern PHONE_PATTERN = Pattern.compile("^1[3|4|5|7|8]\\d{9}$");
    
    private static final Pattern EMAIL_PATTERN = Pattern.compile("^(\\w)+(\\.\\w+)*@(\\w)+((\\.\\w+)+)$");

    static {
        RESPONSE_FAILED = new JSONObject();
        RESPONSE_FAILED.put(KEY_CODE, ResponseCode.FAILED.getCode());
        RESPONSE_FAILED.put(KEY_MESSAGE, ResponseCode.FAILED.getMessage());

        RESPONSE_SUCCEED = new JSONObject();
        RESPONSE_SUCCEED.put(KEY_CODE, ResponseCode.SUCCEED.getCode());
        RESPONSE_SUCCEED.put(KEY_MESSAGE, ResponseCode.SUCCEED.getMessage());
    }

    private WebUtils() {

    }

    public static JSONObject toFailedResponse() {
        return RESPONSE_FAILED;
    }

    public static JSONObject toResponse(ResponseCode responseCode) {
        JSONObject json = new JSONObject();
        json.put(KEY_CODE, responseCode.getCode());
        json.put(KEY_MESSAGE, responseCode.getMessage());
        return json;
    }

    public static JSONObject toResponse(Object data, HttpServletRequest request) {
        JSONObject succeed = (JSONObject) RESPONSE_SUCCEED.clone();
        succeed.put(KEY_DATA, data);
        succeed.put(KEY_CONTEXT_PATH, request.getContextPath());
        return succeed;
    }
    
    public static boolean isPhone(String phone) {
        return PHONE_PATTERN.matcher(phone).find();
    }
    
    public static boolean isEmail(String email) {
        return EMAIL_PATTERN.matcher(email).find();
    }
}
