package algorithms;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class IndexParser {
    private IParser indexParser;
    private Stemmer stemmer;
    private Set<String> stopWords;

    public IndexParser(IParser indexParser, Set<String> stopWords) {
        this.indexParser = indexParser;
        this.stopWords = stopWords;
        this.stemmer = null;
    }

    public IndexParser(IParser indexParser, Stemmer stemmer, Set<String> stopWords) {
        this.indexParser = indexParser;
        this.stemmer = stemmer;
        this.stopWords = stopWords;
    }

    private List<String> parse(String st) {
        return indexParser.parse(st);
    }

    public void parse(Doc docToParse) {
        List<String> docParseContent = parse(docToParse.getContent());
        removeStopWords(docParseContent);
        docToParse.setDocLength(docParseContent.size());
        docToParse.setParseContent(docParseContent);
    }

    private void removeStopWords(List<String> docParseContent) {
        Iterator<String> listIt = docParseContent.iterator();
        while (listIt.hasNext()) {
            String term = listIt.next();
            if (stopWords.contains(term))
                listIt.remove();
        }
    }

    public void parseWithStemmer(Doc docToParse) {
        if (null == stemmer)
            parse(docToParse);
        List<String> docParseContent = parse(docToParse.getContent());
        removeStopWords(docParseContent);
        docParseContent = stemDoc(docParseContent);
        docToParse.setDocLength(docParseContent.size());
        docToParse.setParseContent(docParseContent);
    }

    private List<String> stemDoc(List<String> docParseContent) {
        List<String> stemTokenList = new ArrayList<>();
        for (String termBeforeStem :
                docParseContent) {
            stemmer.add(termBeforeStem.toCharArray(), termBeforeStem.length());
            stemmer.stem();
            stemTokenList.add(stemmer.toString());
        }
        return stemTokenList;
    }


}
