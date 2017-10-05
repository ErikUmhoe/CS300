
public class Fish {
	
	
	private int fishX, fishY; //Fish x and y
	private PApplet processing;
	private PImage fishImage;
	
	public static void main(String[] args) {
		

	}
	
	//Constructor that creates a new fish w/ random locations
	public Fish(PApplet processing)
	{
		this.processing = processing;
		fishImage = processing.loadImage("images" + java.io.File.separator + "FISH.png");
		fishX = Utility.randomInt(this.processing.width);
		fishY = Utility.randomInt(this.processing.height);
	}
	
	//Constructor that creates a nw fish w/ set locations
	public Fish(PApplet processing, int x, int y)
	{
		this.processing = processing;
		fishImage = processing.loadImage("images" + java.io.File.separator + "FISH.png");
		fishX = x;
		fishY = y;
		
	}
	
	//Updates location of the fish
	public void update()
	{
		//If fish is not on the right edge of the screen, move it one unit to the right
		if(fishX < processing.width)
		{
			fishX++;
		}
		
		//If fish is on the right edge of the screen,  move it to the left edge
		else
		{
			fishX = 0;
		}
		processing.image(fishImage,fishX, fishY);
	}
	
	//Method that detects collision b/w food and fish
	public void tryToEat(Food food)
	{
		
		//If fish is w/ in 40 units of the food, eat the food
		if(food.distanceTo(fishX, fishY) < 40)
		{
			food.getEaten();
		}
	}
	
	//Finds the distance b/w the food and the fish
	public float distanceTo(int x, int y)
	{
		return new Float(Math.abs(Math.sqrt((Math.abs(x - fishX)*Math.abs(x - fishX))+ 
				(Math.abs(y - fishY)*Math.abs(y - fishY)))));

	}
	
	//If the fish is caught by the hook, move the fish to a random y position and to the left edge of the screen
	public void getEaten()
	{
		fishX = 0;
		fishY = Utility.randomInt(this.processing.height);
	
	}

}
