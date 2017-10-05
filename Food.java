/**
 * This is a food object.
 *
 *@author Nick Stoffel
 *@author Erik Umhoefer
 *@version 4.0
 *@since 2017-10-04
 */
public class Food {
	
	private PApplet processing;
	PImage img;
	private int x, y;
	
	/**
	 * Constructor that creates a new food w/ random locations
	 * 
	 * @param processing
	 */
	public Food(PApplet processing)
	{
		this.processing = processing;
		img = processing.loadImage( "images" + java.io.File.separator + "FOOD.png" );
		x = Utility.randomInt( processing.width );
		y = Utility.randomInt( processing.height );
	}
	
	/**
	 * Constructor that creates a new food w/ set locations
	 * 
	 * @param processing
	 * @param x
	 * @param y
	 */
	public Food(PApplet processing, int x, int y)
	{
		this.processing = processing;
		img = processing.loadImage( "images" + java.io.File.separator + "FOOD.png" );
		this.x = x;
		this.y = y;
	}
	
	/**
	 * Changes the location of the food
	 */
	public void update()
	{
		// If food is not on the left edge of the screen, move one to the left
		if( x > 0 )
		{
			x -= 1;
		}
		
		// If food is on the left edge of the screen, move it to the right edge
		else
		{
			x = processing.width - 1;
		}
		
		// If food is not on the bottom of the screen, move it one down
		if( y < processing.height-1 )
		{
			y += 1;
		}
		
		// If food is on the bottom of the screen, move it to the top of the screen
		else
		{
			y = 0;
		}
		
		processing.image(img,x,y);
	}
	
	/**
	 * Returns the distance of the food to an object
	 * 
	 * @param x
	 * @param y
	 * @return
	 */
	public float distanceTo(int x, int y) 
	{
		double deltaXSquared = Math.pow( ( x - this.x ), 2 );
		double deltaYSquared = Math.pow( ( y - this.y ), 2 );
		return new Float( Math.abs( Math.sqrt( deltaXSquared + deltaYSquared ) ) );
	}
	
	/**
	 * If the food collides with a fish, set the food x coordinate to a random position
	 * and move the food to the top of the screen
	 */
	public void getEaten() 
	{
		x = Utility.randomInt( processing.width );
		y = processing.height-1;
	}
}
