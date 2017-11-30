package Test;

import IO.PostingIO;
import IO.ReadFile;
import algorithms.*;

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
        PostingIO postingIO = new PostingIO("D:\\documents\\users\\laadan");
        for (char i = '0'; i <= '9'; i++) {
            System.out.println(i);
        }
//        long stopTime;
//        long elapsedTime;
//        ArrayList<Integer> loc;
//        long startTime = System.currentTimeMillis();
//        ReadFile read = new ReadFile();
//
////        HashSet<String> stopWords = read.createStopWordsSet("C:\\School\\IR\\Search Engine\\stop_words.txt");
//        HashSet<String> stopWords = read.createStopWordsSet("D:\\documents\\users\\sergayen\\stop_words.txt");
//
//        Indexer indexer = new Indexer(stopWords);
//
//        IndexParser parse = new IndexParser(new Parse(), new Stemmer());
//        read.setBatchSize(100);
////        read.readFiles(new File("C:\\School\\IR\\Search Engine\\corpus"));
//        List<List<Doc>> list = read.getDocList();
//        stopTime = System.currentTimeMillis();
//        elapsedTime = stopTime - startTime;
//        System.out.println("Finished Reading Time: " + elapsedTime);
//
//        for (List<Doc> docList : list) {
//            for (Doc d : docList) {
//                parse.parseWithStemmer(d);
////                List<String> listyTheDirtyList = d.getParseContent();
//                indexer.index(d);
////              break;
//            }
////            break;
//        }
//
//        System.out.println("-");
//
//        stopTime = System.currentTimeMillis();
//        elapsedTime = stopTime - startTime;
//        System.out.println("Total Finish Time: " + elapsedTime);
    }
}



