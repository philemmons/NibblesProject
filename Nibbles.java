import objectdraw.*;
import java.awt.*;
import java.awt.event.*;

// J. Yang 2000; Modified by A. Danyluk 2000 and then by Tom Murtagh Nov, 2001
// Game of Nibbles

// Plays the game of Nibbles.  It draws a snake on the screen and a piece of food.
// The user uses the arrow keys to move the snake and try to eat the food.  The snake dies
// if it runs into the boundary or itself.
public class Nibbles extends WindowController implements KeyListener
{
        private static final int CELLSIZE = 10;  // these two define a "field" of 50x50 cells
        private static final int NCELLS = 50;      //  each having size 10. 
        
        private FramedRect border = new FramedRect(0,0,NCELLS*CELLSIZE, NCELLS*CELLSIZE,canvas);
                // draw a border around field
                      
        
        private Snake theSnake;    // The snake that moves around the screen
        private FilledRect apple;  // the apple
        
        
        // Remembers if there is a key depressed currently.
        private boolean keyDown = false;
              
        public Nibbles(){
            startController(550,600);
        }
        public void begin()
        {

                apple = new FilledRect(200,300, CELLSIZE-1,CELLSIZE-1,canvas); 
                // apple starts at same position but moves to new random location after snake eats it
                apple.setColor(Color.RED);
                theSnake = new Snake(NCELLS, CELLSIZE, apple, border, canvas);  
                 // snake needs to remember the field info, the apple, the border and the canvas
                
                // Get ready to handle the arrow keys
                requestFocus();
                addKeyListener(this);
                canvas.addKeyListener(this);
        }

        // Required by KeyListener Interface.
        public void keyTyped(KeyEvent e)
        {
        }

        // Remember that the key is no longer down.
        public void keyReleased(KeyEvent e)
        {
                keyDown = false;
        }

        // Handle the arrow keys by telling the snake to go in the direction of the arrow.
        public void keyPressed(KeyEvent e)
        {
                if (!keyDown)
                {
                        if (e.getKeyCode() == KeyEvent.VK_UP) {
                                                theSnake.setDirection(Direction.UP);

                        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                                                theSnake.setDirection(Direction.DOWN);

                        } else if (e.getKeyCode() == KeyEvent.VK_LEFT ) {
                                                theSnake.setDirection(Direction.LEFT);

                        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT ) {
                                                theSnake.setDirection(Direction.RIGHT);
                        }
                }
                keyDown = true;
        }
}
