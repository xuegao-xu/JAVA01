package moocSuper06;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.InetAddress;
import java.net.Socket;
 
public class Client1 {
 
	public static void main(String[] args) {
		try {
			Socket s=new Socket(InetAddress.getByName("127.0.0.1"),8001);//需要服务端先开启

			InputStream ips=s.getInputStream();
			BufferedReader brNet=new BufferedReader(new InputStreamReader(ips));
			
			OutputStream ops=s.getOutputStream();
			
			DataOutputStream dos=new DataOutputStream(ops);
			
			String msg="666";
			while(true) {
				Thread.sleep(10000);
			
				dos.writeBytes(msg+System.getProperty("line.separator"));
				System.out.println("Server said:"+brNet.readLine());
			}
 
		}catch(Exception e) {
			e.printStackTrace();
		}
 
	}
 
}