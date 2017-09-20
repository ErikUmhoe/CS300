import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		
		/*Data data = new Data(); No longer needed
		setup(data);
		while(true)
		{
			update(data);
		}
		*/
		Utility.startSimulation();
		
	}
	public static void setup(Data data) {
		int height = data.processing.height, width = data.processing.width;
		int fishNum = 4,foodNum = 6, hookNum = 1;
		data.tank = new char[height][width];
		//fillTank(data.tank,'~'); No longer needed
		data.processing.background(0,255,255);
		
		
		data.fishPositions = generateRandomPositions(fishNum, width, height);
		data.foodPositions = generateRandomPositions(foodNum, width, height);
		data.hookPositions = generateRandomPositions(hookNum, width, height);
		
		for(int row = 0; row < data.fishPositions.length; row++)
		{
			placeObjectInTank("><(('>",data.processing, data.fishPositions[row][0], data.fishPositions[row][1]);
		}
		for(int row = 0; row < data.foodPositions.length; row++)
		{
			placeObjectInTank("*",data.processing, data.foodPositions[row][0], data.foodPositions[row][1]);
		}
		for(int row = 0; row < data.hookPositions.length; row++)
		{
			placeObjectInTank("J",data.processing, data.hookPositions[row][0], data.hookPositions[row][1]);
		}
		//renderTank(data.tank); No longer needed
		
	}
	
	public static void update(Data data) {
			//Puts objects in first. For dx, negative value moves it right, positive left. For dy, negative value moves down, positive up
			data.fishPositions = moveAllObjects(data.fishPositions,1,0, data.tank[0].length, data.tank.length);
			data.foodPositions = moveAllObjects(data.foodPositions,-1,1, data.tank[0].length, data.tank.length);
			data.hookPositions = moveAllObjects(data.hookPositions,0,-1, data.tank[0].length, data.tank.length);
			data.processing.clear();
			data.processing.background(0,255,255);
			
			for(int row = 0; row < data.fishPositions.length; row++)
			{
				placeObjectInTank("><(('>",data.processing, data.fishPositions[row][0], data.fishPositions[row][1]);
			}
			for(int row = 0; row < data.foodPositions.length; row++)
			{
				placeObjectInTank("*",data.processing, data.foodPositions[row][0], data.foodPositions[row][1]);
			}
			for(int row = 0; row < data.hookPositions.length; row++)
			{
				placeObjectInTank("J",data.processing, data.hookPositions[row][0],data.hookPositions[row][1]);
			}
		
	}

	/**
	 * Copies the water character into every position in the tank array. The two-dimensional tank
	 * array can have dimensions of any size(s).
	 * 
	 * @param tank will contain all water characters after this method is called.
	 * @param water is the character copied into the tank.
	 * 
	 * Not needed with current setup
	 */
	public static void fillTank(char[][] tank, char water)
	{
		for(int row = 0; row < tank.length;row++)
		{
			for(int col = 0;col < tank[row].length;col++)
			{
				tank[row][col] = water;
			}
		}
	}
	 
	/**
	 * Prints the contents of the tank into the console in row major order, so that the 
	 * smallest row indexes are on top and the smallest column indexes are on the left. For 
	 * example:
	 * tank[0][0] tank[0][1] tank[0][2] ...
	 * tank[1][0] tank[1][1] tank[1][2] ...
	 * ...
	 * Each row is on its own line, and this method should work for two-dimensional tanks with 
	 * dimensions of any size.
	 * 
	 * @param tank contains the characters that will be printed to the console.
	 * 
	 * Not needed with current setup
	 */
	public static void renderTank(char[][] tank)
	{
		for(int row = 0; row < tank.length; row++)
		{
			for(int col = 0; col < tank[row].length; col++)
			{
				System.out.print(tank[row][col]);
			}
			System.out.println();
		}
	}
	
	//Needs to have feature added to prevent multiple positions with same Y Coordinate
	public static int[][] generateRandomPositions(int number, int width, int height)
	{
		int[][] randomPositions = new int[number][2];
		for(int row = 0; row < randomPositions.length; row++)
		{
			for(int col = 0; col < randomPositions[row].length; col++)
			{
				if(col == 0) {
					randomPositions[row][col] = Utility.randomInt(width);
				}
				else if(col > 0)
				{
					randomPositions[row][col] = Utility.randomInt(height);
				}
				
			}
			
		}
		
		return randomPositions;
		
	}
	
	public static void placeObjectInTank(String object, PApplet processing, int column, int row)
	{
		PImage img = processing.loadImage("images" + java.io.File.separator + "FISH.png");
		if(object.equals("><(('>"))
		{
			img = processing.loadImage("images" + java.io.File.separator + "FISH.png");
		}
		else if(object.equals("*"))
		{
			img = processing.loadImage("images" + java.io.File.separator + "FOOD.png");

		}
		else if(object.equals("J"))
		{
			img = processing.loadImage("images" + java.io.File.separator + "HOOK.png");
			processing.fill(0);
			processing.line(column+4,row-5,column+4,0);
		}
		processing.image(img,column,row);
		
		/*
		if(object.length() > 1) No longer needed
		{
			String remain = object;
			int count = 0;
			for(int xCord = column; xCord >= 0 && count < remain.length(); xCord--)
			{
				rocessing.text(remain.charAt(remain.length()-(count+1)),xCord,row);
				processing.image(img,xCord,row);
				count++;
			}
			for(int xCord = column;count < remain.length(); xCord--)
			{
				processing.text(remain.charAt(remain.length()-(count+1)),xCord,row);
				processing.image(img,xCord,row);
				count++;
			}
		}
		*/
		
	}
	public static int[][] moveAllObjects(int[][] positions, int dx, int dy,int width, int height)
	{
		for(int row = 0; row < positions.length; row++)
		{
			if(dx < 0) //Needed to ensure proper wrapping and no out of bounds exceptions
			{
				if(positions[row][0] > 0)
				{
					positions[row][0] += dx;
				}
				else
				{
					positions[row][0] = width - 1;
				}
			}
			else if(dx > 0)
			{
				if(positions[row][0] < width-dx)
				{
					positions[row][0] += dx;
				}
				else
				{
					positions[row][0] = 0;
				}
			}

			
			if(dy < 0) //Needed to ensure proper wrapping and no out of bounds exceptions.

			{
				if(positions[row][1] > 0)
				{
					positions[row][1] += -(height + 50 - positions[row][1])/50;;
				}
				else
				{
					positions[row][1] = height - 1;
					
				}
			}
			else if(dy > 0)
			{
				if(positions[row][1] < height-dy)
				{
					positions[row][1] += dy;
				}
				else
				{
					positions[row][1] = 0;
				}
			}
			
		}
		return positions;
	}
	
	public static void onClick(Data data, int mouseX, int mouseY)
	{
		data.hookPositions[0][0] = mouseX;
		data.hookPositions[0][1] = data.processing.height-1;
	}
}