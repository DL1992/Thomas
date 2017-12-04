package algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Doc {
    private int docLength;
    private String docName;
    private String content;
    private String mostCommonTerm;
    private int mostCommonTermTf;
    private List<String> parseContent;
    private Map<String, StringBuilder> termsLocMap;
    private Map<String, Integer> termsInDocNum;

    public Doc(String docName, String content) {
        this.docLength = 0;
        this.docName = docName;
        this.content = content;
        this.parseContent = new ArrayList<>();
        this.termsLocMap = new HashMap<>();
        this.termsInDocNum = new HashMap<>();
        this.mostCommonTerm = "";
        this.mostCommonTermTf = 0;

    }

    public void createTermLocMap() {
        if (null == parseContent)
            return;
        for (int i = 0; i < parseContent.size(); i++) {
            if (!termsLocMap.containsKey(parseContent.get(i))) {
                termsInDocNum.put(parseContent.get(i), 1);
                termsLocMap.put(parseContent.get(i), new StringBuilder());
                termsLocMap.get(parseContent.get(i)).append(i);
            } else {
                termsLocMap.get(parseContent.get(i)).append(" " + i);
                int repeatTimes = termsInDocNum.get(parseContent.get(i));
                repeatTimes++;
                if (repeatTimes > mostCommonTermTf) {
                    mostCommonTermTf = repeatTimes;
                    mostCommonTerm = parseContent.get(i);
                }
                termsInDocNum.put(parseContent.get(i), repeatTimes);
            }
        }
    }

    public String termInDocLoc2(String term) {
        return termsInDocNum.get(term).toString() + " " + termsLocMap.get(term) + " *";
    }

    public String termInDocLoc(String term) {
        int repeatTimes = 0;
        StringBuilder termInDocLoc = new StringBuilder();
        if (null == parseContent)
            return null;
        for (int i = 0; i < parseContent.size(); i++) {
            if (parseContent.get(i).equals(term)) {
                termInDocLoc.append(" " + i);
                repeatTimes++;
            }
        }
        termInDocLoc.append(" *");
        if (repeatTimes > mostCommonTermTf) {
            mostCommonTermTf = repeatTimes;
            mostCommonTerm = term;
        }
        return termInDocLoc.insert(0, "" + repeatTimes).toString();
    }

    public int getDocLength() {
        return docLength;
    }

    public void setDocLength(int docLength) {
        if (docLength > 0)
            this.docLength = docLength;
        else
            this.docLength = 0;
    }

    public List<String> getParseContent() {
        return parseContent;
    }

    public void setParseContent(List<String> parseContentList) {
        if (null != parseContentList) {
            this.parseContent = parseContentList;
            this.content = null;
        }
    }

    public String getDocName() {
        return docName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getMostCommonTerm() {
        return mostCommonTerm;
    }

    public int getMostCommonTermTf() {
        return mostCommonTermTf;
    }

    @Override
    public String toString() {
        return String.format("doc name is: %s. doc length is: %d. most common term is: %s , tf of common word is: %s", docName, docLength, mostCommonTerm, mostCommonTermTf);
    }
}
