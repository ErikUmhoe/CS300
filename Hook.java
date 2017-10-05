
public class Hook {
	
	private int hookX, hookY; //Hook X and Y Pos
	private PApplet processing;
	private PImage hookImage;
	
	//Constructor that creates new Hook w/ random locations
	public Hook(PApplet processing)
	{
		this.processing = processing;
		hookImage = processing.loadImage("images" + java.io.File.separator + "HOOK.png");
		hookX = Utility.randomInt(this.processing.width);
		hookY = Utility.randomInt(this.processing.height);
	}
	
	//Constructor that creates new Hook w/ set locations
	public Hook(PApplet processing, int x, int y)
	{
		this.processing = processing;
		hookImage = processing.loadImage("images" + java.io.File.separator + "HOOK.png");
		hookX = x;
		hookY = y;
	}
	//Updates the location of the hook coordinates
	public void update()
	{	
		//If hook is not at the top of the screen, keep moving
		if(hookY > 0)
		{
			hookY += -(processing.height + 50 - hookY)/50;
		}
		//If the hook is at the top of the screen, move it back to the botttom
		else
		{
			hookY = processing.height;
			hookY += -(processing.height + 50 - hookY)/50;
		}
		processing.image(hookImage,hookX, hookY);
		processing.line(hookX+4,hookY-5,hookX+4,0);
	}
	
	//Handles collisions b/w hook and fish
	public void tryToCatch(Fish fish)
	{
		if(fish.distanceTo(hookX, hookY) < 40)
		{
			fish.getEaten();
		}
	}
	
	//Moves hook x coordinate to the location of the click, and the y coordinate to the bottom of the applet
	public void handleClick(int mouseX, int mouseY)
	{
		hookX = mouseX;
		hookY = processing.height-1;
	}

}
