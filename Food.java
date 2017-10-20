/**
 * This is a food object.
 *
 *@author Nick Stoffel
 *@author Erik Umhoefer
 *@version 4.0
 *@since 2017-10-04
 */
public class Food extends SimObject{

	
	/**
	 * Constructor that creates a new food w/ random locations
	 * 
	 * @param processing
	 */
	public Food()
	{
		super("images" + java.io.File.separator + "FOOD.png");
	}

	/**
	 * Constructor that creates a new food w/ set locations
	 * 
	 * @param processing
	 * @param xPos
	 * @param yPos
	 */
	public Food(int xPos, int yPos)
	{
		super("images" + java.io.File.separator + "FOOD.png", xPos, yPos);
	}

	/**
	 * Changes the location of the food
	 */
	@Override
	public void update()
	{
		// If food is not on the left edge of the screen, move one to the left
		if( xPos > 0 )
		{
			xPos -= 1;
		}

		// If food is on the left edge of the screen, move it to the right edge
		else
		{
			xPos = processing.width - 1;
		}

		// If food is not on the bottom of the screen, move it one down
		if( yPos < processing.height-1 )
		{
			yPos += 1;
		}

		// If food is on the bottom of the screen, move it to the top of the screen
		else
		{
			yPos = 0;
		}

		processing.image(image,xPos,yPos);
	}

	/**
	 * Returns the distance of the food to an object
	 * 
	 * @param xPos
	 * @param yPos
	 * @return
	 */
	public float distanceTo(int xPos, int yPos) 
	{
		double deltaXSquared = Math.pow( ( xPos - this.xPos ), 2 );
		double deltaYSquared = Math.pow( ( yPos - this.yPos ), 2 );
		return new Float( Math.abs( Math.sqrt( deltaXSquared + deltaYSquared ) ) );
	}

	/**
	 * If the food collides with a fish, set the food xPos coordinate to a random position
	 * and move the food to the top of the screen
	 */
	public void getEaten() 
	{
//		xPos = Utility.randomInt( processing.width );
//		yPos = processing.height-1;
		remove = true;
	}
}