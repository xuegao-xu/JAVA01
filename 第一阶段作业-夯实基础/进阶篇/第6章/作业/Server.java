package moocSuper06;

import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
public class Server{
   public static void main(String[] args) {
	   ThreadPoolExecutor executor=(ThreadPoolExecutor)Executors.newFixedThreadPool(4);
	   List<Socket> slist=new ArrayList<Socket>();
	   int num=0;
	   try { 
		   ServerSocket ss=new ServerSocket(8001);
		   while(true) {
			   Socket s=ss.accept();
			   slist.add(s);
			   System.out.println("来了一个Client");
			   executor.execute(new Worker(s,slist,num));
			   num++;
		   }
	   }catch(Exception e) {
		   e.printStackTrace();
	   }
   }
}