package drawevent;

import java.util.Arrays;

/**
 * Created by BontaPeter on 2016. 05. 17..
 * This class describes a draw event of 6/45 game type.
 */
public class DrawEvent645 extends DrawEvent
{
    private byte[] numbers;

    public DrawEvent645()
    {
        super();
        numbers = new byte[7];
    }

    public byte[] getNumbers()
    {
        return numbers;
    }

    public void setNumbers(byte[] numbers)
    {
        this.numbers = numbers;
    }

    @Override
    public DrawEvent parse(String line)
    {
        String[] spitedLine = line.split(";");
        DrawEvent645 event = new DrawEvent645();
        event.setYear(Short.parseShort(spitedLine[0]));
        event.setWeekNumber(Byte.parseByte(spitedLine[1]));
        int lineLength = spitedLine.length;
        int beginIndex;
        if (lineLength > 19) {
            beginIndex = lineLength - 7;
        } else {
            beginIndex = lineLength - 6;
        }
        int j = 0;
        for (int i = beginIndex; i < lineLength; i++) {
            event.numbers[j++] = Byte.parseByte(spitedLine[i]);
        }
        return event;
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
