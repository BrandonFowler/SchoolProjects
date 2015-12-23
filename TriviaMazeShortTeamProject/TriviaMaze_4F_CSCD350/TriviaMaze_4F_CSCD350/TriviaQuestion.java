
//Authors: Brandon Fowler, James White, Zach Lontz
//Class CSCD350
//Quarter: Spring 2014
//Group Project

package TriviaMaze_4F_CSCD350;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Random;
import java.util.Scanner;

//Responsible for connecting to a database and asking a question=====================================
public class TriviaQuestion {
	
	protected String question, o1, o2, o3, o4;				//Question and multiple choice answers
	protected String answer;								//True answer
	private Statement stmt = null;							//Stores a SQL statement
	private Connection c = null;							//Database Connection
	private ResultSet rs = null;							
	private String randomNum;								//Stores string representation of random number for question ID
	private Random rand = new Random();						//Random object to get a number for question ID look up
	private int tempNum;									//Random Number for question ID look up														
	private int lifeLinesLeft;								//Number of life lines left
	private int[] QIDs;										//List of question IDs already used
	//Connect to database to build question and answer========================================================
	public TriviaQuestion(int lifeLines, int[] QIDs){
	  	try {
	  		
	  		//Set up connection and generate number for ID look up
		  	Class.forName("org.sqlite.JDBC");
		  	c = DriverManager.getConnection("jdbc:sqlite::resource:Questions.db");
			stmt = c.createStatement();
			tempNum = rand.nextInt(40) + 1;
			
			//Check if Question ID has been used and generate until new question is found
			this.QIDs = QIDs;
			boolean used = true;
			while(used){
				int i = 0;
				for(i = 0; i < this.QIDs.length && this.QIDs[i] != 0; i++){
					if(tempNum == this.QIDs[i]){
						break;
					}
				}
				if(i < this.QIDs.length && tempNum == this.QIDs[i]){
					tempNum = rand.nextInt(40) + 1;
				}
				else{
					used = false;
					if(i < this.QIDs.length){
						this.QIDs[i] = tempNum;
					}
				}
			}
			
			//Get question from database and store java variables
			randomNum = tempNum + " ";
			rs = stmt.executeQuery( "SELECT * FROM Questions WHERE ID = " + randomNum + ";" );
			this.question = rs.getString("Question");
			this.o1 = rs.getString("Option1");
			this.o2 = rs.getString("Option2");
			this.o3 = rs.getString("Option3");
			this.o4 = rs.getString("Option4");
			this.answer = rs.getString("Answer");
			this.lifeLinesLeft = lifeLines;
		} catch (Exception e) {
			System.out.println("Failed to generate a question. Exiting program");
			System.exit(0);
		}
	}
	
	//Ask the question, get user answer, compare with real answer, return true or false=======================
	public boolean askQuestion(){
		Scanner userInput = new Scanner(System.in);
		String input;
			
		//Ask question and get input
		System.out.println("Please select the correct answer or use a life line. J = jump, W = 2 trys.");
		System.out.println("Life lines remaining: "+this.lifeLinesLeft);
		System.out.println();
		System.out.println(this.question);
		System.out.println(this.o1);
		System.out.println(this.o2);
		System.out.println(this.o3);
		System.out.println(this.o4);
		System.out.print("Choose:");
		input = userInput.nextLine();
		input = input.toUpperCase();
		System.out.println();
		
		//Check for bad input, prompt user until good input is given 
		while(input.compareTo("A")!=0 && input.compareTo("B")!=0 && input.compareTo("C")!=0 && input.compareTo("D")!=0 
				&& input.compareTo("J")!=0 && input.compareTo("W")!=0 && input.compareTo("CHEAT")!=0){
			
			System.out.println("Not a valid option.");
			System.out.print("Try another choice:");
			input = userInput.nextLine();
			input = input.toUpperCase();
			System.out.println();
		}
		
		//If user has chosen to jump the question, try to execute option
		if(input.compareTo("J")==0){
			if(this.lifeLinesLeft > 0){
				this.lifeLinesLeft--;
				input = this.answer;
			}
			else{
				System.out.println("You have no life lines left to use!");
				System.out.println();
				return askQuestion();
			}
		}
		
		//If user has chosen to attempt the question twice, try to execute the option
		if (input.compareTo("W")==0){
			if(this.lifeLinesLeft > 0){
				this.lifeLinesLeft--;
				System.out.println("You have two chances to answer correctly");
				boolean doubleTry = askQuestion();
				if(!doubleTry){
					System.out.println("Wrong answer. Try again.");
					doubleTry = askQuestion();
				}
				return doubleTry;
			}
			else{
				System.out.println("You have no life lines left to use!");
				System.out.println();
				return askQuestion();
			}
		}
		
		//check answer and return true if correct or false if incorrect
		if(input.compareTo(this.answer)==0 || input.compareTo("CHEAT")==0){
			return true;
		}
		return false;
	}
	
	//Used to get life lines remaining after question is asked================================================
	public int getLifeLinesLeft(){
		return this.lifeLinesLeft;
	}
	
	//Return list of question IDs already used================================================================
	public int[] getQIDs(){
		return this.QIDs;
	}
}
