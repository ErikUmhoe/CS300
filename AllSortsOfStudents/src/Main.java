import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Object[][] objs = readFile("test02.txt");
	}

	public static Object[][] readFile(String path) {
		
		ArrayList<String> lines = new ArrayList<String>();	//Contains all lines of the file seperated into different elements
		Scanner fileReader = null;							//Reads the file line by line into lines arraylist
		Object[][] values;
		try
		{
			File students = new File(path);	//Creates a new file of the path provided
			fileReader = new Scanner(students); //Creates new scanner to iterate through the lines of the file
			//Adds every line of the file into the arraylist as its own element
			while(fileReader.hasNextLine())
			{
				lines.add(fileReader.nextLine());
			}
			
			//Creates a 2D array of size number of lines x number of values in a line
			values = new Object[lines.size()][lines.get(0).split(",").length];
			
			//Parses through each line, assigning it into the 2D Array
			//First element of row of 2D array is the name in string, all other values in that row 
			//are an integer value of the score
			for(int i = 0; i < values.length; i++)
			{
				for(int j = 0; j < values[i].length; j++)
				{
					//Splits each line by comma
					String[] line = lines.get(i).split(",");
					values[i][j] = line[j];
					
					//Trims off any excess whitespace
					values[i][j] = values[i][j].toString().trim();
					
					//Changes values of test scores from string into integer
					if(j > 0)
					{
						values[i][j] = String.valueOf(values[i][j]);
					}
				}
			}
			for(int i = 0; i < values.length; i++)
			{
				for(int j = 0; j < values[i].length; j++)
					System.out.print(values[i][j] + " ");
				System.out.println();
			}
			return values;
			
			
			
			
		}catch(FileNotFoundException e) //if no file is found with the specified path
		{
			System.out.println("No file of the path \"" + path + "\" was found. Please provide a correct filepath.");
			return null;
		}
		
	}
}
