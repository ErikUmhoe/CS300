import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class SwimSimulation {
	private PApplet processing;
	private Fish[] fish;
	private Food[] food;
	private Hook hook;
	
	public SwimSimulation(PApplet processing)
	{
		this.processing = processing;
		
		File ssf = new File("FileOptions.ssf");
		String[] ssdLocations = readSSF(ssf);
		
		for(int i = 0; i < ssdLocations.length; i++)
		{
			System.out.println(ssdLocations[i]);
		}
		
		int randomSSD = Utility.randomInt(ssdLocations.length);
		System.out.println("Random location: " + ssdLocations[randomSSD]);
		
		readSSD(ssdLocations[randomSSD]);
		
		//fish = new Fish[4];
		//for(int num = 0; num < fish.length; num++)
			//fish[num] = new Fish(processing);
		
		//food = new Food[6];
		//for(int num = 0; num < food.length; num++)
		//	food[num] = new Food(processing);
		
		//hook = new Hook(processing);
	}
	
	public void update()
	{

		processing.clear();
		processing.background(0,255,255);
		
		for(int num = 0; num < fish.length; num++)
			fish[num].update();
		
		for(int num = 0; num < food.length; num++)
			food[num].update();
		
		hook.update();
		
		for(int numFish = 0; numFish < fish.length; numFish++)
		{
			for(int numFood = 0; numFood < food.length; numFood++)
			{
				fish[numFish].tryToEat(food[numFood]);
			}
		}
		
		for(int numFish = 0; numFish < fish.length; numFish++)
		{
			hook.tryToCatch(fish[numFish]);
		}
	}
	
	public void handleClick(int mouseX, int mouseY)
	{
		hook.handleClick(mouseX, mouseY);
	}
	
	public String[] readSSF(File ssf)
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
	private void readSSD(String ssdLoc)
	{
		Scanner reader = null;
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
					sub = lines.get(x).split(":");
					sub[1] = sub[1].trim();
					index = Integer.parseInt(sub[1]);
					fish = new Fish[index];
					for(int y = 1; y <= index; y++)
					{
						objectPosition = lines.get( x + y ).split(",");
						fish[y - 1] = new Fish(processing, Integer.parseInt(objectPosition[0].trim()), Integer.parseInt(objectPosition[1].trim()));
					}
					x += index;
				}
				else if(lines.get(x).contains("food"))
				{
					sub = lines.get(x).split(":");
					sub[1] = sub[1].trim();
					index = Integer.parseInt(sub[1]);
					food = new Food[index];
					for(int y = 1; y <= index; y++)
					{
						objectPosition = lines.get( x + y ).split(",");
						food[y - 1] = new Food(processing, Integer.parseInt(objectPosition[0].trim()), Integer.parseInt(objectPosition[1].trim()));
					}
					x += index;
				}
				else if(lines.get(x).contains("hook"))
				{
					objectPosition = lines.get( x + 1 ).split(",");
					hook = new Hook(processing, Integer.parseInt(objectPosition[0].trim()), Integer.parseInt(objectPosition[1].trim()));
				}
				else
				{
					
				}
			}
			
		}catch(FileNotFoundException e)
		{
			System.out.println("WARNING: Could not find or open the " + ssdLoc + " file.");
		}finally {
            if( reader != null ) reader.close();
        }
	}
}
