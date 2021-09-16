import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ReducerInterface extends Remote {
    void StartTask(String url) throws RemoteException;
}
