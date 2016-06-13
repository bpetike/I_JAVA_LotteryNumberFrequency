package drawevent;

import datafilehandler.DataFileReader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by BontaPeter on 2016. 05. 17..
 * This class collects draw event data and produces the result list.
 */
public class DrawEventHandler
{
    private List<DrawEvent> collectedEvents;
    private HashMap<Byte, List<Integer>> frequencyList;
    private DrawEventCreator eventCreator;

    public DrawEventHandler()
    {
        collectedEvents = new ArrayList<>();
        frequencyList = new HashMap<>();
        eventCreator = new DrawEventCreator();

    }

    /**
     * This method returns the frequency list of lottery numbers
     * @return HashMap<Byte, List<Integer>> - a map of lottery numbers as keys and list of week numbers as values
     */
    public HashMap<Byte, List<Integer>> getFrequencyList()
    {
        return frequencyList;
    }

    /**
     * This method creates the frequency list of lottery numbers
     * @param year - a chosen year number
     * @param gameType - a chosen game type
     */
    public void produceFrequencyList(short year, GameType gameType)
    {
        collectDrawEvents(year, gameType);
        if (collectedEvents != null && collectedEvents.size() > 0)
        {
            for (byte i = 1; i < gameType.getMaxNumber() + 1; i++) {
                Set<Integer> temp = new HashSet<>();
                for (DrawEvent event : collectedEvents)
                {
                    for (byte number : event.getNumbers())
                    {
                        if (i == number)
                        {
                            temp.add((int) event.getWeekNumber());
                        }
                    }
                }
                List<Integer> weeks = new ArrayList<>(temp);
                Collections.sort(weeks);
                frequencyList.put(i, weeks);
            }
        }
    }

    private List<DrawEvent> collectDrawEvents(short year, GameType gameType)
    {
        List<String> lines = DataFileReader.readLines(year, gameType);
        if (lines != null && lines.size() > 0)
        {
            for (String line : lines)
            {
                DrawEvent event = eventCreator.getEvent(gameType);
                event.parse(line);
                collectedEvents.add(event);
            }
        }
        Collections.sort(collectedEvents);
        return collectedEvents;
    }
}
