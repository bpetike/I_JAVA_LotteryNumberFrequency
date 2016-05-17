package drawevent;

import validator.Validator;

import java.util.Arrays;

/**
 * Created by BontaPeter on 2016. 05. 17..
 * This class describes a draw event of 7/35 game type.
 */
public class DrawEvent735 extends DrawEvent
{
    private byte[] numbersDrawnByHand;
    private byte[] numbersDrawnByMachine;

    public DrawEvent735()
    {
        numbersDrawnByHand = new byte[7];
        numbersDrawnByMachine = new byte[7];
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
    public DrawEvent parse(String line)
    {
        String[] spitedLine = line.split(";");
        DrawEvent735 event = new DrawEvent735();
        if ( !(Validator.checkYear(spitedLine[0], GameType.SKANDI) &&
                Validator.checkWeekNumber(spitedLine[1])) ) {
            return null;
        }
        event.setYear(Short.parseShort(spitedLine[0]));
        event.setWeekNumber(Byte.parseByte(spitedLine[1]));
        int lineLength = spitedLine.length;
        int j = 0;
        for (int i = lineLength - 14; i < lineLength - 7; i++) {
            try
            {
                event.numbersDrawnByHand[j++] = Byte.parseByte(spitedLine[i]);
            } catch (NumberFormatException e)
            {
                return null;
            }
        }
        j = 0;
        for (int i = lineLength - 7; i < lineLength; i++) {
            try
            {
                event.numbersDrawnByMachine[j++] = Byte.parseByte(spitedLine[i]);
            } catch (NumberFormatException e)
            {
                return null;
            }
        }
        if (Validator.checkNumbers(event.numbersDrawnByHand, GameType.SKANDI) &&
                Validator.checkNumbers(event.numbersDrawnByMachine, GameType.SKANDI)) {
            return event;
        }
        return null;
    }

    @Override
    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append("Year: ").append(getYear()).append('\n');
        sb.append("Week number: ").append(getWeekNumber()).append('\n');
        sb.append("Numbers drawn by hand: ").append(Arrays.toString(numbersDrawnByHand)).append('\n');
        sb.append("Numbers drawn by machine: ").append(Arrays.toString(numbersDrawnByMachine));
        return sb.toString();
    }
}
