package controller;

import datafilehandler.DataFileReader;
import datafilehandler.FileSplitter;
import datafilehandler.UpdateChecker;
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

    public  boolean updateFiles()
    {
        UpdateChecker uc = new UpdateChecker();
        FileSplitter fs = new FileSplitter();
        String basePath = DataFileReader.BASEPATH;
        String event590RawFilePath = basePath + FileSplitter.EVENT590RAWDATAFILEPATH;
        String event645RawFilePath = basePath + FileSplitter.EVENT645RAWDATAFILEPATH;
        String event735RawFilePath = basePath + FileSplitter.EVENT735RAWDATAFILEPATH;
        boolean event590RawFileExists = uc.checkForRawDataFile(event590RawFilePath);
        if (uc.checkForRawDataFile(event590RawFilePath) || uc.checkForAllSplitFiles(event590RawFilePath, GameType.OTOS))
        {
            uc.downloadUpdate(UpdateChecker.EVENT590URL, true);
            fs.splitRawDataFile(event590RawFilePath);

        }
        if (uc.checkForRawDataFile(event645RawFilePath) || uc.checkForAllSplitFiles(event645RawFilePath, GameType.HATOS))        {
            uc.downloadUpdate(UpdateChecker.EVENT645URL, true);
            fs.splitRawDataFile(event645RawFilePath);
        }
        if (uc.checkForRawDataFile(event735RawFilePath) || uc.checkForAllSplitFiles(event735RawFilePath, GameType.SKANDI))
        {
            uc.downloadUpdate(UpdateChecker.EVENT735URL, true);
            fs.splitRawDataFile(event735RawFilePath);
        }
        if (uc.checkForUpdate(UpdateChecker.EVENT590URL, event590RawFilePath))
        {
            uc.downloadUpdate(UpdateChecker.EVENT590URL, false);
        }
        if (uc.checkForUpdate(UpdateChecker.EVENT645URL, event645RawFilePath))
        {
            uc.downloadUpdate(UpdateChecker.EVENT645URL, false);
        }
        if (uc.checkForUpdate(UpdateChecker.EVENT735URL, event735RawFilePath))
        {
            uc.downloadUpdate(UpdateChecker.EVENT735URL, false);
        }

        return true;
    }
}
