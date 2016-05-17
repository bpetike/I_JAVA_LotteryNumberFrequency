package datafilereader;

import drawevent.GameType;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BontaPeter on 2016. 05. 17..
 * This class is responsible for reading data from specified data file by year.
 */
public class DataFileReader
{
    public static List<String> readLines(int year, GameType gameType) {
        String basePath = new File("").getAbsolutePath();
        String filePath = basePath + "\\data\\" + gameType.toString().toLowerCase() + String.valueOf(year) + ".csv";
        File file = new File(filePath);
        List<String> lines = new ArrayList<>();
        try
        {
            BufferedReader bReader = new BufferedReader(new FileReader(file));
            String line;
            while ((line = bReader.readLine()) != null) {
                lines.add(line);
            }
            bReader.close();
        } catch (FileNotFoundException e)
        {
            System.out.println("File not found.");
            e.printStackTrace();
        } catch (IOException e)
        {
            System.out.println("Something went wrong, can't read file.");
            e.printStackTrace();
        }
        return lines;
    }
}
