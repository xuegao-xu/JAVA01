package moocSuper07;

import java.util.Date;
import java.util.Properties;
 
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class TextMessage {
    private String from;//�����˵�ַ
    private String to;//�ռ��˵�ַ
    private String subject;//����
    private String body;//����
	
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
        //���÷�����
        message.setFrom(new InternetAddress(from));
        //�����ռ���
        message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
        //���÷�������
        message.setSentDate(new Date());
        //�����ʼ�����
        message.setSubject(subject);
        //���ô��ı��ļ����ݵ��ʼ�����
        message.setText(body);
        //���沢�������յ��ʼ�����
        message.saveChanges();

        return message;
    }
    
    
}