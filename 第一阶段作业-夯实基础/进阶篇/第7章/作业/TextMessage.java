package moocSuper07;

import java.util.Date;
import java.util.Properties;
 
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class TextMessage {
    private String from;//发件人地址
    private String to;//收件人地址
    private String subject;//标题
    private String body;//正文
	
    public TextMessage(String from, String to, String subject, String body) {
		super();
		this.from = from;
		this.to = to;
		this.subject = subject;
		this.body = body;
	}
    
    public MimeMessage generate()throws Exception{
        Session session=Session.getDefaultInstance(new Properties());
        MimeMessage message=new MimeMessage(session);
        //设置发件人
        message.setFrom(new InternetAddress(from));
        //设置收件人
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        //设置发送日期
        message.setSentDate(new Date());
        //设置邮件主题
        message.setSubject(subject);
        //设置纯文本文件内容的邮件正文
        message.setText(body);
        //保存并生成最终的邮件内容
        message.saveChanges();

        return message;
    }
    
    
}