package com.keji50.portal.service;

import com.keji50.portal.common.utils.constants.Constants;
import com.keji50.portal.dao.mapper.AccountValidatePoMapper;
import com.keji50.portal.dao.po.AccountValidatePo;
import com.keji50.portal.service.out.email.EmailGatewayService;
import com.keji50.portal.service.out.sms.SmsGatewayService;
import java.util.Calendar;
import java.util.Date;
import javax.annotation.Resource;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * 用户手机邮箱验证服务
 * 
 * @author sophia
 * @version
 * @since Ver 1.1
 * @Date 2016年1月22日 下午10:41:35
 * 
 * @see
 */
@Service(value = "accountValidateService")
public class AccountValidateService {

    @Resource(name = "accountValidatePoMapper")
    private AccountValidatePoMapper accountValidatePoMapper;

    @Resource(name = "smsGatewayService")
    private SmsGatewayService smsGatewayService;

    @Resource(name = "emailGatewayService")
    private EmailGatewayService emailGatewayService;

    /**
     * 发送验证码
     * 
     * @param object
     *            验证实体， 手机号或者邮箱
     * @param type
     *            验证类型 0手机号 1邮箱
     * @param ip
     *            客户端ip地址
     * 
     * @return AccountValidatePo 验证对象， 可获取其id作为验证的标示
     * @throws
     */
    public AccountValidatePo sendValidateCode(String object, String type, String ip) {
        AccountValidatePo po = new AccountValidatePo(object, type);
        po.setCode(getValidateCode());
        po.setExpire(getValidateExpire(Calendar.MINUTE, 3)); // 有效时间三分钟
        po.setIp(ip);

        // 插入验证记录到数据表， 如插入成功， 调用短信网关发送验证短信/调用邮件网关发送邮件
        int count = accountValidatePoMapper.insert(po);
        if (count > 0) {
            // 异步发送验证码
            if (StringUtils.equals(type, Constants.VALIDATE_TYPE_PHONE)) {
                smsGatewayService.sendSms(po);
            }

            if (StringUtils.equals(type, Constants.VALIDATE_TYPE_EMAIL)) {
                emailGatewayService.sendEmail(po);
            }
        }
        return po;
    }

    /**
     * 验证码验证
     * 
     * @param id
     *            验证码唯一id
     * @param code
     *            验证码
     * @return boolean 验证是否通过
     * @throws
     */
    public boolean doValidate(int id, String code) {
        AccountValidatePo po = accountValidatePoMapper.selectById(id);

        // 短信验证是否存在 && 短信验证码是否过期 && 短信验证码是否正确
        return po != null && new Date().compareTo(po.getExpire()) <= 0 && StringUtils.equals(po.getCode(), code);
    }

    /**
     * 获取4位随机数字验证码
     */
    private String getValidateCode() {
        int random = (int) (Math.random() * 9999);
        return StringUtils.leftPad(String.valueOf(random), 4, '0');
    }

    /**
     * 获取验证码有效时间
     */
    private Date getValidateExpire(int field, int amount) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(field, amount);
        return calendar.getTime();
    }

}
