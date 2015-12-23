
//Authors: Brandon Fowler, James White, Zach Lontz
//Class CSCD350
//Quarter: Spring 2014
//Group Project

package TriviaMaze_4F_CSCD350;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;

//Contains main and is responsible for starting and ending the game===========================================================
public class Play {

	public static void main(String[] args) {
		
		
		Scanner getInput = new Scanner(System.in);
		String choice = "nil";								//Used later for user input
		int run = 0;										//Tracks ending conditions
		String name="";
		
		System.out.println("Welcome to the trivia maze!");
		System.out.println("Your task is to find the finish before your enemy marked with an ! finds and kills you.");
		System.out.println("In order to progress you must move through rooms towards the finish marked with an F.");
		System.out.println("Your position in the maze is marked with P.");
		System.out.println("Everytime you find a new room, you must answer a question correctly to enter it.");
		System.out.println("You score will increase when you get questions right.");
		System.out.println();
		System.out.println("Rules:");
		System.out.println("To make a choice enter the number of your chosen menu option.");
		System.out.println("When asked for a direction to move, N =  North, S = South, etc.");
		System.out.println("Answer questions by choosing A,B,C or D.");
		System.out.println("You can use life lines to help with questions. J = jump question, W = 2 Trys");
		System.out.println();
		
		//Makes sure valid input is given in order to start or quit the game
		while(choice.compareTo("1") !=0 && choice.compareTo("2") != 0){
			System.out.print("Type 1 to start the game or 2 to quit:");
			choice = getInput.next();
			
			if(choice.compareTo("1") !=0 && choice.compareTo("2") != 0){
				System.out.println("Invalid Choice, try again");
			}
		}
		
		//If player wishes to play. Get player name and starting maze size
		if(choice.compareTo("1") == 0){
			System.out.println();
			System.out.print("Choose your name:");
			try{
				BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
				name = br.readLine();
			}
			catch(IOException e){
				System.out.println("Could not understand name. Sorry!");
				name = "player";
			}
			System.out.println();
		}
		else{
			System.out.println("Bye!");
			System.exit(0);
		}
			
		boolean play = true;
			
		while(play){
			
			//Makes sure valid input is given for maze size
			while(choice.compareTo("S") !=0 && choice.compareTo("M") != 0 && choice.compareTo("L") != 0){
				System.out.print("What type of maze would you like? Type L for large, M for medium or S for small:");
				choice = getInput.next();
				choice = choice.toUpperCase();
				
				if(choice.compareTo("S") !=0 && choice.compareTo("M") != 0 && choice.compareTo("L") != 0){
					System.out.println("Invalid Choice, try again");
				}
			}
			
			Manager game = new Manager(choice, name);			//Set up the game
			run = game.run();									//Run the game
			
			if(run == -1){															//Player quit the game
				System.out.println();
				System.out.println("Exiting Game! Goodbye "+name+"!");
			}
			if(run == 1){															//Player won the game
				System.out.println();
				System.out.println(""+name+" wins! Congratz!");
			}
			if(run == 2){															//Player was caught by the enemy and lost
				System.out.println();
				System.out.println("Your enemy killed you! Gameover "+name+"!");
			}
			if(run == 3){															//Player cannot complete the game
				System.out.println();
				System.out.println("No paths left to exit! Gameover "+name+"!");
			}
			
			//Keep game running if player wishes or end
			while(choice.compareTo("Y") !=0 && choice.compareTo("N") != 0){
				System.out.println();
				System.out.print("Whould you like to play again?(Y = yes, N = no):");
				choice = getInput.next();
				choice = choice.toUpperCase();
				System.out.println();
				
				if(choice.compareTo("Y") != 0 && choice.compareTo("N") != 0){
					System.out.println("Invalid Choice, try again");
				}
			}
			if(choice.compareTo("Y") == 0){
				play = true;
			}
			else{
				play = false;
				System.out.println("Goodbye!");
			}
		}
	}
}
