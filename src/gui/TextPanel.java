package gui;

import javax.swing.*;
import java.awt.*;

/**
 * Created by BontaPeter on 2016. 05. 31..
 */
public class TextPanel extends JPanel
{
    private JLabel textArea;

    public TextPanel()
    {
        textArea = new JLabel("This application displays the frequency of lottery numbers for the chosen game type." +
                "The result list will contain the numbers from 1 to the maximum number in the chosen game" +
                "type with the week numbers when that particular nuumber was drawn.");
        setLayout(new BorderLayout());
        add(textArea, BorderLayout.NORTH);
    }
}
