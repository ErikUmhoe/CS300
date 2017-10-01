
public class Hook {
	private int hookX, hookY;
	private PApplet processing;
	private PImage hookImage;
	
	
	public Hook(PApplet processing)
	{
		this.processing = processing;
		hookImage = processing.loadImage("images" + java.io.File.separator + "HOOK.png");
		hookX = Utility.randomInt(this.processing.width);
		hookY = Utility.randomInt(this.processing.height);
	}
	public Hook(PApplet processing, int x, int y)
	{
		this.processing = processing;
		hookImage = processing.loadImage("images" + java.io.File.separator + "HOOK.png");
		hookX = x;
		hookY = y;
	}
	public void update()
	{
		if(hookY > 0)
		{
			hookY += -(processing.height + 50 - hookY)/50;
		}
		else
		{
			hookY = processing.height;
			hookY += -(processing.height + 50 - hookY)/50;
		}
		processing.image(hookImage,hookX, hookY);
		processing.line(hookX+4,hookY-5,hookX+4,0);
	}
	public void tryToCatch(Fish fish)
	{
		if(fish.distanceTo(hookX, hookY) < 40)
		{
			fish.getEaten();
		}
	}
	public void handleClick(int mouseX, int mouseY)
	{
		hookX = mouseX;
		hookY = processing.height-1;
	}

}
