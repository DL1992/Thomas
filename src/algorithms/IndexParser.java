package algorithms;

import java.util.List;

public class IndexParser{
    private Parser indexParser;

    public IndexParser(Parser indexParser) {
        this.indexParser = indexParser;
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


}
