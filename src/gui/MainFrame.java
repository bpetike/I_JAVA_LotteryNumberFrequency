package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by BontaPeter on 2016. 05. 31..
 */
public class MainFrame extends JFrame
{
    public static final Dimension SCREENSIZE = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
    private MainPanel mainPanel;

    public MainFrame()
    {
        super("Lottery Number Frequency");
        mainPanel = new MainPanel();
        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        positionFrame();
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

    private void positionFrame()
    {
        Dimension screenSize = SCREENSIZE;
        setPreferredSize(new Dimension(350, 100));
        Dimension windowSize = new Dimension(getPreferredSize());
        int wdwLeft = 300 + screenSize.width / 2 - windowSize.width / 2;
        int wdwTop = screenSize.height / 2 - windowSize.height / 2;
        pack();
        setLocation(wdwLeft, wdwTop);
    }

}
