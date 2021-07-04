package moocSuper08;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;
 
public class WarehouseImpl extends UnicastRemoteObject implements Warehouse{
    String cmd1="javac HelloWorld";
    String cmd2="java HelloWorld";
	protected WarehouseImpl() throws RemoteException {
		super();
		
	}
 
	public List<String> cmdExec(String cmd) throws RemoteException {
		List<String> result=new ArrayList<String>();
		if(cmd1.equals(cmd)) {
			Process p;
			String[] cmds=new String[2];
			cmds[0]="javac";
			cmds[1]="D:/temp/HelloWord.java";
			
			try {			
				p=Runtime.getRuntime().exec(cmds);		
				InputStream fis=p.getInputStream();	
				InputStreamReader isr=new InputStreamReader(fis);	
				BufferedReader br=new BufferedReader(isr);
				String line=null;
	
				while((line=br.readLine())!=null) {
					result.add(line);
				}
				System.out.println("");
				int exitVal=p.waitFor(); //获取进程最后返回状态
				System.out.println("Process exitValue: "+exitVal);
			}catch(Exception e) {
				e.printStackTrace();
			}
			result.add("编译成功");
			return result;
		}else if(cmd2.equals(cmd)){
			Process p;
			String[] cmds=new String[2];
			cmds[0]="java";
			cmds[1]="HelloWorld";
			
			try {	
				p=Runtime.getRuntime().exec(cmds,null,new File("D:/temp"));					
				InputStream fis=p.getInputStream();				
				InputStreamReader isr=new InputStreamReader(fis);				
				BufferedReader br=new BufferedReader(isr);
				String line=null;
			
				while((line=br.readLine())!=null) {
					result.add(line);
					System.out.println(line);
				}
				System.out.println("");
				int exitVal=p.waitFor(); //获取进程最后返回状态
				System.out.println("Process exitValue: "+exitVal);
			}catch(Exception e) {
				e.printStackTrace();
			}
			result.add("执行成功");
			return result;
 
		}else {
			Process p;
			
			
			try {				
				p=Runtime.getRuntime().exec(cmd);				
				InputStream fis=p.getInputStream();			
				InputStreamReader isr=new InputStreamReader(fis);			
				BufferedReader br=new BufferedReader(isr);
				String line=null;
			
				while((line=br.readLine())!=null) {
					result.add(line);
				}
				System.out.println("");
				int exitVal=p.waitFor(); //获取进程最后返回状态
				System.out.println("Process exitValue: "+exitVal);
			}catch(Exception e) {
				e.printStackTrace();
			}
			return result;
		}
		
	}
 
}