package controller;

import drawevent.DrawEventHandler;
import drawevent.GameType;
import validator.Validator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by BontaPeter on 2016. 05. 31..
 */
public class Controller
{
    private short year;
    private GameType gameType;
    private Map<Byte, List<Integer>> resultList;

    public Controller()
    {
        resultList = new HashMap<>();
    }

    public boolean performTask(String year, String gameType)
    {
        this.gameType = GameType.getGameType(gameType);
        if (Validator.checkYear(year, this.gameType))
        {
            this.year = Short.parseShort(year);
            DrawEventHandler handler = new DrawEventHandler();
            handler.produceFrequencyList(this.year, this.gameType);
            resultList = handler.getFrequencyList();
            return true;
        }
        return false;
    }


    public Map<Byte, List<Integer>> getResultList()
    {
        return resultList;
    }
}
