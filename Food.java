
public class Food {
	
	private int foodX, foodY; //Food x and Y
	private PApplet processing;
	private PImage foodImage;
	
	//Constructor that creates a new food w/ random locations
	public Food(PApplet processing)
	{
		this.processing = processing;
		foodImage = processing.loadImage("images" + java.io.File.separator + "FOOD.png");
		foodX = Utility.randomInt(this.processing.width);
		foodY = Utility.randomInt(this.processing.height);
	}
	
	//Constructor that creates a new food w/ set locations
	public Food(PApplet processing, int x, int y)
	{
		this.processing = processing;
		foodImage = processing.loadImage("images" + java.io.File.separator + "FOOD.png");
		foodX = x;
		foodY = y;
	}
	
	//Changes the location of the food
	public void update()
	{
		//If food is not on the left edge of the screen, move one to the left
		if(foodX > 0)
		{
			foodX--;
		}
		
		//If food is on the left edge of the screen, move it to the right edge
		else
		{
			foodX = processing.width;
		}
		
		//If food is not on the bottom of the screen, move it one down
		if(foodY < processing.height)
		{
			foodY++;
		}
		
		//If food is on the bottom of the screen, move it to the top of the screen
		else
		{
			foodY = 0;
		}
		processing.image(foodImage,foodX, foodY);
	}
	
	
	//Returns the distance of the food to an object
	public float distanceTo(int x, int y)
	{
		return new Float(Math.abs(Math.sqrt((Math.abs(x - foodX)*Math.abs(x - foodX))
				+ (Math.abs(y - foodY)*Math.abs(y - foodY)))));
		
	}
	
	//If the food collides with a fish, set the food x coordinate to a random position and move the food to the top of the screen
	public void getEaten()
	{
		foodX = Utility.randomInt(this.processing.width);
		foodY = 0;
	}

}


