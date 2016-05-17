package drawevent;

/**
 * Created by BontaPeter on 2016. 05. 17..
 * This enumeration represents the game types.
 */
public enum GameType
{
    OTOS("5/90"),
    HATOS("6/45"),
    SKANDI("7/35");

    private String name;

    private GameType(String name) {
        this.name = name;
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
}
