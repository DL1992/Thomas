package Test;

import IO.ReadFile;
import algorithms.Parse;
import algorithms.Parser;

import java.io.File;

public class Main {
    public static void main(String[] args) {
//        long hz= Runtime.getRuntime().totalMemory();
//        System.out.println(hz);

        long startTime = System.currentTimeMillis();
        ReadFile read = new ReadFile();
        Parser parse = new Parse();
//        String s = "bla bla bla 23/34 blac";
////        System.out.println("s,t,y".split(",").toString());
        read.readFiles(new File("D:\\documents\\users\\sergayen\\FUN\\corpus"));
//        read.readFiles(new File("C:\\School\\IR\\Search Engine\\corpus"));
//        List<Doc> list = read.getDocList();
//        for (Doc d :
//                list) {
//            System.out.println(d.getDocName());
//            List<String> tl = parse.parse(d.getContent());
//        }
        long stopTime = System.currentTimeMillis();
        long elapsedTime = stopTime - startTime;
        System.out.println(elapsedTime);
//            if (d.getDocName().equals("FBIS3-47"))
//                System.out.println(d);
//        System.out.println("May 1994 apr 12 2013");
//        List<String> tl = parse.parse("Dor May 1994");
//        for (String s : tl
//                ) {
//            System.out.println(s);

    }

}
//}


