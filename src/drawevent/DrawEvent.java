package drawevent;

import validator.Validator;

/**
 * Created by BontaPeter on 2016. 05. 17..
 * This is the abstract class of draw events.
 */
public abstract class DrawEvent
{
    private short year;
    private byte weekNumber;

    public DrawEvent()
    {

    }


    public byte getWeekNumber()
    {
        return weekNumber;
    }

    public void setWeekNumber(byte weekNumber) {
        this.weekNumber = weekNumber;
    }

    public void setYear(short year) {
        this.year = year;
    }

    public short getYear()
    {
        return year;
    }

    public abstract DrawEvent parse(String line);

    protected boolean checkEventYearAndWeekNumber(String year, String weekNumber, GameType gameType) {
        return !(Validator.checkYear(year, gameType) &&
                Validator.checkWeekNumber(weekNumber));
    }
}
