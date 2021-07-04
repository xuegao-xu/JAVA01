package moocSuper07;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
 
public class MailClientSend {
    private Session session;
    private Transport transport;
    private String username;
    private String password;
    private String smtpServer;
	
    public MailClientSend(String username, String password, String smtpServer) {
		super();
		this.username = username;
		this.password = password;
		this.smtpServer = smtpServer;
	}
    
    public void init()throws Exception{
    	Properties props=new Properties();
    	props.put("mail.transport.protocol", "smtp");
    	props.put("mail.smtp.class", "com.sun.mail.smtp.SMTPTransport");
    	props.put("mail.smtp.host", smtpServer);//设置发送邮件服务器
    	props.put("mail.smtp,port","25");
    	props.put("mail.smtp.auth", "true");//SMTP服务器需要身份验证
    	
    	session=Session.getInstance(props,new Authenticator() {	 //验证账户
    		public PasswordAuthentication getPasswordAuthentication() {
    			return new PasswordAuthentication(username,password);
    		}
    	});
    	
    	transport=session.getTransport();
    	
    }
    public void sendMessage(String from, String to, String subject, String body)throws Exception{
    	TextMessage tmsg=new TextMessage(from,to,subject,body);
    	
    	Message msg=tmsg.generate();
    	
    	transport.connect();
    	transport.sendMessage(msg, msg.getAllRecipients());
    	System.out.println("邮件已经发送成功");
    }
    public void close()throws Exception{
    	transport.close();
    }
    
}