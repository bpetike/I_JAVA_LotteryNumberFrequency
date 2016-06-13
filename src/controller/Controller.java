package controller;

import datafilehandler.DataFileReader;
import datafilehandler.FileSplitter;
import datafilehandler.UpdateChecker;
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
        UpdateChecker uc = new UpdateChecker();
        FileSplitter fs = new FileSplitter();
        String basePath = DataFileReader.BASEPATH;
        makeDataFolderIfNotExists(basePath);
        String event590RawFilePath = basePath + FileSplitter.EVENT590RAWDATAFILEPATH;
        String event645RawFilePath = basePath + FileSplitter.EVENT645RAWDATAFILEPATH;
        String event735RawFilePath = basePath + FileSplitter.EVENT735RAWDATAFILEPATH;
        boolean event590RawFileExists = uc.checkForRawDataFile(event590RawFilePath);
        performUpdate(uc, fs, event590RawFilePath, event645RawFilePath, event735RawFilePath);

        return true;
    }

    private void performUpdate(UpdateChecker uc, FileSplitter fs, String event590RawFilePath, String event645RawFilePath, String event735RawFilePath)
    {
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
    }

    private void makeDataFolderIfNotExists(String basePath)
    {
        File dataFolder = new File(basePath + "\\data\\");
        if (!dataFolder.exists())
        {
            boolean newFile = dataFolder.mkdir();
        }
    }
}
