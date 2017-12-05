package algorithms;

import java.util.*;

public class Indexer {
    private Map<String, StringBuilder> termMap;

    public Indexer() {
        this.termMap = new HashMap<>();
    } /// TODO: check if TreeMap better

    public Map<String, StringBuilder> getTermMap() {
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
                    termMap.put(term, new StringBuilder());
                }
                termMap.get(term).append(docToIndex.getDocName() + " " + docToIndex.termInDocLoc2(term));
            }

        }
    }
}
