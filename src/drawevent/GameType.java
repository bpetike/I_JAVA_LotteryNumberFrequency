package drawevent;

/**
 * Created by BontaPeter on 2016. 05. 17..
 * This enumeration represents the game types.
 */
public enum GameType
{
    OTOS("5/90", 1957, 90),
    HATOS("6/45", 1988, 45),
    SKANDI("7/35", 1999, 35);

    private String name;
    private int minYear;
    private int maxNumber;

    private GameType(String name, int minYear, int maxNumber) {
        this.name = name;
        this.minYear = minYear;
        this.maxNumber = maxNumber;
    }

    public static GameType getGameType(String name) {
        for (GameType gt : GameType.values())
        {
            if (gt.name.equals(name)) {
                return gt;
            }
        }
        return null;
    }

    public int getMinYear()
    {
        return minYear;
    }

    public int getMaxNumber()
    {
        return maxNumber;
    }
}
