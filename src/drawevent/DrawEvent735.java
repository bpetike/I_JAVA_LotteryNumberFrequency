package drawevent;

import validator.Validator;

import java.util.Arrays;

/**
 * Created by BontaPeter on 2016. 05. 17..
 * This class describes a draw event of 7/35 game type.
 */
public class DrawEvent735 implements DrawEvent
{
    private short year;
    private  byte weekNumber;
    private byte[] numbersDrawnByHand;
    private byte[] numbersDrawnByMachine;

    public DrawEvent735()
    {
        numbersDrawnByHand = new byte[7];
        numbersDrawnByMachine = new byte[7];
    }

    public short getYear()
    {
        return year;
    }

    public byte getWeekNumber()
    {
        return weekNumber;
    }

    public byte[] getNumbersDrawnByHand()
    {
        return numbersDrawnByHand;
    }

    public byte[] getNumbersDrawnByMachine()
    {
        return numbersDrawnByMachine;
    }

    @Override
    public void parse(String line)
    {
        String[] spitedLine = line.split(";");
        if (checkEventYearAndWeekNumber(spitedLine[0], spitedLine[1], GameType.SKANDI)) {
            return;
        }
        year = Short.parseShort(spitedLine[0]);
        weekNumber = Byte.parseByte(spitedLine[1]);
        int lineLength = spitedLine.length;
        int j = 0;
        for (int i = lineLength - 14; i < lineLength - 7; i++) {
            try
            {
                numbersDrawnByHand[j++] = Byte.parseByte(spitedLine[i]);
            } catch (NumberFormatException e)
            {
                return;
            }
        }
        j = 0;
        for (int i = lineLength - 7; i < lineLength; i++) {
            try
            {
                numbersDrawnByMachine[j++] = Byte.parseByte(spitedLine[i]);
            } catch (NumberFormatException e)
            {
                return;
            }
        }
        if (!checkEventNumbers(numbersDrawnByHand, numbersDrawnByMachine)) {
            numbersDrawnByHand = null;
            numbersDrawnByMachine = null;
        }
    }

    @Override
    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("Year: ").append(year).append('\n');
        sb.append("Week number: ").append(weekNumber).append('\n');
        sb.append("Numbers drawn by hand: ").append(Arrays.toString(numbersDrawnByHand)).append('\n');
        sb.append("Numbers drawn by machine: ").append(Arrays.toString(numbersDrawnByMachine));
        return sb.toString();
    }

    private boolean checkEventYearAndWeekNumber(String year, String weekNumber, GameType gameType)
    {
        return !(Validator.checkYear(year, gameType) &&
                Validator.checkWeekNumber(weekNumber));
    }

    private boolean checkEventNumbers(byte[] drawnByHand, byte[] drawnByMachine) {
        return Validator.checkNumbers(drawnByHand, GameType.SKANDI) &&
                Validator.checkNumbers(drawnByMachine, GameType.SKANDI) &&
                Validator.checkForNoRepeat(drawnByHand) &&
                Validator.checkForNoRepeat(drawnByMachine);
    }

    @Override
    public int compareTo(DrawEvent o)
    {
        DrawEvent735 event = (DrawEvent735) o;
        return this.weekNumber - event.getWeekNumber();
    }
}
