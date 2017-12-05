package algorithms;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Indexer {
    private Map<String, StringBuilder> termMap;
    private Map<String, String> docMap;

    public Indexer() {
        this.termMap = new HashMap<>();
        this.docMap = new HashMap<>();
    }

    public Map<String, StringBuilder> getTermMap() {
        return termMap;
    }

    public Map<String, String> getDocMap() {
        return docMap;
    }

    public void clearMap() {
        termMap.clear();
        docMap.clear();
    }

    public void index(Doc docToIndex) {
        List<String> termList = docToIndex.getParseContent();
//        docToIndex.createTermLocMap();
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
        docMap.put(docToIndex.getDocName(), docToIndex.getDocLength() + " " + docToIndex.getMostCommonTerm() + " " + docToIndex.getMostCommonTermTf());
    }
}
