package hu.codecool;

import drawevent.DrawEventHandler;
import drawevent.GameType;

public class Main {

    public static void main(String[] args) {
        DrawEventHandler handler = new DrawEventHandler();
        handler.produceFrequencyList((short) 2001, GameType.OTOS);

    }
}
