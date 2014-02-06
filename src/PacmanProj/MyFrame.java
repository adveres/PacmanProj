/**
 * @author Brian Hare, Adam Veres
 * This is a recreation of Pacman for cmpsc221 at Penn State University.
 * Professor Matt Davis, Fall 2008
 */
package PacmanProj;

import java.awt.*;
import javax.swing.*;

public class MyFrame extends JFrame {

    private static final long serialVersionUID = 7320669415882161204L;

    public static void main(String[] args) {
        MyFrame frame = new MyFrame("Pacman Game");
        frame.setVisible(true);
    }

    protected MyFrame(String windowTitle) {
        super(windowTitle);
        // setLayout(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(new Dimension(1200, 925));

        Container cp = getContentPane();
        MyPanel meow = new MyPanel();
        // meow.setBounds(0, 0, 1200, 900);
        cp.add(meow);
    }
}
