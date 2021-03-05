import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface MapperInterface extends Remote {
    void SendReducersList(List<String> rList) throws RemoteException;
    void PrintTimeHarMap() throws RemoteException;
}