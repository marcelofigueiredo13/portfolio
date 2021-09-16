import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class SDClient {
    private static File results;

    public static void main(String [] args) {
        StartServices();
        SendFiles("C:/Users/ma99/Desktop/ESTGV/3º ano/SD/Projeto/Ficheiros/");
        SubmitTask();
        GetResults();
    }

    public static void StartServices()
    {
        Integer port = 1026;
        Thread t = new Thread()
        {
          public void run(){
              String s;

              try{
                  SDRegistry.main(new String[0]);

                  SDStorage.main(new String[0]);

                  for(int i = 0; i < 2; ++i){
                      s = String.valueOf(port+i);
                      SDMapper.main(new String[]{s});
                  }

                  for(int i = 2; i < 5; ++i){
                      s = String.valueOf(port+i);
                      SDReducer.main(new String[]{s});
                  }

                  SDMaster.main(new String[0]);

              } catch (Exception e) {
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

    public static void SendFiles(String path) {
        try {
            ObjectRegistryInterface ori = (ObjectRegistryInterface) Naming.lookup("rmi://localhost:1023/registry");

            StorageInterface storage = (StorageInterface) Naming.lookup(ori.resolve(String.valueOf(1024)));

            File folder = new File(path);
            File[] list = folder.listFiles();

            storage.SetNumberOfFiles(list.length - 1);

            for(File file : list){
                if(file.isFile()){
                    File f = new File(folder + file.getName());
                    FileInputStream in=new FileInputStream(file);
                    byte [] mydata = new byte[1024*1024];
                    int mylen = in.read(mydata);

                    storage.SubmitFile(path, file.getName(), mydata, mylen);
                }
            }
            //storage.PrintTimeHarMap();
        } catch (NotBoundException | IOException e) {
            e.printStackTrace();
        }
    }

    public static void SubmitTask(){
        try {
            ObjectRegistryInterface ori = (ObjectRegistryInterface) Naming.lookup("rmi://localhost:1023/registry");

            MasterInterface master = (MasterInterface) Naming.lookup(ori.resolve(String.valueOf(1025)));

            master.FilesLoaded();
        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            e.printStackTrace();
        }
    }

    public static void GetResults(){
        try {
            ObjectRegistryInterface ori = (ObjectRegistryInterface) Naming.lookup("rmi://localhost:1023/registry");

            MasterInterface master = (MasterInterface) Naming.lookup(ori.resolve(String.valueOf(1025)));

            results = master.GetResults();

        } catch (NotBoundException | MalformedURLException | RemoteException e) {
            e.printStackTrace();
        }
    }

}
