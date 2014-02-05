/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package PacmanProj;

/**
 *
 * @author atv5011
 */
public class GameBoard {

       final int BLOCK_SIZE = 50;
    
    	char[][] gameBoard = {
			{'w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w'},
			{'w','w','d','d','d','w','d','d','d','d','d','d','d','d','d','d','d','d','d','w'},
			{'w','d','d','w','d','d','d','w','w','d','w','w','d','w','w','w','w','w','d','w'},
			{'w','d','w','w','w','w','w','w','d','d','w','d','d','w','d','d','d','p','d','w'},
			{'w','d','p','d','d','w','d','d','d','w','w','d','w','w','d','w','w','w','w','w'},
			{'w','d','w','w','d','d','d','w','d','d','w','d','d','d','d','d','d','d','d','w'},
			{'w','d','d','w','w','d','w','w','w','d','w','w','w','w','w','d','w','w','d','w'},
			{'w','w','d','w','w','d','d','w','d','d','d','w','d','d','d','d','d','d','d','w'},
			{'w','d','d','d','w','w','d','g','d','w','d','g','d','w','w','w','d','w','w','w'},
			{'w','d','w','d','d','d','d','w','d','d','d','w','d','d','d','d','d','d','d','w'},
			{'w','d','w','d','w','w','d','w','w','w','w','w','d','w','w','w','w','w','d','w'},
			{'w','d','w','d','w','p','d','d','d','n','d','d','d','d','d','d','d','d','d','w'},
			{'w','d','d','d','w','d','w','d','w','d','w','w','d','w','w','w','d','w','d','w'},
			{'w','d','w','d','d','d','w','d','w','d','w','w','d','d','d','w','d','w','d','w'},
			{'w','d','w','w','w','w','w','d','w','d','d','d','d','w','d','d','d','w','d','w'},
			{'w','d','w','d','d','d','w','d','w','w','w','w','d','w','w','w','d','w','d','w'},
			{'w','p','d','d','w','d','d','d','d','d','d','d','d','d','d','d','d','p','d','w'},
			{'w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w','w'}
			};
    /**
     * Checks to see if the player's Pacman runs over a dot
     * @return is a boolean
     */
    public boolean isDot(int x, int y)
    {
    	for(int row = 0; row<18; row++)
    	{
    		for (int col = 0; col<20; col++)
    		{
    			if(x == col*BLOCK_SIZE && y == row*BLOCK_SIZE &&
    					gameBoard[row][col] == 'd')
    			{
    				gameBoard[row][col] = 'n';
    				return true;
    			}
    		}
    	}
    	return false;
    }
        /**
     * Checks to see if the player's Pacman runs over a POWERRRRR dot
     * @return is a boolean
     */
    public boolean isPowerDot(int x, int y)
    {
    	for(int row = 0; row<18; row++)
    	{
    		for (int col = 0; col<20; col++)
    		{
    			if(x == col*BLOCK_SIZE && y == row*BLOCK_SIZE &&
    					gameBoard[row][col] == 'p')
    			{
    				gameBoard[row][col] = 'n';
    				return true;
    			}
    		}
    	}
    	return false;
    }
    /**
     * Sets the entire board to walls. This is for once the game ends.
     */
    public void setBoardToWalls()
    {
        for(int row = 0; row<18; row++)
    	{
    		for (int col = 0; col<20; col++)
    		{
                    gameBoard[row][col] = 'w';
                }
        }
    }
}
