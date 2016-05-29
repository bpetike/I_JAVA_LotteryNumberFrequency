package drawevent;

import validator.Validator;

import java.util.Arrays;

/**
 * Created by BontaPeter on 2016. 05. 17..
 * This class describes a draw event of 5/90 game type.
 */
public class DrawEvent590 implements DrawEvent
{
    private short year;
    private byte weekNumber;
    private byte[] numbers;

    public DrawEvent590() {
        numbers = new byte[5];
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
        if (checkEventYearAndWeekNumber(spitedLine[0], spitedLine[1], GameType.OTOS)) {
            return;
        }
        year = Short.parseShort(spitedLine[0]);
        weekNumber = Byte.parseByte(spitedLine[1]);
        int lineLength = spitedLine.length;
        int j = 0;
        for (int i = lineLength - 5; i < lineLength; i++) {
            try
            {
                this.numbers[j++] = Byte.parseByte(spitedLine[i]);
            } catch (NumberFormatException e)
            {
                break;
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

    private boolean checkEventNumbers(byte[] numbers) {
        return Validator.checkNumbers(numbers, GameType.OTOS) &&
                Validator.checkForNoRepeat(numbers);
    }

    private boolean checkEventYearAndWeekNumber(String year, String weekNumber, GameType gameType)
    {
        return !(Validator.checkYear(year, gameType) &&
                Validator.checkWeekNumber(weekNumber));
    }

    @Override
    public int compareTo(DrawEvent o)
    {
        DrawEvent590 event = (DrawEvent590) o;
        return this.weekNumber - event.getWeekNumber();
    }
}
