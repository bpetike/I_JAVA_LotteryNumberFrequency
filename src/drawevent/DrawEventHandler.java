package drawevent;

import datafilereader.DataFileReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by BontaPeter on 2016. 05. 17..
 * This class collects draw event data and produces the result list.
 */
public class DrawEventHandler
{
    private List<DrawEvent> collectedEvents;
    private HashMap<Byte, Integer> frequencyList;

    public DrawEventHandler()
    {
        collectedEvents = new ArrayList<>();
        frequencyList = new HashMap<>();

    }

    public HashMap<Byte, Integer> getFrequencyList()
    {
        return frequencyList;
    }

    private List<DrawEvent> collectDrawEvents(short year, GameType gameType)
    {
        List<String> lines = DataFileReader.readLines(year, gameType);
        if (lines != null && lines.size() > 0)
        {
            for (String line : lines)
            {
                DrawEventCreator eventCreator = new DrawEventCreator();
                DrawEvent event = eventCreator.getEvent(gameType);
                event.parse(line);
                collectedEvents.add(event);
            }
        }
        Collections.sort(collectedEvents);
        return collectedEvents;
    }

    public void produceFrequencyList(short year, GameType gameType)
    {
        collectDrawEvents(year, gameType);
        
    }
}
