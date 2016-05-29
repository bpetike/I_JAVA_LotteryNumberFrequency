package hu.codecool;

import datafilereader.DataFileReader;
import drawevent.DrawEventHandler;
import drawevent.GameType;
import filesplitter.FileSplitter;

import java.util.HashMap;
import java.util.List;


public class Main {

    public static void main(String[] args) {
        DrawEventHandler handler = new DrawEventHandler();
        handler.produceFrequencyList((short) 2014, GameType.HATOS);
        HashMap<Byte, List<Integer>> freq = handler.getFrequencyList();
        for (byte key : freq.keySet())
        {
            System.out.println(key + "  " + freq.get(key));
        }
    }
}
