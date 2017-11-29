package algorithms;

import java.util.*;

public class Indexer {
    private Map<String,String> termMap;
    private Set<String> stopWordsSet;

    public void Indexer(HashSet<String> stopWordsSet){
        this.termMap = new HashMap<>();
        this.stopWordsSet = stopWordsSet;
    }

    public void Indexing(Doc docToIndex){
        List<String> termList = docToIndex.getParseContent();
        for (String s: termList
             ) {
            ArrayList<Integer> termInDocLoc;
            if(!stopWordsSet.contains(s)) {
                termInDocLoc = docToIndex.termInDocLoc(s);

            }
        }
    }

}
