package hu.codecool;


import datafilereader.DataFileReader;
import drawevent.DrawEvent;
import drawevent.DrawEventCreator;
import drawevent.GameType;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<String> lines = DataFileReader.readLines(2005, GameType.HATOS);
        List<DrawEvent> events = new ArrayList<>();
        for (String line : lines)
        {
            DrawEventCreator evemtCreator = new DrawEventCreator();
            DrawEvent event = evemtCreator.getEvent(GameType.HATOS);
            event.parse(line);
            events.add(event);
        }
        System.out.println(events.size());
    }
}
