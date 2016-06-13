package validator;

import drawevent.GameType;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by BontaPeter on 2016. 05. 17..
 * This class is used to validate draw event objects.
 */
public class Validator
{
    public static final int CURRENTYEAR = (Calendar.getInstance().get(Calendar.YEAR));
    public static final int MAXWEEKNUMBER = 53;

    /**
     * This method checks that the input year is greater than or equal to the minimum year of the
     * chosen game type and less than or equal to the current year.
     * @param year - a chosen year number in String
     * @param gameType - a chosen game tyoe
     * @return boolean - returns true if year is between the minimum year of game type and the current year
     *                  otherwise false
     */
    public static boolean checkYear(String year, GameType gameType) {
        try
        {
            short parsedYear = Short.parseShort(year);
            if (parsedYear >= gameType.getMinYear() && parsedYear <= CURRENTYEAR) {
                return true;
            }
        } catch (NumberFormatException nfe) {
            return false;
        }
        return false;
    }

    /**
     * This method checks thet the input week number is between 0 and the MAXWEEKNUMNER constant value
     * @param weekNumber - a week number in String
     * @return boolean - returns true, if week number is greater than 0 and less than or equal to MAXWEEKNUMBER
     */
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

    /**
     * This method checks if drawn numbers are in the interval of available numbers of a game type
     * @param numbers - drawn numbers of a DrawEvent
     * @param gameType - a chosen game type
     * @return boolean - returns true, if the numbers are between 0 and the maximum number of the game type
     */
    public static boolean checkNumbers(byte[] numbers, GameType gameType) {
        int maxNumber = getMaxNumber(numbers);
        int minNumber = getMinNumber(numbers);
        return minNumber >= 0 && maxNumber <= gameType.getMaxNumber() && numbers.length == gameType.getDrawnNumbers();
    }

    /**
     * This method checks for duplication in the drawn numbers
     * @param numbers - drawn numbers of a DrawEvent
     * @return boolean - returns true, if there is no duplicate numbers in the input array
     */
    public static boolean checkForNoRepeat(byte[] numbers) {
        Set<Byte> tempSet = new HashSet<>();
        for (byte number : numbers)
        {
            if (tempSet.contains(number)) {
                return false;
            }
            tempSet.add(number);
        }
        return true;
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
