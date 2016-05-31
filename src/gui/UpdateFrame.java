package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by BontaPeter on 2016. 05. 31..
 */
public class UpdateFrame extends JFrame
{
    private JLabel textLabel;

    public UpdateFrame()
    {
        super();
        textLabel = new JLabel("Checking for data updates.. This may take a few moments", JLabel.CENTER);
        setLayout(new BorderLayout());
        add(textLabel, BorderLayout.CENTER);
        setSize(340, 50);
        setUndecorated(true);
        setVisible(true);
    }
}
