import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.*;

public class Mapper implements MapperInterface, Serializable {

    private static List<String> reducers = new ArrayList<>();

    private static LinkedHashMap<String, ArrayList<ResourceInfo>> timeHarMap = new LinkedHashMap<>();

    private Set<Set<String>> reducer1 = new HashSet<>();
    private Set<Set<String>> reducer2 = new HashSet<>();
    private Set<Set<String>> reducer3 = new HashSet<>();

    private List<Set<Set<String>>> setList = new ArrayList<>();

    private static HashMap<String, Set> combinationsPerReducer = new HashMap<>();

    public void SendReducersList(List<String> rList){
        System.out.println("Mapper: Reducers list received");
        reducers = rList;
        GetTimeHarMap();
        SplitCombinations();
        StoreCombinationsPerReducer();
        StartProcessingCombinations();
    }

    public void GetTimeHarMap(){
        System.out.println("Mapper: TimeHarMap loaded from Storage");

        try {
            ObjectRegistryInterface ori = (ObjectRegistryInterface) Naming.lookup("rmi://localhost:1023/registry");

            StorageInterface storage = (StorageInterface) Naming.lookup(ori.resolve(String.valueOf(1024)));

            timeHarMap = (storage.GetTimeHarMap());

        } catch (RemoteException | NotBoundException | MalformedURLException e) {
            e.printStackTrace();
        }
    }

    public void PrintTimeHarMap(){
        ArrayList<ResourceInfo> array;
        for(String chave: timeHarMap.keySet()){
            String k = chave;
            System.out.println("Chave: " + k);
            array = timeHarMap.get(k);
            for(int i = 0; i < array.size(); ++i){
                System.out.println(array.get(i));
            }
        }
    }

    public Set<Set<String>> GetCombinationsSet(){
        System.out.println("Mapper: Calculating combinations");
        ArrayList<String> resources = new ArrayList<>(timeHarMap.keySet());
        Set<Set<String>> combinations = Sets.combinations(ImmutableSet.copyOf(resources), 2);
        //System.out.println(combinations.toArray().length);
        return combinations;
    }

    public void SplitCombinations(){
        System.out.println("Mapper: Splitting combinations");

        Integer n1 = 0, n2 = 0, n3 = 0;
        Integer size = GetCombinationsSet().size();

        n1 = size / 3;
        n2 = (size - n1) / 2;
        n3 = size - n1 - n2;

        int i = 0;
        for (Set<String> s : GetCombinationsSet()) {
            if(i < n1){
                reducer1.add(s);
                setList.add(reducer1);
            }
            if((i > n1) && (i <= (n1 + n2))){
                reducer2.add(s);
                setList.add(reducer2);
            }
            if((i >= (n1 + n2)) && (i <= (n1 + n2 + n3))){
                reducer3.add(s);
                setList.add(reducer3);
            }
            i++;
        }
        CreateCPRHashMap();
    }

    public void CreateCPRHashMap(){
        System.out.println("Mapper: Creating combinations per reducer hashmap");
        for(int i=0; i < reducers.size(); ++i){
            combinationsPerReducer.put(reducers.get(i), setList.get(i));
            //System.out.println("Reducer " + reducers.get(i) + " Tamanho do set" + setList.get(i).size());
        }
    }

    public void StoreCombinationsPerReducer(){
        System.out.println("Mapper: Storing combinations per reducer");
        try {
            ObjectRegistryInterface ori = (ObjectRegistryInterface) Naming.lookup("rmi://localhost:1023/registry");

            StorageInterface storage = (StorageInterface) Naming.lookup(ori.resolve(String.valueOf(1024)));

            storage.SaveCombinationsPerReducer(combinationsPerReducer);

        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            e.printStackTrace();
        }
    }

    public void StartProcessingCombinations(){
        System.out.println("Mapper: Sending combinations per reducer to Storage");

        for(int i=0; i < reducers.size(); ++i){

            int finalI = i;
            Thread t = new Thread()
            {
                public void run() {
                    ReducerInterface reducer = null;
                    try {
                        reducer = (ReducerInterface) Naming.lookup(reducers.get(finalI));
                        reducer.StartTask(reducers.get(finalI));
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
    }
}
