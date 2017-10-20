/**
 * This is a fish object.
 *
 *@author Nick Stoffel
 *@author Erik Umhoefer
 *@version 4.0
 *@since 2017-10-04
 */
public class Fish extends SimObject{

	/**
	 * Constructor that creates a new fish w/ random locations
	 * 
	 * @param processing
	 */
	public Fish()
	{

		super( "images" + java.io.File.separator + "FISH.png" );
		this.xPos = Utility.randomInt( processing.width );
		this.yPos = Utility.randomInt( processing.height );
		
	}

	/**
	 * Constructor that creates a new fish w/ set locations
	 * 
	 * @param processing
	 * @param xPos
	 * @param yPos
	 */
	public Fish(int xPos, int yPos)
	{

		super( "images" + java.io.File.separator + "FISH.png" );
		this.xPos = xPos;
		this.yPos = yPos;
	}

	/**
	 * Updates location of the fish
	 */
	@Override
	public void update()
	{
		//If fish is not on the right edge of the screen, move it one unit to the right
		if(xPos < processing.width-1)
		{
			xPos += 1;
		}

		//If fish is on the right edge of the screen,  move it to the left edge
		else
		{
			xPos = 0;
		}

		processing.image( image, xPos, yPos );
	}

	/**
	 * Method that detects collision b/w food and fish
	 * 
	 * @param food
	 */
	@Override
	public void tryToInteract(SimObject other)
	{
		//Fish eating some food
		if(other instanceof Food)
		{
			if(other.distanceTo(xPos, yPos) < 40)
			{
				((Food)other).getEaten();
//				other.xPos = Utility.randomInt( processing.width );
//				other.yPos = processing.height-1;
			}
		}
	}

	/**
	 * Finds the distance b/w the food and the fish
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
	 * If the fish is caught by the hook, move the fish to a random yPos position
	 * and to the left edge of the screen
	 */
	public void getCaught()
	{
		xPos = 0;
		yPos = Utility.randomInt( processing.height );
	}

}