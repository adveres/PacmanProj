/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package PacmanProj;

import java.awt.Color;
import javax.swing.*;

/**
 * 
 * @author atv5011
 */
public class Game {

    final int BLOCK_SIZE = 50;

    Audio sounds;
    GameBoard gb;

    Pacman pacman;
    Pacman evil1;
    Pacman evil2;

    String newWinner = null;

    DatabaseClass topScore = new DatabaseClass();

    int score;
    int lives;

    /**
     * Default Constructor for Game
     */
    public Game() {
        newWinner = JOptionPane.showInputDialog(null, "Enter your name : ",
                "Player Name!", 3);
        score = 0;
        lives = 3;
        gb = new GameBoard();
        sounds = new Audio();
        pacman = new Pacman(450, 550, Color.YELLOW, 0);
        evil1 = new Pacman(350, 400, Color.RED, 180);
        evil2 = new Pacman(550, 400, Color.RED, 0);
        // topScore.doConnect();
        // topScore.setNew(500, "PacKing");
    }

    /**
     * Checks to see if the game is over based on lives going to Zero or all of
     * the dots being consumed from the board.
     * 
     * @return is a boolean
     */
    public boolean isGameOver() {
        if (lives == 0)
            return true;

        for (int row = 0; row < 18; row++) {
            for (int col = 0; col < 20; col++) {
                if (gb.gameBoard[row][col] == 'd')
                    return false;
            }
        }
        return true;
    }

    /**
     * Does one of the Pacman objects collide with something else?
     * 
     * @return is a boolean
     */
    public boolean isCollision(Pacman p) {
        if ((isGhost() == 1 || isGhost() == 2) && pacman.powered == false) {
            sounds.playDeath();
            lives--;
            pacman.initializePos(450, 550, 0);
            evil1.initializePos(350, 400, 180);
            evil2.initializePos(550, 400, 0);
            return true;
        } else if (isGhost() == 1 && pacman.powered == true) {
            evil1.initializePos(350, 400, 180);
            score += 50;
        } else if (isGhost() == 2 && pacman.powered == true) {
            evil2.initializePos(550, 400, 0);
            score += 50;
        }
        return isWall(p);
        // return (isGhost()||isWall(p));
    }

    /**
     * Checks to see if one of the Pacman objects is trying to go through a
     * wall.
     * 
     * @return is a boolean
     */
    public boolean isWall(Pacman pac) {
        for (int row = 0; row < 18; row++) {
            for (int col = 0; col < 20; col++) {
                // Left
                if ((col * BLOCK_SIZE) + BLOCK_SIZE == pac.getX()
                        && (row * BLOCK_SIZE) + BLOCK_SIZE > pac.getY()
                        && (row * BLOCK_SIZE) < pac.getY() + BLOCK_SIZE
                        && gb.gameBoard[row][col] == 'w'
                        && pac.getDirection() == 180) {
                    return true;
                }
                // Right
                else if ((col * BLOCK_SIZE) == pac.pacmanx + BLOCK_SIZE
                        && (row * BLOCK_SIZE) + BLOCK_SIZE > pac.getY()
                        && (row * BLOCK_SIZE) < pac.getY() + BLOCK_SIZE
                        && gb.gameBoard[row][col] == 'w'
                        && pac.getDirection() == 0) {
                    return true;
                }
                // Up
                else if ((row * BLOCK_SIZE) + BLOCK_SIZE == pac.getY()
                        && (col * BLOCK_SIZE) + BLOCK_SIZE > pac.pacmanx
                        && (col * BLOCK_SIZE) < pac.pacmanx + BLOCK_SIZE
                        && gb.gameBoard[row][col] == 'w'
                        && pac.getDirection() == 90) {
                    return true;
                }
                // Down
                else if ((row * BLOCK_SIZE) == pac.getY() + BLOCK_SIZE
                        && (col * BLOCK_SIZE) + BLOCK_SIZE > pac.getX()
                        && (col * BLOCK_SIZE) < pac.getX() + BLOCK_SIZE
                        && gb.gameBoard[row][col] == 'w'
                        && pac.getDirection() == 270) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks to see if the Player's Pacman object hits into a "ghost"
     * 
     * @return is a boolean
     */
    public int isGhost() {

        if (Math.abs(pacman.getX() - evil1.getX()) < BLOCK_SIZE
                && Math.abs(pacman.getY() - evil1.getY()) < BLOCK_SIZE)
            return 1;
        else if (Math.abs(pacman.getX() - evil2.getX()) < BLOCK_SIZE
                && Math.abs(pacman.getY() - evil2.getY()) < BLOCK_SIZE)
            return 2;
        else
            return 0;

    }

    /**
     * Checks to see if the current player's score is a new top score
     * 
     * @return is a boolean
     */
    public boolean isNewHighScore() {
        if (score > topScore.getHighScore()) {
            return true;
        }
        return false;
    }

    /**
     * Sets the new high score if the current player has a new top score
     */
    public void setNewHighScore() {
        topScore.setNew(score, newWinner);
        // topScore.setNew(score, "ADAM");
    }

}
