
public class SimObject {

	protected int xPos, yPos;
	protected static PApplet processing;
	protected PImage image;
	protected boolean remove;
	public static void setProcessing(PApplet processing1) {
		processing = processing1;
	}
	
	public SimObject(String imagePath)
	{
		if(processing == null)
		{
			throw new IllegalStateException("SimObject.setProcessing() must be called before constructing any SimObjects.");
			
		}
		
		image = processing.loadImage(imagePath);
		xPos = Utility.randomInt( processing.width );
		yPos = Utility.randomInt( processing.height );
		remove = false;
	}
	public SimObject(String imagePath, int xCord, int yCord)
	{
		if(processing == null)
		{
			throw new IllegalStateException("SimObject.setProcessing() must be called before constructing any SimObjects.");
			
		}
		
		image = processing.loadImage(imagePath);
		xPos = xCord;
		yPos = yCord;
		remove = false;
	}
	
	public void update()
	{
		
	}
	public float distanceTo(int xPos, int yPos) 
	{
		double deltaXSquared = Math.pow( ( xPos - this.xPos ), 2 );
		double deltaYSquared = Math.pow( ( yPos - this.yPos ), 2 );
		return new Float( Math.abs( Math.sqrt( deltaXSquared + deltaYSquared ) ) );
	}

	public void tryToInteract(SimObject other) 
	{
		
	}
	
	public boolean shouldBeRemoved()
	{
		return remove;
	}
}
