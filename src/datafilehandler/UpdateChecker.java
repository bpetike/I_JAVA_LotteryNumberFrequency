package datafilehandler;


import datafilehandler.DataFileReader;
import drawevent.GameType;
import validator.Validator;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;

/**
 * Created by BontaPeter on 2016. 05. 29..
 * This class downloads the updated data files.
 */
public class UpdateChecker
{
    public static final String EVENT590URL = "http://www.szerencsejatek.hu/xls/otos.csv";
    public static final String EVENT645URL = "http://www.szerencsejatek.hu/xls/hatos.csv";
    public static final String EVENT735URL = "http://www.szerencsejatek.hu/xls/skandi.csv";
    private final int EXTENSION_LENGTH = 4;

    /**
     * This method checks for a data file needs to be updated.
     * @param fileURL - the URL of a raw data file on the internet
     * @param rawfilePath - the path of a raw data file
     * @return boolean - return true if update is available, false if the files are up to date
     */

    public boolean checkForUpdate(String fileURL, String rawfilePath)
    {
        return !readFirstLineOnInternet(fileURL).equals(readFirstLineOnDisk(rawfilePath));
    }

    /**
     * This method downloads the update from the internet.
     * @param fileURL - the URL of the raw data file on the internet
     * @param rawFile - if true, the method downloads a raw data file,
     *                if false, the method downloads only the updated data
     */

    public void downloadUpdate(String fileURL, boolean rawFile) {
        try
        {
            URL url = new URL(fileURL);
            InputStream inStream = url.openStream();
            InputStreamReader iStReader = new InputStreamReader(inStream);
            BufferedReader bReader = new BufferedReader(iStReader);
            boolean result = false;
            String targetFileName = getTargetFileName(fileURL);
            String path = DataFileReader.BASEPATH  + "\\data\\";
            BufferedWriter bWriter;
            if (rawFile)
            {
                bWriter = updateRawFile(bReader, targetFileName, path);

            } else
            {
                bWriter = updateSplitFile(bReader, targetFileName, path);
            }
            bReader.close();
            bWriter.close();
        } catch (MalformedURLException mfue)
        {
            System.out.println("There is something fishy with the URL. " + mfue.getMessage());
        } catch (IOException ioe)
        {
            System.out.println("Cannot open stream. " + ioe.getMessage());
        }
    }

    /**
     * This method checks if a raw data file is present in the data folder.
     * @param rawFilePath - the path of the raw data file
     * @return boolean - returns true, if the raw data file is present in the folder, otherwise false
     */

    public boolean checkForRawDataFile(String rawFilePath) {
        File file = new File(rawFilePath);
        return !file.exists();
    }

    /**
     * This method checks if a raw data file has all its split files present in the data folder.
     * @param rawFilePath - the path for a raw data file
     * @param gameType - a game type is needed to define the number of split files
     * @return boolean - returns true if all split data files are present, otherwise false
     */

    public boolean checkForAllSplitFiles(String rawFilePath, GameType gameType)
    {
        File rawFile = new File(rawFilePath);
        String folderPath = rawFile.getParent();
        String fileName = rawFile.getName();
        String fileNamePrefix = fileName.substring(0, fileName.length() - EXTENSION_LENGTH);
        File[] files = new File(folderPath).listFiles();
        int counter = 0;
        if (files != null)
        {
            for (File file : files)
            {
                if (file.getName().contains(fileNamePrefix))
                {
                    counter++;
                }
            }
        }
        int difference = Validator.CURRENTYEAR - gameType.getMinYear() + 1;
        return difference != counter - 1;
    }

    private BufferedWriter updateSplitFile(BufferedReader bReader, String targetFileName, String path) throws IOException
    {
        boolean result;
        BufferedWriter bWriter;
        bReader.mark(1);
        String firstLine = bReader.readLine();
        int beginValue = Integer.valueOf(firstLine.split(";")[1]);
        targetFileName = targetFileName.substring(0, targetFileName.length() - EXTENSION_LENGTH) +
                String.valueOf(Validator.CURRENTYEAR) + ".csv";
        File targetFile = new File(path + targetFileName);
        result = targetFile.delete();
        bWriter = new BufferedWriter(new FileWriter(targetFile, true));
        bReader.reset();
        for (int i = beginValue; i > 0; i--)
        {
            String line = bReader.readLine();
            bWriter.write(line + "\n");
        }
        return bWriter;
    }

    private BufferedWriter updateRawFile(BufferedReader bReader, String targetFileName, String path) throws IOException
    {
        boolean result;
        BufferedWriter bWriter;File targetFile = new File(path + targetFileName);
        result = targetFile.delete();
        bWriter = new BufferedWriter(new FileWriter(targetFile, true));
        String line;
        while ((line = bReader.readLine()) != null)
        {
            bWriter.write(line + "\n");
        }
        return bWriter;
    }

    private String readFirstLineOnDisk(String rawfilePath)
    {
        File file = new File(rawfilePath);
        String firstLine = null;
        try
        {
            BufferedReader bReader = new BufferedReader(new FileReader(file));
            firstLine = bReader.readLine();
            bReader.close();
        } catch (IOException e)
        {
            return null;
        }
        return firstLine;

    }

    private String readFirstLineOnInternet(String fileURL)
    {
        String firstLine = null;
        try
        {
            URL url = new URL(fileURL);
            InputStream inStream = url.openStream();
            InputStreamReader iStReader = new InputStreamReader(inStream);
            BufferedReader bReader = new BufferedReader(iStReader);
            firstLine = bReader.readLine();
            bReader.close();
        } catch (MalformedURLException mfue)
        {
            System.out.println("There is something wrong with the URL. " + mfue.getMessage());
        } catch (IOException ioe)
        {
            System.out.println("Cannot open stream. " + ioe.getMessage());
        }
        return firstLine;
    }

    private String getTargetFileName(String fileURL) {
        String[] urlParts = fileURL.split("/");
        int URL_LAST_PART_INDEX = 4;
        return urlParts[URL_LAST_PART_INDEX];
    }
}
