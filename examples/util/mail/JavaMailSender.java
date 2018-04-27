package com.homevip.util.mail;

import java.util.concurrent.Executor;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * 邮件发送
 * @author PanPan
 */
public class JavaMailSender {
	final String title;
	final String subject;
	final String message;
	final String emails;
	
	final Log _Log = LogFactory.getLog(this.getClass());
	
	/**
	 * 发送邮件线程池，防止发送邮件阻塞
	 */
	Executor m_Executor = new ThreadPoolExecutor(1, 100, 60L, TimeUnit.SECONDS,
			new SynchronousQueue<Runnable>());


	public JavaMailSender(String title, String subject, String message,
			String emails) {
		this.title = title;
		this.subject = subject;
		this.message = message;
		this.emails = emails;
	}

	public void sendMail() throws Exception {
		// 发送邮件
		m_Executor.execute(new Runnable() {
			public void run() {
				try {
					JavaMail mail = new JavaMail();
					mail.mail_title = title;
					mail.emails = emails;
					mail.subject = subject;
					mail.content = message;
					mail.sendmail();
				} catch (Exception e) {
					_Log.error(e);
				}
			}
		});
	}
}
