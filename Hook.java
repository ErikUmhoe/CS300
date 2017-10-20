/**
 * This is a hook object.
 *
 *@author Nick Stoffel
 *@author Erik Umhoefer
 *@version 4.0
 *@since 2017-10-04
 */
public class Hook extends SimObject{

	

	/**
	 * Constructor that creates new Hook w/ random locations
	 * 
	 * @param processing
	 */
	public Hook()
	{
		super( "images" + java.io.File.separator + "HOOK.png" );
		this.xPos = Utility.randomInt( processing.width );
		this.yPos = Utility.randomInt( processing.height );
	}

	/**
	 * Constructor that creates new Hook w/ set locations
	 * 
	 * @param processing
	 * @param xPos
	 * @param yPos
	 */
	public Hook(int xPos, int yPos)
	{
		super( "images" + java.io.File.separator + "HOOK.png" );
		this.xPos = xPos;
		this.yPos = yPos;
	}

	/**
	 * Updates the location of the hook coordinates
	 */
	@Override
	public void update()
	{
		//If hook is not at the top of the screen, keep moving
		if( yPos > 0 )
		{
			yPos += -( processing.height + 50 - yPos ) / 50;
		}

		// If the hook is at the top of the screen, move it back to the bottom
		else
		{
			yPos = processing.height - 1;
		}

		processing.fill( 0 );
		processing.line( xPos+4, yPos-5, xPos+4, 0 );
		processing.image( image, xPos, yPos );
	}

	/**
	 * Moves hook xPos coordinate to the location of the click,
	 * and the yPos coordinate to the bottom of the applet
	 * 
	 * @param mouseX
	 * @param mouseY
	 */
	public void handleClick(int mouseX, int mouseY)
	{
		xPos = mouseX;
		yPos = processing.height-1;
	}

	/**
	 * Handles collisions b/w hook and fish
	 * 
	 * @param fish
	 */
	@Override
	public void tryToInteract(SimObject other)
	{
		
		if(other instanceof Fish)
		{
			if( other.distanceTo( this.xPos, this.yPos ) < 40 )
			{
				((Fish)other).getCaught();

			}
		}
	}
}