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
    	props.put("mail.smtp.host", smtpServer);//���÷����ʼ�������
    	props.put("mail.smtp,port","25");
    	props.put("mail.smtp.auth", "true");//SMTP��������Ҫ�����֤
    	
    	session=Session.getInstance(props,new Authenticator() {	 //��֤�˻�
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
    	System.out.println("�ʼ��Ѿ����ͳɹ�");
    }
    public void close()throws Exception{
    	transport.close();
    }
    
}