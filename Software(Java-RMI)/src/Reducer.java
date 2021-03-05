import java.io.Serializable;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.*;

public class Reducer implements ReducerInterface, Serializable {

    private static String URL;

    private static Set<Set<String>> combinations = new HashSet<>();

    private static LinkedHashMap<String, ArrayList<ResourceInfo>> tHM = new LinkedHashMap<>();

    private LinkedList<ProcessCombinationModel> combinationStatistics = new LinkedList<>();

    private static Integer nFiles = 0;

    private Integer i = 0;

    public void StartTask(String url){
        System.out.println("Reducer: Starting task");
        URL = url;
        GetData();
        StartProcessing();
        StoreCombinationStatistics();
    }

    public void GetData(){
        System.out.println("Reducer: Getting stored data (number of files, combinations and timeharmap)");
        try {
            ObjectRegistryInterface ori = (ObjectRegistryInterface) Naming.lookup("rmi://localhost:1023/registry");

            StorageInterface storage = (StorageInterface) Naming.lookup(ori.resolve(String.valueOf(1024)));

            combinations = (storage.GetCombinations(URL));
            tHM = (storage.GetTimeHarMap());
            nFiles = (storage.GetFileNumber());
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            e.printStackTrace();
        }
    }

    public void StartProcessing(){
        System.out.println("Reducer: Start processing combinations");
        Set r;
        StringBuffer line = new StringBuffer();
        Iterator combIterator = combinations.iterator();
        int i = 0;
        while (combIterator.hasNext()){
            r = (Set) combIterator.next();

            line.setLength(0);
            Iterator lineIterator = r.iterator();
            while(lineIterator.hasNext()){
                if(line.length()>0) line.append(",");
                line.append(lineIterator.next().toString());
            }

            ProcessCombinationModel combinationInfo= new ProcessCombinationModel();
            combinationInfo.combination = line.toString();
            calculateStatistics(combinationInfo, nFiles/*line.length()*/);
            //if(i++%100000==0) System.out.println("Comb " + i);
        }

    }

    private void calculateStatistics(ProcessCombinationModel combinationInfo, int fileCount){
        boolean resourceFound=false;

        String[] resources = combinationInfo.combination.split(","); // resources of each combination
        for (int i =0; i < fileCount; i++) { //controlo por run
            for(String combinationResource : resources){
                resourceFound=false;
                for(ResourceInfo comb : tHM.get(combinationResource))
                    if(comb.harRun == i) {
                        resourceFound = true;
                        combinationInfo.resourceLength += comb.resourceLength;
                        break;
                    }
                if(!resourceFound){ break;}
            }
            if(resourceFound){
                combinationInfo.numberOfRuns++;
            }
        }

        combinationInfo.percentage = (float) combinationInfo.numberOfRuns/fileCount;

        //combinationInfo.percentage = combinationInfo.percentage*100;

        //System.out.println(combinationInfo.percentage);

        if(combinationInfo.percentage > 0.01/*0.5*/) {
            //System.out.print("Combination + probability");

            //for(String s: resources) System.out.print(System.identityHashCode(s) + "  ");
            //System.out.print(combinationInfo.percentage + "\n");

            combinationStatistics.add(combinationInfo);
            //System.out.println(combinationInfo);
            //System.out.println("Comb valida. Percentagem: " + combinationInfo.percentage);
        }
    }

    public void StoreCombinationStatistics(){
        System.out.println("Reducer: Storing combination statistics");
        try {
            ObjectRegistryInterface ori = (ObjectRegistryInterface) Naming.lookup("rmi://localhost:1023/registry");

            StorageInterface storage = (StorageInterface) Naming.lookup(ori.resolve(String.valueOf(1024)));

            storage.SaveCombinationsStatistics(combinationStatistics, URL);

        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            e.printStackTrace();
        }
    }
}
