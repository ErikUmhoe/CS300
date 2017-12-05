import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner in = new Scanner(System.in);
		String userIn;
		
		System.out.print("Enter the name of your student data file: ");
		Object[][] objs = readFile(in.nextLine());
		selectionSort(objs, 0);
		do
		{
			printScores(objs);
			System.out.print("> ");
			userIn = in.nextLine().trim();
			if(userIn.substring(0,1).equalsIgnoreCase("q")) {} // User wants to Quit
			
			else if(userIn.substring(0,1).equalsIgnoreCase("o")) // User wants Optimal Time
			{
				heapSort(objs, Integer.parseInt(userIn.substring(1).trim()));
			}
			
			else if(userIn.substring(0,1).equalsIgnoreCase("a")) // User wants Adaptive
			{
				sortInsertion(objs, Integer.parseInt(userIn.substring(1).trim()));
			}
			
			else if(userIn.substring(0,1).equalsIgnoreCase("f")) // User wants Fewest Swaps
			{
				selectionSort(objs, Integer.parseInt(userIn.substring(1).trim()));
			}
			
			else // Unknown command
				System.out.println("Unknown Command");
			
		} while (!userIn.substring(0,1).equalsIgnoreCase("q"));
		
		in.close();
				
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
			values = new Object[lines.size()][lines.get(0).split(",").length+1];
			
			//Parses through each line, assigning it into the 2D Array
			//First element of row of 2D array is the name in string, all other values in that row 
			//are an integer value of the score
			for(int i = 0; i < values.length; i++)
			{
				// Splits each line by semicolon
				String[] line = lines.get(i).split(":");
				
				// Puts name of student into values
				values[i][0] = line[0].trim();
				
				// Splits each score by comma
				line = line[1].split(",");
				for(int j = 1; j < values[i].length; j++)
				{
					// Converts scores to int and put in values
					values[i][j] = Integer.parseInt(line[j-1].trim());
				}
			}
			return values;
		}catch(FileNotFoundException e) //if no file is found with the specified path
		{
			System.out.println("No file of the path \"" + path + "\" was found. Please provide a correct filepath.");
			return null;
		} finally
		{
			if(fileReader != null)
			{
				fileReader.close();
			}
		}
		
	}
	
	public static void sortInsertion(Object[][] values, int key)
	{
		boolean swap = false;
		for(int i = 1; i < values.length; i++)
		{
			for(int j = i; j > 0; j--)
			{
				// Compare by names alphabetically
				if(key == 0 && values[j][key].toString().compareTo(values[j-1][key].toString()) < 0)
					swap = true;

				// If key is not 0, compare by the integer values
				else if( key > 0 && Integer.parseInt(values[j][key].toString()) 
						< (Integer.parseInt(values[j-1][key].toString())))
					swap = true;							
				// If swap is true, swap values
				if(swap)
				{
					swap(values, i, j-1);
					swap = false;
				}
			}
		}
	}
	
	//Selection sort, so far only alphabetically works.
	public static void selectionSort(Object[][] values, int key)
	{
		boolean swap = false;
		//if(key == 0)
		//{
			for(int i = 0; i < values.length; i++)
			{
				int minIndex = i;
				for(int j = i+1; j < values.length; j++)
				{
					if(key == 0 && values[j][key].toString().compareTo(values[minIndex][key].toString()) < 0)
						minIndex = j;
					else if(key > 0 && Integer.parseInt(values[j][key].toString()) 
							< Integer.parseInt(values[minIndex][key].toString()))
						minIndex = j;
				}
				swap(values, i, minIndex);
			}
		//}
	}
	
	// Heap Sort
	public static void heapSort(Object[][] values, int key)
	{
		for(int i = (values.length-1)/2; i >= 0; i--)
			heapify(values, i, values.length-1, key);
		System.out.println("Built Heap");
		int sizeOfHeap = values.length-1;
		for(int i = sizeOfHeap; i>0; i--)
		{
			swap(values, 0, i);
			sizeOfHeap--;
			heapify(values, 0, sizeOfHeap, key);
		}
	}
	
	//Build Heap
	public static void heapify(Object[][] values, int i, int size,  int key)
	{
		int left = 2*i+1;
	    int right = 2*i+2;
	    int max;
	    if(key == 0)
	    {
	    	if(left < size && values[left][key].toString().compareTo(values[i][key].toString()) > 0)
	    		max = left;
	    	else 
	    		max = i;
	    	if(right < size && values[right][key].toString().compareTo(values[max][key].toString()) > 0)
	    		max = right;
	    }
	    else
	    {
	    	if(left < size && Integer.parseInt(values[left][key].toString()) 
					> Integer.parseInt(values[i][key].toString()))
	    		max = left;
	    	else 
	    		max = i;
	    	if(right < size && Integer.parseInt(values[right][key].toString()) 
					> Integer.parseInt(values[max][key].toString()))
	    		max = right;
	    }
	    // If max is not current node, exchange it with max of left and right child
	    if(max!=i) {
	    	//System.out.println("Max: "+values[max][key]+ "Swapping with i: "+ values[i][key]+" key: "+key);
	    	swap(values, i, max);
	    	heapify(values, max, size,  key);
	    }
	}
	
	//Swap values
	public static void swap(Object[][] values, int i, int j)
	{
		Object[] temp = values[j];
		values[j] = values[i];
		values[i] = temp;
	}
	
	// Prints the students and scores
	public static void printScores(Object[][] objs)
	{
		for(int i = 0; i < objs.length; i++)
		{
			System.out.print(objs[i][0] + ":\t");
			for(int j = 1; j < objs[i].length-1; j++)
				System.out.print(objs[i][j] + ", ");
			System.out.println(objs[i][objs[i].length-1]);
		}
	}
}