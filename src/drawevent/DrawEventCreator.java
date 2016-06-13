package drawevent;

/**
 * Created by BontaPeter on 2016. 05. 17..
 * This class is responsible for creating specific DrawEvent objects based on the game type.
 */
public class DrawEventCreator
{

    /**
     * This method creates specific DrawEvent objects based on the game type.
     * @param gameType - the chosen game type
     * @return DrawEvent - the corresponding DraWevent object to the game type
     */
    public DrawEvent getEvent(GameType gameType) {
        if (gameType == null) {
            return null;
        }
        if (gameType.equals(GameType.OTOS)) {
            return new DrawEvent590();
        } else if(gameType.equals(GameType.HATOS)) {
            return new DrawEvent645();
        } else if(gameType.equals(GameType.SKANDI))
        {
            return new DrawEvent735();
        }
        return null;
    }

}
