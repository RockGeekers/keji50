package com.keji50.portal.common.utils.constants;

import lombok.Getter;

/**
 * 系统响应返回码枚举
 *
 * @author chao.li
 * @version
 * @since Ver 1.1
 * @Date 2015年12月12日 下午12:56:42
 *
 * @see
 */
public enum ResponseCode {

    // 成功返回码
    SUCCEED("0", "成功"),

    // 系统出错返回码
    FAILED("-1", "系统开小差啦"),
    
    // 手机号或者邮箱已存在
    ALREADY_EXIST("-2", "手机号或邮箱已存在");

    @Getter
    private String code;

    @Getter
    private String message;

    private ResponseCode(String code, String message) {
        this.code = code;
        this.message = message;
    }

}
