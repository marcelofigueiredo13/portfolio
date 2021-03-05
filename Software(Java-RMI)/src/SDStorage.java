import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class SDStorage{
    public static void main(String[] args){

        Registry r = null;

        try{
            r = LocateRegistry.createRegistry(1024);

            Storage s = new Storage();

            r.rebind("storage", s);

            System.out.println("Storage ready!");

            ObjectRegistryInterface object = (ObjectRegistryInterface) Naming.lookup("rmi://localhost:1023/registry");
            object.addService(String.valueOf(1024), "rmi://localhost:1024/storage");

        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            e.printStackTrace();
        }

        System.out.println("Port:" + 1024);
    }
}