package IO;

import java.io.*;
import java.util.*;

public class PostingIO {
    private String postingPath;
    private int postingFileNum;
    private Map<Character, Writer> termPathMap;


    public PostingIO(String path) {
        postingFileNum = 0;
        initialize(path);
    }

    private void initialize(String path) {
        createPostingFolders(path);
        termPathMap = new HashMap<>();
    }

    private void createPostingFolders(String path) {
        postingPath = path + "\\Posting";
        //TODO: should have a function to delete the folders in start over
        new File(postingPath).mkdirs();
        new File(postingPath + "\\A-E").mkdirs();
        new File(postingPath + "\\F-J").mkdirs();
        new File(postingPath + "\\K-O").mkdirs();
        new File(postingPath + "\\P-T").mkdirs();
        new File(postingPath + "\\U-Z").mkdirs();
        new File(postingPath + "\\0-9").mkdirs();
//        new File(postingPath + "\\JUNK").mkdirs();
    }

    private void createPostingMap() {
        try {
            for (char alphabet = 'a'; alphabet <= 'z'; alphabet++) {
                if (alphabet >= 'a' && alphabet <= 'e')
                    termPathMap.put(alphabet, new BufferedWriter(new FileWriter(new File(postingPath + "\\A-E\\aPosting" + postingFileNum), true)));
                if (alphabet >= 'f' && alphabet <= 'j')
                    termPathMap.put(alphabet, new BufferedWriter(new FileWriter(new File(postingPath + "\\F-J\\aPosting" + postingFileNum), true)));
                if (alphabet >= 'k' && alphabet <= 'o')
                    termPathMap.put(alphabet, new BufferedWriter(new FileWriter(new File(postingPath + "\\K-O\\aPosting" + postingFileNum), true)));
                if (alphabet >= 'p' && alphabet <= 't')
                    termPathMap.put(alphabet, new BufferedWriter(new FileWriter(new File(postingPath + "\\P-T\\aPosting" + postingFileNum), true)));
                if (alphabet >= 'u' && alphabet <= 'z')
                    termPathMap.put(alphabet, new BufferedWriter(new FileWriter(new File(postingPath + "\\U-Z\\aPosting" + postingFileNum), true)));
            }
            for (char i = '0'; i <= '9'; i++) {
                termPathMap.put(i, new BufferedWriter(new FileWriter(new File(postingPath + "\\0-9\\aPosting" + postingFileNum), true)));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void closePostingMap() {
        for (Writer writer :
                termPathMap.values()) {
            try {
                writer.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //Sort using creating a treeMap
    //TODO: delete this
    public void createPostingFile(Map<String, List<String>> tempPosting) {
        try {
            Map<String, List<String>> sortedTempPosting = new TreeMap<>(tempPosting);
            createPostingMap();
            for (Map.Entry<String, List<String>> entry :
                   /* tempPosting */sortedTempPosting.entrySet()) {
                if (Character.isLetter(entry.getKey().charAt(0)) || Character.isDigit(entry.getKey().charAt(0))) { /// This should not be here - parser should work better.
                    String st = entry.getKey().toString() + "*";
                    termPathMap.get(entry.getKey().toLowerCase().charAt(0)).write(st);
                    termPathMap.get(entry.getKey().toLowerCase().charAt(0)).flush();
                    for (int i = 0; i < entry.getValue().size(); i++) {
                        st = entry.getValue().get(i).toString() + " ";
                        termPathMap.get(entry.getKey().toLowerCase().charAt(0)).write(st);
                        termPathMap.get(entry.getKey().toLowerCase().charAt(0)).flush();
                    }
                    termPathMap.get(entry.getKey().toLowerCase().charAt(0)).write("\n");
                    termPathMap.get(entry.getKey().toLowerCase().charAt(0)).flush();
                }
            }
            closePostingMap();
            postingFileNum++;
//            if (postingFileNum == 5){
//                postingFileNum = 0;
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void createDocPosting(Map<String, String> tempDocPosting) {
        try {
            BufferedWriter docWrite = new BufferedWriter(new FileWriter(new File(postingPath + "\\docPosting"), true));
            for (Map.Entry<String, String> entry :
                    tempDocPosting.entrySet()) {
                docWrite.write(entry.getKey() + " " + entry.getValue() + "*");
                docWrite.write("\n");
            }
            docWrite.flush();
            docWrite.close();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    //SORT using sorting the keys in a list. this is the right function
    public void createPostingFile2(Map<String, StringBuilder> tempPosting) {
        try {
            List<String> sortedKeyList = new ArrayList<>(tempPosting.keySet());
            Collections.sort(sortedKeyList);
            createPostingMap();
            for (String term :
                    sortedKeyList) {
                if (Character.isLetter(term.charAt(0)) || Character.isDigit(term.charAt(0))) { /// This should not be here - parser should work better.
                    String st = term + "*";
                    termPathMap.get(term.toLowerCase().charAt(0)).write(st);
                    st = tempPosting.get(term).toString();
                    termPathMap.get(term.toLowerCase().charAt(0)).write(st);
                    termPathMap.get(term.toLowerCase().charAt(0)).write("\n");
                    termPathMap.get(term.toLowerCase().charAt(0)).flush();
                }
            }
            closePostingMap();
            postingFileNum++;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Map<String, String> createPostingDic(int numOfDoc) {
        Map<String, String> postingDic = new HashMap<>();
        File postingFile = new File(postingPath);
        for (File f :
                postingFile.listFiles()) {
            if (f.isDirectory()) {
                File[] tempPostingFile = f.listFiles();
                try {
                    addTermsFromPosting(numOfDoc, postingDic, tempPostingFile[0].getCanonicalPath());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return postingDic;
    }

    private void addTermsFromPosting(int numOfDoc, Map<String, String> postingDic, String canonicalPath) {
        try {
            BufferedReader tempBuffReader = new BufferedReader(new FileReader(canonicalPath));
            String line;
            int lineNum = 1;
            int numTermInDocs;
            double termDfi;
            while (null != (line = tempBuffReader.readLine())) {
                String[] splitLine = line.split("\\*");
                numTermInDocs = splitLine.length - 1;
                termDfi = Math.log(numOfDoc / (float) numTermInDocs);
                postingDic.put(splitLine[0], String.format("%s %s %s %.2f", canonicalPath, lineNum, numTermInDocs, termDfi));
                lineNum++;
            }
            tempBuffReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    private String createTermInfo(String postingLine){
//        String termInfo;
//        String[] splitLine = postingLine.split("\\*");
//        int numTermInDocs = splitLine.length-1;
//    }
}
