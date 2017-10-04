import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SwimSimulation {

	private PApplet processing;
	private Fish[] fishes;
	private Food[] foods;
	private Hook hook;

	public static void main(String[] args) {
		
		
	}

	public SwimSimulation(PApplet processing) {

		this.processing = processing;
		File ssf = new File("FileOptions.ssf");
		String[] ssdLocations = readSSF(ssf);
		
		if(ssdLocations[0]==null)
		{
			this.loadDefault(processing);
		}
		else {
			for(int i = 0; i < ssdLocations.length; i++)
			{
				System.out.println(ssdLocations[i]);
			}
		}
		
		/*int height = processing.height, width = processing.width;
		int fishNum = 4, foodNum = 6, hookNum = 1;
		
		fishes = new Fish[fishNum];
		hook = new Hook(processing);
		foods = new Food[foodNum];
		
		for (int i = 0; i < fishNum; i++) {
			fishes[i] = new Fish(this.processing);
		}
		for (int i = 0; i < foodNum; i++) {
			foods[i] = new Food(this.processing);
		}*/

	}

	public void update() {

		processing.clear();
		processing.background(0, 255, 255);

		/*
		 * for(int row = 0; row < fishPositions.length; row++) {
		 * Main.placeObjectInTank("><(('>",processing, fishPositions[row][0],
		 * fishPositions[row][1]); }
		 */
		/*
		 * for(int row = 0; row < foodPositions.length; row++) {
		 * Main.placeObjectInTank("*",processing, foodPositions[row][0],
		 * foodPositions[row][1]); } for(int row = 0; row < hookPositions.length; row++)
		 * { Main.placeObjectInTank("J",processing,
		 * hookPositions[row][0],hookPositions[row][1]); }
		 */
		for (int i = 0; i < fishes.length; i++) {
			fishes[i].update();
			for (int x = 0; x < foods.length; x++) {
				fishes[i].tryToEat(foods[x]);
			}
			hook.tryToCatch(fishes[i]);
		}
		for (int i = 0; i < foods.length; i++) {
			foods[i].update();
		}
		hook.update();

	}

	public void handleClick(int mouseX, int mouseY) {
		/*
		 * hookPositions[0][0] = mouseX; hookPositions[0][1] = processing.height-1;
		 */

		hook.handleClick(mouseX, mouseY);
	}

	public static String[] readSSF(File ssf)
	{
		String[] files;
		String sub = "";
		try
		{
			int locationSemi;
			Scanner reader = new Scanner(ssf);
			while(reader.hasNextLine())
			{
				sub += reader.nextLine();
			}
			files = sub.split(";");
			for(int i = 0; i < files.length; i++)
			{
				files[i] = files[i].trim();
				files[i].replace('\\',File.separatorChar).replace('/',File.separatorChar);
			} 
			
			
		}catch(FileNotFoundException e)
		{
			System.out.println("WARNING: Could not find or open the FileOptions.ssf file.");
			files = new String[] {null};
			
			return files;
			
		}
		return files;
		
	}
	
	private void loadDefault(PApplet processing)
	{
		fishes = new Fish[4];
		foods = new Food[6];
		hook = new Hook(this.processing);
		for(int i = 0; i < fishes.length; i++)
		{
			fishes[i] = new Fish(processing);
		}
		for(int i = 0; i < foods.length; i++)
		{
			foods[i] = new Food(processing);
		}	
		
	}
	
	
	

}
