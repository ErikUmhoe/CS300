import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.zip.DataFormatException;

/**
 * This creates a fish tank simulation.
 *
 *@author Nick Stoffel
 *@author Erik Umhoefer
 *@version 4.0
 *@since 2017-10-04
 */
public class SwimSimulation {

	//Creates the simObjects that will be instantiated here
	private PApplet processing;
	protected ArrayList<SimObject> simObjects = new ArrayList<SimObject>();
	
	private Hook hook; 
	/**
	 * Method that sets up necessary requirements for the applet to run
	 * 
	 * @param processing
	 */
	public SwimSimulation(PApplet processing) 
	{

		SimObject.setProcessing(processing);
		File ssf = new File("FileOptions.ssf"); 
		/* Looks for FileOptions.ssf and splits the ssd file locations into strings.
		 * if the file is found
		 */
		String[] ssdLocations = readSSF(ssf);

		// If there is an issue with FileOptions.ssf, load the default values for locations
		if(ssdLocations[0]==null)
		{
			this.loadDefault(SimObject.processing);
		}
		// Randomly selects a file location for ssd file
		int randomSSD = Utility.randomInt(ssdLocations.length);

		// Reads the ssd file and sets up fish tank simObjects
		readSSD(ssdLocations[randomSSD]);
		
	}

	/**
	 * Method that handles the updating of the applet.
	 */
	public void update() 
	{
		
		//simObjects.add(new TestSimObject(SimObject.processing.width, SimObject.processing.height));
		if(Utility.randomInt(20) == 1)
		{
			simObjects.add(new Food(Utility.randomInt(SimObject.processing.width), SimObject.processing.height));
			
		}

		SimObject.processing.clear();
		SimObject.processing.background(0, 255, 255);

		//Calls update on each fish, food, and hook object
		for (int i = 0; i < simObjects.size(); i++) {
			simObjects.get(i).update();
			for (int x = 0; x < simObjects.size(); x++) {
				simObjects.get(i).tryToInteract(simObjects.get(x));
			}
			hook.tryToInteract(simObjects.get(i));
		}
		for (int i = 0; i < simObjects.size(); i++) {
			simObjects.get(i).update();
		}
		hook.update();
		for(int i = 0; i < simObjects.size(); i++)
		{
			if(simObjects.get(i).shouldBeRemoved() == true)
			{
				//System.out.println(simObjects.get(i));
				simObjects.remove(i);
				i--;
			}
		}
//		for(SimObject sim: simObjects)
//		{
//			System.out.println(sim.toString());
//		}
	}

	/**
	 * Handles the click by calling the hook's handleClick method.
	 * 
	 * @param mouseX
	 * @param mouseY
	 */
	public void handleClick(int mouseX, int mouseY)
	{
		hook.handleClick(mouseX, mouseY);

	}

	/**
	 * Attempts to read FileOptions.ssf.
	 * 
	 * @param ssf
	 * @return
	 */
	public static String[] readSSF(File ssf)
	{
		String[] files;
		String sub = "";
		Scanner reader = null;

		try
		{
			/* This code block reads from the ssf file and seperates the 
			 * .ssd file paths within to an array of the locations
			 */
			reader = new Scanner(ssf);

			// Reads entire file into the String sub
			while(reader.hasNextLine())
			{
				sub += reader.nextLine();
			}

			// Splits the String sub into an array of strings, each element holding one file path
			files = sub.split(";");

			// Trims off extra white space and fixes any issues with separator characters
			for(int i = 0; i < files.length; i++)
			{
				files[i] = files[i].trim();
				files[i].replace('\\', File.separatorChar).replace('/', File.separatorChar);
			} 


		} catch(FileNotFoundException e) /* If the ssf file is not found, print an error
		and load the defaults */
		{
			System.out.println("WARNING: Could not find or open the FileOptions.ssf file.");
			files = new String[] {null};

			return files;

		} finally // Closes Scanner
		{
			if(reader != null)
			{
				reader.close();
			}
		}
		return files;

	}

	/**
	 * Loads the default values for the number of fish and foods, and gives each object random locations.
	 * 
	 * @param processing
	 */
	private void loadDefault(PApplet processing)
	{
		simObjects = new ArrayList<SimObject>(10);
		hook = new Hook();

		for(int i = 0; i < 4; i++)
		{
			simObjects.add(new Fish());
		}
		for(int i = 0; i < 6; i++)
		{
			simObjects.add(new Food());
		}	

	}

