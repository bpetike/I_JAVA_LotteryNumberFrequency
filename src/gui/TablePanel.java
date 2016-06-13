package gui;

import javax.swing.*;
import java.awt.*;
import java.util.Map;
import java.util.List;

/**
 * Created by BontaPeter on 2016. 06. 01..
 * The result frame contains this panel.
 */
public class TablePanel extends JPanel
{
    private JLabel label;
    private JTable table;
    private FrequencyTableModel tableModel;

    public TablePanel(Map<Byte, List<Integer>> resultList, String year, String gameType)
    {
        label = new JLabel("Year: " + year + " Game type: " + gameType,JLabel.CENTER);
        table = new JTable();
        tableModel = new FrequencyTableModel(resultList);
        setLayout(new BorderLayout());
        table.setModel(tableModel);
        table.getColumnModel().getColumn(0).setMaxWidth(40);
        add(label, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
    }
}
