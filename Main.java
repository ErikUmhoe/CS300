////////////////////ALL ASSIGNMENTS INCLUDE THIS SECTION /////////////////////
//
//Title:           Fish Assignment #4; creates a fish game running on an applet
//Files:           Hook.java, Fish.java, Food.java, Swimsulation.java, FishOptions.ssf, any amount of Swim Simulation Data (.ssd) files
//Course:          CS300, fall term, 2017
//
//Author:         	Erik Umhoefer
//Email:          	ejumhoefer@wisc.edu
//Lecturer's Name: 	Gary Dahl
//
////////////////////PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
//Partner Name:    Nick Stoffel
//Partner Email:   nastoffel@wisc.edu
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
//				
//	
//
/////////////////////////////// 80 COLUMNS WIDE ///////////////////////////////


public class Main {

	private static SwimSimulation swimSim;
	public static void main(String[] args) 
	{
		
		//Starts the swimSimulation
		Utility.startSimulation();
		
		
	}
	public static void setup(Data data) 
	{
		//Instantiates new swimSimulation object 
		swimSim = new SwimSimulation(data.processing);
		
		
	}
	
	public static void update(Data data) 
	{
		
		swimSim.update();	
		
		
	}

	
	public static void onClick(Data data, int mouseX, int mouseY)
	{
		//Calls swimSim handleclick method, passing the click input to be handled by swimSim
		swimSim.handleClick(mouseX, mouseY);
	}
}