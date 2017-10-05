/**
 * This is a fish object.
 *
 *@author Nick Stoffel
 *@author Erik Umhoefer
 *@version 4.0
 *@since 2017-10-04
 */
public class Fish {

	private PApplet processing;
	PImage img;
	private int x, y;

	/**
	 * Constructor that creates a new fish w/ random locations
	 * 
	 * @param processing
	 */
	public Fish(PApplet processing)
	{
		this.processing = processing;
		img = processing.loadImage( "images" + java.io.File.separator + "FISH.png" );
		x = Utility.randomInt( processing.width );
		y = Utility.randomInt( processing.height );
	}

	/**
	 * Constructor that creates a new fish w/ set locations
	 * 
	 * @param processing
	 * @param x
	 * @param y
	 */
	public Fish(PApplet processing, int x, int y)
	{
		this.processing = processing;
		img = processing.loadImage( "images" + java.io.File.separator + "FISH.png" );
		this.x = x;
		this.y = y;
	}

	/**
	 * Updates location of the fish
	 */
	public void update()
	{
		//If fish is not on the right edge of the screen, move it one unit to the right
		if(x < processing.width-1)
		{
			x += 1;
		}

		//If fish is on the right edge of the screen,  move it to the left edge
		else
		{
			x = 0;
		}

		processing.image( img, x, y );
	}

	/**
	 * Method that detects collision b/w food and fish
	 * 
	 * @param food
	 */
	public void tryToEat(Food food)
	{
		if( food.distanceTo( x, y ) < 40 )
			food.getEaten();
	}

	/**
	 * Finds the distance b/w the food and the fish
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
	 * If the fish is caught by the hook, move the fish to a random y position
	 * and to the left edge of the screen
	 */
	public void getCaught()
	{
		x = 0;
		y = Utility.randomInt( processing.height );
	}

}