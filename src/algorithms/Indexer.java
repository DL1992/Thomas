package algorithms;

import java.util.*;

public class Indexer {
    private Map<String,String> termMap;
    private Set<String> stopWordsSet;

    public Indexer(HashSet<String> stopWordsSet){
        this.termMap = new HashMap<>();
        this.stopWordsSet = stopWordsSet;
    }

    public void index(Doc docToIndex){
        List<String> termList = docToIndex.getParseContent();
        HashSet<String> termInDocPool = new HashSet<>();
        for (String term: termList
             ) {
            if(!stopWordsSet.contains(term)) {
                if (!termInDocPool.contains(term)) {
                    termInDocPool.add(term);
                    String termInDocLoc = docToIndex.termInDocLoc(term);
                }
            }
        }
    }

}
