package PacmanProj;

import java.awt.Color;
import java.util.Random;

public class Pacman {

    int mouthAngle;
    int pacmanx;
    int pacmany;
    int direction;
    int lives;
    int mouthChange;
    boolean mouthOpen;
    Color col;
    boolean powered;

    /**
     * Pacman Default Constructor
     */
    public Pacman() {
        pacmanx = 450;
        pacmany = 550;
        lives = 3;
        mouthAngle = 45;
        direction = 0;
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
        pacmanx = x;
        pacmany = y;
        direction = dir;
    }

    /**
     * Changes the color of a Pacman object
     * 
     * @param color is the new color
     */
    public void setColor(Color color) {
        col = color;
    }

    /**
     * Moves this Pacman object left or right
     * 
     * @param shift is the amount to be moved
     */
    public void moveHorizontal(int shift) {
        pacmanx += shift;
    }

    /**
     * Moves this Pacman object up or down
     * 
     * @param shift is the amount to be moved
     */
    public void moveVertical(int shift) {
        pacmany += shift;
    }

    /**
     * This method determines what the mouth should appear as given the previous
     * mouth appearance
     */
    public void animateMouth() {
        if (getAngle() == 0)
            mouthOpen = false;
        else if (getAngle() == 45)
            mouthOpen = true;
        if (mouthOpen)
            mouthAngle -= mouthChange;
        else
            mouthAngle += mouthChange;
    }

    /**
     * generates a random number between 1 and 4 and returns it
     * 
     * @return temp is the random number
     */
    public int randomNumber() {
        Random gen = new Random();
        int temp = gen.nextInt(4);

        return temp;
    }

    /**
     * Generates a random number and causes the direction to change based on
     * this
     */
    public void randomDir() {
        int rand = randomNumber();
        int temp = 0;

        switch (rand) {
        case 0:
            temp = 0;
            break;
        case 1:
            temp = 90;
            break;
        case 2:
            temp = 180;
            break;
        case 3:
            temp = 270;
            break;
        }

        direction = temp;
    }

    /**
     * This method moves the evil pacmen based on the direction they are facing
     */
    public void moveEvil() {

        switch (direction) {
        case 0:
            moveHorizontal(10);
            break;
        case 90:
            moveVertical(-10);
            break;
        case 180:
            moveHorizontal(-10);
            break;
        case 270:
            moveVertical(10);
            break;
        }

    }

    /**
     * Returns the x coordinate of this Pacman object
     * 
     * @return pacman x is the x coord
     */
    public int getX() {
        return pacmanx;
    }

    /**
     * Returns the y coordinate of this Pacman object
     * 
     * @return pacmany is the y coord
     */
    public int getY() {
        return pacmany;
    }

    /**
     * Returns the direction in degrees of this Pacman object
     * 
     * @return direction is the direction currently faced
     */
    public int getDirection() {
        return direction;
    }

    /**
     * Returns the mouth angle of this Pacman object
     * 
     * @return mouthangle is the angle
     */
    public int getAngle() {
        return mouthAngle;
    }

}
