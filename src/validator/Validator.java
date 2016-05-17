package validator;

import drawevent.GameType;

import java.util.Calendar;

/**
 * Created by BontaPeter on 2016. 05. 17..
 * This class is used to validate draw event objects.
 */
public class Validator
{
    public static final int CURRENTYEAR = (Calendar.getInstance().get(Calendar.YEAR));
    public static final int MAXWEEKNUMBER = 53;

    public static boolean checkYear(String year, GameType gameType) {
        try
        {
            short parsedYear = Short.parseShort(year);
            if (parsedYear > gameType.getMinYear() && parsedYear < CURRENTYEAR) {
                return true;
            }
        } catch (NumberFormatException nfe) {
            return false;
        }
        return false;
    }

    public static boolean checkWeekNumber(String weekNumber) {
        try
        {
            short parsedWeekNumber = Short.parseShort(weekNumber);
            if (parsedWeekNumber > 0 && parsedWeekNumber <= MAXWEEKNUMBER) {
                return true;
            }
        } catch (NumberFormatException nfe) {
            return false;
        }
        return false;
    }

    public static boolean checkNumbers(byte[] numbers, GameType gameType) {
        int maxNumber = getMaxNumber(numbers);
        int minNumber = getMinNumber(numbers);
        return minNumber > 0 && maxNumber <= gameType.getMaxNumber();
    }

    private static int getMaxNumber(byte[] numbers)
    {
        int maxNumber = 0;
        for (byte number : numbers)
        {
            if (number > maxNumber)
            {
                maxNumber = number;
            }
        }
        return maxNumber;
    }

    private static int getMinNumber(byte[] numbers)
    {
        int minNumber = numbers[0];
        for (byte number : numbers)
        {
            if (number < minNumber)
            {
                minNumber = number;
            }
        }
        return minNumber;
    }
}
