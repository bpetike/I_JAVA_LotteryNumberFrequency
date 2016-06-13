package gui;

import javax.swing.table.AbstractTableModel;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Created by BontaPeter on 2016. 06. 01..
 * The table model of the result frame.
 */
public class FrequencyTableModel extends AbstractTableModel
{
    private String[] colNames = new String[]{"Number", "Week numbers"};
    private Object[][] data;

    public FrequencyTableModel(Map<Byte, List<Integer>> resultList)
    {
        data = new  Object[resultList.size()][2];
        int index = 0;
        for (byte key : resultList.keySet())
        {
            List<Integer> weekLitt = resultList.get(key);
            data[index][0] = key;
            data[index][1] = weekLitt;
            index++;
        }
    }

    @Override
    public String getColumnName(int column)
    {
        return colNames[column];
    }

    @Override
    public int getRowCount()
    {
        return data.length;
    }

    @Override
    public int getColumnCount()
    {
        return colNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        return data[rowIndex][columnIndex];
    }
}
