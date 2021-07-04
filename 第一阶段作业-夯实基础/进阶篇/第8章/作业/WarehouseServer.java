package moocSuper08;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
 
public class WarehouseServer {
 
	public static void main(String[] args) throws Exception {
		System.out.println("产生服务器对象");
		WarehouseImpl centralWarehouse=new WarehouseImpl();
		
		System.out.println("将服务器对象绑定在8001端口，对外提供服务");
		LocateRegistry.createRegistry(8001);//定义端口
		Naming.rebind("rmi://127.0.0.1:8001/warehouse1", centralWarehouse);
		System.out.println("等待客户端连接……");
		
	}
 
}