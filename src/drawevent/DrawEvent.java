package drawevent;

/**
 * Created by BontaPeter on 2016. 05. 28..
 * This interface must be implemented to create a specific DrawEvent.
 */
public interface DrawEvent extends Comparable<DrawEvent>
{
    /**
     * This method is used to parse a DrawEvent from a String.
     * @param line - a String which can be split by a delimiter
     */
    public void parse(String line);

    /**
     * This method returns the week number from a DrawEvent
     * @return byte - the week number of a DrawEvent
     */
    public byte getWeekNumber();

    /**
     * This method returns the drawn numbers from a DrawEvemt
     * @return byte[] - the drawn lottery numbers of a DrawEvent
     */
    public byte[] getNumbers();
}
