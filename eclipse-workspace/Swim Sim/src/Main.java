import java.util.Arrays;

public class Main {

	public static void main(String[] args) {
		char[][] tank = new char[10][10];
		fillTank(tank,'~');
		
		renderTank(tank);
		
		System.out.println();
		System.out.println("Random Positions:");
	
		
		
		int[][] posB = generateRandomPositions(3,10,10);
		
		System.out.println(Arrays.deepToString(posB));
		for(int row = 0; row < posB.length; row++)
		{
			placeFishInTank(tank, posB[row][0], posB[row][1]);
		}
		
		renderTank(tank);
		
		
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
	
	public static void placeFishInTank(char[][] tank, int xPos, int yPos)
	{
		
	}
}


