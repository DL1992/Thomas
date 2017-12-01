package Test;

import IO.PostingMerger;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        PostingMerger pm = new PostingMerger();
        File f = new File("C:\\School\\IR\\Search Engine\\Posting");
        for (File f1 : f.listFiles()
                ) {
            try {
                pm.mergeFiles(f1.getCanonicalFile());
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

/*
        long stopTime;
        long elapsedTime;
        ArrayList<Integer> loc;
        long startTime = System.currentTimeMillis();
        ReadFile read = new ReadFile();

        HashSet<String> stopWords = read.createStopWordsSet("C:\\School\\IR\\Search Engine\\stop_words.txt");
//        HashSet<String> stopWords = read.createStopWordsSet("D:\\documents\\users\\sergayen\\stop_words.txt");
//
        Indexer indexer = new Indexer(stopWords);

        IndexParser parse = new IndexParser(new Parse(), new Stemmer());
        read.setBatchSize(2);
        read.readFiles(new File("C:\\School\\IR\\Search Engine\\corpus"));
        List<List<Doc>> list = read.getDocList();
        stopTime = System.currentTimeMillis();
        elapsedTime = stopTime - startTime;
        System.out.println("Finished Reading Time: " + elapsedTime);
        PostingIO ps = new PostingIO("C:\\School\\IR\\Search Engine");

        for (List<Doc> docList : list) {
            indexer.clearMap();
            for (Doc d : docList) {
                parse.parse(d);
//                List<String> listyTheDirtyList = d.getParseContent();
                indexer.index(d);
//                break;
            }
            ps.createPostingFile(indexer.getTermMap());
//          break;
        }

        System.out.println("-");

        stopTime = System.currentTimeMillis();
        elapsedTime = stopTime - startTime;
        System.out.println("Total Finish Time: " + elapsedTime);*/
    }
}




