package algorithms;

import java.util.ArrayList;
import java.util.List;

public class IndexParser{
    private Parser indexParser;
    private Stemmer stemmer;

    public IndexParser(Parser indexParser) {
        this.indexParser = indexParser;
    }
    public IndexParser(Parser indexParser, Stemmer stemmer){
        this.indexParser = indexParser;
        this.stemmer = stemmer;
    }

    private List<String> parse(String st) {
        return indexParser.parse(st);
    }

    public Doc parse(Doc docToParse) {
        List<String> docParseContent = parse(docToParse.getContent());
        docToParse.setDocLength(docParseContent.size());
        docToParse.setParseContent(docParseContent);
        return docToParse;
    }

    public Doc parseWithStemmer(Doc docToParse){
        List<String> docParseContent= parse(docToParse.getContent());
        docParseContent=stemDoc(docParseContent);
        docToParse.setDocLength(docParseContent.size());
        docToParse.setParseContent(docParseContent);
        return docToParse;

    }

    private List<String> stemDoc(List<String> docParseContent) {
        List<String> stemTokenList = new ArrayList<>();
        for (String termBeforeStem:
             docParseContent) {
            stemmer.add(termBeforeStem.toCharArray(),termBeforeStem.length());
            stemmer.stem();
            stemTokenList.add(stemmer.toString());
        }
        return stemTokenList;
    }


}
