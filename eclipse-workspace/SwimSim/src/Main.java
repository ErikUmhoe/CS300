
import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		int height = 8, width = 32;
		char[][] tank = new char[height][width];
		fillTank(tank,'~');
		
		renderTank(tank);
		
		System.out.println();
		System.out.println("Random Positions:");
		
		int[][] posF = generateRandomPositions(4,width,height);
		
		System.out.println(Arrays.deepToString(posF));
		for(int row = 0; row < posF.length; row++)
		{
			placeFishInTank(tank, posF[row][0], posF[row][1]);
		}
		
		renderTank(tank);
		
		for(int x = 0; x < 100; x++)
		{
			Utility.pause(200);
			System.out.println("\n\n\n");
			posF=moveAllFish(posF, width, height);
			for(int row = 0; row < posF.length; row++)
			{
				placeFishInTank(tank, posF[row][0], posF[row][1]);
			}
			renderTank(tank);
		}
		
		
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
		for(int row = tank.length-1; row >= 0; row--)
		{
			for(int col = tank[row].length-1; col >= 0; col--)
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
		int[][] positions = duplicateCheck(randomPositions, height);
		
		return positions;
		
	}
	
	public static void placeFishInTank(char[][] tank, int xPos, int yPos)
	{
		tank[yPos][xPos] = '>';
		String fish = "'((<>";
		int fishChar = 0;
		
		for(int xCord = xPos + 1; xCord < tank[yPos].length && fishChar < fish.length(); xCord++)
		{
			tank[yPos][xCord] = fish.charAt(fishChar);
			fishChar++;
		}
		if(xPos>(tank[yPos].length-(fish.length()+2)))
		{
			tank[yPos][fish.length()-fishChar]='~';
		}
		else
		{
			tank[yPos][xPos+fish.length()+1]='~';
		}
		for(int xCord = 0;fishChar < fish.length(); xCord++)
		{
			tank[yPos][xCord] = fish.charAt(fishChar);
			fishChar++;
		}
	}
	
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
	
	public static int[][] moveAllFish(int[][] positions, int width, int height)
	{
		for(int row = 0; row < positions.length; row++)
		{
			if(positions[row][0]>0)
			{
				positions[row][0]--;
			}
			else
			{
				positions[row][0] = width-1;
			}
		}
		return positions;
	}
}