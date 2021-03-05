import harreader.HarReader;
import harreader.HarReaderException;
import harreader.model.Har;
import harreader.model.HarEntry;
import java.io.*;
import java.util.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class Storage implements StorageInterface, Serializable{

    private static HashMap<String, String> paths = new HashMap<>();

    private static LinkedHashMap<String, ArrayList<ResourceInfo>> tHM = new LinkedHashMap<>();

    private static Integer nFicheiros = 0;

    private static Integer i = 0;

    private static HashMap<String, Set> combinationsPerReducer = new HashMap<>();

    private static LinkedList<ProcessCombinationModel> combinationStatistics = new LinkedList<>();

    private static List<String> finishedReducers = new ArrayList<>();

    private static Integer processed = 0;

    public void SubmitFile(String path, String filename, byte[] data, int len) {
        try{
            File f= new File(filename);
            FileOutputStream out = new FileOutputStream(f,true);
            out.write(data,0,len);
            out.flush();
            out.close();

            if(paths.containsKey(filename)==false)
            {
                paths.put(filename, path);
                i++;
            }

            if(i.equals(nFicheiros)){
                System.out.println("Storage: Files loaded");
                CreateTimeHarMap();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void SetNumberOfFiles(Integer n){
        System.out.println("Storage: Saving number of files");
        nFicheiros = n;
    }

    public Integer GetFileNumber(){
        System.out.println("Storage: Returning number of files");
        return nFicheiros;
    }

    public void CreateTimeHarMap(){
        System.out.println("Storage: TimeHarMap created");
        for(String key: paths.keySet())
        {
            try {
                FillResourcesMap(paths.get(key), key, tHM);
            } catch (HarReaderException e) {
                e.printStackTrace();
            }
        }
    }

    public void PrintTimeHarMap() {
        ArrayList<ResourceInfo> array;
        for(String chave: tHM.keySet()){
            String k = chave;
            System.out.println("Chave: " + k);
            array = tHM.get(k);
            for(int i = 0; i < array.size(); ++i){
                System.out.println(array.get(i));
            }
        }
    }

    public LinkedHashMap<String, ArrayList<ResourceInfo>> GetTimeHarMap(){
        System.out.println("Storage: Returning TimeHarMap");
        return tHM;
    }

    public void SaveCombinationsPerReducer(HashMap<String, Set> CPR){
        System.out.println("Storage: Saving the combinations per reducer hashmap");
        combinationsPerReducer = CPR;
        /*for(String s: combinationsPerReducer.keySet()){
            System.out.println(s);
            System.out.println(combinationsPerReducer.get(s).size());
        }*/
    }

    public Set GetCombinations(String servico){
        System.out.println("Storage: Returning combinations");
        //System.out.println(servico);
        for(String s: combinationsPerReducer.keySet()){
            if(s == servico){
                //System.out.println(combinationsPerReducer.get(s));
                return combinationsPerReducer.get(s);
            }
        }
        return null;
    }

    public void SaveCombinationsStatistics(LinkedList<ProcessCombinationModel> cs, String url){
        System.out.println("Storage: Saving combinations statistics");
        if(!finishedReducers.contains(url))
        {
            finishedReducers.add(url);
            combinationStatistics.addAll(cs);
            //System.out.println("Storage size: " + combinationStatistics.size());
            processed++;
        }
    }

    public Integer GetProcessed(){
        System.out.println("Storage: Returning control variable");
        return  processed;
    }

    public LinkedList<ProcessCombinationModel> GetStatistcs(){
        System.out.println("Storage: Returning processed statistcs");
        return combinationStatistics;
    }

    public void PrintStatistics(){
        System.out.println("Storage: Printing combinations statistcs");
        for(int i=0; i < combinationStatistics.size(); ++i){
            System.out.println("Percentagem: " + combinationStatistics.get(i).percentage);
            System.out.println("Combinação: " + combinationStatistics.get(i).combination);
            System.out.println("N runs: " + combinationStatistics.get(i).numberOfRuns);
            System.out.println("Tamanho: " + combinationStatistics.get(i).resourceLength);
        }
    }

    public int FillResourcesMap(String path, String fileName, LinkedHashMap<String, ArrayList<ResourceInfo>> timeHarMap) throws HarReaderException {
        int[] count = new int[]{0};
        int fileCount = 0;
        HarReader harReader = new HarReader();
        File file = new File(path + fileName);
        while (file.exists()){
            Har otherHar = harReader.readFromFile(file);
            for (HarEntry otherEntry : otherHar.getLog().getEntries()) {
                if (!otherEntry.getResponse().getHeaders().get(0).getValue().contains("no-cache")) {
                    ResourceInfo resourceInfo = new ResourceInfo();
                    resourceInfo.resourceTime = (float) otherEntry.getTime();
                    resourceInfo.resourceType = otherEntry.get_resourceType();
                    resourceInfo.cachedResource = otherEntry.getResponse().getHeaders().get(0).getValue();
                    resourceInfo.resourceLength = otherEntry.getResponse().getBodySize();
                    resourceInfo.harRun = fileCount;

                    if (timeHarMap.containsKey(otherEntry.getRequest().getUrl())) {
                        ArrayList<ResourceInfo> list = timeHarMap.get(otherEntry.getRequest().getUrl());
                        AtomicBoolean repeatedCall = new AtomicBoolean(false);
                        list.forEach(value -> {
                            if (value.resourceTime.equals(resourceInfo.resourceTime)) {
                                repeatedCall.set(true);
                                return;
                            }
                        });
                        if (!repeatedCall.get())
                            timeHarMap.get(otherEntry.getRequest().getUrl()).add(resourceInfo);
                    } else {
                        ArrayList<ResourceInfo> l = new ArrayList<>();
                        l.add(resourceInfo);
                        timeHarMap.put(otherEntry.getRequest().getUrl(), l);
                    }
                }
            }
            file = new File(path + fileName + "_" + ++fileCount +".har");
        }
        return fileCount;
    }
}