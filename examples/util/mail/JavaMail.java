package com.homevip.util.mail;

import java.security.Security;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.homevip.core.util.Global;
import com.homevip.util.RegexUtil;
import com.homevip.util.StringUtil;

/**
 * 群发邮件
 */
public class JavaMail {
	final Log _Log = LogFactory.getLog(this.getClass());
	
	public String emails;
	public String subject;
	public String content;
	public String mailencode="GBK";
	public String attachment;
	
	//改为可动态传入邮箱参数
	public String mail_from="";
	public String mail_user="";
	public String mail_password="";
	public String smtp_server="";
	public String mail_title="";
	
	public int sendmaz=0; //0 正常 1 cc 2 bcc
	
	
	//发送邮件
	public void sendmail() throws Exception {
		Properties props=new Properties();
		
		if(this.mail_from.length()==0) this.mail_from=Global.mail_from;
		if(this.mail_user.length()==0) this.mail_user=Global.mail_user;
		if(this.mail_password.length()==0) this.mail_password=Global.mail_password;
		if(this.smtp_server.length()==0) this.smtp_server=Global.smtp_server;
		if(this.mail_title.length()==0) this.mail_title="";
		
		//_Log.info(Global.MailSign);
		
	    //验证
	    if(Global.MailSign.equalsIgnoreCase("SSL")){
	    	//Gmail SSL发送
	    	Security.addProvider(new com.sun.net.ssl.internal.ssl.Provider());
	    	final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";

	    	props.put("mail.smtp.host",this.smtp_server);
	    	props.setProperty("mail.smtp.socketFactory.class", SSL_FACTORY);
	    	props.setProperty("mail.smtp.socketFactory.fallback", "false");
	    	props.setProperty("mail.smtp.port","465");
	    	props.setProperty("mail.smtp.socketFactory.port","465");
	    	props.put("mail.smtp.auth","true");
		}else{
			//普通发送
		    props.put("mail.smtp.host",this.smtp_server); 
		    props.put("mail.smtp.auth","true"); 
		}
	    
		SMTPAuth auth = new SMTPAuth(this.mail_user,this.mail_password); //邮件密码		    
	    Session s=Session.getInstance(props,auth); //验证用户密码 
	    
	    //组成邮件内容
	    MimeMessage message=new MimeMessage(s);
	    InternetAddress from=new InternetAddress(this.mail_from, this.mail_title,"GBK");
	    message.setFrom(from);
	    String[] arr_email=StringUtil.splitString(this.emails, ",");
	    InternetAddress[] address=new InternetAddress[arr_email.length]; 
	    for (int i=0;i<arr_email.length;i++){ 
	    	if(RegexUtil.isEmail(arr_email[i]))
	    		address[i]=new InternetAddress(arr_email[i]);
	    }
	    if(this.sendmaz==0)
	    	message.setRecipients(Message.RecipientType.TO,address);
	    else if(this.sendmaz==1)
	    	message.setRecipients(Message.RecipientType.CC,address);
	    else
	    	//发送电子报时采用密送方式
	    	message.setRecipients(Message.RecipientType.BCC,address);
	    message.setSubject(this.subject,this.mailencode);
	    //内容
	    Multipart mp = new MimeMultipart("alternative");  
        MimeBodyPart mbp = new MimeBodyPart();
        this.content="<div style='line-height:30px'>"+this.content+"</div>";
        mbp.setContent(this.content, "text/html;charset="+this.mailencode); 
        mp.addBodyPart(mbp);
        
        //Multipart加入到信件 
        try{
		    if(this.attachment!=null && this.attachment.length()>0){ 
	            mbp = new MimeBodyPart();  
	            FileDataSource fds=new FileDataSource(this.attachment); //得到数据源  
	            mbp.setDataHandler(new DataHandler(fds)); //得到附件本身并至入BodyPart  
	            mbp.setFileName(fds.getName());  //得到文件名同样至入BodyPart  
	            mp.addBodyPart(mbp); 
		    }
        }catch(Exception e){
	    	System.out.println(e.toString());
	    }
	    message.setContent(mp); //Multipart加入到信件 
	    
	    Transport transport=s.getTransport("smtp");
	    transport.connect(this.smtp_server,this.mail_user,this.mail_password);
	    transport.sendMessage(message,message.getAllRecipients());
	    transport.close();    
	}	

}

class SMTPAuth extends javax.mail.Authenticator{   
	private String user,password;   
	public SMTPAuth(String u,String p){   
		user=u;   
		password=p;   
	}   
	public void getuserinfo(String getuser,String getpassword)   {   
		user = getuser;   
		password= getpassword;   
	}   
	protected   javax.mail.PasswordAuthentication   getPasswordAuthentication()   {   
     	return   new   javax.mail.PasswordAuthentication(user,   password);   
	}   
}	
