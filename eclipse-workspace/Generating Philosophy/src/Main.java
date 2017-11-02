import java.util.Scanner;
import java.util.function.Function;
////////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
//Title:           Generate Philosphy
//Files:           Main.java, Generator.java, EvenNumberGenerator.java, NumberGenerator.java
//Course:          CS300, Fall Term, 2017
//
//Author:         	Nick Stoffel
//Email:          	nastoffel@wisc.edu
//Lecturer's Name: 	Gary Dahl
//
////////////////////PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
//Partner Name:    Erik Umhoefer
//Partner Email:   ejumhoefer@wisc.edu
//Lecturer's Name: Gary Dahl
//
//VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//__x_ Write-up states that pair programming is allowed for this assignment.
//__x_ We have both read and understand the course Pair Programming Policy.
//__x_ We have registered our team prior to the team registration deadline.
//
///////////////////////////// CREDIT OUTSIDE HELP /////////////////////////////
//
//Students who get help from sources other than their partner must fully 
//acknowledge and credit those sources of help here.  Instructors and TAs do 
//not need to be credited here, but tutors, friends, relatives, room mates 
//strangers, etc do.  If you received no outside help from either type of 
//source, then please explicitly indicate NONE.
//
//Persons:         NONE
//Online Sources: 	NONE
//
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////
public class Main {

	/**
	 * Main method to get a user to enter in a Wikipedia page then this checks
	 * the entered page if it leads to the Wikipedia page Philosophy
	 * @param args
	 */
	public static void main(String[] args) {

		String wikiChoice;
		Scanner input = new Scanner(System.in);
		
		System.out.print("Please enter a Wikipedia topic: " );
		wikiChoice = input.nextLine();
		wikiChoice = wikiChoice.replaceAll(" ", "_"); // Replaces all spaces with "_"
		wikiChoice = wikiChoice.trim();

		int count = 0;
		// For each loop to go through a generated list Wikipedia pages until it 
		// fails or reaches Philosophy
		for(String i : new Generator<String>(100,"/wiki/"+wikiChoice,new NextWikiLinkFunction()))
		{
			System.out.println(count +": " + i);
			count++;
			if(i.indexOf("Philosophy") > 0)
			{	
				break; // Breaks the for each loop if the page is Philosophy
			}
			else if(i.contains("FAILED"))
				break; // Breaks from the for each loop if the Wiki fails to load

		}
		
		input.close();
	}

}

/**
 * 
 * @author Erik U
 * @author Nick S
 *
 */
class DoubleFunction implements Function<Integer, Integer> {

	@Override
	public Integer apply(Integer t) {

		return t * 2;
	}

}

/**
 * Class that has one 
 * 
 * @author Erik U
 * @author Nick S
 *
 */
class AddExclamationFunction implements Function<String, String> {

	@Override
	public String apply(String t) {
		return t += "!";
	}

}
