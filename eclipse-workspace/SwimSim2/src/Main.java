import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		
		/*Data dataMain = new Data();
		setup(dataMain);
		while(true)
		{
			update(dataMain);
		}
		*/
		Utility.startSimulation();
		
		
		
	}
	
	public static void update(Data data) {
		for(int x = 0; x < Integer.MAX_VALUE; x++)
		{
			
			
			//Puts objects in first. For dx, negative value moves it right, positive left. For dy, negative value moves down, positive up
			data.fishPositions = moveAllObjects(data.fishPositions,1,0, data.tank[0].length, data.tank.length);
			data.foodPositions = moveAllObjects(data.foodPositions,-1,1, data.tank[0].length, data.tank.length);
			data.hookPositions = moveAllObjects(data.hookPositions,0,-1, data.tank[0].length, data.tank.length);
			
			
			data.processing.background(0,255,255);
			
			for(int row = 0; row < data.fishPositions.length; row++)
			{
				placeObjectInTank("><(('>",data.tank, data.fishPositions[row][0], data.fishPositions[row][1]);
			}
			for(int row = 0; row < data.foodPositions.length; row++)
			{
				placeObjectInTank("*",data.tank, data.foodPositions[row][0], data.foodPositions[row][1]);
			}
			for(int row = 0; row < data.hookPositions.length; row++)
			{
				placeObjectInTank("J",data.tank, data.hookPositions[row][0],data.hookPositions[row][1]);
			}
			
		}
		
	}

	public static void setup(Data dataMain) {
		int height = 8, width = 32;
		int fishNum = 4,foodNum = 6, hookNum = 1;
		dataMain.tank = new char[height][width];
		fillTank(dataMain.tank,'~');
		
		renderTank(dataMain.tank);
		
		System.out.println();
		System.out.println("Random Positions:");
		
		dataMain.fishPositions = generateRandomPositions(fishNum,dataMain.tank[0].length, dataMain.tank.length);
		dataMain.foodPositions = generateRandomPositions(foodNum,dataMain.tank[0].length, dataMain.tank.length);
		dataMain.hookPositions = generateRandomPositions(hookNum,dataMain.tank[0].length, dataMain.tank.length);
		
		for(int row = 0; row < dataMain.fishPositions.length; row++)
		{
			placeObjectInTank("><(('>",dataMain.tank, dataMain.fishPositions[row][0], dataMain.fishPositions[row][1]);
		}
		for(int row = 0; row < dataMain.foodPositions.length; row++)
		{
			placeObjectInTank("*",dataMain.tank, dataMain.foodPositions[row][0], dataMain.foodPositions[row][1]);
		}
		for(int row = 0; row < dataMain.hookPositions.length; row++)
		{
			placeObjectInTank("J",dataMain.tank, dataMain.hookPositions[row][0], dataMain.hookPositions[row][1]);
		}
		renderTank(dataMain.tank);
		
	}

	/**
	 * Copies the water character into every position in the tank array. The two-dimensional tank
	 * array can have dimensions of any size(s).
	 * 
	 * @param tank will contain all water characters after this method is called.
	 * @param water is the character copied into the tank.
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
		//Uses recursive method to ensure that no duplicate positions
		//int[][] positions = duplicateCheck(randomPositions, height);
		
		return randomPositions;
		
	}
	
	public static void placeObjectInTank(String object, PApplet processing, int column, int row)
	{
		/*tank[row][column] = object.charAt(object.length()-1);
		if(object.length() > 1)
		{
			String remain = object;
			int count = 0;
			for(int xCord = column; xCord >= 0 && count < remain.length(); xCord--)
			{
				tank[row][xCord] = remain.charAt(remain.length()-(count+1));
				count++;
			}
			for(int xCord = tank[0].length-1;count < remain.length(); xCord--)
			{
				tank[row][xCord] = remain.charAt(remain.length()-(count+1));
				count++;
			}
		}*/
		
		
	}
	//Beautiful Recursive method to check if any duplicate positions are in place
	public static int[][] duplicateCheck(int[][] positions, int height)
	{
		for(int row = positions.length-1; row > 0; row--)
		{
			for(int prevRow = row-1; prevRow >= 0; prevRow--)
			{
				if(positions[row][1] == positions[prevRow][1])
				{
					positions[row][1] = Utility.randomInt(height);
					duplicateCheck(positions, height);
				}
			}
		}
		return positions;
	}
	
	public static int[][] moveAllObjects(int[][] positions, int dx, int dy,int width, int height)
	{
		int realDy=dy;
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

			
			if(realDy < 0) //Needed to ensure proper wrapping and no out of bounds exceptions.

			{
				if(positions[row][1] > 0)
				{
					positions[row][1] += realDy;
				}
				else
				{
					positions[row][1] = height - 1;
				}
			}
			else if(realDy > 0)
			{
				if(positions[row][1] < height-realDy)
				{
					positions[row][1] += realDy;
				}
				else
				{
					positions[row][1] = 0;
				}
			}
			
		}
		return positions;
	}
}