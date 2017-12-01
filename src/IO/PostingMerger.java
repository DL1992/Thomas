package IO;

import java.io.*;

public class PostingMerger {
    private int mergeNum;


    public PostingMerger() {
        this.mergeNum = 0;
    }

    public void mergeFiles(File mergePath) {
        File[] postingFiles = mergePath.listFiles();
        int counter = postingFiles.length;
        while (counter > 1) {
            postingFiles = mergePath.listFiles();
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
            BufferedWriter mergePostWriter = new BufferedWriter(new FileWriter(new File(mergePath + "\\M" + mergeNum)));
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
                mergePostWriter.write(secondPostLine + "\n");
                while (null != (secondPostLine = secondPostReader.readLine())) {
                    mergePostWriter.write(secondPostLine + "\n");
                    mergePostWriter.flush();
                }
            } else {
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
