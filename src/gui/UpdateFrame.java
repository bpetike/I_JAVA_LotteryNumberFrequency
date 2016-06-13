package gui;

import controller.Controller;

import javax.swing.*;
import java.awt.*;

/**
 * Created by BontaPeter on 2016. 05. 31..
 * This frame appears at first after launching the application.
 */
public class UpdateFrame extends JFrame
{
    private JLabel textLabel;
    private Controller controller;

    public UpdateFrame()
    {
        super("Checking for updates...");
        controller = new Controller();
        textLabel = new JLabel("This may take a few moments", JLabel.CENTER);
        setLayout(new BorderLayout());
        add(textLabel, BorderLayout.CENTER);
        positionFrame();
        pack();
        setVisible(true);
        if (controller.updateFiles())
        {
            dispose();
        }
    }

    private void positionFrame()
    {
        Dimension screenSize = MainFrame.SCREENSIZE;
        setPreferredSize(new Dimension(340, 100));
        Dimension windowSize = new Dimension(getPreferredSize());
        int wdwLeft = 200 + screenSize.width / 2 - windowSize.width / 2;
        int wdwTop = screenSize.height / 2 - windowSize.height / 2 - 100;
        setLocation(wdwLeft, wdwTop);
    }
}
