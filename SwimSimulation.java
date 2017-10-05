import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.zip.DataFormatException;

public class SwimSimulation {

	//Creates the objects that will be instantiated here
	private PApplet processing;
	private Fish[] fishes; 
	private Food[] foods; 
	private Hook hook; 

	//Method that sets up necessary requirements for the applet to run
	public SwimSimulation(PApplet processing) 
	{

		this.processing = processing;
		File ssf = new File("FileOptions.ssf"); 
		//Looks for FileOptions.ssf and splits the ssd file locations into strings if the file is found
		String[] ssdLocations = readSSF(ssf);
		
		//If there is an issue with FileOptions.ssf, load the default values for locations
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
		int randomSSD = Utility.randomInt(ssdLocations.length);
		System.out.println("Random location: " + ssdLocations[randomSSD]);
		
		readSSD(ssdLocations[randomSSD]);
	}
	
	//Method that handles the updating of the applet
	public void update() 
	{

		processing.clear();
		processing.background(0, 255, 255);

		//Calls update on each fish, food, and hook object
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
	//Handles the click by calling the hook's handleClick method
	public void handleClick(int mouseX, int mouseY)
	{
		hook.handleClick(mouseX, mouseY);
		
	}
	//Attempts to read FileOptions.ssf
	public static String[] readSSF(File ssf)
	{
		String[] files;
		String sub = "";
		Scanner reader = null;
		try
		{
			/* This code block reads from the ssf file and seperates the 
			.ssd file paths within to an array of the locations */
			int locationSemi;
			reader = new Scanner(ssf);
			
			//Reads entire file into the String sub
			while(reader.hasNextLine())
			{
				sub += reader.nextLine();
			}
			
			//Splits the String sub into an array of strings, each element holding one file path
			files = sub.split(";");
			
			//Trims off extra white space and fixes any issues with seperator charactors
			for(int i = 0; i < files.length; i++)
			{
				files[i] = files[i].trim();
				files[i].replace('\\',File.separatorChar).replace('/',File.separatorChar);
			} 
			
			
		}catch(FileNotFoundException e) //If the ssf file is not found, print an error and load the defaults
		{
			System.out.println("WARNING: Could not find or open the FileOptions.ssf file.");
			files = new String[] {null};
			
			return files;
			
		}finally //Closes Scanner
		{
			if(reader != null)
			{
				reader.close();
			}
		}
		return files;
		
	}
	
	//Loads the default values for the number of fish and foods, and gives each object random locations
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
	private void readSSD(String ssdLoc)
	{
		Scanner reader = null;
		String curObject = "";
		String[] sub;
		String[] objectPosition;
		ArrayList<String> lines = new ArrayList<String>();
		File ssd = new File(ssdLoc);
		int index;
		
		try {
			reader = new Scanner(ssd);
			while(reader.hasNextLine())
			{
				lines.add(reader.nextLine());
			}
			
			for(int x = 0; x < lines.size(); x++)
			{
				lines.get(x).trim();
				lines.set(x, lines.get(x).toLowerCase());
				if(lines.get(x).isEmpty())
				{
					lines.remove(x);
					x--;
				}
			}

			for(int x = 0; x < lines.size(); x++)
			{
				System.out.println("Line " + x + ": "+lines.get(x));
			}

			for(int x = 0; x < lines.size(); x++)
			{
				if(lines.get(x).contains("fish"))
				{
					curObject = "FISH";
					sub = lines.get(x).split(":");
					sub[1] = sub[1].trim();
					index = Integer.parseInt(sub[1]);
					fishes = new Fish[index];
					for(int y = 1; y <= index; y++)
					{
						objectPosition = lines.get( x + y ).split(",");
						fishes[y - 1] = new Fish(processing, Integer.parseInt(objectPosition[0].trim()), Integer.parseInt(objectPosition[1].trim()));
					}
					x += index;

					for(int y = 0; y < fishes.length; y++)
					{
						if(fishes[y] == null)
						{
							throw new DataFormatException();
						}
					}
				}

				else if(lines.get(x).contains("food"))
				{
					curObject = "FOOD";
					sub = lines.get(x).split(":");
					sub[1] = sub[1].trim();
					index = Integer.parseInt(sub[1]);
					foods = new Food[index];
					for(int y = 1; y <= index; y++)
					{
						objectPosition = lines.get( x + y ).split(",");
						foods[y - 1] = new Food(processing, Integer.parseInt(objectPosition[0].trim()), Integer.parseInt(objectPosition[1].trim()));
					}
					x += index;
					for(int y = 0; y < foods.length; y++)
					{
						if(foods[y] == null)
						{
							throw new DataFormatException();
						}
					}
				}

				else if(lines.get(x).contains("hook"))
				{
					curObject = "HOOK";
					objectPosition = lines.get( x + 1 ).split(",");
					hook = new Hook(processing, Integer.parseInt(objectPosition[0].trim()), Integer.parseInt(objectPosition[1].trim()));
					if(hook == null)
					{
						throw new DataFormatException();
					}
					x++;
				}
				else
				{
					throw new DataFormatException();
				}
			}

			if( fishes == null || foods == null || hook == null)
			{
				System.out.println("Warning: Missing specification for the number and initial positions of fishes, foods, or hook.");
				throw new DataFormatException();
			}
		}catch(FileNotFoundException e)
		{
			System.out.println("WARNING: Could not find or open the " + ssdLoc + " file.");
			this.loadDefault(this.processing);
		}catch(NullPointerException e)
		{
			System.out.println("WARNING: Failed to load objects and positions from file.");
			this.loadDefault(this.processing);
		}catch(ArrayIndexOutOfBoundsException e)
		{
			System.out.println("Warning Number of " + curObject + " does not match number of " + curObject + " positions.");
			this.loadDefault(this.processing);
		}catch(NumberFormatException e)
		{
			System.out.println("Warning Number of " + curObject + " does not match number of " + curObject + " positions.");
			this.loadDefault(this.processing);
		}
		catch(DataFormatException e)
		{
			System.out.println("Warning Number of " + curObject + " does not match number of " + curObject + " positions.");
			this.loadDefault(this.processing);
		}finally {
            if( reader != null ) reader.close();
        }
	
	}

}