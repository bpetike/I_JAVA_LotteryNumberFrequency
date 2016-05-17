package drawevent;

import validator.Validator;

import java.util.Arrays;

/**
 * Created by BontaPeter on 2016. 05. 17..
 * This class describes a draw event of 5/90 game type.
 */
public class DrawEvent590 extends DrawEvent
{
    private byte[] numbers;

    public DrawEvent590() {
        super();
        numbers = new byte[5];
    }


    public byte[] getNumbers()
    {
        return numbers;
    }

    @Override
    public DrawEvent parse(String line)
    {
        String[] spitedLine = line.split(";");
        DrawEvent590 event = new DrawEvent590();
        if ( !(Validator.checkYear(spitedLine[0], GameType.OTOS) &&
                Validator.checkWeekNumber(spitedLine[1])) ) {
            return null;
        }
        event.setYear(Short.parseShort(spitedLine[0]));
        event.setWeekNumber(Byte.parseByte(spitedLine[1]));
        int lineLength = spitedLine.length;
        int j = 0;
        for (int i = lineLength - 5; i < lineLength; i++) {
            try
            {
                event.numbers[j++] = Byte.parseByte(spitedLine[i]);
            } catch (NumberFormatException e)
            {
                break;
            }
        }
        if (Validator.checkNumbers(event.numbers, GameType.OTOS)) {
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
        sb.append("Drawn numbers: ").append(Arrays.toString(numbers));
        return sb.toString();
    }
}
