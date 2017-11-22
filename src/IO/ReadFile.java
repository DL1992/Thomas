package IO;

import algorithms.Doc;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;

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

    //Noneses - just trying things out
    public void createDocs(String path) {
        Scanner reader = null;
        String docName = null;
        int docLength = 0;
        try {
            reader = new Scanner(new BufferedReader(new FileReader(path)));
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                if (line.contains("<DOC>")) {
                    line = reader.nextLine();
                    docName = line.substring(line.indexOf(" ") + 1, line.lastIndexOf(" "));
                    docLength = 0;
                } else if (line.contains("<TEXT>")) {
                    StringBuilder sb = new StringBuilder();
                    while (reader.hasNextLine() && !line.contains("</TEXT>")) {
                        line = reader.nextLine().trim().replaceAll("\\s+", " ");
                        if (line.matches("")) {
                            continue;
                        }
                        //TODO: remove if's and printing. also num=... should be removed and replace in docLength=
                        if (docName.equals("FBIS3-47"))
                            System.out.print(line + " ");
                        sb.append(line);
                        int num = line.split(" ").length;
                        if (docName.equals("FBIS3-47"))
                            System.out.println(num);
                        docLength += num;
                    }
                    Doc doc = new Doc(docLength - 1, docName, sb.toString());
                    docList.add(doc);
//                    break;
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (null != reader)
                reader.close();
        }
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
                if (file.isDirectory()) {
                    System.out.println("directory " + file.getCanonicalPath());
                    readFiles(file);
                    break; //Don't belong here!!!
                } else {
                    System.out.println("file:" + file.getName());
                    createDocs(file.getCanonicalPath());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
