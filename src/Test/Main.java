package Test;

import IO.ReadFile;
import algorithms.Doc;
import algorithms.Parse;
import algorithms.Parser;

import java.io.File;
import java.util.List;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
//        long hz= Runtime.getRuntime().totalMemory();
//        System.out.println(hz);
//        Pattern capitalLetterPattern = Pattern.compile(".*[A-Z].*");
//        String st= "dasdja-das--das.%%()#$%%^&&*(_)(()_()dka--sd";
//        if (capitalLetterPattern.matcher(st).matches())
//            System.out.println("yay");




        long startTime = System.currentTimeMillis();
        ReadFile read = new ReadFile();
        Parser parse = new Parse();
//        String s = "bla bla bla 23/34 blac";
////        System.out.println("s,t,y".split(",").toString());
        read.readFiles(new File("D:\\documents\\users\\laadan\\dorpus"));
        List<List<Doc>> list = read.getDocList();
        for (List<Doc> docList : list) {
            for (Doc d: docList) {
                List<String> tl = parse.parse(d.getContent());
            }
            break;
        }
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println(elapsedTime);
    }
}



