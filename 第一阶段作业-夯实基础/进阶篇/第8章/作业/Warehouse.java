package moocSuper08;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;
 
public interface Warehouse extends Remote{
    List<String> cmdExec(String cmd)throws RemoteException;
}