package Test;

import IO.ReadFile;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        ReadFile read = new ReadFile();
        read.readFiles(new File("C:\\School\\IR\\Search Engine\\corpus"));
    }
}
