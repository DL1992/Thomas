package IO;

import algorithms.Doc;

import java.io.*;
import java.util.*;

public class ReadFile {
    private List<Doc> docList;

    public ReadFile() {
        this.docList = new ArrayList<>();
    }

    public List<Doc> getDocList() {
        return docList;
    }

    /**
     * Creates a hash set of stop words from a given file.
     * this set will be used by the parser in order to remove them from documents in the Collection
     *
     * @param path is the path to the file containing the stop words.
     * @return the hash set of stop words.
     */
    public HashSet<String> createStopWordsSet(String path) {
        HashSet<String> stopWordsSet = new HashSet<>();
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader(path));
            String stopWord;
            while (null != (stopWord = reader.readLine())) {
                stopWordsSet.add(stopWord);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != reader)
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
        }
        return stopWordsSet;
    }


    public void createDocs(String path) throws IOException {
        BufferedReader reader = null;
        String docName = null;
        String docContent = null;
        List<String> docContentList = null;
        try {
            reader = new BufferedReader(new FileReader(path));
            String line;
            while (null != (line=reader.readLine())) {
                if (line.contains("<DOC>")) {
                    line = reader.readLine();
                    docName = findName(line);
                    docContentList = new ArrayList<>();
                } else if (line.contains("<TEXT>")) {
                    while((null != (line=reader.readLine())) && !line.contains("</TEXT>")) {
                        docContentList.add(line);
                    }
                    docContent = joinString(docContentList);
//                    Doc doc = new Doc(docName, docContent);
//                    System.out.println(doc.toString());
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != reader)
                reader.close();
        }
    }

    private String joinString(List<String> docContentList) {
        StringJoiner joinedStrings = new StringJoiner(" ");
        for (String docLine : docContentList) {
            joinedStrings.add(docLine);
        }
        return joinedStrings.toString();
    }

    private String findName(String line) {
        String docName = "";
        for (int i = 0; i < line.length(); i++) {
            if (line.charAt(i)=='>'){
                i++;
                while(line.charAt(i+1) != '<'){
                    docName+=line.charAt(i);
                    i++;
                }
                return docName;
            }
        }
        return "";
    }

    /**
     * Iterates over all the files in a given directory.
     *
     * @param dir is the main\home directory
     */
    public void readFiles(File dir) {
        try {
            File[] files = dir.listFiles();
            for (File file : files) {
                File[] insideFile = file.listFiles();
                createDocs(insideFile[0].getCanonicalPath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
