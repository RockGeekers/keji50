package com.keji50.portal.dao.po;

import com.alibaba.druid.util.StringUtils;
import com.keji50.portal.common.utils.constants.Constants;
import com.keji50.portal.service.out.email.EmailTemplate;
import com.keji50.portal.service.out.sms.SmsTemplate;

import java.util.Date;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 客户手机邮箱认证PO
 * 
 * @author chao.li
 * @version
 * @since Ver 1.1
 * @Date 2016年1月14日 上午8:46:14
 * 
 * @see
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AccountValidatePo extends BasePo {

    public AccountValidatePo() {

    }

    public AccountValidatePo(String object, String type) {
        this.object = object;
        this.type = type;
    }

    /**
     * 主键id
     */
    private int id;

    /**
     * 验证类型 0：手机号 ，1：邮箱
     */
    private String type;

    /**
     * 验证对象， 手机号或者邮箱
     */
    private String object;

    /**
     * 验证码
     */
    private String code;

    /**
     * 过期时间
     */
    private Date expire;

    /**
     * 客户ip
     */
    private String ip;

    public String getValidateContent() {
        // 短信验证内容
        if (StringUtils.equals(type, Constants.VALIDATE_TYPE_PHONE)) {
            return String.format(SmsTemplate.VALIDATION_TEMPLATE.getTemplate(), code);
        }

        // 邮件验证内容
        if (StringUtils.equals(type, Constants.VALIDATE_TYPE_EMAIL)) {
            return String.format(EmailTemplate.VALIDATION_TEMPLATE.getTemplate(), code);
        }

        return "";
    }
}
