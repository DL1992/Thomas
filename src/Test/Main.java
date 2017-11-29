package Test;

import IO.ReadFile;
import algorithms.Doc;
import algorithms.IndexParser;
import algorithms.Parse;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Main {
    public static void main(String[] args) {
//        long hz= Runtime.getRuntime().totalMemory();
//        System.out.println(hz);
//        Pattern capitalLetterPattern = Pattern.compile(".*[A-Z].*");
//        String st= "dasdja-das--das.%%()#$%%^&&*(_)(()_()dka--sd";
//        if (capitalLetterPattern.matcher(st).matches())
//            System.out.println("yay");

        long stopTime;
        long elapsedTime;
        ArrayList<Integer> loc;
        long startTime = System.currentTimeMillis();
        ReadFile read = new ReadFile();

        HashSet<String> stopWords = read.createStopWordsSet("C:\\School\\IR\\Search Engine\\stop_words.txt");
        IndexParser parse = new IndexParser(new Parse());
        read.setBatchSize(1);
        read.readFiles(new File("C:\\School\\IR\\Search Engine\\corpus"));
        List<List<Doc>> list = read.getDocList();
        stopTime = System.currentTimeMillis();
        elapsedTime = stopTime - startTime;
        System.out.println("Finished Reading Time: " + elapsedTime);

        for (List<Doc> docList : list) {
            for (Doc d : docList) {
                parse.parse(d);
                List<String> listyTheDirtyList = d.getParseContent();
//                int size = listyTheDirtyList.size();
//                for (int i = 0; i < size; i++) {
//                    String s = listyTheDirtyList.get(i);
//                    if(!stopWords.contains(s)) {
//                        loc = d.termInDocLoc(s);
//                    }
////                    System.out.println(String.format("the term %s, doc location: ", d.getParseContent().get(i)));
//                }
                System.out.println(d);
//                System.out.println("gf");
//                break;
            }
//            break;
        }
       
        stopTime = System.currentTimeMillis();
        elapsedTime = stopTime - startTime;
        System.out.println("Total Finish Time: " + elapsedTime);
    }
}



