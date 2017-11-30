package Test;

import IO.ReadFile;
import algorithms.Doc;
import algorithms.IndexParser;
import algorithms.Indexer;
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

//        HashSet<String> stopWords = read.createStopWordsSet("C:\\School\\IR\\Search Engine\\stop_words.txt");
        HashSet<String> stopWords = read.createStopWordsSet("D:\\documents\\users\\sergayen\\stop_words.txt");

        Indexer indexer = new Indexer(stopWords);

        IndexParser parse = new IndexParser(new Parse());
        read.setBatchSize(180);
//        read.readFiles(new File("C:\\School\\IR\\Search Engine\\corpus"));
        read.readFiles(new File("D:\\documents\\users\\sergayen\\corpus"));

        List<List<Doc>> list = read.getDocList();
        stopTime = System.currentTimeMillis();
        elapsedTime = stopTime - startTime;
        System.out.println("Finished Reading Time: " + elapsedTime);

        for (List<Doc> docList : list) {
            for (Doc d : docList) {
                parse.parse(d);
//                List<String> listyTheDirtyList = d.getParseContent();
                indexer.index(d);
//              break;
            }
//            break;
        }
       
        stopTime = System.currentTimeMillis();
        elapsedTime = stopTime - startTime;
        System.out.println("Total Finish Time: " + elapsedTime);
    }
}



