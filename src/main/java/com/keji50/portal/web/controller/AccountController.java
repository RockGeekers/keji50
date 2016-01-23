package com.keji50.portal.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.keji50.portal.common.utils.WebUtils;
import com.keji50.portal.common.utils.constants.Constants;
import com.keji50.portal.common.utils.constants.ResponseCode;
import com.keji50.portal.dao.po.AccountPo;
import com.keji50.portal.dao.po.AccountValidatePo;
import com.keji50.portal.service.AccountService;
import com.keji50.portal.service.AccountValidateService;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 非登录用户行为Controller
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

    
    
    
    @RequestMapping(value = "/forget", method = RequestMethod.GET)
    public String forget() {
        return "account/forget/forget";
    }
    
    @RequestMapping(value = "/sign_in", method = RequestMethod.GET)
    public String signIn() {
        return "account/signin/signin";
    }
    
    @RequestMapping(value = "/sign_up", method = RequestMethod.GET)
    public String signUp() {
        return "account/signup/signup";
    }
    
    /**
     * ajax 发送验证码
     */
    @RequestMapping(value = "/ajax/send_code", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject ajaxSendCode(HttpServletRequest request) {
        JSONObject response = WebUtils.toFailedResponse();

        // 验证手机号邮箱是否合法
        String username = request.getParameter("username");
        if (StringUtils.isEmpty(username)) {
            response.put(WebUtils.KEY_MESSAGE, "手机号或邮箱不能为空");
            return response;
        }

        String usernameType = getUsernameType(username);
        if (StringUtils.equals("-1", usernameType)) {
            response.put(WebUtils.KEY_MESSAGE, "手机号或邮箱不正确");
            return response;
        }

        // 发送验证短信或者验证邮件
        AccountValidatePo po = accountValidateService.sendValidateCode(username, usernameType, request.getRemoteAddr());
        if (po.getId() <= 0) {
            return WebUtils.toFailedResponse();
        }

        return WebUtils.toResponse(po.getId(), request);
    }

    /**
     * ajax 用户注册
     */
    @RequestMapping(value = "/ajax/sign_up", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject ajaxSignUp(HttpServletRequest request) {
        JSONObject response = WebUtils.toFailedResponse();
        // 手机号或邮箱不为空
        String username = request.getParameter("username");
        if (StringUtils.isEmpty(username)) {
            response.put(WebUtils.KEY_MESSAGE, "用户名不能为空");
            return response;
        }

        String usernameType = getUsernameType(username);
        if (StringUtils.equals("-1", usernameType)) {
            response.put(WebUtils.KEY_MESSAGE, "用户名不是手机号或邮箱");
            return response;
        }

        // 手机号或邮箱不能被占用
        if (accountService.isUsernameExist(username, usernameType)) {
            response.put(WebUtils.KEY_MESSAGE, "该手机号或邮箱已经注册");
            return response;
        }

        // 校验码是否正确
        String verifyId = request.getParameter("verifyId");
        String verifyCode = request.getParameter("verifyCode");
        if (StringUtils.isEmpty(verifyId) || StringUtils.isEmpty(verifyCode) || !accountValidateService.doValidate(Integer.parseInt(verifyId), verifyCode)) {
            response.put(WebUtils.KEY_MESSAGE, "验证码验证失败");
            return response;
        }

        // 密码和校验密码是否一致
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        if (StringUtils.isEmpty(password) || StringUtils.isEmpty(confirmPassword) || !StringUtils.equals(password, confirmPassword)) {
            response.put(WebUtils.KEY_MESSAGE, "密码输入不一致");
            return response;
        }

        // 用户注册， 并将注册好的客户加入session
        AccountPo account = accountService.register(username, usernameType, password);
        if (account != null) {
            request.getSession().setAttribute("loginUser", account);
            response = WebUtils.toResponse(ResponseCode.SUCCEED);
        }

        return response;
    }

    /**
     * ajax 用户注册
     */
    @RequestMapping(value = "/ajax/sign_in", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject ajaxSignIn(HttpServletRequest request) {
        JSONObject response = WebUtils.toFailedResponse();

        // 验证用户名密码是否合法
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            response.put(WebUtils.KEY_MESSAGE, "用户名密码不能为空");
            return response;
        }

        String usernameType = getUsernameType(username);
        if (StringUtils.equals("-1", usernameType)) {
            response.put(WebUtils.KEY_MESSAGE, "用户名不是手机号或邮箱");
            return response;
        }

        // 验证用户名密码是否正确
        AccountPo account = accountService.login(username, usernameType, password);
        if (account == null) {
            response.put(WebUtils.KEY_MESSAGE, "用户名或密码错误");
            return response;
        }

        // 将用户信息加入session
        request.getSession().setAttribute("loginUser", account);
        return WebUtils.toResponse(ResponseCode.SUCCEED);
    }
    
    /**
     * ajax 用户注销登录
     */
    @RequestMapping(value = "/ajax/sign_out", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject ajaxSignOut(HttpServletRequest request) {
        request.getSession().invalidate();
        return WebUtils.toResponse(ResponseCode.SUCCEED);
    }

    /**
     * ajax 用户忘记密码
     */
    @RequestMapping(value = "/ajax/forget_reset", method = RequestMethod.POST)
    @ResponseBody
    public JSONObject ajaxforgetReset(HttpServletRequest request) {
        JSONObject response = WebUtils.toFailedResponse();

        // 验证用户名是否合法
        String username = request.getParameter("username");
        String newPassword = request.getParameter("password");
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(newPassword)) {
            response.put(WebUtils.KEY_MESSAGE, "用户名密码不能为空");
            return response;
        }
        String usernameType = getUsernameType(username);
        if (StringUtils.equals("-1", usernameType)) {
            response.put(WebUtils.KEY_MESSAGE, "用户名不是手机号或邮箱");
            return response;
        }

        // 验证用户名是否存在
        if (!accountService.isUsernameExist(username, usernameType)) {
            response.put(WebUtils.KEY_MESSAGE, "用户名不存在");
            return response;
        }

        // 校验码是否正确
        String verifyId = request.getParameter("verifyId");
        String verifyCode = request.getParameter("verifyCode");
        if (StringUtils.isEmpty(verifyId) || StringUtils.isEmpty(verifyCode) || !accountValidateService.doValidate(Integer.parseInt(verifyId), verifyCode)) {
            response.put(WebUtils.KEY_MESSAGE, "验证码验证失败");
            return response;
        }

        if (accountService.updateResetPassword(username, usernameType, newPassword)) {
            response = WebUtils.toResponse(ResponseCode.SUCCEED);
        }
        return response;
    }

    

    private String getUsernameType(String username) {
        String type = "-1";
        if (WebUtils.isPhone(username)) {
            type = Constants.VALIDATE_TYPE_PHONE;
        }
        if (WebUtils.isEmail(username)) {
            type = Constants.VALIDATE_TYPE_EMAIL;
        }

        return type;
    }
}
