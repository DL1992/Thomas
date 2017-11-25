package Test;

import algorithms.Parse;
import algorithms.Parser;

import java.util.List;

public class Main {
    public static void main(String[] args) {
//        ReadFile read = new ReadFile();
////        String s = "bla bla bla 23/34 blac";
//////        System.out.println("s,t,y".split(",").toString());
//        read.readFiles(new File("C:\\School\\IR\\Search Engine\\corpus"));
//        List<Doc> list = read.getDocList();
//        for (Doc d :
//                list) {
//            if (d.getDocName().equals("FBIS3-47"))
//                System.out.println(d);
        Parser parse = new Parse();
        System.out.println("NBA dra ABIgil Mac");
        List<String> tl = parse.parse("NBA dra ABIgil Mac");
        for (String s : tl
                ) {
            System.out.println(s);

        }

    }
}


