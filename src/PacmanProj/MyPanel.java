package PacmanProj;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import Utils.Directions;
import static PacmanProj.Game.BLOCK_SIZE;

public class MyPanel extends JPanel implements ActionListener, KeyListener {

    private static final int EVIL_DIR_TICKS = 5;
    private static final long serialVersionUID = 3306983287208108071L;
    private Timer myTimer;
    private Game game;
    private int moveCounter = 0, evilDirectionCounter = 0, powerCounter = 0;

    JTextField b = new JTextField();

    // int score = 0;

    /**
     * Default constructor for MyPanel
     */
    public MyPanel() {
        super();

        game = new Game();
        game.getSounds().playIntroMusic();

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
     * dot.
     * 
     * @param ActionEvent
     */
    public void actionPerformed(ActionEvent action) {
        if (!game.isGameOver()) {

            game.pacman.animateMouth();
            game.evil1.animateMouth();
            game.evil2.animateMouth();
            repaint();

            if (game.pacman.isPowered()) {
                powerCounter++;

                if (powerCounter >= 150) {
                    game.pacman.setPowered(false);
                    powerCounter = 0;
                    game.evil1.setColor(Color.RED);
                    game.evil2.setColor(Color.RED);
                }
            }

            // Every 5 ticks the evil Pacmen pick a new direction to go
            // 5 ticks matters because each tick they move 10, and each block is
            // 50.
            if (evilDirectionCounter >= EVIL_DIR_TICKS) {
                do {
                    game.evil1.pickNewRandomDirection();
                } while (game.isCollision(game.evil1));

                do {
                    game.evil2.pickNewRandomDirection();
                } while (game.isCollision(game.evil2));
                evilDirectionCounter = 0;
            }

            // evilMoveCounter slows down the evil Pacmen. They move every other tick.
            // They're kind of spastic without this.
            if (moveCounter >= 1) {
                if (!game.isCollision(game.evil1)) {
                    game.evil1.moveCurrentDirection(game.pacman.isPowered());
                }
                if (!game.isCollision(game.evil2)) {
                    game.evil2.moveCurrentDirection(game.pacman.isPowered());
                }
                if (!game.isCollision(game.pacman)) {
                    game.pacman.moveCurrentDirection(false);
                    handleDotCollision();
                }
                moveCounter = 0;
            }

            // Increment the counters on each tick
            moveCounter++;
            evilDirectionCounter++;
        }

    }
    
    /**
     * Deal with dot collision
     */
    private void handleDotCollision(){

        //If we got a dot, munch and gimme points
        if (game.gb.isDot(game.pacman.getX(), game.pacman.getY())) {
            game.getSounds().playMunch();
            game.score += 10;
        }
        
        //If a power dot, power up pacman, extra pts, and make ghosts scared!
        if (game.gb.isPowerDot(game.pacman.getX(), game.pacman.getY())) {
            game.getSounds().playMunch();
            game.score += 15;
            game.pacman.setPowered(true);
            game.evil1.setColor(Color.BLUE);
            game.evil2.setColor(Color.BLUE);
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

        // Exit if the user hit <ESC>
        if (x == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        }
        
        int prevDir = game.pacman.getDirection();

        // Movement controls
        if (x == KeyEvent.VK_LEFT) {
            game.pacman.setDirection(Directions.LEFT);
        } else if (x == KeyEvent.VK_RIGHT) {
            game.pacman.setDirection(Directions.RIGHT);
        } else if (x == KeyEvent.VK_UP) {
            game.pacman.setDirection(Directions.UP);
        } else if (x == KeyEvent.VK_DOWN) {
            game.pacman.setDirection(Directions.DOWN);
        }    
        
        //However, if Pacman is now colliding with something, revert it.
        if (game.isCollision(game.pacman)) {
            game.pacman.setDirection(prevDir);
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

        // Draw lives title string
        m.drawString("Lives:", 1010, 350);

        // Displays the score on the sidebar
        m.drawString("Score:", 1010, 300);
        m.setFont(new java.awt.Font("Dialog", 1, 20));
        m.setColor(Color.RED);
        m.drawString("" + game.score, 1100, 300);

        // Displays the current lives
        m.drawString("" + game.lives, 1060, 350);

        // Displays the pacman icon
        m.setColor(Color.CYAN);
        m.fillArc(1050, 10, 100, 100, 45, 270);

        // Displays Highscore
        m.setColor(Color.BLUE);
        m.setFont(new java.awt.Font("Dialog", 1, 15));
        m.drawString("High Score:", 1005, 500);
        m.drawString("TODO", 1007, 525);
        // m.drawString("" + game.topScore.getHighScore() + " " +
        // game.topScore.getName(), 1007, 525);

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
        // g.drawString("" + game.topScore.getHighScore() + " " +
        // game.topScore.getName(), 500, 700);

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
                g.fillRect(count2 * BLOCK_SIZE, count1 * BLOCK_SIZE, BLOCK_SIZE, BLOCK_SIZE);
            }
        }
        // This should fill in dots and POWERRRR dots where necessary

        for (int count1 = 0; count1 < 18; count1++) {
            for (int count2 = 0; count2 < 20; count2++) {
                if (game.gb.gameBoard[count1][count2] == 'd') {
                    g.setColor(Color.WHITE);
                    g.fillOval(count2 * BLOCK_SIZE + (BLOCK_SIZE / 3), count1 * BLOCK_SIZE
                            + (BLOCK_SIZE / 3), 15, 15);
                }
                if (game.gb.gameBoard[count1][count2] == 'p') {
                    g.setColor(Color.MAGENTA);
                    g.fillOval(count2 * BLOCK_SIZE + (BLOCK_SIZE / 3), count1 * BLOCK_SIZE
                            + (BLOCK_SIZE / 3), 20, 20);
                }
            }
        }

        g.setColor(game.pacman.col);
        g.fillArc(game.pacman.getX(), game.pacman.getY(), BLOCK_SIZE, BLOCK_SIZE,
                game.pacman.mouthAngle + game.pacman.getDirection(),
                270 + ((45 - game.pacman.getMouthAngle()) * 2));

        g.setColor(game.evil1.col);
        g.fillArc(game.evil1.getX(), game.evil1.getY(), BLOCK_SIZE, BLOCK_SIZE,
                game.evil1.mouthAngle + game.evil1.getDirection(),
                270 + ((45 - game.evil1.getMouthAngle()) * 2));
        g.fillArc(game.evil2.getX(), game.evil2.getY(), BLOCK_SIZE, BLOCK_SIZE,
                game.evil2.mouthAngle + game.evil2.getDirection(),
                270 + ((45 - game.evil2.getMouthAngle()) * 2));

        if (game.isGameOver()) {
            game.gb.setBoardToWalls();
            myTimer.stop();
            if (game.isNewHighScore())
                game.setNewHighScore();
            displayEnd(g);
        }
    }

}
