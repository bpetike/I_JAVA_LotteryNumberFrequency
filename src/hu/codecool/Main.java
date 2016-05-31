package hu.codecool;

import datafilereader.DataFileReader;
import filesplitter.FileSplitter;
import updatechecker.UpdateChecker;


public class Main {

    public static void main(String[] args) {
        UpdateChecker uc = new UpdateChecker();
        String path = DataFileReader.BASEPATH;
        String fileName = FileSplitter.EVENT645RAWDATAFILEPATH;
        boolean rawFileExists = uc.checkForRawDataFile(path + fileName);
        uc.downloadUpdate(UpdateChecker.EVENT645URL, rawFileExists);
    }
}
