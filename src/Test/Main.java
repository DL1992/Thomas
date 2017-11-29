package Test;

import IO.ReadFile;
import algorithms.Doc;
import algorithms.IndexParser;
import algorithms.Parse;
import algorithms.Parser;

import java.io.File;
import java.util.ArrayList;
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

        long stopTime;
        long elapsedTime;

        long startTime = System.currentTimeMillis();
        ReadFile read = new ReadFile();
        IndexParser parse = new IndexParser(new Parse());
        read.setBatchSize(1815);
        read.readFiles(new File("D:\\documents\\users\\laadan\\dorpus"));
        List<List<Doc>> list = read.getDocList();
        stopTime = System.currentTimeMillis();
        elapsedTime = stopTime - startTime;
        System.out.println(elapsedTime);
        ArrayList<Integer> loc;
        for (List<Doc> docList : list) {
            for (Doc d: docList) {
                parse.parse(d);

//                for (int i = 0; i < d.getParseContent().size(); i++) {
//                    loc = d.termInDocLoc(d.getParseContent().get(i));
//                    for (int j:
//                         loc) {
////                        System.out.println(j);
//                    }
////                    System.out.println(String.format("the term %s, doc location:", d.getParseContent().get(i)));
//                }
//                System.out.println(d);
//                System.out.println("gf");
//                break;
            }
//            break;
        }
//        List<String> tl =  parse.parse("3\\2");
//        System.out.println(tl.get(0));
         stopTime = System.currentTimeMillis();
         elapsedTime = stopTime - startTime;
        System.out.println(elapsedTime);
    }
}



