package drawevent;

import validator.Validator;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by BontaPeter on 2016. 05. 17..
 * This class describes a draw event of 6/45 game type.
 */
public class DrawEvent645 implements DrawEvent
{
    private short year;
    private byte weekNumber;
    private byte[] numbers;

    public DrawEvent645()
    {
        numbers = new byte[7];
    }

    public short getYear()
    {
        return year;
    }

    public byte getWeekNumber()
    {
        return weekNumber;
    }

    public byte[] getNumbers()
    {
        return numbers;
    }

    @Override
    public void parse(String line)
    {
        String[] spitedLine = line.split(";");
        if ( checkEventYearAndWeekNumber(spitedLine[0], spitedLine[1], GameType.HATOS)) {
            return;
        }
        year = Short.parseShort(spitedLine[0]);
        weekNumber = Byte.parseByte(spitedLine[1]);
        int lineLength = spitedLine.length;
        int beginIndex;
        if (lineLength > 19) {
            beginIndex = lineLength - 7;
        } else {
            beginIndex = lineLength - 6;
        }
        int j = 0;
        for (int i = beginIndex; i < lineLength; i++) {
            try
            {
                numbers[j++] = Byte.parseByte(spitedLine[i]);
            } catch (NumberFormatException e)
            {
                return;
            }
        }
        if (!checkEventNumbers(numbers)) {
            numbers = null;
        }
    }

    @Override
    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("Year: ").append(year).append('\n');
        sb.append("Week number: ").append(weekNumber).append('\n');
        sb.append("Drawn numbers: ").append(Arrays.toString(numbers));
        return sb.toString();
    }

    private boolean checkEventYearAndWeekNumber(String year, String weekNumber, GameType gameType)
    {
        return !(Validator.checkYear(year, gameType) &&
                Validator.checkWeekNumber(weekNumber));
    }

    private boolean checkEventNumbers(byte[] numbers) {
        return Validator.checkNumbers(numbers, GameType.HATOS) &&
                Validator.checkForNoRepeat(numbers);
    }

    @Override
    public int compareTo(DrawEvent o)
    {
        DrawEvent645 event = (DrawEvent645) o;
        return this.weekNumber - event.getWeekNumber();
    }
}
