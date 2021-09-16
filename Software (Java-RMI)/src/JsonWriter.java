import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.File;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class JsonWriter {

    public JsonWriter(){
    }

    public void SaveDiffResourcesTimes (List<ResourceNodeModel> nodeList, ArrayList<ResourcesTimeModel> resourcesTimeModels, String fileName) {
        try {
            JSONArray node = new JSONArray();
            JSONArray link = new JSONArray();
            for (ResourceNodeModel nodeModel:nodeList) {
                    JSONObject arrayObj = new JSONObject();
                    arrayObj.put("name", nodeModel.firstRsrc);
                    arrayObj.put("type", nodeModel.type);
                    arrayObj.put("probability", (double) Math.round(nodeModel.probability*100)/100);
                    arrayObj.put("cacheable", nodeModel.cachedResource);
                    arrayObj.put("id", Integer.parseInt(nodeModel.firstRsrc.split("-")[0]));
                    arrayObj.put("group", 1);
                    node.add(arrayObj);
            }
            for (ResourcesTimeModel resourceModel:resourcesTimeModels) {
                JSONObject arrayObj = new JSONObject();
               // if(resourceModel.diffResourceTime > 0 && resourceModel.diffResourceTime2 > 0){
                    arrayObj.put("source",Integer.parseInt(resourceModel.firstRsrc.split("-")[0]));
                    arrayObj.put("target",Integer.parseInt(resourceModel.secondRsrc.split("-")[0]));
                    String dt = "2020-01-01";  // Start date
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                    Calendar c = Calendar.getInstance();
                    c.setTime(sdf.parse(dt));
                    c.add(Calendar.DATE, 10);  // number of days to add
                    dt = sdf.format(c.getTime());  // dt is now the new date
                    arrayObj.put("event_date",dt);
                    arrayObj.put("value",resourceModel.diffResourceTime);
                    arrayObj.put("secondValue",resourceModel.diffResourceTime2);
                    arrayObj.put("median",resourceModel.median);
                    arrayObj.put("percentileFive",resourceModel.percentil_cinco);
                    arrayObj.put("percentileNinetyFive",resourceModel.percentil_noventaCinco);
                    link.add(arrayObj);
                //}
            }

            JSONObject obj = new JSONObject();
            obj.put("nodes", node);
            obj.put("links",link);
            FileWriter writer = new FileWriter(new File("/Users/cunha/Downloads/webbench/src/main/java/files/" + fileName + "ResourcesTimes.json"));
            writer.write(obj.toJSONString());
            writer.close();
        } catch (Exception e) {
            // e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

}
