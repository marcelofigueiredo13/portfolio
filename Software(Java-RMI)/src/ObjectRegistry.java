import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ObjectRegistry extends UnicastRemoteObject implements ObjectRegistryInterface {
    private static final long serialVersionUID = 1L;
    private static HashMap<String, String> objects = new HashMap<String, String>();

    protected ObjectRegistry() throws RemoteException {
        super();
    }

    @Override
    public void addService(String objectID, String serverAddress) {
        objects.put(objectID, serverAddress);
    }

    @Override
    public String resolve(String objectID) {
        if (objects.containsKey(objectID)) {
            return objects.get(objectID);
        }
        return null;
    }

    public List<String> ReturnURLs(String servico){
        List<String> l = new ArrayList<>();

        for(String chave: objects.keySet()){
            String k = chave;
            if(objects.get(k).contains(servico)){
                l.add(objects.get(k));
            }
        }
        return l;
    }

    public Integer ReturnN(String servico){
        Integer i = 0;
        for(String chave: objects.keySet()){
            String k = chave;
            if(objects.get(k).contains(servico)){
                i++;
            }
        }
        return i;
    }

}