package hu.codecool;

import updatechecker.UpdateChecker;


public class Main {

    public static void main(String[] args) {
        UpdateChecker uc = new UpdateChecker();
        uc.downloadUpdate(UpdateChecker.EVENT735URL);
    }
}
