package controller;

import datafilehandler.DataFileReader;
import datafilehandler.FileSplitter;
import datafilehandler.Updater;
import drawevent.DrawEventHandler;
import drawevent.GameType;
import validator.Validator;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by BontaPeter on 2016. 05. 31..
 * This class is the controller class of the graphical user interface.
 */
public class Controller
{
    private short year;
    private GameType gameType;
    private Map<Byte, List<Integer>> resultList;
    private Updater updater;
    private FileSplitter splitter;

    public Controller()
    {
        resultList = new HashMap<>();
    }

    /**
     * This method performs the event collecting and the production of frequency list, which will be
     * displayed to the user.
     * @param year String - This is the year number from the user input
     * @param gameType String - The game type given by the user
     * @return boolean - Returns true if the production of the frequency list succeeds.
     */

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


    /**
     * This method is used on the main frame to get the produced freqency list.
     * @return HashMap with byte keys and list of intergers as values.
     */

    public Map<Byte, List<Integer>> getResultList()
    {
        return resultList;
    }

    /**
     * This method is used on the update frame to update the data files.
     * @return boolean - Returns true when the update is complete.
     */

    public  boolean updateFiles()
    {
        updater = new Updater();
        splitter = new FileSplitter();
        String basePath = DataFileReader.BASEPATH;
        makeDataFolderIfNotExists(basePath);
        String event590RawFilePath = basePath + FileSplitter.EVENT590RAWDATAFILEPATH;
        String event645RawFilePath = basePath + FileSplitter.EVENT645RAWDATAFILEPATH;
        String event735RawFilePath = basePath + FileSplitter.EVENT735RAWDATAFILEPATH;
        boolean event590RawFileExists = updater.checkForRawDataFile(event590RawFilePath);
        if (updater != null && splitter != null)
        {
            performUpdate(event590RawFilePath, event645RawFilePath, event735RawFilePath);
        }

        return true;
    }

    private void performUpdate(String event590RawFilePath, String event645RawFilePath, String event735RawFilePath)
    {
        if (updater.checkForRawDataFile(event590RawFilePath) || updater.checkForAllSplitFiles(event590RawFilePath, GameType.OTOS))
        {
            updater.downloadUpdate(Updater.EVENT590URL, true);
            splitter.splitRawDataFile(event590RawFilePath);

        }
        if (updater.checkForRawDataFile(event645RawFilePath) || updater.checkForAllSplitFiles(event645RawFilePath, GameType.HATOS))        {
            updater.downloadUpdate(Updater.EVENT645URL, true);
            splitter.splitRawDataFile(event645RawFilePath);
        }
        if (updater.checkForRawDataFile(event735RawFilePath) || updater.checkForAllSplitFiles(event735RawFilePath, GameType.SKANDI))
        {
            updater.downloadUpdate(Updater.EVENT735URL, true);
            splitter.splitRawDataFile(event735RawFilePath);
        }
        if (updater.checkForUpdate(Updater.EVENT590URL, event590RawFilePath))
        {
            updater.downloadUpdate(Updater.EVENT590URL, false);
        }
        if (updater.checkForUpdate(Updater.EVENT645URL, event645RawFilePath))
        {
            updater.downloadUpdate(Updater.EVENT645URL, false);
        }
        if (updater.checkForUpdate(Updater.EVENT735URL, event735RawFilePath))
        {
            updater.downloadUpdate(Updater.EVENT735URL, false);
        }
    }

    private void makeDataFolderIfNotExists(String basePath)
    {
        File dataFolder = new File(basePath + File.pathSeparator + "data" + File.pathSeparator);
        if (!dataFolder.exists())
        {
            boolean newFile = dataFolder.mkdir();
        }
    }
}
