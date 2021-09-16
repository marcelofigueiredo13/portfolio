import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class SDReducer{
    public static void main(String[] args) {
        Registry r = null;
        try{
            r = LocateRegistry.createRegistry(Integer.parseInt(args[0]));

            Reducer red= new Reducer();

            r.rebind("reducer", red);

            System.out.println("Reducer ready!");

            ObjectRegistryInterface object = (ObjectRegistryInterface) Naming.lookup("rmi://localhost:1023/registry");
            object.addService(String.valueOf(Integer.parseInt(args[0])), "rmi://localhost:" + String.valueOf(Integer.parseInt(args[0])) + "/reducer");

        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            e.printStackTrace();
        }

        System.out.println("Port:" + Integer.parseInt(args[0]));
    }
}
