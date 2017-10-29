
public class Fish {
	
	private int fishX, fishY;
	private PApplet processing;
	private PImage fishImage;
	
	public static void main(String[] args) {
		

	}
	
	public Fish(PApplet processing)
	{
		this.processing = processing;
		fishImage = processing.loadImage("images" + java.io.File.separator + "FISH.png");
		fishX = Utility.randomInt(this.processing.width);
		fishY = Utility.randomInt(this.processing.height);
	}
	public Fish(PApplet processing, int x, int y)
	{
		this.processing = processing;
		fishImage = processing.loadImage("images" + java.io.File.separator + "FISH.png");
		fishX = x;
		fishY = y;
		
	}
	public void update()
	{
		if(fishX < processing.width)
		{
			fishX++;
		}
		else
		{
			fishX = 0;
		}
		processing.image(fishImage,fishX, fishY);
	}
	public void tryToEat(Food food)
	{
		if(food.distanceTo(fishX, fishY) < 40)
		{
			food.getEaten();
		}
	}
	
	public float distanceTo(int x, int y)
	{
		return new Float(Math.abs(Math.sqrt((Math.abs(x - fishX)*Math.abs(x - fishX))+ (Math.abs(y - fishY)*Math.abs(y - fishY)))));

	}
	public void getEaten()
	{
		fishX = 0;
		fishY = Utility.randomInt(this.processing.height);
	
	}

}
