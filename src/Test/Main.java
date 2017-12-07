package Test;

import IO.ReadFile;
import algorithms.*;

import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        long stopTime;
        long elapsedTime;
        ArrayList<Integer> loc;
        long startTime = System.currentTimeMillis();
        long indexTime;
        long postingTime = 0;
        long mergerTime;
        ReadFile read = new ReadFile();
        read.setBatchSize(100);
        HashSet<String> stopWords = read.createStopWordsSet("D:\\documents\\users\\laadan\\stop_words.txt");
//        HashSet<String> stopWords = read.createStopWordsSet("C:\\School\\IR\\Search Engine\\stop_words.txt");
        Indexer indexer = new Indexer("D:\\documents\\users\\laadan");
//        Indexer indexer = new Indexer("C:\\School\\IR\\Search Engine");
        IndexParser parse = new IndexParser(new Parse(), new Stemmer(), stopWords);
//        read.readFiles(new File("C:\\School\\IR\\Search Engine\\corpus"));

        for(int i=0;i<1;i++){
            read.readFiles(new File("D:\\documents\\users\\laadan\\corpus"));
            List<List<Doc>> list = read.getDocList();
            stopTime = System.currentTimeMillis();
            elapsedTime = stopTime - startTime;
            System.out.println("Finished Reading Time: " + elapsedTime);
            indexTime = System.currentTimeMillis();
            for (List<Doc> docList : list) {
                List<Thread> threads = new ArrayList<>();
                for (Doc d : docList) {
                    threads.add(new Thread(() -> parse.parse(d)));
                }
                for (Thread t :
                        threads) {
                    t.start();
                }
                for (Thread t :
                        threads) {
                    try {
                        t.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                indexer.index(docList);
            }
            stopTime = System.currentTimeMillis();
            elapsedTime = stopTime - indexTime;
            System.out.println("Finished temp posting Time: " + elapsedTime);
            mergerTime = System.currentTimeMillis();
            indexer.mergeTempPosting();
            stopTime = System.currentTimeMillis();
            elapsedTime = stopTime - mergerTime;
            System.out.println("Finished merging Time: " + elapsedTime);
        }
        long dicTime = System.currentTimeMillis();
        indexer.createFinalDic();
        stopTime = System.currentTimeMillis();
        elapsedTime = stopTime - dicTime;
        System.out.println("Total dic time: " + elapsedTime);
        long termTime = System.currentTimeMillis();
        TermInfo ti = indexer.getTermInfo("party");
        stopTime = System.currentTimeMillis();
        elapsedTime = stopTime - termTime;
        System.out.println("total term time: " + elapsedTime);
        indexer.saveTermDic("D:\\documents\\users\\laadan\\dic");
        stopTime = System.currentTimeMillis();
        elapsedTime = stopTime - startTime;
        System.out.println("total time: " + elapsedTime);

    }
}




