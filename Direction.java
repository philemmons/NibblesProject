// Written by Tom Murtagh, Nov. 2001

// Class to represent snake's direction.
// This is a very simple class.  It's only purpose is to
// group together a horizontal and vertical speed into a pair so that we can
// treat them as a single object.
public class Direction
{

                // Constant names used to describe directions outside this class
        public static final Direction UP    = new Direction(0,-1),
                                      DOWN  = new Direction(0, 1),
                                      LEFT  = new Direction(-1,0),
                                      RIGHT = new Direction( 1,0);

        // The individual parts of the direction vector
        private int xchange;
        private int ychange;

        // Create a new direction.
        // Parameters:
        //    xchange - horizontal movement per cycle
        //    ychange - vertical movement per cycle
        private Direction(int xchange, int ychange)
        {
                this.xchange = xchange;
                this.ychange = ychange;
        }

        // Return the horizontal component of velocity
        public int getXchange()
        {
                return xchange;    
        }

        // Return the vertical component of velocity
        public int getYchange()
        {
                return ychange;
        }

        // Check if one direction is the opposite of another
        public boolean isOpposite(Direction newDirection)
        {
                return xchange == -newDirection.getXchange() &&
                       ychange == -newDirection.getYchange();
        }

        // Return a string representation of a velocity so that it can be output
        // WHY HERE?  A TESTER? FOR SYS OUTPUT??
        public String toString ()
        {
                return "(" + xchange + ", " + ychange + ")";
        }
}