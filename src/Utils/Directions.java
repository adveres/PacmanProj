package Utils;

public class Directions {
    
    //How many directions we support... 4 (up/down/left/right)
    public static final int DIRECTIONS = 4;
    
    //Directions in terms of degrees
    public static final int RIGHT = 0;
    public static final int UP = 90;
    public static final int LEFT = 180;
    public static final int DOWN = 270;

    //How many (pixels??) we move on a move tick
    public static final int MOVE_DELTA = 10;
    public static final int MOVE_SLOWLY = 5;
}
