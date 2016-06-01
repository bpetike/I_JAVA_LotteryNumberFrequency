package gui;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Map;

/**
 * Created by BontaPeter on 2016. 06. 01..
 */
public class ResultFrame extends JFrame
{
    private TablePanel tablePanel;

    public ResultFrame(Map<Byte, List<Integer>> resultList, String year, String gameType)
    {
        super("Lottery Number Frequency");
        tablePanel = new TablePanel(resultList, year, gameType);
        setLayout(new BorderLayout());
        add(tablePanel, BorderLayout.CENTER);
        positionFrame();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    private void positionFrame()
    {
        Dimension screenSize = MainFrame.SCREENSIZE;
        Dimension windowSize = new Dimension(getPreferredSize());
        int wdwLeft = 100 + screenSize.width / 2 - windowSize.width / 2;
        int wdwTop = screenSize.height / 2 - windowSize.height / 2;
        pack();
        setLocation(wdwLeft, wdwTop);
    }
}
