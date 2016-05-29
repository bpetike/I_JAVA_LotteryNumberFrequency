package hu.codecool;

import datafilereader.DataFileReader;
import drawevent.DrawEventHandler;
import drawevent.GameType;
import filesplitter.FileSplitter;

public class Main {

    public static void main(String[] args) {
        DrawEventHandler handler = new DrawEventHandler();
        handler.produceFrequencyList((short) 2015, GameType.OTOS);

    }
}
