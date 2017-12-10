package algorithms;

import java.util.Map;

public class Cache {
    private Map<String,TermInfo> cacheMap;

    public Cache(Map<String, TermInfo> cacheMap) {
        this.cacheMap = cacheMap;
    }
}
