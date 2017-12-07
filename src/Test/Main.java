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

//        HashSet<String> stopWords = read.createStopWordsSet("D:\\documents\\users\\laadan\\stop_words.txt");
        HashSet<String> stopWords = read.createStopWordsSet("C:\\School\\IR\\Search Engine\\stop_words.txt");

//        Indexer indexer = new Indexer("D:\\documents\\users\\laadan");
        Indexer indexer = new Indexer("C:\\School\\IR\\Search Engine");

        IndexParser parse = new IndexParser(new Parse(), new Stemmer(), stopWords);
        read.setBatchSize(5);
        read.readFiles(new File("C:\\School\\IR\\Search Engine\\corpus"));
//        read.readFiles(new File("D:\\documents\\users\\laadan\\corpus"));
        int numOfDoc = read.getNumOfDoc();
        List<List<Doc>> list = read.getDocList();
        stopTime = System.currentTimeMillis();
        elapsedTime = stopTime - startTime;
        System.out.println("Finished Reading Time: " + elapsedTime);
        indexTime = System.currentTimeMillis();
//        PostingIO ps = new PostingIO("D:\\documents\\users\\laadan");
//        PostingIO ps = new PostingIO("C:\\School\\IR\\Search Engine");
//        PostingMerger pm = new PostingMerger();
        int counter = 0;

        for (List<Doc> docList : list) {
            List<Thread> threads = new ArrayList<>();
//            indexer.clearMap();
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
//                List<String> listyTheDirtyList = d.getParseContent();
//                d.createTermLocMap();
//            for (Doc d :
//                    docList) {
//                indexer.index(d);
//            }
            indexer.index(docList);
            postingTime = System.currentTimeMillis();
//            ps.createPostingFile2(indexer.getTermMap());
//            ps.createDocPosting(indexer.getDocMap());

//            counter++;
//            if (counter % 4 == 0) {
//                File f = new File("D:\\documents\\users\\laadan\\Posting");
//                File f = new File("C:\\School\\IR\\Search Engine\\Posting");
//                for (File f1 : f.listFiles()
//                        ) {
//                    try {
//                        pm.mergeFiles(f1.getCanonicalFile());
//                    } catch (IOException e) {
//                        e.printStackTrace();
//                    }

//                }
//            }
//            ps.closePostingMap();
//          break;
        }
//        indexer.clearMap();
        stopTime = System.currentTimeMillis();
        elapsedTime = stopTime - postingTime;
        System.out.println("Finished temp posting Time: " + elapsedTime);
        mergerTime = System.currentTimeMillis();
        indexer.mergeTempPosting();
//        File f = new File("D:\\documents\\users\\laadan\\Posting");
//        File f = new File("C:\\School\\IR\\Search Engine\\Posting");
//        pm.threadMerge(f);
//        List<Thread> threadsList = new ArrayList<>();
//        for (File f1 : f.listFiles()
//                ) {
//            threadsList.add(new Thread(() -> {
//                try {
//                    pm.mergeFiles(f1.getCanonicalFile());
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }));
//        }
//        for (Thread t:
//             threadsList) {
//            t.start();
//        }
//        for (Thread t:
//             threadsList) {
//            try {
//                t.join();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
        Map<String, String> dic = indexer.createFinalDic();
        stopTime = System.currentTimeMillis();
        elapsedTime = stopTime - mergerTime;
        System.out.println("Finished merging Time: " + elapsedTime);

        stopTime = System.currentTimeMillis();
        elapsedTime = stopTime - startTime;
        System.out.println("Total Finish Time: " + elapsedTime);

    }
}




