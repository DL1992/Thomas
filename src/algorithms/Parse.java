package algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Parse implements Parser {

    Map<String, String> months;

    public Parse() {
        this.months = initMonths();
    }

    private Map<String, String> initMonths() {
        Map<String, String> monthMap = new HashMap<>();
        monthMap.put("january", "01");
        monthMap.put("jan", "01");
        monthMap.put("february", "02");
        monthMap.put("feb", "02");
        monthMap.put("march", "03");
        monthMap.put("mar", "03");
        monthMap.put("april", "04");
        monthMap.put("apr", "04");
        monthMap.put("may", "05");
        monthMap.put("june", "06");
        monthMap.put("jun", "06");
        monthMap.put("july", "07");
        monthMap.put("jul", "07");
        monthMap.put("august", "08");
        monthMap.put("aug", "08");
        monthMap.put("september", "09");
        monthMap.put("sep", "09");
        monthMap.put("october", "10");
        monthMap.put("oct", "10");
        monthMap.put("november", "11");
        monthMap.put("nov", "11");
        monthMap.put("december", "12");
        monthMap.put("dec", "12");
        return monthMap;
    }

    @Override
    public List<String> parse(String st) {
        List<String> tokenList = new ArrayList<>();
        String[] splitString = st.replaceAll("[^%.\\w\\s/\"]", "").split(" ");
        for (int i = 0; i < splitString.length; i++) {
//            System.out.print(splitString[i] + " ");
            String stToParse = splitString[i];
            if (stToParse.matches(".*\\d.*")) {
                if (parseFraction(tokenList, stToParse)) {
                    continue;
                }
                if (parseDate(tokenList, splitString, i)) {
                    i++;
                    continue;
                }
                stToParse = parseDecimalPoint(stToParse);
                if (i + 1 != splitString.length)
                    if (parsePrecent(tokenList, stToParse, splitString[i + 1])) {
                        continue;
                    }
            } else {
                if (stToParse.matches(".*[A-Z].*")) {
                    String largetoken = stToParse + " ";
                    while (i != splitString.length && splitString[i].matches(".*[A-Z].*")) {
                        largetoken += splitString[i] + " ";
                        tokenList.add(splitString[i].toLowerCase());
                        i++;
                    }
                    tokenList.add(largetoken.toLowerCase());
                    continue;
                }
            }
        }
        return tokenList;
    }

    private boolean parseDate(List<String> tokenList, String[] splitString, int i) {
        String dateToken;
        int dayOfDate = Integer.parseInt(splitString[i].replace("th", ""));
        if (dayOfDate < 1 || dayOfDate > 31) {
            return false;
        } else {
            dateToken = String.format("%02d", dayOfDate);
            if (i + 1 != splitString.length) {
                i++;
                if (months.containsKey(splitString[i].toLowerCase())) {
                    dateToken = dateToken + "/" + months.get(splitString[i].toLowerCase());
                } else {
                    return false;
                }
                if (i + 1 != splitString.length) {
                    i++;
                    if (splitString[i].matches(".*\\d.*")) {
                        if (splitString[i].length() == 2) {
                            dateToken += "/19" + splitString[i];
                        } else {
                            dateToken += "/" + splitString[i];
                        }
                    }
                }
            } else {
                return false;
            }
            tokenList.add(dateToken);
            return true;
        }
    }


    private boolean parseFraction(List<String> tokenList, String stToParse) {
        if (stToParse.matches("\\d+/\\d+")) {
            String[] d = stToParse.split("/");
            tokenList.add(String.format("%.2f", Double.parseDouble(d[0]) / Double.parseDouble(d[1])));
            return true;
        }
        return false;
    }

    private String parseDecimalPoint(String stToParse) {
        if (stToParse.contains(".")) {
            Double number = Double.parseDouble(stToParse.replace("%", ""));
            stToParse = String.format("%.02f", number) + "%";
        }
        return stToParse;
    }

    private boolean parsePrecent(List<String> tokenList, String stToParse, String stNextParse) {
        if (stToParse.endsWith("%")) {
            tokenList.add(stToParse.replace("%", "") + " percent");
            return true;
        } else if (stNextParse.equals("percent") || stNextParse.equals("percentage")) {
            tokenList.add(stToParse + " percent");
            return true;
        }
        return false;
    }


//    public IndexHelper parse(Doc docToParse) {
//        return new IndexHelper(docToParse, parse(docToParse.getContent()));
//    }
}
