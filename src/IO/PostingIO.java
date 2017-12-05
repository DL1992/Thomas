package IO;

import java.io.*;
import java.util.*;

public class PostingIO {
    private String postingPath;
    private int postingFileNum;
    private Map<Character, Writer> termPathMap;

    //    private Map<Character, String> termPathMap;

    public PostingIO(String path) {
        postingFileNum = 0;
        initialize(path);
    }

    private void initialize(String path) {
        createPostingFolders(path);
        termPathMap = new HashMap<>();
//        createPostingMap(path);
    }

    private void createPostingFolders(String path) {
        postingPath = path + "\\Posting";
        new File(postingPath).mkdirs();
        new File(postingPath + "\\A-E").mkdirs();
        new File(postingPath + "\\F-J").mkdirs();
        new File(postingPath + "\\K-O").mkdirs();
        new File(postingPath + "\\P-T").mkdirs();
        new File(postingPath + "\\U-Z").mkdirs();
        new File(postingPath + "\\0-9").mkdirs();
//        new File(postingPath + "\\JUNK").mkdirs();
    }

    private void createPostingMap() throws IOException {
//        Map<Character, Writer> termPathMap = new HashMap<>();
        for (char alphabet = 'a'; alphabet <= 'z'; alphabet++) {
            if (alphabet >= 'a' && alphabet <= 'e')
                termPathMap.put(alphabet, new BufferedWriter(new FileWriter(new File(postingPath + "\\A-E\\posting" + postingFileNum), true)));
            if (alphabet >= 'f' && alphabet <= 'j')
                termPathMap.put(alphabet, new BufferedWriter(new FileWriter(new File(postingPath + "\\F-J\\posting" + postingFileNum), true)));
            if (alphabet >= 'k' && alphabet <= 'o')
                termPathMap.put(alphabet, new BufferedWriter(new FileWriter(new File(postingPath + "\\K-O\\posting" + postingFileNum), true)));
            if (alphabet >= 'p' && alphabet <= 't')
                termPathMap.put(alphabet, new BufferedWriter(new FileWriter(new File(postingPath + "\\P-T\\posting" + postingFileNum), true)));
            if (alphabet >= 'u' && alphabet <= 'z')
                termPathMap.put(alphabet, new BufferedWriter(new FileWriter(new File(postingPath + "\\U-Z\\posting" + postingFileNum), true)));
        }
        for (char i = '0'; i <= '9'; i++) {
            termPathMap.put(i, new BufferedWriter(new FileWriter(new File(postingPath + "\\0-9\\posting" + postingFileNum), true)));
        }
//        this.termPathMap = termPathMap;
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
    public void createPostingFile(Map<String, List<String>> tempPosting) {
        try {
            Map<String, List<String>> sortedTempPosting = new TreeMap<>(tempPosting);
            createPostingMap();
            for (Map.Entry<String, List<String>> entry :
                   /* tempPosting */sortedTempPosting.entrySet()) {
                if (Character.isLetter(entry.getKey().charAt(0)) || Character.isDigit(entry.getKey().charAt(0))) { /// This should not be here - parser should work better.
                    String st = entry.getKey().toString() + "*" + entry.getValue().get(0).toString();
                    termPathMap.get(entry.getKey().toLowerCase().charAt(0)).write(st);
                    termPathMap.get(entry.getKey().toLowerCase().charAt(0)).flush();
                    for (int i = 1; i < entry.getValue().size(); i++) {
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

    //SORT using sorting the keys in a list.
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
//                    termPathMap.get(term.toLowerCase().charAt(0)).flush();
                    st = tempPosting.get(term).toString() + " ";
                    termPathMap.get(term.toLowerCase().charAt(0)).write(st);
//                    termPathMap.get(term.toLowerCase().charAt(0)).flush();
                    termPathMap.get(term.toLowerCase().charAt(0)).write("\n");
                    termPathMap.get(term.toLowerCase().charAt(0)).flush();
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
}
