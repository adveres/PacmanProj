package PacmanProj;

import java.awt.Color;

import Utils.Directions;
import Utils.Utils;

public class Pacman {

    int mouthAngle;
    int pacmanX;
    int pacmanY;
    int direction;
    int lives;
    int mouthChange;
    boolean mouthOpen;
    Color col;
    private boolean powered;

    /**
     * Pacman Default Constructor
     */
    public Pacman() {
        initializePos(450, 550, 0);
        lives = 3;
        mouthAngle = 45;
        lives = 3;
        col = Color.YELLOW;
        mouthChange = 5;
        powered = false;
    }

    /**
     * Pacman Constructor with xpos, ypos, color, and direction as parameters
     * 
     * @param x is the x coord to place this Pacman at
     * @param y is the y coord to place this Pacman at
     * @param c is the color of this Pacman
     * @param dir is the direction this Pacman will face
     */
    public Pacman(int x, int y, Color c, int dir) {
        initializePos(x, y, dir);
        mouthAngle = 45;
        direction = 0;
        lives = 3;
        col = c;
        mouthChange = 5;
        powered = false;
    }

    /**
     * Initializes position and facing direction of a Pacman object
     * 
     * @param x is the xpos on the frame
     * @param y is the ypos on the frame
     * @param dir is the direction this pacman will face
     */
    public void initializePos(int x, int y, int dir) {
        pacmanX = x;
        pacmanY = y;
        direction = dir;
    }

    /**
     * Moves this Pacman object left or right
     * 
     * @param shift is the amount to be moved
     */
    public void moveHorizontal(int shift) {
        pacmanX += shift;
    }

    /**
     * Moves this Pacman object up or down
     * 
     * @param shift is the amount to be moved
     */
    public void moveVertical(int shift) {
        pacmanY += shift;
    }

    /**
     * This method determines what the mouth should appear as given the previous
     * mouth appearance
     */
    public void animateMouth() {

        // Bounce the mouth between 0 and 45 degrees
        if (getMouthAngle() == 0) {
            mouthOpen = false;
        } else if (getMouthAngle() == 45) {
            mouthOpen = true;
        }

        // If mouth was open, we're closing it. If it was closed, we're opening it.
        if (mouthOpen) {
            mouthAngle -= mouthChange;
        } else {
            mouthAngle += mouthChange;
        }
    }

    /**
     * Generates a random number and causes the direction to change based on
     * that.
     */
    public void randomDir() {
        int rand = Utils.generateRandomNumber(Directions.DIRECTIONS);
        int temp = 0;

        switch (rand) {
        case 0:
            temp = Directions.RIGHT;
            break;
        case 1:
            temp = Directions.UP;
            break;
        case 2:
            temp = Directions.LEFT;
            break;
        case 3:
            temp = Directions.DOWN;
            break;
        }

        direction = temp;
    }

    /**
     * This method moves the evil pacmen based on the direction they are facing
     */
    public void moveEvil() {

        switch (direction) {
        case Directions.RIGHT:
            moveHorizontal(Directions.MOVE_DELTA);
            break;
        case Directions.UP:
            moveVertical(-Directions.MOVE_DELTA);
            break;
        case Directions.LEFT:
            moveHorizontal(-Directions.MOVE_DELTA);
            break;
        case Directions.DOWN:
            moveVertical(Directions.MOVE_DELTA);
            break;
        }

    }

    public int getX() {
        return pacmanX;
    }

    public int getY() {
        return pacmanY;
    }

    public int getDirection() {
        return direction;
    }
    public void setDirection(int direction){
        this.direction = direction;
    }

    public int getMouthAngle() {
        return mouthAngle;
    }

    public void setColor(Color color) {
        col = color;
    }

    public boolean isPowered() {
        return powered;
    }

    public void setPowered(boolean powered) {
        this.powered = powered;
    }


}
