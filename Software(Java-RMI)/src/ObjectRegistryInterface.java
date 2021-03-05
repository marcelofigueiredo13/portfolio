import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

public interface ObjectRegistryInterface extends Remote{
    void addService(String objectID, String serverAddress) throws RemoteException;
    String resolve(String objectID) throws RemoteException;
    List<String> ReturnURLs(String servico) throws RemoteException;
    Integer ReturnN(String servico) throws RemoteException;
}