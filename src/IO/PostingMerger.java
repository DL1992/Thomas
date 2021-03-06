package IO;

import java.io.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class PostingMerger {
    private volatile int mergeNum;
    private char mergeChar;
    private ExecutorService threadPool;


    public PostingMerger() {
        this.mergeNum = 0;
        this.mergeChar ='z';
    }

    public void threadMerge(File mergePath, boolean mergeFlag) {
        this.threadPool = Executors.newCachedThreadPool();
        for (File f :
                mergePath.listFiles()) {
            if (f.isDirectory())
                threadPool.execute(() -> {
                    try {
                        mergeFiles(f.getCanonicalFile(), mergeFlag);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
        }
        threadPool.shutdown();
        try {
            threadPool.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        mergeChar -=1;
    }

    public void mergeFiles(File mergePath, boolean mergeFlag) {
        File[] postingFiles = mergePath.listFiles();
        int counter = postingFiles.length;
        while (counter > 1) {
            postingFiles = mergePath.listFiles();
            if(!mergeFlag){
                if((postingFiles[1].length()) > (150000*1024)) { //Merge only big files of more them 150MB
                    if(!(postingFiles[0].length() > (150000*1024))){
                        counter--;
                        continue;
                    }
                }
            }
            try {
                mergePostingFiles(mergePath.getCanonicalPath(), postingFiles[0].getCanonicalPath(), postingFiles[1].getCanonicalPath());
                new File(postingFiles[0].getCanonicalPath()).delete();
                new File(postingFiles[1].getCanonicalPath()).delete();
                counter--;
                mergeNum++;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void mergePostingFiles(String mergePath, String firstPosting, String secondPosting) {
        try {
            BufferedReader firstPostReader = new BufferedReader(new FileReader(firstPosting));
            BufferedReader secondPostReader = new BufferedReader(new FileReader(secondPosting));
            BufferedWriter mergePostWriter = new BufferedWriter(new FileWriter(new File(mergePath + "\\" + mergeChar + mergeNum)));
            String firstPostLine = firstPostReader.readLine();
            String secondPostLine = secondPostReader.readLine();
            while (null != firstPostLine && null != secondPostLine) {
                int compareInt = compareTerm(firstPostLine, secondPostLine);
                if (compareInt > 0) {
                    mergePostWriter.write(secondPostLine + "\n");
                    secondPostLine = secondPostReader.readLine();
                } else if (compareInt < 0) {
                    mergePostWriter.write(firstPostLine + "\n");
                    firstPostLine = firstPostReader.readLine();
                } else {
                    StringBuilder mergeLine = new StringBuilder();
                    mergeLine.append(firstPostLine + secondPostLine.substring(secondPostLine.indexOf("*") + 1, secondPostLine.length()));
                    mergeLine.append("\n");
                    mergePostWriter.write(mergeLine.toString());
                    firstPostLine = firstPostReader.readLine();
                    secondPostLine = secondPostReader.readLine();
                }
                mergePostWriter.flush();
            }
            if (null == firstPostLine) {
                if (null != secondPostLine)
                    mergePostWriter.write(secondPostLine + "\n");
                while (null != (secondPostLine = secondPostReader.readLine())) {
                    mergePostWriter.write(secondPostLine + "\n");
                    mergePostWriter.flush();
                }
            } else {
                if (null != firstPostLine)
                    mergePostWriter.write(firstPostLine + "\n");
                while (null != (firstPostLine = firstPostReader.readLine())) {
                    mergePostWriter.write(firstPostLine + "\n");
                    mergePostWriter.flush();
                }
            }
            mergePostWriter.close();
            firstPostReader.close();
            secondPostReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int compareTerm(String firstPostLine, String secondPostLine) {
        String s1 = firstPostLine.substring(0, firstPostLine.indexOf("*"));
        String s2 = secondPostLine.substring(0, secondPostLine.indexOf("*"));
        return s1.compareTo(s2);
    }
}
