package com.keji50.portal.service;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.keji50.portal.common.utils.MD5Utils;
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

    public AccountPo register(AccountPo account) {
        return accountPoMapper.insert(account) == 1 ? account : null;
    }

    public AccountPo login(String username, String password) {
        return accountPoMapper.selectByPassword(username, MD5Utils.md5(password));
    }

    public boolean updateAccount(AccountPo po) {
        return accountPoMapper.update(po) == 1;
    }
    
    public int countAccount(Map<String, Object> conditions) {
        return accountPoMapper.count(conditions);
    }

}
