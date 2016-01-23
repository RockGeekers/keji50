package com.keji50.portal.web.controller;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.keji50.portal.common.utils.WebUtils;
import com.keji50.portal.common.utils.constants.Constants;
import com.keji50.portal.common.utils.constants.ResponseCode;
import com.keji50.portal.dao.po.AccountValidatePo;
import com.keji50.portal.service.AccountService;
import com.keji50.portal.service.AccountValidateService;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * ClassName:AccountController Function: TODO ADD FUNCTION Reason: TODO ADD
 * REASON
 * 
 * @author sophia
 * @version
 * @since Ver 1.1
 * @Date 2016年1月23日 上午10:01:01
 * 
 * @see
 */
@Controller
@RequestMapping(value = "/account")
public class AccountController {

    @Resource(name = "accountService")
    private AccountService accountService;

    @Resource(name = "accountValidateService")
    private AccountValidateService accountValidateService;

    @RequestMapping(value = "/sendcode", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject ajaxSendCode(HttpServletRequest request) {
        String object = request.getParameter("object");
        String type = request.getParameter("type");
        if (StringUtils.isEmpty(object) || StringUtils.isEmpty(type)) {
            return WebUtils.toFailedResponse();
        }
        // 当前验证对象是否被占用
        Map<String, Object> conditions = new HashMap<String, Object>(1);
        if (StringUtils.equals(type, Constants.VALIDATE_TYPE_PHONE)) {
            conditions.put("phone", object);
        }
        if (StringUtils.equals(type, Constants.VALIDATE_TYPE_EMAIL)) {
            conditions.put("email", object);
        }
        if (accountService.countAccount(conditions) > 0) {
            return WebUtils.toFailedResponse(ResponseCode.ALREADY_EXIST);
        }

        // 发送验证短信或者验证邮件
        AccountValidatePo po = accountValidateService.sendValidateCode(object, type, request.getRemoteAddr());
        if (po.getId() <= 0) {
            return WebUtils.toFailedResponse();
        }

        return WebUtils.toResponse(po.getId(), request);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject ajaxInfosByCategory(@PathVariable("id") int infocategoryId, HttpServletRequest request) {
        return null;
    }
}
