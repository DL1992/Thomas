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
        ReadFile read = new ReadFile();
        Indexer indexer = new Indexer("D:\\documents\\users\\laadan",read.createCacheSet(),false);
        long startTime = System.currentTimeMillis();
        indexer.loadTermDic("D:\\documents\\users\\laadan\\dic");
        long dicTime = System.currentTimeMillis();
        System.out.println("time to load dic is: " + (dicTime-startTime));

        Cache c = new Cache();
        c.loadCache("D:\\documents\\users\\laadan\\binaryCache");
        int a = 1;
//        long stopTime;
//        long elapsedTime;
//        long startTime = System.currentTimeMillis();
//        long indexTime;
//        long mergerTime;
//        long readTime;
//        ReadFile read = new ReadFile();
//        read.setBatchSize(101);
//        HashSet<String> stopWords = read.createStopWordsSet("D:\\documents\\users\\laadan\\stop_words.txt");
//////        HashSet<String> stopWords = read.createStopWordsSet("C:\\School\\IR\\Search Engine\\stop_words.txt");
//        Indexer indexer = new Indexer("D:\\documents\\users\\laadan", read.createCacheSet(),false);
////
//////        Indexer indexer = new Indexer("C:\\School\\IR\\Search Engine");
//        IndexParser parse = new IndexParser(new Parse(), new Stemmer(), stopWords);
//////        read.readFiles(new File("C:\\School\\IR\\Search Engine\\corpus"));
////
//        for(int i=0;i<18;i++){
//            read.readFiles(new File("D:\\documents\\users\\laadan\\corpus"));
//            List<List<Doc>> list = read.getDocList();
//            readTime = System.currentTimeMillis();
//            elapsedTime = readTime - startTime;
//            System.out.println("Finished Reading Time: " + elapsedTime);
//            indexTime = System.currentTimeMillis();
//            List<Thread> threadsList = new ArrayList<>();
//            for (List<Doc> docList : list) {
//                threadsList.add(new Thread(() -> parseDocs(parse, docList)));
//            }
//            for (Thread t :
//                    threadsList) {
//                t.start();
//            }
//            for (Thread t :
//                    threadsList) {
//                try {
//                    t.join();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//            for (List<Doc> docList : list) {
//                indexer.index(docList);
//            }
//            indexer.createPostFiles();
//            stopTime = System.currentTimeMillis();
//            elapsedTime = stopTime - indexTime;
//            System.out.println("Finished temp posting Time: " + elapsedTime);
//            if(i%3==0){
//                mergerTime = System.currentTimeMillis();
//                indexer.mergeTempPosting(false);
//                stopTime = System.currentTimeMillis();
//                elapsedTime = stopTime - mergerTime;
//                System.out.println("Finished merging Time: " + elapsedTime);
//            }
//            if(i==17)///should be 17!!!!!
//                indexer.mergeTempPosting(true);
//        }
//        long dicTime = System.currentTimeMillis();
//        indexer.createFinalDic();
//        stopTime = System.currentTimeMillis();
//        elapsedTime = stopTime - dicTime;
//        System.out.println("Total dic time: " + elapsedTime);
////        long termTime = System.currentTimeMillis();
////        TermInfo ti = indexer.getTermInfo("party");
////        stopTime = System.currentTimeMillis();
////        elapsedTime = stopTime - termTime;
////        System.out.println("total term time: " + elapsedTime);
//        dicTime = System.currentTimeMillis();
//        indexer.saveTermDic("D:\\documents\\users\\laadan\\dic");
//        stopTime = System.currentTimeMillis();
//        elapsedTime = stopTime - startTime;
//        System.out.println("time to save dic: " + elapsedTime);
//        dicTime = System.currentTimeMillis();
//        Cache c = indexer.createCache();
//        stopTime = System.currentTimeMillis();
//        elapsedTime = stopTime - startTime;
//        System.out.println("time to create cache: " + elapsedTime);
//        dicTime = System.currentTimeMillis();
//        c.saveCache("D:\\documents\\users\\laadan\\binaryCache");
//        stopTime = System.currentTimeMillis();
//        elapsedTime = stopTime - startTime;
//        System.out.println("time to save cache: " + elapsedTime);
//        stopTime = System.currentTimeMillis();
//        elapsedTime = stopTime - startTime;
//        System.out.println("total time: " + elapsedTime);
//    }
//
//    private static void parseDocs(IndexParser parse, List<Doc> docList) {
//        List<Thread> threads = new ArrayList<>();
//        for (Doc d : docList) {
//            threads.add(new Thread(() -> parse.parse(d)));
//        }
//        for (Thread t :
//                threads) {
//            t.start();
//        }
//        for (Thread t :
//                threads) {
//            try {
//                t.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }
}




