package gui;

import drawevent.GameType;

import javax.swing.*;
import java.awt.*;
import java.util.Vector;

/**
 * Created by BontaPeter on 2016. 05. 31..
 */
public class MainPanel extends JPanel
{
    private JLabel yearLabel;
    private JTextField yearField;
    private JLabel gameTypeLabel;
    private JComboBox gameTypeComboBox;
    private JButton submitButton;

    public MainPanel()
    {
        yearLabel = new JLabel("Enter a year number: ");
        yearField = new JTextField(4);
        gameTypeLabel = new JLabel("Choose game type: ");
        gameTypeComboBox = new JComboBox();
        submitButton = new JButton("Show Frequency");
        String informationValue = "This application displays the frequency of lottery numbers for the chosen game type." +
                                    " The result list will contain the numbers from 1 to the maximum number in the chosen game" +
                                    "type with the week numbers when that particular nuumber was drawn.";
        submitButton.setToolTipText("<html><p width=\"480\">" + informationValue + "</p></html>");
        setupComboBox();
        setLayout(new GridBagLayout());
        GridBagConstraints gc = new GridBagConstraints();
        positionElements(gc);

    }

    private void positionElements(GridBagConstraints gc)
    {

        //////////// First row ///////////////////////////////////
        gc.gridy = 0;

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.fill = GridBagConstraints.NONE;
        gc.anchor = GridBagConstraints.LINE_END;
        gc.insets = new Insets(0, 0, 0, 5);
        add(yearLabel, gc);

        gc.gridx = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(yearField, gc);

        ////////////Second row ///////////////////////////////////
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 0.1;

        gc.gridx = 0;
        gc.insets = new Insets(0, 0, 0, 5);
        gc.anchor = GridBagConstraints.LINE_END;
        add(gameTypeLabel, gc);

        gc.gridx = 1;
        gc.insets = new Insets(0, 0, 0, 0);
        gc.anchor = GridBagConstraints.LINE_START;
        add(gameTypeComboBox, gc);

        ////////////Third row ///////////////////////////////////
        gc.gridy++;

        gc.weightx = 1;
        gc.weighty = 2.0;

        gc.gridx = 1;
        gc.anchor = GridBagConstraints.FIRST_LINE_START;
        gc.insets = new Insets(0, 0, 0, 10);
        add(submitButton, gc);

    }

    private void setupComboBox()
    {
        DefaultComboBoxModel gameTypeModel = new DefaultComboBoxModel();
        gameTypeModel.addElement("5790");
        gameTypeModel.addElement("6745");
        gameTypeModel.addElement("7735");
        gameTypeComboBox.setModel(gameTypeModel);
        gameTypeComboBox.setSelectedIndex(0);
    }
}
