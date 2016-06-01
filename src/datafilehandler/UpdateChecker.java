package datafilehandler;


import datafilehandler.DataFileReader;
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

/**
 * Created by BontaPeter on 2016. 05. 29..
 * This class downloads the updated data files.
 */
public class UpdateChecker
{
    public static final String EVENT590URL = "http://www.szerencsejatek.hu/xls/otos.csv";
    public static final String EVENT645URL = "http://www.szerencsejatek.hu/xls/hatos.csv";
    public static final String EVENT735URL = "http://www.szerencsejatek.hu/xls/skandi.csv";

    public boolean checkForUpdate(String fileURL, String rawfilePath)
    {
        return !readFirstLineOnInternet(fileURL).equals(readFirstLineOnDisk(rawfilePath));
    }

    public void downloadUpdate(String fileURL, boolean rawFile) {
        try
        {
            URL url = new URL(fileURL);
            InputStream inStream = url.openStream();
            InputStreamReader iStReader = new InputStreamReader(inStream);
            BufferedReader bReader = new BufferedReader(iStReader);
            String targetFileName = getTargetFileName(fileURL);
            String path = DataFileReader.BASEPATH  + "\\data\\";
            BufferedWriter bWriter;
            if (rawFile)
            {
                File targetFile = new File(path + targetFileName);
                bWriter = new BufferedWriter(new FileWriter(targetFile, true));
                String line;
                while ((line = bReader.readLine()) != null)
                {
                    bWriter.write(line + "\n");
                }

            } else
            {
                bReader.mark(1);
                String firstLine = bReader.readLine();
                int beginValue = Integer.valueOf(firstLine.split(";")[1]);
                boolean result;
                targetFileName = targetFileName.substring(0, targetFileName.length()-4) +
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

    public boolean checkForRawDataFile(String rawFilePath) {
        File file = new File(rawFilePath);
        return !file.exists();
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
            System.out.println("There is something fishy with the URL. " + mfue.getMessage());
        } catch (IOException ioe)
        {
            System.out.println("Cannot open stream. " + ioe.getMessage());
        }
        return firstLine;
    }

    private String getTargetFileName(String fileURL) {
        String[] urlParts = fileURL.split("/");
        return urlParts[4];
    }
}
