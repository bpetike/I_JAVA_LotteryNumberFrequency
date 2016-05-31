package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by BontaPeter on 2016. 05. 31..
 */
public class MainFrame extends JFrame
{
    private MainPanel mainPanel;

    public MainFrame()
    {
        super("Lottery Number Frequency");
        mainPanel = new MainPanel();
        setLayout(new BorderLayout());
        add(mainPanel, BorderLayout.CENTER);
        setSize(350, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

}
