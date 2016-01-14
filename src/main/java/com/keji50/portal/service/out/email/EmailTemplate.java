package com.keji50.portal.service.out.email;

import lombok.Getter;

public enum EmailTemplate {
	
	
	VALIDATION_TEMPLATE("0", "亲爱的科技50用户， 您好。<br> 您的验证码是 %s <br> 此邮件由系统发出， 请勿回复"),
	RESET_PASSWORD_TEMPLATE("1", "点击下面链接重置密码，48小时有效，链接只能使用一次，请尽快使用！<a href='%s' target='_blank'>点击重置</a>"),
	UNBIND_TEMPLATE("2","点击下面链接解绑邮箱，48小时有效，链接只能使用一次，请尽快解绑！<a href='%s' target='_blank'>点击解绑</a>");

	@Getter
	private String type;

	@Getter
	private String template;
	
	private EmailTemplate(String type, String template) {
		this.type = type;
		this.template = template;
	}
	
	public static String getTemplate(String type) {
		for (EmailTemplate emailTemplate : values()) {
			if (emailTemplate.getType().equals(type)) {
				return emailTemplate.getTemplate();
			}
		}

		return "";
	}
}
