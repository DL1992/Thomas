package algorithms;

import java.util.*;

public class Indexer {
    private Map<String,List<String>> termMap;
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
                    if(!termMap.containsKey(term))
                    {
                        termMap.put(term, new ArrayList<>());
                    }
                    termMap.get(term).add(docToIndex.getDocName() + " " + docToIndex.termInDocLoc(term));
                }
            }
        }
    }

}
