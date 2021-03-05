import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class SDMaster {

    public static void main(String[] args) {

        Registry r = null;
        try{
            r = LocateRegistry.createRegistry(1025);

            Master m = new Master();

            r.rebind("master", m);

            System.out.println("Master ready!");

            ObjectRegistryInterface object = (ObjectRegistryInterface) Naming.lookup("rmi://localhost:1023/registry");
            object.addService(String.valueOf(1025), "rmi://localhost:1025/master");

        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            e.printStackTrace();
        }

        System.out.println("Port:" + 1025);
    }
}