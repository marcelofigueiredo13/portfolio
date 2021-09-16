import exceptions.InvalidServerReplicaLocationException;

import java.util.ArrayList;

public class ServerReplica {
    private String place;
    private Double latitude;
    private Double longitude;
    private ArrayList<EbookCopy> ebookCopies = new ArrayList<>();

    public ServerReplica(String place,  Double latitude, Double longitude) throws InvalidServerReplicaLocationException {
        if(place == null || place.equals(""))
            throw new InvalidServerReplicaLocationException();
        else
            this.place = place;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public ArrayList<EbookCopy> getEbookCopies() {
        return ebookCopies;
    }

    public void setEbookCopies(ArrayList<EbookCopy> ebookCopies) {
        this.ebookCopies = ebookCopies;
    }

    public EbookCopy getEbookCopy(String name){
        if(ebookCopies.isEmpty())
            return null;//"This server has no copies
        for(int i = 0; i < ebookCopies.size(); ++i){
            if(ebookCopies.get(i).getEbookCopy().getName().equals(name)){
                return ebookCopies.get(i);//ebookCopies.get(i).getEbookCopy().getName();
            }
        }
        return null;//"Ebook copy not found";
    }

    public void addEbookCopy(EbookCopy ebook){
        ebookCopies.add(ebook);
    }

    public void addMultipleEbookCopies(ArrayList<EbookCopy> ebookCopyArrayList){
        ebookCopies.addAll(ebookCopyArrayList);
    }
}
