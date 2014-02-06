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
class Game {

    public static final int BLOCK_SIZE = 50;

    private Audio sounds;
    GameBoard gb;
    Pacman pacman;
    Pacman evil1;
    Pacman evil2;
    //DatabaseClass topScore = new DatabaseClass();

    String newWinner = null;
    int score;
    int lives;

    /**
     * Default Constructor for Game
     */
    public Game() {
        newWinner = JOptionPane.showInputDialog(null, "Enter your name : ", "Player Name!", 3);
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
     * @return true for game over, false otherwise
     */
    public boolean isGameOver() {
        
        //Out of lives? Game ova!
        if (lives == 0) {
            return true;
        }

        //Check the board, if there are dots left we aren't done.
        for (int row = 0; row < 18; row++) {
            for (int col = 0; col < 20; col++) {
                if (gb.gameBoard[row][col] == 'd') {
                    return false;
                }
            }
        }
        
        //The board must not have had any dots
        return true;
    }

    /**
     * Does one of the Pacman objects collide with something else?
     * 
     * @return is a boolean
     */
    public boolean isCollision(Pacman p) {

        // If Pacman collides with a ghost and isn't powered, he dies.
        if ((isGhost() == 1 || isGhost() == 2) && !pacman.isPowered()) {
            sounds.playDeath();
            lives--;
            pacman.initializePos(450, 550, 0);
            evil1.initializePos(350, 400, 180);
            evil2.initializePos(550, 400, 0);
            return true;
        }

        // If Pacman collides with a ghost and IS powered - they die/reset
        // position
        else if (isGhost() == 1 && pacman.isPowered()) {
            evil1.initializePos(350, 400, 180);
            score += 50;
        } else if (isGhost() == 2 && pacman.isPowered()) {
            evil2.initializePos(550, 400, 0);
            score += 50;
        }

        // If we didn't collide with a ghost, is there a wall?
        return isWall(p);
        // return (isGhost()||isWall(p));
    }

    /**
     * Checks to see if one of the Pacman objects is trying to go through a
     * wall.
     * 
     * @return true for yes wall, false for no
     */
    public boolean isWall(Pacman pac) {
        for (int row = 0; row < 18; row++) {
            for (int col = 0; col < 20; col++) {
                // Left
                if (((col * BLOCK_SIZE) + BLOCK_SIZE) == pac.getX()
                        && (row * BLOCK_SIZE) + BLOCK_SIZE > pac.getY()
                        && (row * BLOCK_SIZE) < pac.getY() + BLOCK_SIZE
                        && gb.gameBoard[row][col] == 'w' && pac.getDirection() == 180) {
                    return true;
                }
                // Right
                else if ((col * BLOCK_SIZE) == (pac.getX() + BLOCK_SIZE)
                        && (row * BLOCK_SIZE) + BLOCK_SIZE > pac.getY()
                        && (row * BLOCK_SIZE) < pac.getY() + BLOCK_SIZE
                        && gb.gameBoard[row][col] == 'w' && pac.getDirection() == 0) {
                    return true;
                }
                // Up
                else if (((row * BLOCK_SIZE) + BLOCK_SIZE) == pac.getY()
                        && (col * BLOCK_SIZE) + BLOCK_SIZE > pac.getX()
                        && (col * BLOCK_SIZE) < pac.getX() + BLOCK_SIZE
                        && gb.gameBoard[row][col] == 'w' && pac.getDirection() == 90) {
                    return true;
                }
                // Down
                else if ((row * BLOCK_SIZE) == (pac.getY() + BLOCK_SIZE)
                        && (col * BLOCK_SIZE) + BLOCK_SIZE > pac.getX()
                        && (col * BLOCK_SIZE) < pac.getX() + BLOCK_SIZE
                        && gb.gameBoard[row][col] == 'w' && pac.getDirection() == 270) {
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
//        if (score > topScore.getHighScore()) {
//            return true;
//        }
        return false;
    }

    /**
     * Sets the new high score if the current player has a new top score
     */
    public void setNewHighScore() {
        //topScore.setNew(score, newWinner);
        // topScore.setNew(score, "ADAM");
    }
    
    public Audio getSounds(){
        return this.sounds;
    }

}
