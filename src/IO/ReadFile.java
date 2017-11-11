package IO;

import java.io.*;
import java.util.HashSet;

public class ReadFile {

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
    public void createDoc(String path) {
        BufferedReader reader = null;
        try {
            PrintWriter writer = null;
            reader = new BufferedReader(new FileReader(path));
            writer = new PrintWriter(new FileWriter("test"));
            String line;
            while (null != (line = reader.readLine())) {
                writer.println(line);
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
                    break;
                } else {
                    System.out.println("file:" + file.getName());
                    createDoc(file.getCanonicalPath());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
