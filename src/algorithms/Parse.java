package algorithms;

import java.util.ArrayList;
import java.util.List;

public class Parse implements Parser {

    @Override
    public List<String> parse(String st) {
        List<String> tokenList = new ArrayList<>();
        String[] splitString = st.split(" ");
        for (String s :
                splitString) {
            if (s.matches("\\d+/\\d+")) {
                String[] d = s.split("/");
                System.out.println(String.format("%.2f", Double.parseDouble(d[0]) / Double.parseDouble(d[1])));
            }

        }
        return null;
    }

//    public IndexHelper parse(Doc docToParse) {
//        return new IndexHelper(docToParse, parse(docToParse.toString()));
//    }
}
