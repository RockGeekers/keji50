package com.keji50.portal.service;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

import com.keji50.portal.common.utils.MD5Utils;
import com.keji50.portal.common.utils.constants.Constants;
import com.keji50.portal.dao.mapper.AccountPoMapper;
import com.keji50.portal.dao.po.AccountPo;

/**
 * 客户相关服务
 * 
 * @author sophia
 * @version
 * @since Ver 1.1
 * @Date 2016年1月23日 上午9:40:08
 * 
 * @see
 */
@Service(value = "accountService")
public class AccountService {

    @Resource(name = "accountPoMapper")
    private AccountPoMapper accountPoMapper;

    @Resource(name = "accountValidateService")
    private AccountValidateService accountValidateService;

    /**
     * 用户注册
     * 
     * @param username
     *            注册用户名， 手机号或者邮箱
     * @param type
     *            用户名类型， 0手机号 1邮箱
     * @param password
     *            密码
     * @return AccountPo 注册成功返回的用户对象
     * @throws
     */
    public AccountPo register(String username, String type, String password) {
        AccountPo po = new AccountPo();
        if (StringUtils.equals(type, Constants.VALIDATE_TYPE_PHONE)) {
            po.setPhone(username);
        }
        if (StringUtils.equals(type, Constants.VALIDATE_TYPE_EMAIL)) {
            po.setEmail(username);
        }
        po.setPassword(MD5Utils.md5(password));

        return accountPoMapper.insert(po) == 1 ? po : null;
    }

    /**
     * 用户登录
     * 
     * @param username
     *            登录名， 手机号或者邮箱
     * @param usernameType
     *            登录名类型， 0手机号 1邮箱
     * @param password
     *            登录密码
     * @return AccountPo 登录后的账户信息， 登录失败时返回null
     * @throws
     */
    public AccountPo login(String username, String usernameType, String password) {
        AccountPo account = accountPoMapper.selectByCondition(toCondition(username, usernameType));
        return account != null && StringUtils.equals(account.getPassword(), MD5Utils.md5(password)) ? account : null;
    }

    /**
     * 用户名是否存在
     * 
     * @param username
     *            用户名
     * @param usernameType
     *            用户名类型
     * 
     * @return boolean true存在 false不存在
     * @throws
     */
    public boolean isUsernameExist(String username, String usernameType) {
        return accountPoMapper.count(toCondition(username, usernameType)) > 0;
    }

    /**
     * 用户忘记密码重置
     * 
     * @param username
     *            用户名
     * @param usernameType
     *            用户名类型
     * @param resetPassword
     *            新密码
     * @return boolean true成功 false失败
     * @throws
     */
    public boolean updateResetPassword(String username, String usernameType, String newPassword) {
        return accountPoMapper.updatePasswordByUsername(username, usernameType, MD5Utils.md5(newPassword)) > 0;
    }

    public boolean updateAccount(AccountPo po) {
        return accountPoMapper.update(po) == 1;
    }

    private Map<String, Object> toCondition(String username, String usernameType) {
        Map<String, Object> conditions = new HashMap<String, Object>(1);
        if (StringUtils.equals(usernameType, Constants.VALIDATE_TYPE_PHONE)) {
            conditions.put("phone", username);
        }
        if (StringUtils.equals(usernameType, Constants.VALIDATE_TYPE_EMAIL)) {
            conditions.put("email", username);
        }

        return conditions;
    }

}
