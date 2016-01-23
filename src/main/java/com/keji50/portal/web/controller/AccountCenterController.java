package com.keji50.portal.web.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONObject;
import com.keji50.portal.common.utils.WebUtils;
import com.keji50.portal.common.utils.constants.ResponseCode;
import com.keji50.portal.dao.po.AccountPo;
import com.keji50.portal.service.AccountService;
import com.keji50.portal.service.AccountValidateService;

/**
 * 用户中心 Controller
 * 
 * @author sophia
 * @version
 * @since Ver 1.1
 * @Date 2016年1月23日 下午4:23:56
 * 
 * @see
 */
@Controller
@RequestMapping(value = "/uc")
public class AccountCenterController {
    
    @Resource(name = "accountService")
    private AccountService accountService;

    @Resource(name = "accountValidateService")
    private AccountValidateService accountValidateService;

    @RequestMapping(value = "/home", method = RequestMethod.GET)
    public String home() {
        return "account/home/home";
    }
    
    /**
     * ajax 登录用户修改密码
     */
    @RequestMapping(value = "/ajax/reset", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject ajaxReset(HttpServletRequest request) {
        JSONObject response = WebUtils.toFailedResponse();
        // 原始密码不能为空
        String oldPassword = request.getParameter("oldPassword");
        if (StringUtils.isEmpty(oldPassword)) {
            response.put(WebUtils.KEY_MESSAGE, "旧密码不能为空");
            return response;
        }

        // 密码和校验密码是否一致
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        if (StringUtils.isEmpty(password) || StringUtils.isEmpty(confirmPassword) || !StringUtils.equals(password, confirmPassword)) {
            response.put(WebUtils.KEY_MESSAGE, "密码输入不一致");
            return response;
        }

        // 从session中获取登录用户信息
        AccountPo account = (AccountPo) request.getSession().getAttribute("loginUser");
        // 根据用户id， 原密码， 新密码更新用户密码
        if (accountService.updateResetPassword(account.getId(), oldPassword, password)) {
            response = WebUtils.toResponse(ResponseCode.SUCCEED);
        }
        return response;
    }
}
