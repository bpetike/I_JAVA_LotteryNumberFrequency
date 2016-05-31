package app;

import drawevent.GameType;
import gui.MainFrame;
import gui.UpdateFrame;
import validator.Validator;

import javax.swing.*;


public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainFrame();
            }
        });
    }
}
