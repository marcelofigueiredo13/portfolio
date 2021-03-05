import java.io.File;
import java.rmi.Remote;
import java.rmi.RemoteException;

public interface MasterInterface extends Remote {
    void FilesLoaded() throws RemoteException;
    File GetResults() throws RemoteException;
}