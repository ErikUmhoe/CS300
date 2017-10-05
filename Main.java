////////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
//Title:           Fish Assignment #4; creates a fish game running on an applet
//Files:           Hook.java, Fish.java, Food.java, Swimsulation.java, FishOptions.ssf, any amount of Swim Simulation Data (.ssd) files
//Course:          CS300, fall term, 2017
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
//Online Sources: 	https://docs.oracle.com/javase/7/docs/api/java/util/Arrays.html (JavaDoc for arrays)
//					https://www.javatpoint.com/java-string-isempty (Information regarding the isEmpty() method)
//					https://www.tutorialspoint.com/java/java_documentation.htm (JavaDoc Comment documentation)
//	
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////

/**
 * This program is the driver class for a fish tank simulation that 
 * creates an animated applet of a fish tank.
 *
 *@author Nick Stoffel
 *@author Erik Umhoefer
 *@version 4.0
 *@since 2017-10-04
 */
public class Main {
	
	private static SwimSimulation swimSimulation;
	
	/**
	 * Main method that starts the simulation.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		Utility.startSimulation();
	}
	
	/**
	 * Setup method that creates a new SwimSimulation Object.
	 * 
	 * @param data
	 */
	public static void setup(Data data) {
		
		swimSimulation = new SwimSimulation(data.processing);
	}
	
	/**
	 * Update method every time the simulation updates.
	 * Routes update info to swimSimulation Object.
	 * 
	 * @param data
	 */
	public static void update(Data data) {
		
		swimSimulation.update();
	}

	/**
	 * Detects user input, in this case a mouse click.
	 * Routes mouse click info to swimSimluation Object.
	 * 
	 * @param data
	 * @param mouseX
	 * @param mouseY
	 */
	public static void onClick(Data data, int mouseX, int mouseY)
	{
		swimSimulation.handleClick(mouseX, mouseY);
	}
}