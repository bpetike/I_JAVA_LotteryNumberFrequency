package drawevent;

/**
 * Created by BontaPeter on 2016. 05. 17..
 * This is the abstract class of draw events.
 */
public class DrawEventCreator
{

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
