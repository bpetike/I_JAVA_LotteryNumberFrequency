package drawevent;

/**
 * Created by BontaPeter on 2016. 05. 17..
 * This enumeration represents the game types.
 */
public enum GameType
{
    OTOS("5/90", 1957, 5, 90),
    HATOS("6/45", 1988, 7, 45),
    SKANDI("7/35", 1999, 7, 35);

    private String name;
    private int minYear;
    private int drawnNumbers;
    private int maxNumber;

    private GameType(String name, int minYear, int drawnNumbers, int maxNumber) {
        this.name = name;
        this.minYear = minYear;
        this.drawnNumbers = drawnNumbers;
        this.maxNumber = maxNumber;
    }

    /**
     * This method returns a GameType value based on a String name
     * @param name - the name of a game type
     * @return GameType - a specific value of GameType enum
     */
    public static GameType getGameType(String name) {
        for (GameType gt : GameType.values())
        {
            if (gt.name.equals(name)) {
                return gt;
            }
        }
        return null;
    }

    /**
     * This method returns the minimum year of a GameType value
     * @return int - the minimum year of a game type
     */
    public int getMinYear()
    {
        return minYear;
    }

    /**
     * This method returns the maximum available number of a GameType value
     * @return int - the maxumim number that can be drawn
     */
    public int getMaxNumber()
    {
        return maxNumber;
    }

    /**
     * This method returns how many numbers can be drawn at a GameType value
     * @return int - the amount of numbers can be drawn
     */
    public int getDrawnNumbers()
    {
        return drawnNumbers;
    }
}
