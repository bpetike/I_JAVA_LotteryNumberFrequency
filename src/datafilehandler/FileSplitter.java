package datafilehandler;

import datafilehandler.DataFileReader;
import validator.Validator;

import java.io.*;

/**
 * Created by BontaPeter on 2016. 05. 17..
 * This class is responsible for splitting raw data files.
 */
public class FileSplitter
{
    public static final String EVENT590RAWDATAFILEPATH =
            File.separator + "data"+ File.separator + "otos.csv";
    public static final String EVENT645RAWDATAFILEPATH =
            File.separator + "data"+ File.separator + "hatos.csv";
    public static final String EVENT735RAWDATAFILEPATH =
            File.separator + "data"+ File.separator + "skandi.csv";

    /**
     * This method splits a raw data file given by its path into separate files by year.
     * @param rawFilePath - the path of the file to be split
     */

    public void splitRawDataFile(String rawFilePath)
    {
        deleteExistingDataFiles(rawFilePath);
        File rawDataFile = new File(rawFilePath);
        BufferedReader bReader;
        BufferedWriter bWriter = null;
        try
        {
            bReader = new BufferedReader(new FileReader(rawDataFile));
            String line;
            int year = Validator.CURRENTYEAR;
            String yearString = String.valueOf(year);
            String targetFile;
            writeToFile(rawFilePath, bReader, year, yearString);
            bReader.close();
        } catch (FileNotFoundException fnfe)
        {
            System.out.println("The data file not found!");
            fnfe.printStackTrace();
        } catch (IOException ioe)
        {
            System.out.println("Something went wrong, can't read data file.");
            ioe.printStackTrace();
        }

    }

    private void writeToFile(String rawFilePath, BufferedReader bReader, int year, String yearString) throws IOException
    {
        String line;
        String targetFile;
        String dataFolderName = File.separator + "data" + File.separator;
        while ((line = bReader.readLine()) != null)
        {
            String[] splitedLine = line.split(";");
            targetFile = DataFileReader.BASEPATH + dataFolderName + getFileName(rawFilePath) + yearString + ".csv";
            if (splitedLine[0].equals(yearString)) {
                writeLine(targetFile, line);
            } else
            {
                yearString = String.valueOf(--year);
                targetFile = DataFileReader.BASEPATH + dataFolderName + getFileName(rawFilePath) + yearString + ".csv";
                writeLine(targetFile, line);
            }
        }
    }

    private void writeLine(String fileName, String line) throws IOException
    {
        BufferedWriter bWriter;
        File newFile = new File(fileName);
        bWriter = new BufferedWriter(new FileWriter(newFile, true));
        bWriter.write(line + "\n");
        bWriter.close();
    }

    private String getFileName(String filePath) {
        File file = new File(filePath);
        String fileName = file.getName();
        int fileNameLength = fileName.length();
        int EXTENSION_LENGTH = 4;
        return fileName.substring(0, fileNameLength - EXTENSION_LENGTH);
    }

    private void deleteExistingDataFiles(String filePath)
    {
        File path = new File(DataFileReader.BASEPATH + File.separator + "data" + File.separator);
        File[] files = path.listFiles();
        String fileNamePrefix = getFileName(filePath);
        if (files != null)
        {
            for (File file : files)
            {
                boolean result = false;
                if (file.getName().matches(fileNamePrefix+"\\d+.*"))
                {
                    result = file.delete();
                }
            }
        }
    }
}
