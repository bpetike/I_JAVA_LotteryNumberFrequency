package datafilereader;

import drawevent.GameType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by BontaPeter on 2016. 05. 17..
 * This class is responsible for reading data from specified data file by year.
 */
public class DataFileReader
{
    public static final String BASEPATH = new File("").getAbsolutePath();

    public static List<String> readLines(int year, GameType gameType) {
        String filePath = BASEPATH + "\\data\\" + gameType.toString().toLowerCase() + String.valueOf(year) + ".csv";
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
        } catch (IOException e)
        {
            return null;
        }
        return lines;
    }
}
