package IO;

import algorithms.Doc;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.StringJoiner;
import java.util.regex.Pattern;

public class ReadFile {
    private List<List<Doc>> docList;
    private Pattern clearSpacesPattern;
    private Pattern clearJunkPattern;
    private int currFileNum;
    private int batchSize;


    public ReadFile() {
        this.docList = new ArrayList<>();
        clearSpacesPattern = Pattern.compile("\\s+");
        clearJunkPattern = Pattern.compile("[^-%.\\w\\s/\"]");
        currFileNum = 0;
        batchSize = 1;
    }

    public void setBatchSize(int batchSize) {
        if (!(batchSize < 0))
            this.batchSize = batchSize;
    }

    public List<List<Doc>> getDocList() {
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

    public List<Doc> createDocs(String path) throws IOException {
        List<Doc> docList = new ArrayList<>();
        BufferedReader reader = null;
        String docName = null;
        String docContent = null;
        List<String> docContentList = null;
        try {
            reader = new BufferedReader(new FileReader(path));
            String line;
            while (null != (line = reader.readLine())) {
                if (line.contains("<DOC>")) {
                    line = reader.readLine();
                    docName = findName(line);
                    docContentList = new ArrayList<>();
                } else if (line.contains("<TEXT>")) {
                    while ((null != (line = reader.readLine())) && !line.contains("</TEXT>")) {
                        docContentList.add(line);
                    }
                    docContent = joinString(docContentList);
                    docContent = clearSpacesPattern.matcher(docContent).replaceAll(" ");
                    docContent = clearJunkPattern.matcher(docContent).replaceAll("");
                    Doc doc = new Doc(docName, docContent);
                    docList.add(doc);
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
        return docList;
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
            if (line.charAt(i) == '>') {
                i++;
                while (line.charAt(i) != '<') {
                    docName += line.charAt(i);
                    i++;
                }
                break;
            }
        }
        return docName;
    }

    /**
     * Iterates over all the files in a given directory.
     *
     * @param dir is the main\home directory
     */
    public void readFiles(File dir) {
        File[] files = dir.listFiles();
        readFilesBatch(files, batchSize);
    }

    private void readFilesBatch(File[] files, int batchSize) {
        if (currFileNum != 0)
            docList.clear();
        try {
            for (int i = 0; i < batchSize; i++) {
                if (currFileNum < files.length) {
                    File[] insideFile = files[currFileNum].listFiles();
                    docList.add(createDocs(insideFile[0].getCanonicalPath()));
                    currFileNum++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
