package moocSuper06;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.List;
 
class Worker implements Runnable{
    Socket s;
    List<Socket> slist;
    int num;
    
    public Worker(Socket s,List<Socket> slist,int num) {
    	this.s=s;
    	this.slist=slist;
        this.num=num;    	
    }
	public void run() {
		try {
			System.out.println("服务已经启动");
			InputStream ips=s.getInputStream();
			BufferedReader br=new BufferedReader(new InputStreamReader(ips));
			
			
			while(true) {
				String strWord=br.readLine();
				System.out.println("No."+num+" client said:"+strWord+":"+strWord.length());
				if(strWord.equals("quit")) {
					break;
				}
				if(strWord!=null) {
				for(int i=0;i<slist.size();i++) {
					Socket sc=slist.get(i);
					OutputStream ops=sc.getOutputStream();
					DataOutputStream dos=new DataOutputStream(ops);
					dos.writeBytes("No."+num+" client said"+strWord+"---->"+System.getProperty("line.separator"));//向所有客户端发送消息
				}
				}	
				num++;
			}
			br.close();

			s.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}
    
}