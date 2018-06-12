import objectdraw.*;
import java.awt.*;
 
/**
* EXERCISE: NIBBLES - FINAL PROJECT
* TO GAIN EXPERIENCE WORKING WITH 1 DIMENSIONAL ARRAYS
* EAT THE APPLES, STAY INBOUNDS, TRY NOT TO EAT YOUR TAIL
* 
* PHILEMMONS
* CSIS10a, WEEK 17, 5-27-10  
* EMAIL: phil_emmons@sbcglobal.net
**/

//  J. Yang 2000; modified by A. Danyluk 2000 and B. Lerner 2000
//  Modified some more by Tom Murtagh Nov. 2001
//  creates a snake for the game of Nibbles

public class Snake extends ActiveObject {
    // constants to control keyboard movement
    // the snake's growth rate (added links per apple)
    private static final int GROWTHRATE = 4;

    // Color the snake appears on the screen.
    private static final Color SNAKE_COLOR = Color.GREEN;

    // maximum length of snake
    private static final int MAXLENGTH = 400;

    // pause time between movements
    private static final int PAUSE_TIME = 100;

    // list of Locations occupied by snake's body
    private FilledRect[] body;
    private FilledRect apple; // the food
    private FramedRect border;  //  the boundary of the playing area
    Location  nextCell;   // next cell to be occupied by snake head
    RandomIntGenerator randomCell;  // to generate new cell positions for moving apple
    
    // Current length of the snake
    private int currentLength;

    // The dimensions of the board and cell size
    private int nCells, cellSize;
    
     // How many more cells the snake should be allowed to grow for
    // eating some food (a countdown timer for the run method. if toGrow>0 shrink() is avoided
    private int toGrow;
    
    // The current direction in which the snake is moving
    private Direction curDirection = Direction.UP;
    
    private boolean outOfBoundsResult = false,
                    ateSelfResult = false;

    private DrawingCanvas canvas;
    
    // Construct a snake
    // Parameters:
    public Snake(int cells, int size_ofcells, FilledRect anApple, FramedRect aBorder, 
                 DrawingCanvas aCanvas) {
        // initialize instance variables
        nCells = cells;
        cellSize = size_ofcells;
        apple = anApple;
        border = aBorder;
        canvas = aCanvas;
        toGrow = 3;
        // initialize body as an array of FilledRects of MAXLENGTH
        body = new FilledRect[ MAXLENGTH ];  
        // set cell 0 of body to a new FilledRect at the center of the area  ie  THE FIRST
        body[0] = new FilledRect( border.getWidth()/2 , border.getHeight()/2 , 
                                  cellSize -1 , cellSize -1, canvas );
        // set currentLength  to 1 
        currentLength = 1;
        // initialize randomCell
        randomCell = new RandomIntGenerator( 0, nCells - 1);
        
        start();
    } // END SNAKE
    
 
    // Add one cell to the head of the snake
    private void stretch() {
        // starting at the end of the array (index: currentLength), 
        // move every cell up one index position
        // using a for loop     (  body[k] = body[k-1]   )
        for( int z = currentLength; z > 0; z-- ) {
           body[z] = body[z-1];
        } 
        // CREATES NEXT WORM SEGMENT, ie  THE REST
        nextCell = body[0].getLocation();
        body[1] = new FilledRect( nextCell, cellSize -1, cellSize -1, canvas );
        body[1].setColor( SNAKE_COLOR );      
        // .EQUALS DUE TO PRIMATIVE DATA TYPE; 
            if( curDirection.equals(Direction.UP) ) {
                body[0].move( 0, -cellSize );
            } else if( curDirection.equals( Direction.DOWN ) ) {
                body[0].move( 0, cellSize );
            } else if(  curDirection.equals( Direction.LEFT ) ) {
                body[0].move( -cellSize, 0 );
            } else if(  curDirection.equals( Direction.RIGHT ) ) {
                body[0].move( cellSize, 0 );
            }
        // add one to currentLength
        currentLength ++;
    } // END STRETCH

    // Remove one cell from the tail of the snake
    private void shrink() {
        // remove the last FilledRect in body from canvas and reduce currentLength by 1
            currentLength --; // BEGINNING CELL IS ZERO, AVOIDS NULL
            body[ currentLength ].removeFromCanvas();
    }
    
    public void run() {
       while( true ){  //  play game forever   
        // Repeatedly move the snake until it eats itself or moves out of bounds
            // METHODS CHECKS LOCATIONS BEFORE MOVING
            while( !outOfBounds() && !ateSelf() ) { 
                // Add a new cell in the current direction
                stretch();  // define this methods
                if( body[0].overlaps( apple ) ) { 
                    toGrow = toGrow + GROWTHRATE;
                    apple.moveTo( randomCell.nextValue() * cellSize, randomCell.nextValue() * cellSize );
                }
                // remove the last cell from body
                if( currentLength > toGrow ) {
                    shrink(); 
                }
                // Take a short break
                pause( PAUSE_TIME );  
            }// END ( !outOfBounds() && !ateSelf() )

            // Snake dies. Slowly remove the snake from the screen.
            // RETURN VALUES OF METHODS 
            while( outOfBoundsResult  || ateSelfResult ) {
                  for( int z = currentLength; 1 < z; z-- ){
                     shrink();
                     pause( PAUSE_TIME );
                   }
                   // reset snake to beginning position and size of 1, toGrow of 3
                   reset();
            }   
        }// END (outOfBounds  || ateSelf)
        
    }// END OF RUN

    public void setDirection( Direction dir ) {// KEEPS SNAKE FROM GOIING IN OPPSITE DIRECTION
        if (!dir.isOpposite( curDirection ))
            curDirection = dir;
    }
    
    // define outOfBounds, a method with no parameters
    public boolean outOfBounds(){
        double xBody =  body[0].getX() + cellSize; // ANTICIPATE OVERLAPPING LOCATIONS
        double yBody  = body[0].getY() + cellSize; // BORDER, AND BODY[0]
        Location wormHead = new Location( xBody , yBody );
    // return true or false depending on whether the snake head is outside border
    // use the .contains method for border to see if the Location of the 
    // head is within or without
        outOfBoundsResult = !border.contains( wormHead );
        return outOfBoundsResult;
    }
    
    // define ateSelf, a method with no parameters 
     public boolean ateSelf() {
    // return true or false depending on whether the snake head is overlapping another
    // cell in its body array.
        for( int z = 1; z < currentLength; z++ ){
            if( body[0].overlaps( body[z] ) ) {
                ateSelfResult = true;
            }
        }
        return ateSelfResult;   
    }// END ateSelf
    
    private void reset(){ // RESETS VALUES TO INITIAL START
         pause( PAUSE_TIME * 1.5 ); 
         body[0].moveTo( border.getWidth()/2 , border.getHeight()/2 );
         currentLength = 1;
         toGrow = 3;
         ateSelfResult = false;
         outOfBoundsResult = false;
    }// END reset
    
} //END OF CLASS