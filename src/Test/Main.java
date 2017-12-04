package Test;

import IO.PostingIO;
import IO.PostingMerger;
import IO.ReadFile;
import algorithms.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Main {
    public static void main(String[] args) {


        long stopTime;
        long elapsedTime;
        ArrayList<Integer> loc;
        long startTime = System.currentTimeMillis();
        ReadFile read = new ReadFile();

//        HashSet<String> stopWords = read.createStopWordsSet("D:\\documents\\users\\laadan\\stop_words.txt");
        HashSet<String> stopWords = read.createStopWordsSet("C:\\School\\IR\\Search Engine\\stop_words.txt");

        Indexer indexer = new Indexer();

        IndexParser parse = new IndexParser(new Parse(), new Stemmer(), stopWords);
        read.setBatchSize(100);
        read.readFiles(new File("C:\\School\\IR\\Search Engine\\corpus"));
//        read.readFiles(new File("D:\\documents\\users\\laadan\\corpus"));
        List<List<Doc>> list = read.getDocList();
        stopTime = System.currentTimeMillis();
        elapsedTime = stopTime - startTime;
        System.out.println("Finished Reading Time: " + elapsedTime);
//        PostingIO ps = new PostingIO("D:\\documents\\users\\laadan");
        PostingIO ps = new PostingIO("C:\\School\\IR\\Search Engine");
        PostingMerger pm = new PostingMerger();
        int counter = 0;

        for (List<Doc> docList : list) {
            indexer.clearMap();
            for (Doc d : docList) {
                parse.parse(d);
//                List<String> listyTheDirtyList = d.getParseContent();
//                d.createTermLocMap();
                indexer.index(d);
//                int n = 0;
//                break;
            }
            ps.createPostingFile(indexer.getTermMap());
            counter++;
            if (counter % 4 == 0) {
//                File f = new File("D:\\documents\\users\\laadan\\Posting");
                File f = new File("C:\\School\\IR\\Search Engine\\Posting");
                for (File f1 : f.listFiles()
                        ) {
                    try {
                        pm.mergeFiles(f1.getCanonicalFile());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                }
            }
//            ps.closePostingMap();
//          break;
        }


        System.out.println("-");

        stopTime = System.currentTimeMillis();
        elapsedTime = stopTime - startTime;
        System.out.println("Total Finish Time: " + elapsedTime);
    }
}




