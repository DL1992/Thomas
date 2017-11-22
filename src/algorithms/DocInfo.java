package algorithms;

public class DocInfo {
    private int docLength;
    private String mostCommonTerm;
    private int mostCommonTermFreq;

    public DocInfo(int docLength, String mostCommonTerm, int mostCommonTermFreq) {
        this.docLength = docLength;
        this.mostCommonTerm = mostCommonTerm;
        this.mostCommonTermFreq = mostCommonTermFreq;
    }

    public int getDocLength() {
        return docLength;
    }

    public void setDocLength(int docLength) {
        this.docLength = docLength;
    }

    public String getMostCommonTerm() {
        return mostCommonTerm;
    }

    public void setMostCommonTerm(String mostCommonTerm) {
        this.mostCommonTerm = mostCommonTerm;
    }

    public int getMostCommonTermFreq() {
        return mostCommonTermFreq;
    }

    public void setMostCommonTermFreq(int mostCommonTermFreq) {
        this.mostCommonTermFreq = mostCommonTermFreq;
    }

}
