//////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
// Title:           AllSortsOfStudents
// Files:           Main.java, test01.txt, test02.txt, test03.txt
// Course:          CS300
//
// Author:          Nick Stoffel
// Email:           nastoffel@wisc.edu
// Lecturer's Name: Gary Dahl
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    Erik Umhoefer
// Partner Email:   ejumhoefer@wisc.edu
// Lecturer's Name: Gary Dahl
// 
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   x Write-up states that pair programming is allowed for this assignment.
//   x We have both read and understand the course Pair Programming Policy.
//   x We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
// Students who get help from sources other than their partner must fully 
// acknowledge and credit those sources of help here.  Instructors and TAs do 
// not need to be credited here, but tutors, friends, relatives, room mates 
// strangers, etc do.  If you received no outside help from either type of 
// source, then please explicitly indicate NONE.
//
// Persons:         NONE
// Online Sources:  https://www.java2blog.com/heap-sort-in-java/ - helped with heap sort logic
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * This program loads in a text file with student names and test scores.
 * It then sorts using insertions, selection, and heap sorts these students/scores
 * based on a user entered in choice.
 * 
 *@author Nick Stoffel
 *@author Erik Umhoefer
 *@version 1.0
 *@since 2017-12-5
 */
public class Main {
	/**
	 * Main method for the program. Asks user for the text file.
	 * Then asks which sort the user wants to use and what data to use it on.
	 * 
	 * @param args
	 */
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

			// User wants to quit.
			if(userIn.substring(0,1).equalsIgnoreCase("q")) {}

			// User wants optimal time sort (heap sort).
			else if(userIn.substring(0,1).equalsIgnoreCase("o"))
			{
				// Call heap sort method with the data location key they want to be sorted.
				heapSort(objs, Integer.parseInt(userIn.substring(1).trim()));
			}

			// User wants adaptive sort (insertion sort).
			else if(userIn.substring(0,1).equalsIgnoreCase("a"))
			{
				// Call heap sort method with the data location key they want to be sorted.
				insertionSort(objs, Integer.parseInt(userIn.substring(1).trim()));
			}

			// User wants fewest swaps (selection sort).
			else if(userIn.substring(0,1).equalsIgnoreCase("f"))
			{
				// Call selection sort method with the data location key they want to be sorted.
				selectionSort(objs, Integer.parseInt(userIn.substring(1).trim()));
			}

