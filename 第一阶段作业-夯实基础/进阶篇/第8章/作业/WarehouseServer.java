package moocSuper08;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
 
public class WarehouseServer {
 
	public static void main(String[] args) throws Exception {
		System.out.println("��������������");
		WarehouseImpl centralWarehouse=new WarehouseImpl();
		
		System.out.println("���������������8001�˿ڣ������ṩ����");
		LocateRegistry.createRegistry(8001);//����˿�
		Naming.rebind("rmi://127.0.0.1:8001/warehouse1", centralWarehouse);
		System.out.println("�ȴ��ͻ������ӡ���");
		
	}
 
}