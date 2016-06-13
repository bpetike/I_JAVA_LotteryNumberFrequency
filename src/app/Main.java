package app;

import gui.MainFrame;
import gui.UpdateFrame;

import javax.swing.*;

/**
 * <h2>Lottery Number Frequency</h2>
 * This application displays the frequency of drawn lottery numbers in a given year and game type.
 * The game types are the Hungarian lottery game types (5/90, 6/45, 7/35). The frequency list consists of
 * the drawable numbers of the chosen game type, and a list of week numbers corresponding to each number.
 *
 * @author BontaPeter
 * @version 1.0
 * @since April 2016
 */

public class Main {

    /**
     * This is the entry point of the application. The main method runs the update and
     * the main frames.
     * @param args unused
     */

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
