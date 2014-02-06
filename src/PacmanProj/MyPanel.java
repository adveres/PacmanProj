package PacmanProj;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class MyPanel extends JPanel implements ActionListener, KeyListener {

    private static final long serialVersionUID = 3306983287208108071L;
    private Timer myTimer;
    private Game game;
    private int counter = 0, counter2 = 0, counter3 = 0;

    JTextField b = new JTextField();
    final int BLOCK_SIZE = 50;

    // int score = 0;

    /**
     * Default constructor for MyPanel
     */
    public MyPanel() {
        super();

        game = new Game();
        game.sounds.playIntroMusic();

        b.setOpaque(false);
        add(b);
        b.addKeyListener(this);

        myTimer = new Timer(40, this);
        myTimer.start();

    }

    /**
     * This method decides what to do with each firing of the timer. It calls
     * all appropriate methods to check for gameover, and move evil pacmen. It
     * also handles changing states for evil pacmen when Pacman gets a POWERRR
     * dot
     */
    public void actionPerformed(ActionEvent e) {
        if (!game.isGameOver()) {

            game.pacman.animateMouth();
            game.evil1.animateMouth();
            game.evil2.animateMouth();
            repaint();

            if (game.pacman.powered == true)
                counter3++;
            if (counter3 >= 150) {
                game.pacman.powered = false;
                counter3 = 0;
                game.evil1.setColor(Color.RED);
                game.evil2.setColor(Color.RED);
            }

            if (counter2 >= 5) {
                do {
                    game.evil1.randomDir();
                } while (game.isCollision(game.evil1));

                do {
                    game.evil2.randomDir();
                } while (game.isCollision(game.evil2));
                counter2 = 0;
            }
            if (counter >= 1) {

                if (!game.isCollision(game.evil1))
                    game.evil1.moveEvil();
                if (!game.isCollision(game.evil2))
                    game.evil2.moveEvil();
                counter = 0;
            }
            counter++;
            counter2++;
        }

    }

    /**
     * Gets the key pressed as a parameter and determines what to do, whether it
     * is to move or exit the application.
     * 
     * @param e is the key pressed
     */
    public void keyPressed(KeyEvent e) {
        int x = e.getKeyCode();

        // Exit!
        if (x == KeyEvent.VK_ESCAPE)
            System.exit(0);

        // Movement controls
        if (x == KeyEvent.VK_LEFT) {
            game.pacman.direction = 180;
            if (!game.isCollision(game.pacman))
                game.pacman.moveHorizontal(-10);
        }
        if (x == KeyEvent.VK_RIGHT) {
            game.pacman.direction = 0;
            if (!game.isCollision(game.pacman))
                game.pacman.moveHorizontal(10);
        }
        if (x == KeyEvent.VK_UP) {
            game.pacman.direction = 90;
            if (!game.isCollision(game.pacman))
                game.pacman.moveVertical(-10);
        }
        if (x == KeyEvent.VK_DOWN) {
            game.pacman.direction = 270;
            if (!game.isCollision(game.pacman))
                game.pacman.moveVertical(10);
        }

        // Deal with dot collision
        if (game.gb.isDot(game.pacman.getX(), game.pacman.getY())) {
            game.sounds.playMunch();
            game.score += 10;

        }
        if (game.gb.isPowerDot(game.pacman.getX(), game.pacman.getY())) {
            game.sounds.playMunch();
            game.score += 15;
            game.pacman.powered = true;
            game.evil1.setColor(Color.BLUE);
            game.evil2.setColor(Color.BLUE);
        }

    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {

    }

    /**
     * Displays the sidebar to the right of the gameboard
     * 
     * @param m is the graphics
     */
    public void displaySidebar(Graphics m) {
        // A cheap way of setting the background to black
        m.setColor(Color.BLACK);
        m.fillRect(1000, 0, 200, 925);

        // Displays title stuff and credits
        m.setColor(Color.ORANGE);
        m.setFont(new java.awt.Font("Dialog", 1, 15));
        m.drawString("Pacman recreation by:", 1010, 150);
        m.setColor(Color.WHITE);
        m.drawString("Brian Hare", 1010, 165);
        m.drawString("Adam Veres", 1010, 180);

        m.drawString("Lives:", 1010, 350);
        // Displays the score on the sidebar
        m.drawString("Score:", 1010, 300);
        m.setFont(new java.awt.Font("Dialog", 1, 20));
        m.setColor(Color.RED);
        m.drawString("" + game.score, 1090, 300);
        // Displays the current lives
        m.drawString("" + game.lives, 1060, 350);
        // Displays the pacman icon
        m.setColor(Color.CYAN);
        m.fillArc(1050, 10, 100, 100, 45, 270);
        // Displays Highscore
        m.setColor(Color.BLUE);
        m.setFont(new java.awt.Font("Dialog", 1, 15));
        m.drawString("High Score:", 1005, 500);
        m.drawString(
                "" + game.topScore.getHighScore() + " "
                        + game.topScore.getName(), 1007, 525);

    }

    /**
     * Displays the ending screen once the game is deemed over
     * 
     * @param g is graphics
     */
    public void displayEnd(Graphics g) {

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, 1200, 925);

        if (game.lives == 0) {
            g.setColor(Color.RED);
            g.setFont(new java.awt.Font("Dialog", 1, 250));
            g.drawString("GAME", 150, 300);
            g.drawString("OVER", 150, 500);
        } else {
            g.setColor(Color.ORANGE);
            g.setFont(new java.awt.Font("Dialog", 1, 250));
            g.drawString("VICTORY", 50, 300);
        }

        myTimer.stop();
        g.setFont(new java.awt.Font("Dialog", 1, 20));
        g.drawString("High Score:", 500, 650);
        g.drawString(
                "" + game.topScore.getHighScore() + " "
                        + game.topScore.getName(), 500, 700);

        repaint();

        // game.topScore.closeConnection();

    }

    /**
     * This method paints to the screen all desired shapes and colors
     */
    public void paint(Graphics g) {
        // Displays text on the right
        displaySidebar(g);

        // This draws the gameboard with NOTHING on it, handles pacman "residue"
        for (int count1 = 0; count1 < 18; count1++) {
            for (int count2 = 0; count2 < 20; count2++) {
                switch (game.gb.gameBoard[count1][count2]) {
                case 'w':
                    g.setColor(Color.blue);
                    break;

                default:
                    g.setColor(Color.black);
                    break;
                }
                g.fillRect(count2 * BLOCK_SIZE, count1 * BLOCK_SIZE,
                        BLOCK_SIZE, BLOCK_SIZE);
            }
        }
        // This should fill in dots and POWERRRR dots where necessary

        for (int count1 = 0; count1 < 18; count1++) {
            for (int count2 = 0; count2 < 20; count2++) {
                if (game.gb.gameBoard[count1][count2] == 'd') {
                    g.setColor(Color.WHITE);
                    g.fillOval(count2 * BLOCK_SIZE + (BLOCK_SIZE / 3), count1
                            * BLOCK_SIZE + (BLOCK_SIZE / 3), 15, 15);
                }
                if (game.gb.gameBoard[count1][count2] == 'p') {
                    g.setColor(Color.MAGENTA);
                    g.fillOval(count2 * BLOCK_SIZE + (BLOCK_SIZE / 3), count1
                            * BLOCK_SIZE + (BLOCK_SIZE / 3), 20, 20);
                }
            }
        }

        g.setColor(game.pacman.col);
        g.fillArc(game.pacman.getX(), game.pacman.getY(), BLOCK_SIZE,
                BLOCK_SIZE,
                game.pacman.mouthAngle + game.pacman.getDirection(),
                270 + ((45 - game.pacman.getAngle()) * 2));

        g.setColor(game.evil1.col);
        g.fillArc(game.evil1.getX(), game.evil1.getY(), BLOCK_SIZE, BLOCK_SIZE,
                game.evil1.mouthAngle + game.evil1.getDirection(),
                270 + ((45 - game.evil1.getAngle()) * 2));
        g.fillArc(game.evil2.getX(), game.evil2.getY(), BLOCK_SIZE, BLOCK_SIZE,
                game.evil2.mouthAngle + game.evil2.getDirection(),
                270 + ((45 - game.evil2.getAngle()) * 2));

        if (game.isGameOver()) {
            game.gb.setBoardToWalls();
            myTimer.stop();
            if (game.isNewHighScore())
                game.setNewHighScore();
            displayEnd(g);
        }
    }

}
