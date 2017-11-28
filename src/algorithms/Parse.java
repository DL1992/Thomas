package algorithms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

public class Parse implements Parser {
    private Pattern clearSpacesPattern;
    private Pattern clearJunkPattern;
    private Pattern normalNumberPattern;
    private Pattern decimalPattern;
    private Pattern fractionPattern;
    private Pattern capitalLetterPattern;
    private Map<String, String> months;

    public Parse() {
        this.months = initMonths();
        clearSpacesPattern = Pattern.compile("\\s+");
        clearJunkPattern = Pattern.compile("[^-%.\\w\\s/\"]");
        normalNumberPattern = Pattern.compile("\\d+");
        decimalPattern = Pattern.compile("\\d+\\.\\d+");
        fractionPattern = Pattern.compile("\\d+/\\d+");
        capitalLetterPattern = Pattern.compile(".*[A-Z].*");
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
        st = clearSpacesPattern.matcher(st).replaceAll(" ");
        String[] splitString = clearJunkPattern.matcher(st).replaceAll("").split(" ");
        for (int i = 0; i < splitString.length; i++) {
            String stToParse = splitString[i];
            if (Character.isDigit(st.charAt(0))) {
                if (stToParse.endsWith("th") || stToParse.endsWith("st") || stToParse.endsWith("nd") || stToParse.endsWith("rd"))
                    stToParse = stToParse.substring(0, stToParse.length() - 2);
                switch (tokenType(stToParse)) {
                    case 0:// normal number
                        if (tryParsePercent(splitString, i)) {
                            tokenList.add(stToParse + " percent");
                            i++;
                        }
                        else {
                            if (stToParse.length() <= 2 && i + 1 < splitString.length && Integer.parseInt(stToParse) <= 31 && Integer.parseInt(stToParse) > 0) {
                                if (months.containsKey(splitString[i + 1].toLowerCase())) {
                                    i++;
                                    if (stToParse.length() == 1) {
                                        stToParse = "0" + stToParse;
                                    }
                                    if (i + 1 < splitString.length && tokenType(splitString[i + 1]) == 0) {
                                        if (splitString[i+1].length() == 4) {
                                            i++;
                                            tokenList.add(String.format("%s/%s/%s", stToParse, months.get(splitString[i - 1].toLowerCase()), splitString[i]));
                                        }
                                        else if (splitString[i+1].length() == 2) {
                                            i++;
                                            tokenList.add(String.format("%s/%s/%s", stToParse, months.get(splitString[i - 1].toLowerCase()), "19" + splitString[i]));
                                        }
                                        else{
                                            tokenList.add(String.format("%s/%s", stToParse, months.get(splitString[i].toLowerCase())));
                                        }
                                    } else {
                                        tokenList.add(String.format("%s/%s", stToParse, months.get(splitString[i].toLowerCase())));
                                    }
                                }
                                else {
                                    tokenList.add(stToParse);
                                }
                            } else {
                                tokenList.add(stToParse);
                            }
                        }
                        break;
                    case 1://digits.digits
                        if (tryParsePercent(splitString, i)) {
                            tokenList.add(stToParse.substring(0, Math.min(stToParse.indexOf(".") + 3, stToParse.length())) + " percent");
                            i++;
                        } else {
                            tokenList.add(stToParse.substring(0, Math.min(stToParse.indexOf(".") + 3, stToParse.length())));
                        }
                        break;
                    case 2:// digits/digits
                        String[] d = stToParse.split("/");
                        tokenList.add(String.format("%.2f", Double.parseDouble(d[0]) / Double.parseDouble(d[1])));
                        break;
                    case 3: //end in %
                        String checkString = stToParse.substring(0, stToParse.length() - 1);
                        if (tokenType(checkString) == 1) {
                            tokenList.add(checkString.substring(0, Math.max(checkString.indexOf(".") + 2, checkString.length())) + " percent");
                        } else {
                            tokenList.add(checkString + " percent");
                        }
                        break;
                    default: //junk
                        tokenList.add(stToParse);
                        break;
                }
            } else {//Here if the string doesnt conatin a number
                if(capitalLetterPattern.matcher(stToParse).matches()){
                    if(i+1 < splitString.length && capitalLetterPattern.matcher(splitString[i+1]).matches()){
                        tokenList.add(stToParse.toLowerCase());
                        tokenList.add(splitString[i+1].toLowerCase());
                        tokenList.add(stToParse.toLowerCase()+ " " + splitString[i+1].toLowerCase());
                        i++;
                        continue;
                    }
                }
                if(months.containsKey(stToParse.toLowerCase())){
                    if(i + 1 < splitString.length && tokenType(splitString[i + 1]) == 0){
                        if (splitString[i+1].length() == 4) {
                            i++;
                            tokenList.add(String.format("%s/%s",months.get(splitString[i - 1].toLowerCase()), splitString[i]));
                        }
                        else if (i + 1 < splitString.length && splitString[i+1].length() <= 2 && Integer.parseInt(splitString[i+1]) <= 31 && Integer.parseInt(splitString[i+1]) > 0) {
                            i++;
                            String fullDay=splitString[i];
                            if (fullDay.length() == 1) {
                                fullDay = "0" + splitString[i+1];
                            }
                            if(i + 1 < splitString.length && tokenType(splitString[i + 1]) == 0) {
                                if (splitString[i + 1].length() == 4) {
                                    i++;
                                    tokenList.add(String.format("%s/%s/%s", fullDay, months.get(stToParse.toLowerCase()), splitString[i]));
                                }
                                else{
                                    tokenList.add(String.format("%s/%s",fullDay,months.get(stToParse.toLowerCase())));
                                }
                            }
                            else{
                                tokenList.add(String.format("%s/%s",fullDay,months.get(stToParse.toLowerCase())));
                            }
                        }
                        else{
                            tokenList.add(stToParse.toLowerCase());
                        }
                    }
                    else{
                        tokenList.add(stToParse.toLowerCase());
                    }
                }
                else{
                    tokenList.add(stToParse.toLowerCase());
                }
            } // end of else (not a number and checked for words)
        }
        return tokenList;
    }

    private boolean tryParsePercent(String[] splitString, int i) {
        if (i + 1 < splitString.length) {
            if (splitString[i + 1].equals("percent") || splitString[i + 1].equals("percentage")) {
                return true;
            }
        }
        return false;
    }

    private int tokenType(String stToParse) {
        if(normalNumberPattern.matcher(stToParse).matches())
            return 0;
        if(decimalPattern.matcher(stToParse).matches())
            return 1;
        if (fractionPattern.matcher(stToParse).matches())
            return 2;
        if(stToParse.endsWith("%"))
            return 3;
        return 4;
    }

//    public IndexHelper parse(Doc docToParse) {
//        return new IndexHelper(docToParse, parse(docToParse.getContent()));
//    }
}
