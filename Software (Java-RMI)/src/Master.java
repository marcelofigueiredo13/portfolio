import java.io.File;
import java.io.PrintWriter;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.LinkedList;
import java.util.List;

public class Master implements MasterInterface, Serializable {

    private static LinkedList<ProcessCombinationModel> results = new LinkedList<>();

    public void FilesLoaded(){
        System.out.println("Master: Files loaded, called by the Client");
        SendReducersList();
    }

    public void SendReducersList(){
        System.out.println("Master: Reducers list sent");

        List<String> reducers;
        List<String> mappers;

        try {
            ObjectRegistryInterface ori = (ObjectRegistryInterface) Naming.lookup("rmi://localhost:1023/registry");

            mappers = ori.ReturnURLs("mapper");
            reducers = ori.ReturnURLs("reducer");

            for(int i=0; i < mappers.size(); ++i){

                int finalI = i;
                Thread t = new Thread()
                {
                    public void run() {
                        MapperInterface mapper = null;
                        try {
                            mapper = (MapperInterface) Naming.lookup(mappers.get(finalI));
                            mapper.SendReducersList(reducers);
                        } catch (NotBoundException | MalformedURLException | RemoteException e) {
                            e.printStackTrace();
                        }
                    }
                };
                t.run();
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            e.printStackTrace();
        }
    }

    public File GetResults(){
        System.out.println("Master: Checking if combinations have been processed");
        try {
            ObjectRegistryInterface ori = (ObjectRegistryInterface) Naming.lookup("rmi://localhost:1023/registry");

            StorageInterface storage = (StorageInterface) Naming.lookup(ori.resolve(String.valueOf(1024)));

            System.out.println(storage.GetProcessed());

            if(storage.GetProcessed() >= ori.ReturnN("reducers")){
                System.out.println("Master: Returning results to client");
                results = storage.GetStatistcs();
                File f = SaveResourcesCombinationsProbabilities(results, "Cliente.csv");
                return f;
            }
            else
                System.out.println("O processamento ainda não foi realizado!");

        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public File SaveResourcesCombinationsProbabilities (LinkedList<ProcessCombinationModel> resourcesCombinationList, String fileName){
        File f = new File(fileName);
        try {
            PrintWriter writer = new PrintWriter(f);
            StringBuilder sb = new StringBuilder();
            sb.append("Combination");
            sb.append(';');
            sb.append("Probability");
            sb.append(';');
            sb.append("CombinationLength");
            sb.append('\n');
            for(ProcessCombinationModel combinationInfo : resourcesCombinationList){
                sb.append(combinationInfo.combination + ";");
                sb.append(combinationInfo.percentage + ";");
                sb.append(combinationInfo.resourceLength + ";");
                sb.append('\n');
            }
            writer.write(sb.toString());
            writer.close();
        } catch (Exception e) {
            //e.printStackTrace();
            System.out.println(e.getMessage());
        }
        return f;
    }
}