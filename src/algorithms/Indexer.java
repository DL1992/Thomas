package algorithms;

import IO.PostingIO;
import IO.PostingMerger;

import java.io.File;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Indexer {
    private String postingPath;
    private Map<String, StringBuilder> termMap;
    private Map<String, String> docMap;
    private PostingIO pIO;
    private PostingMerger pMerger;

    public Indexer() {
        this.termMap = new HashMap<>();
        this.docMap = new HashMap<>();
    }

    public Indexer(String postingPath) {
        this.postingPath = postingPath;
        this.termMap = new HashMap<>();
        this.docMap = new HashMap<>();
        this.pIO = new PostingIO(postingPath);
        this.pMerger = new PostingMerger();
    }

    public Map<String, StringBuilder> getTermMap() {
        return termMap;
    }

    public Map<String, String> getDocMap() {
        return docMap;
    }

    public void clearMap() {
        termMap.clear();
//        termMap.values().clear();
        docMap.clear();
    }

    public void mergeTempPosting(){
        pMerger.threadMerge(new File(postingPath+ "\\Posting"));
    }

    public void index(List<Doc> docToIndexList) {
        for (Doc docToIndex :
                docToIndexList) {
            List<String> termList = docToIndex.getParseContent();
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
        pIO.createPostingFile2(this.termMap);
        pIO.createDocPosting(this.docMap);
    }


    public void index(Doc docToIndex) {
        List<String> termList = docToIndex.getParseContent();
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
