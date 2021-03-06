package algorithms;

import IO.PostingIO;
import IO.PostingMerger;

import java.io.*;
import java.util.*;

public class Indexer {
    private String postingPath;
    private int numOfDocIndexd;
    private Map<String,String> termDic;
    private Map<String, StringBuilder> termMap;
    private Map<String, String> cacheMap;
    private Set<String> cacheWords;
    private Map<String, String> docMap;
    private PostingIO pIO;
    private PostingMerger pMerger;
    private boolean stemFlag;


    public Indexer(String postingPath, Set<String> cacheWords, boolean stemFlag) {
        this.postingPath = postingPath;
        this.numOfDocIndexd = 0;
        this.termDic = null;
        this.cacheWords = cacheWords;
        this.termMap = new HashMap<>();
        this.cacheMap = new HashMap<>();
        this.docMap = new HashMap<>();
        this.pIO = new PostingIO(postingPath, stemFlag);
        this.pMerger = new PostingMerger();
        this.stemFlag = stemFlag;
    }

    public Map<String, StringBuilder> getTermMap() {
        return termMap;
    }

    public Map<String, String> getDocMap() {
        return docMap;
    }

    public void clearMap() {
        termMap.values().clear();
        docMap.clear();
    }

    public void mergeTempPosting(boolean mergeFlag) {
        if(!stemFlag)
            pMerger.threadMerge(new File(postingPath + "\\Posting"),mergeFlag);
        else
            pMerger.threadMerge(new File(postingPath + "\\stemPosting"),mergeFlag);
    }

    public void createPostFiles(){
        pIO.createPostingFile2(this.termMap);
        pIO.createDocPosting(this.docMap);
        clearMap();
    }

    public void index(List<Doc> docToIndexList) {
        for (Doc docToIndex :
                docToIndexList) {
            index(docToIndex);
        }
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
        numOfDocIndexd++;
    }

    public void createFinalDic() {
        List<Map<String, String>> finalDics = pIO.createPostingDic(numOfDocIndexd, cacheWords);
        this.termDic = finalDics.get(0);
        this.cacheMap = finalDics.get(1);
    }

    public TermInfo getTermInfo(String term){
        if(null == termDic)
            return null;
        String[] termInfoString = termDic.get(term).split(" ");
        TermInfo termInfo = new TermInfo(Integer.parseInt(termInfoString[2]),Double.parseDouble(termInfoString[3]));
        try {
            BufferedReader termReader = new BufferedReader(new FileReader(new File(termInfoString[0])));
            String postingLine=null;
            int i=0;
            while (i<Integer.parseInt(termInfoString[1])){
                postingLine = termReader.readLine();
                i++;
            }
            String[] termDocList = postingLine.split("\\*");
            termInfo.setTermDocList(Arrays.asList(termDocList));
            termReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return termInfo;
    }

    public void saveTermDic(String path){
        try {
            ObjectOutputStream osTermDic = new ObjectOutputStream(new FileOutputStream(new File(path)));
            osTermDic.writeObject(termDic);
            osTermDic.flush();
            osTermDic.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadTermDic(String path){
        ObjectInputStream oisTermDic;
        try {
            oisTermDic = new ObjectInputStream(new FileInputStream(new File(path)));
            termDic=(HashMap<String,String>)oisTermDic.readObject();
            oisTermDic.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Cache createCache(){
       return new Cache(this.cacheMap);
    }


    public void createCacheText() {
        Map<String,TermInfo> cache = new HashMap<>();
        PriorityQueue<Map.Entry<String, String>> pqTerms = new PriorityQueue<>(new Comparator<Map.Entry<String, String>>() {
            @Override
            public int compare(Map.Entry<String, String> o1, Map.Entry<String, String> o2) {
                String[] firstTerm = o1.getValue().split(" ");
                String[] secondTerm = o2.getValue().split(" ");
                return Double.compare(Double.parseDouble(firstTerm[3]), Double.parseDouble(secondTerm[3]));
            }
        });
        for (Map.Entry<String,String> entry:
             termDic.entrySet()) {
            pqTerms.add(entry);
        }
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter(new File("cacheWords")));
            for (int i = 0; i < 10000; i++) {
                Map.Entry<String, String> term = pqTerms.remove();
                bw.write(term.getKey());
                bw.write("\n");
            }
            bw.flush();
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
