package filesplitter;

import java.io.*;
import java.util.Calendar;

/**
 * Created by BontaPeter on 2016. 05. 17..
 * This class is responsible for splitting raw data files if necessary.
 */
public class FileSplitter
{
    public static final String EVENT590RAWDATAFILEPATH = "\\data\\otos.csv";
    public static final String EVENT645RAWDATAFILEPATH = "\\data\\hatos.csv";
    public static final String EVENT735RAWDATAFILEPATH = "\\data\\skandi.csv";

    public void splitRawDataFile(String filePath)
    {
        File rawDataFile = new File(filePath);
        BufferedReader bReader;
        BufferedWriter bWriter = null;
        try
        {
            bReader = new BufferedReader(new FileReader(rawDataFile));
            String line;
            String basePath = new File("").getAbsolutePath();
            Calendar now = Calendar.getInstance();
            int year = now.get(Calendar.YEAR);
            String yearString = String.valueOf(year);
            String targetFile;
            while ((line = bReader.readLine()) != null)
            {
                String[] splitedLine = line.split(";");
                targetFile = basePath + "\\data\\" + getFileName(filePath) + yearString + ".csv";
                if (splitedLine[0].equals(yearString)) {
                    writeLine(targetFile, line);
                } else
                {
                    yearString = String.valueOf(year--);
                    targetFile = basePath + "\\data\\" + getFileName(filePath) + yearString + ".csv";
                    writeLine(targetFile, line);
                }
            }

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
        return fileName.substring(0, fileNameLength-4);
    }
}
