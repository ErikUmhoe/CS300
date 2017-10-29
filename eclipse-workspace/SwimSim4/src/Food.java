
public class Food {
	
	private int foodX, foodY;
	private PApplet processing;
	private PImage foodImage;
	
	
	public Food(PApplet processing)
	{
		this.processing = processing;
		foodImage = processing.loadImage("images" + java.io.File.separator + "FOOD.png");
		foodX = Utility.randomInt(this.processing.width);
		foodY = Utility.randomInt(this.processing.height);
	}
	public Food(PApplet processing, int x, int y)
	{
		this.processing = processing;
		foodImage = processing.loadImage("images" + java.io.File.separator + "FOOD.png");
		foodX = x;
		foodY = y;
	}
	public void update()
	{
		if(foodX > 0)
		{
			foodX--;
		}
		else
		{
			foodX = processing.width;
		}
		if(foodY < processing.height)
		{
			foodY++;
		}
		else
		{
			foodY = 0;
		}
		processing.image(foodImage,foodX, foodY);
	}
	
	public float distanceTo(int x, int y)
	{
		return new Float(Math.abs(Math.sqrt((Math.abs(x - foodX)*Math.abs(x - foodX))+ (Math.abs(y - foodY)*Math.abs(y - foodY)))));
		
	}
	
	public void getEaten()
	{
		foodX = Utility.randomInt(this.processing.width);
		foodY = 0;
	}

}


