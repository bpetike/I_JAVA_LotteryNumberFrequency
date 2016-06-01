package app;

import gui.MainFrame;
import gui.UpdateFrame;

import javax.swing.*;


public class Main {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new UpdateFrame();
            }
        });
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new MainFrame();
            }
        });
    }
}
