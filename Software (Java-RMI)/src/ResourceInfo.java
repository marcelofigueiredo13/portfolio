import java.io.Serializable;

public class ResourceInfo implements Serializable {
    public String resourceType;
    public Float resourceTime;
    public boolean repeatedCall;
    public String cachedResource;
    public Integer harRun;
    public long resourceLength;
}
