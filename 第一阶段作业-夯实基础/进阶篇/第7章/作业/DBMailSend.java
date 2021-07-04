package moocSuper07;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
 
public class DBMailSend {
 
	public static void main(String[] args) {
		Connection conn=null;
		String from;
		String to;
		String subject;
		String content;
		String password;
		String smtpServer;
		try {
			conn=DruidFactory.getConnection();
			System.out.println("连接池构建成功");
			
			Statement stmt=conn.createStatement();
			System.out.println("获取连接成功");
			
			ResultSet rs=stmt.executeQuery("select id,mfrom,mto,msubject,content,password,smtpServer from t_mail order by id");
		    System.out.println("获取数据成功");
		    
		    while(rs.next()) {
		    	System.out.println(rs.getInt(1)+","+rs.getString(2)+","+rs.getString(3)+","+rs.getString(4)+","+rs.getString(5)+","+rs.getString(6)+","+rs.getString(7));
		    	
		    	if(rs.getInt(1)==1) {
		    		from=rs.getString(2);
		    		to=rs.getString(3);
		    		subject=rs.getString(4);
		    		content=rs.getString(5);
		    		password=rs.getString(6);
		    		smtpServer=rs.getString(7);
		    		MailClientSend client=new MailClientSend(from,password,smtpServer);
		    		client.init();
		    		client.sendMessage(from, to, subject, content);
		    		System.out.println("发送邮件成功");
		    	}
		    	
		    }
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(null!=null) {
					conn.close();
				}
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
 
	}
 
}