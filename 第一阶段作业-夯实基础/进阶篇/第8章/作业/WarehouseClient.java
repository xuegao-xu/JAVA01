package moocSuper08;

import java.rmi.RemoteException;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import java.util.Scanner;
 
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NameClassPair;
import javax.naming.NamingException;

public class WarehouseClient {
 
	public static void main(String[] args)throws NamingException,RemoteException{
		Context namingContext=new InitialContext();
        
		System.out.print("RMI 注册表绑定列表：");
		Enumeration<NameClassPair>e=namingContext.list("rmi://127.0.0.1:8001/");
		while(e.hasMoreElements()) {
			System.out.println(e.nextElement().getName());
		}

		String url="rmi://127.0.0.1:8001/warehouse1";
		Warehouse centralWarehouse=(Warehouse) namingContext.lookup(url);
		
		Scanner in=new Scanner(System.in);
		System.out.println("Enter keywords:");
		String kewords=in.nextLine();
		List<String> result=centralWarehouse.cmdExec(kewords);
		for(String res:result) {
			System.out.println(res);
		}
		in.close();
		
	}
 
}