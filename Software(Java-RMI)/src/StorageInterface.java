import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.*;

public interface StorageInterface extends Remote {
    void SubmitFile(String path, String filename, byte[] data, int len) throws RemoteException;
    void SetNumberOfFiles(Integer n) throws RemoteException;
    void PrintTimeHarMap() throws RemoteException;
    LinkedHashMap<String, ArrayList<ResourceInfo>> GetTimeHarMap() throws RemoteException;
    Integer GetFileNumber() throws RemoteException;
    void SaveCombinationsPerReducer(HashMap<String, Set> CPR) throws RemoteException;
    Set GetCombinations(String servico) throws RemoteException;
    void SaveCombinationsStatistics(LinkedList<ProcessCombinationModel> cs, String url) throws RemoteException;
    Integer GetProcessed() throws RemoteException;
    void PrintStatistics() throws RemoteException;
    LinkedList<ProcessCombinationModel> GetStatistcs() throws RemoteException;
}