			// Unknown command.
			else
				System.out.println("Unknown Command");

		} while (!userIn.substring(0,1).equalsIgnoreCase("q"));

		in.close();

	}
	
	/**
	 * Reads the file the user designates and enters it into a 2D Object array.
	 * 
	 * @param path
	 * @return Object[][]
	 */
	public static Object[][] readFile(String path) {

		ArrayList<String> lines = new ArrayList<String>();
		Scanner fileReader = null;
		Object[][] values;

		try
		{
			File students = new File(path);
			fileReader = new Scanner(students);

			// Adds every line of the file into the arraylist as its own element.
			while(fileReader.hasNextLine())
			{
				lines.add(fileReader.nextLine());
			}

			// Creates a 2D array of size number of lines x number of values in a line.
			values = new Object[lines.size()][lines.get(0).split(",").length+1];

			// Parses through each line, assigning it into the 2D Array.
			// First element of row of 2D array is the name in string, all other values in that row 
			// are student scores
			for(int i = 0; i < values.length; i++)
			{
				// Splits each line by a semicolon.
				String[] line = lines.get(i).split(":");

				// Puts the name of the student into values.
				values[i][0] = line[0].trim();

				// Splits each score by commas.
				line = line[1].split(",");

				// Adds scores into values.
				for(int j = 1; j < values[i].length; j++)
				{
					// Puts each score in values in separate array cell.
					values[i][j] = line[j-1].trim();
				}
			}

			return values;
		}catch(FileNotFoundException e)
		{
			System.out.println("No file of the path \"" + path + "\" was found. "
					+ "Please provide a correct filepath.");
			return null;
		} finally
		{
			if(fileReader != null)
				fileReader.close();
		}

	}

	/**
	 * Method that sorts each student by key using insertion sort.
	 * 
	 * @param values
	 * @param key
	 */
	public static void insertionSort(Object[][] values, int key)
	{
		boolean swap = false;
		// Go through entire list of students.
		for(int i = 1; i < values.length; i++)
		{
			// Go through i to first student.
			for(int j = i; j > 0; j--)
			{
				// Compare names alphabetically.
				if(key == 0 && values[j][key].toString().compareTo(values[j-1][key].toString()) < 0)
					swap = true;

				// Compare by the score values at index key to swap if higher index is smaller.
				else if( key > 0 && Integer.parseInt(values[j][key].toString()) 
						< (Integer.parseInt(values[j-1][key].toString())))
					swap = true;	
				
				// If swap is true, swap values.
				if(swap)
				{
					swap(values, j, j-1);
					swap = false;
				}
			}
		}
	}

	/**
	 * Method that sorts each student by key using selection sort.
	 * 
	 * @param values
	 * @param key
	 */
	public static void selectionSort(Object[][] values, int key)
	{
		// Loop through entire list.
		for(int i = 0; i < values.length; i++)
		{
			int minIndex = i;
			// Go through rest of values to find next smallest value.
			for(int j = i+1; j < values.length; j++)
			{
				// Compares student names alphabetically.
				if(key == 0 && values[j][key].toString().compareTo(values[minIndex][key].toString()) < 0)
					minIndex = j;
				
				// Compares student scores at key index to find, and sets minIndex to smallest value.
				else if(key > 0 && Integer.parseInt(values[j][key].toString()) 
						< Integer.parseInt(values[minIndex][key].toString()))
					minIndex = j;
			}
			swap(values, i, minIndex);
		}
	}

	// Heap Sort
	/**
	 * Method that sorts each student by key using heap sort.
	 * 
	 * @param values
	 * @param key
	 */
	public static void heapSort(Object[][] values, int key)
	{
		int sizeOfHeap = values.length-1;
		
		// Builds the heap.
		for(int i=(sizeOfHeap)/2; i >= 0; i--)
			heapify(values, i, sizeOfHeap, key);
		
		// Puts largest heap value at end of loop.
		for(int i=sizeOfHeap; i>0; i--)
		{
			swap(values, 0, i);
			
			sizeOfHeap--;
			
			// Re-heapifies the edited heap.
			heapify(values, 0, sizeOfHeap, key);
		}
	}

	/**
	 * Builds the heap.
	 * 
	 * @param values
	 * @param i
	 * @param size
	 * @param key
	 */
	public static void heapify(Object[][] values, int i, int size,  int key)
	{
		int left = 2*i+1;
		int right = 2*i+2;
		int max;
		// Uses this if comparing names.
		if(key == 0)
		{
			// Sets max value to left if it is within the heap size,
			// and is larger than current index.
			if(left <= size && 
					values[left][key].toString().compareTo(values[i][key].toString()) > 0)
				max = left;
			// Else sets max index to current index.
			else 
				max = i;
			// Sets max value to left if it is within the heap size, 
			// and is larger than current index.
			if(right <= size && 
					values[right][key].toString().compareTo(values[max][key].toString()) > 0)
				max = right;
		}
		// Uses this if comparing scores.
		else
		{
			// Sets max value to left if it is within the heap size,
			// and is larger than current index.
			if(left <= size && Integer.parseInt(values[left][key].toString()) 
					> Integer.parseInt(values[i][key].toString()))
				max = left;
			// Else sets max index to current index.
			else 
				max = i;
			// Sets max value to left if it is within the heap size, 
			// and is larger than current index.
			if(right <= size && Integer.parseInt(values[right][key].toString()) 
					> Integer.parseInt(values[max][key].toString()))
				max = right;
		}
		// If max is not current node, exchange it with max.
		if(max!=i) {
			swap(values, i, max);
			// Go through heap with new index at max.
			heapify(values, max, size,  key);
		}
	}

	/**
	 * Method that swaps the values at the given indexes.
	 * 
	 * @param values
	 * @param i
	 * @param j
	 */
	public static void swap(Object[][] values, int i, int j)
	{
		Object[] temp = values[j];
		values[j] = values[i];
		values[i] = temp;
	}

	/**
	 * Method that prints the students with their scores.
	 * 
	 * @param objs
	 */
	public static void printScores(Object[][] objs)
	{
		// Go through entire list.
		for(int i = 0; i < objs.length; i++)
		{
			// Print the name with a semicolon + tab.
			System.out.print(objs[i][0] + ":\t");
			// Add another tab if the name is less than 7 to keep scores lined up.
			if(objs[i][0].toString().length() < 7)
				System.out.print("\t");
			// Prints till the second last score with a coma in between them.
			for(int j = 1; j < objs[i].length-1; j++)
				System.out.print(objs[i][j] + ", ");
			// Prints the last score.
			System.out.println(objs[i][objs[i].length-1]);
		}
	}
}