import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class SDRegistry {
    public static void main(String[] args) {
        Registry r = null;
        try{
            r = LocateRegistry.createRegistry(1023);
        }catch(RemoteException a){
            a.printStackTrace();
        }

        try{
            ObjectRegistry object = new ObjectRegistry();
            r.rebind("registry", object );

            System.out.println("Object server ready");
        }catch(Exception e) {
            System.out.println("Object server main" + e.getMessage());
        }
        System.out.println("Port:" + 1023);
    }
}