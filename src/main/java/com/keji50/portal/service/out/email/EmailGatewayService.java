package com.keji50.portal.service.out.email;

import com.keji50.portal.dao.po.AccountValidatePo;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeUtility;
import lombok.Setter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;

public class EmailGatewayService implements DisposableBean {

	private static final Logger log = LoggerFactory.getLogger(EmailGatewayService.class);

	@Setter
	private String mailFrom;

	@Setter
	private JavaMailSenderImpl mailSender;
	
	// 处理线程池， 邮件网关异步发送验证邮件
	private ExecutorService pool = Executors.newFixedThreadPool(10);

	public void sendEmail(final AccountValidatePo po) {
		pool.execute(new Runnable() {
			@Override
			public void run() {
				try {
					MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mailSender.createMimeMessage(), false, "GB2312");
					mimeMessageHelper.setFrom(new InternetAddress(MimeUtility.encodeText("科技50") + " <" + mailFrom + ">"));
					mimeMessageHelper.setTo(po.getUsername());
					mimeMessageHelper.setSubject("科技50-验证邮件");
					mimeMessageHelper.setText(po.getValidateContent(), true);
					
					mailSender.send(mimeMessageHelper.getMimeMessage());
				} catch (Exception e) {
					log.error("failed to send email", e);
				}
			}
		});
	}

	@Override
	public void destroy() throws Exception {
		if (pool != null) {
			pool.shutdown();
		}
	}
}
