package algorithms;

import java.util.List;
import java.util.Map;

public class TermInfo {
    private List<String> termDocList;
    private int totalNumInCorpus;
    private double termDfi;

    public TermInfo(int totalNumInCorpus, double termDfi) {
        this.totalNumInCorpus = totalNumInCorpus;
        this.termDfi = termDfi;
    }

    public List<String> getTermDocList() {
        return termDocList;
    }

    public void setTermDocList(List<String> termDocList) {
        this.termDocList = termDocList;
    }

    public int getTotalNumInCorpus() {
        return totalNumInCorpus;
    }

    public void setTotalNumInCorpus(int totalNumInCorpus) {
        this.totalNumInCorpus = totalNumInCorpus;
    }

    public double getTermDfi() {
        return termDfi;
    }

    public void setTermDfi(double termDfi) {
        this.termDfi = termDfi;
    }
}
