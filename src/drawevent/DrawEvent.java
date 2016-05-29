package drawevent;

/**
 * Created by BontaPeter on 2016. 05. 28..
 */
public interface DrawEvent extends Comparable<DrawEvent>
{
    public void parse(String line);
}
