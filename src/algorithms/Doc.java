package algorithms;

import java.util.ArrayList;
import java.util.List;

public class Doc {
    private int docLength;
    private String docName;
    private String content;
    private String mostCommonTerm;
    private int mostCommonTermTf;
    private List<String> parseContent;

    public Doc(String docName, String content) {
        this.docLength=0;
        this.docName = docName;
        this.content = content;
        this.parseContent = new ArrayList<>();
        this.mostCommonTerm="";
        this.mostCommonTermTf=0;

    }

    public ArrayList<Integer> termInDocLoc(String term){
        ArrayList<Integer> termInDocLoc = new ArrayList<>();
        if(null == parseContent)
            return null;
        for (int i = 0; i < parseContent.size(); i++) {
            if(parseContent.get(i).equals(term)){
                termInDocLoc.add(i);
            }
        }
        if(termInDocLoc.size()>mostCommonTermTf){
            mostCommonTermTf = termInDocLoc.size();
            mostCommonTerm = term;
        }
        return termInDocLoc;
    }

    public int getDocLength() {
        return docLength;
    }

    public List<String> getParseContent() {
        return parseContent;
    }

    public String getDocName() {
        return docName;
    }

    public String getContent() {
        return content;
    }

    public String getMostCommonTerm() {
        return mostCommonTerm;
    }

    public int getMostCommonTermTf() {
        return mostCommonTermTf;
    }

    public void setParseContent(List<String> parseContentList) {
        if(null != parseContentList){
            this.parseContent = parseContentList;
            this.content=null;
        }
    }

    public void setDocLength(int docLength) {
        if(docLength>0)
            this.docLength = docLength;
        else
            this.docLength=0;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return String.format("doc name is: %s. doc length is: %d. most common term is: %s , tf of common word is: %s", docName, docLength, mostCommonTerm, mostCommonTermTf);
    }
}
