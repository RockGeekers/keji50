package com.keji50.portal.dao.po;

import com.keji50.portal.common.utils.constants.Constants;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang.StringUtils;

/**
 * 客户信息对象
 * 
 * @author chao.li
 * @version
 * @since Ver 1.1
 * @Date 2015年12月8日 下午3:50:54
 * 
 * @see
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class AccountPo extends BasePo {

    /**
     * 用户id
     */
    private int id;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 密码
     */
    private String password;

    /**
     * 真实姓名
     */
    private String realname;

    /**
     * 用户昵称
     */
    private String nickname;

    /**
     * 用户头像
     */
    private String image;

    /**
     * QQ
     */
    private String qq;

    /**
     * 微博
     */
    private String weibo;

    /**
     * 微信
     */
    private String wechat;

    /**
     * 用户状态
     */
    private int status;

    public String getImage() {
        return StringUtils.isEmpty(image) ? Constants.DEFAULT_ACCOUNT_IMAGE : image;
    }

    public String getNickname() {
        if (StringUtils.isNotEmpty(realname)) {
            return realname;
        }

        if (StringUtils.isNotEmpty(nickname)) {
            return nickname;
        }

        return String.valueOf(id);
    }
}