	/**
	 * Attempts to read a file location with type .ssd.
	 * 
	 * @param ssdLoc
	 */
	private void readSSD(String ssdLoc)
	{
		Scanner reader = null;
		String curObject = "";
		String[] sub;
		String[] objectPosition;
		ArrayList<String> lines = new ArrayList<String>();
	
		int index;

		try {
			// Attempts to read the ssd file
			File ssd = new File(ssdLoc);
			reader = new Scanner(ssd);

			// Transfers the lines of text to an Array List
			while(reader.hasNextLine())
			{
				lines.add(reader.nextLine());
			}

			// Trims each line, sets it to lower case, and removes line if blank.
			for(int x = 0; x < lines.size(); x++)
			{
				lines.set(x, lines.get(x).trim());
				lines.set(x, lines.get(x).toLowerCase());
				if(lines.get(x).isEmpty())
				{
					lines.remove(x);
					x--;
				}
			}

			// Goes through list object and sets up fish, food, and hook simObjects
			for(int x = 0; x < lines.size(); x++)
			{
				// Checks if the current line is setting up fish object
				if(lines.get(x).contains("fish"))
				{
					curObject = "FISH";

					/* Splits up the current line to get the amount of simObjects to set up,
					 *  and trims it
					 */
					sub = lines.get(x).split(":");
					sub[1] = sub[1].trim();

					// Gets the amount of fish simObjects to set up
					index = Integer.parseInt(sub[1]);

					

					// Sets up every fish object needed
					for(int y = 1; y <= index; y++)
					{
						// Splits up the x and y positions of fish object
						objectPosition = lines.get( x + y ).split(",");

						// Adds Fish object to fishes array using trimmed x and y
						simObjects.add(new Fish(Integer.parseInt(objectPosition[0].trim()),Integer.parseInt(objectPosition[1].trim())));
					}

					// Sets x to skip the lines used to set up the fishes array
					x += index;

					// Checks to make sure the correct amount of fish simObjects was set up
					for(int y = 0; y < simObjects.size(); y++)
					{
						if(simObjects.get(y) == null)
						{
							throw new DataFormatException();
						}
					}
				}

				// Checks if the current line is setting up food object
				else if(lines.get(x).contains("food"))
				{
					curObject = "FOOD";

					/* Splits up the current line to get the amount of simObjects to set up,
					 *  and trims it
					 */
					sub = lines.get(x).split(":");
					sub[1] = sub[1].trim();

					// Gets the amount of food simObjects to set up
					index = Integer.parseInt(sub[1]);


					// Sets up every food object needed
					for(int y = 1; y <= index; y++)
					{
						// Splits up the x and y positions of food object
						objectPosition = lines.get( x + y ).split(",");

						// Adds Food object to foods array using trimed x and y
						simObjects.add(new Food( Integer.parseInt(
								objectPosition[0].trim()),
								Integer.parseInt(objectPosition[1].trim())));
					}

					// Sets x to skip the lines used to set up the foods array
					x += index;

					// Checks to make sure the correct amount of Food simObjects was set up
					for(int y = 0; y < simObjects.size(); y++)
					{
						if(simObjects.get(y) == null)
						{
							throw new DataFormatException();
						}
					}
				}

				// Checks if the current line is setting up hook object
				else if(lines.get(x).contains("hook"))
				{
					curObject = "HOOK";

					// Splits up the x and y position of hook object
					objectPosition = lines.get( x + 1 ).split(",");

					// Adds Fish object to fishes array using trimed x and y
					hook = new Hook(Integer.parseInt(objectPosition[0].trim()),
							Integer.parseInt(objectPosition[1].trim()));

					// Sets x to skip the lines used to set up the hook object
					x++;

					// Checks to make sure the correct amount of Hook simObjects was set up
					if(hook == null)
					{
						throw new DataFormatException();
					}

				}

				// Checks if any extra positions were added
				else
				{
					throw new DataFormatException();
				}
			}

			// Checks to make sure every object was set up
			if(simObjects == null || hook == null)
			{
				System.out.println("Warning: Missing specification for the number and initial" +
						"positions of fishes, foods, or hook.");
				throw new DataFormatException();
			}
		} catch(FileNotFoundException e) // Prints error message and calls for default tank
		{
			System.out.println("WARNING: Could not find or open the " + ssdLoc + " file.");
			this.loadDefault(SimObject.processing);
		} catch(NullPointerException e) // Prints error message and calls for default tank
		{
			System.out.println("WARNING: Failed to load objects and positions from file.");
			this.loadDefault(SimObject.processing);
		} catch(ArrayIndexOutOfBoundsException e) // Prints error message and calls for default tank
		{
			System.out.println("Warning Number of " + curObject + " does not match number of " +
					curObject + " positions.");
			this.loadDefault(SimObject.processing);
		} catch(NumberFormatException e) // Prints error message and calls for default tank
		{
			System.out.println("Warning Number of " + curObject + " does not match number of " +
					curObject + " positions.");
			this.loadDefault(SimObject.processing);
		} catch(DataFormatException e) // Prints error message and calls for default tank
		{
			System.out.println("Warning Number of " + curObject + " does not match number of " +
					curObject + " positions.");
			this.loadDefault(SimObject.processing);
		} finally { // Closes scanner
			if(reader != null)
			{
				reader.close();
			}
		}

	}

}