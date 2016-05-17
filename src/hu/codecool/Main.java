package hu.codecool;

import filesplitter.FileSplitter;

import java.io.File;

public class Main {

    public static void main(String[] args) {
        FileSplitter fs = new FileSplitter();
        String basePath = new File("").getAbsolutePath();
        fs.splitRawDataFile(basePath + FileSplitter.EVENT735RAWDATAFILEPATH);
    }
}
