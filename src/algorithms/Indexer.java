package algorithms;

import java.util.*;

public class Indexer {
    private Map<String, List<String>> termMap;

    public Indexer() {
        this.termMap = new TreeMap<>();
    }

    public Map<String, List<String>> getTermMap() {
        return termMap;
    }

    public void clearMap() {
        termMap.clear();
    }

    public void index(Doc docToIndex) {
        List<String> termList = docToIndex.getParseContent();
        docToIndex.createTermLocMap();
        HashSet<String> termInDocPool = new HashSet<>();
        for (String term : termList
                ) {
            if (!termInDocPool.contains(term)) {
                termInDocPool.add(term);
                if (!termMap.containsKey(term)) {
                    termMap.put(term, new ArrayList<>());
                }
                termMap.get(term).add(docToIndex.getDocName() + " " + docToIndex.termInDocLoc2(term));
            }

        }
    }
}
