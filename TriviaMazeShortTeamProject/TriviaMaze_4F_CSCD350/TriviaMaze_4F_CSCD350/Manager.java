
//Authors: Brandon Fowler, James White, Zach Lontz
//Class CSCD350
//Quarter: Spring 2014
//Group Project

package TriviaMaze_4F_CSCD350;

import java.util.Scanner;

//Manages the actual running behavior of the game================================================================
public class Manager {

	protected Maze maze;
	protected Player player;
	protected int playerRow;
	protected int playerCol;
	protected int enemyRow;
	protected int enemyCol;
	protected int[] usedIDs = new int[40];
	
	//Construct manager object with everything needed to run the game
	public Manager(String size, String name){
		this.maze = new Maze(size);
		this.playerRow = 0;
		this.playerCol = 0;
		this.enemyRow = 0;
		this.player = new Player(name);
		for(int i = 0; i < this.usedIDs.length; i++){
			this.usedIDs[i] = 0;
		}
		
		//Enemy position depending on maze size
		if(size.compareTo("S") == 0){
			this.enemyCol = 2;
			this.player.setKeys(1);
			this.player.setLifeLines(1);
		}
		else if(size.compareTo("M") == 0){
			this.enemyCol = 3;
			this.player.setKeys(2);
			this.player.setLifeLines(2);
		}
		else{
			this.enemyCol = 4;
			this.player.setKeys(3);
			this.player.setLifeLines(3);
		}
		
		maze.setEnemy(this.enemyRow,this.enemyCol, 1);				//Set notifier in maze room containing enemy
	}
	
	
	//Keeps the game running until it is over=======================================================================
	public int run() {
		
		int end = 0;																		//1 = game done, 0 = keep playing
		String choice;																		//Choice from user menu
		
		//Run the game
		while(end == 0){
			System.out.println();
			System.out.print(this.maze);													//Show user the maze
			System.out.println();
			System.out.println(""+player.getName()+"'s score: "+player.getScore());			//Print players score
			
			choice = mazeMenu();															//Give user choices
			
			
			if(choice.compareTo("1") == 0){													//If choice 1
				move();																		//Try to move player
			}
			else if(choice.compareTo("4") == 0){											//If choice 2
				end = -1;																	//Signal for end while loop
			}
			else if(choice.compareTo("2") == 0){											//If choice 3
				useKey();																	//Try to use a key
			}
			else if(choice.compareTo("3") == 0){
				printRules();
			}
			else																			//User entered a bad choice
			{
				System.out.println("Invalid Choice! Try Again!");
			}
			
			if(maze.checkEnd(this.playerRow, this.playerCol)){								//If player moved to end of maze
				end = 1;																	//Signal game has been won
			}
			if(this.playerRow == this.enemyRow && this.playerCol == this.enemyCol){			//If enemy has caught player
				end = 2;																	//Signal enemy killed player
			}
			if(this.maze.startTraversal(this.playerRow,this.playerCol) == false){			//Check if maze can be completed
				end = 3;																	//Signal no paths left
			}
		}
		System.out.print(this.maze);
		System.out.println();
		System.out.println(""+player.getName()+"'s score: "+player.getScore());
		System.out.println();
		return end;
	}
	
	//Prints out menu choices for user, and returns the user's choice===================================
	public String mazeMenu(){
		Scanner userInput = new Scanner(System.in);
		
		System.out.println("Your options are:");
		System.out.println("1. Move");
		System.out.println("2. Use Key");
		System.out.println("3. View Game Rules");
		System.out.println("4. Quit");
		System.out.print("Type the number of your option(Example: 2 = quit): ");
		
		String choice = userInput.next();
		
		return choice;
	}
	
	//Calls several object methods to check movement, ask questions, 
	//and move player or lock doors====================================================================
	public void move(){
		Scanner userInput = new Scanner(System.in);
																		
		
		//Give choice and get input
		System.out.print("Which direction would you like to move?(Example: N = North): ");		
		String direction = userInput.nextLine();
		direction = direction.toUpperCase();
		System.out.println();
		
		//Check for proper input
		if(direction.compareTo("N") == 0 || direction.compareTo("S") == 0 || 
				direction.compareTo("E") == 0 || direction.compareTo("W") == 0){
			
			boolean canMove = maze.canMove(this.playerRow,this.playerCol,direction);			//Check if direction is a valid movement
			
			if(canMove){																		//If valid
				boolean Qpass;
				if(maze.checkVisited(this.playerRow,this.playerCol,direction)){					//Check if player has already been here
					Qpass = true;																//Don't ask question
				}
				else{																			//Player hasn't been here
					TriviaQuestion question = new TriviaQuestion(player.getLifeLines(), this.usedIDs);
					Qpass = question.askQuestion();												//Ask a question
					player.setLifeLines(question.getLifeLinesLeft());
					if(Qpass){
						player.setScore(player.getScore()+1);  									//Increase player score
					}
					this.usedIDs = question.getQIDs();
				}
																	
				if(Qpass){																		//If answered correctly
					maze.setOccupied(this.playerRow,this.playerCol,0);							//Set current room as unoccupied
					
					//Change player position
					if(direction.compareTo("N") == 0){
						this.playerRow--;
					}
					else if(direction.compareTo("S") == 0){
						this.playerRow++;
					}
					else if(direction.compareTo("E") == 0){
						this.playerCol++;
					}
					else{
						this.playerCol--;
					}
					
					maze.setOccupied(this.playerRow,this.playerCol,1);							//Set new room as occupied
					maze.setVisited(this.playerRow,this.playerCol,1);							//Mark that new room has been visited
					System.out.println(""+player.getName()+" moved to next room");
				}
				else{																			//If answered incorrectly
					System.out.println("Wrong! The door locks!");
					maze.lockDoors(this.playerRow,this.playerCol,direction);					//Lock doors
				}
			}
			else{																				//Movement is not valid
				System.out.println("That direction is blocked");
			}
			moveEnemy();																		//If player moved or tried to move, move enemy
		}
		else{																					//Input is not valid
			System.out.println("Invalid Choice! Try Again!");
			move();																				//Get new input from user
		}	
	}
	
	//Get direction that player would like to use key, then calls methods to execute the option===========================
	public void useKey(){
		
		if(player.getKeys() > 0){
		
			Scanner userInput = new Scanner(System.in);
		
			//Give choice and get input
			System.out.print("Which direction would you like to use a key?(Example: N = North): ");		
			String direction = userInput.nextLine();
			direction = direction.toUpperCase();
			System.out.println();
			
			if(direction.compareTo("N") == 0 || direction.compareTo("S") == 0 || 
					direction.compareTo("E") == 0 || direction.compareTo("W") == 0){
				boolean used = maze.useKey(this.playerRow,this.playerCol,direction);
				if(used){
					player.setKeys(player.getKeys()-1);
					System.out.println(""+player.getName()+" unlocked a door!");
				}
			}
			else{
				System.out.println();
				System.out.println("Invalid choice! Try again!");
			}
		}
		else{
			System.out.println();
			System.out.println("You have no keys!");
		}
	}
	
	//Moves the enemy towards player============================================================================
	public void moveEnemy(){
		
		if(playerCol < enemyCol && maze.canMove(this.enemyRow, this.enemyCol, "W")){		//Player is West of enemy, move enemy towards player
			maze.setEnemy(this.enemyRow,this.enemyCol,0);
			this.enemyCol--;
			maze.setEnemy(this.enemyRow,this.enemyCol,1);
		}
		else if(playerCol > enemyCol && maze.canMove(this.enemyRow, this.enemyCol, "E")){	//Player is East of enemy, move enemy towards player
			maze.setEnemy(this.enemyRow,this.enemyCol,0);
			this.enemyCol++;
			maze.setEnemy(this.enemyRow,this.enemyCol,1);
		}
		else if(playerRow > enemyRow && maze.canMove(this.enemyRow, this.enemyCol, "S")){	//Player is below enemy, move enemy towards player
			maze.setEnemy(this.enemyRow,this.enemyCol,0);
			this.enemyRow++;
			maze.setEnemy(this.enemyRow,this.enemyCol,1);
		}
		else if(playerRow < enemyRow && maze.canMove(this.enemyRow, this.enemyCol, "N")){	//Player is above enemy, move enemy towards player
			maze.setEnemy(this.enemyRow,this.enemyCol,0);
			this.enemyRow--;
			maze.setEnemy(this.enemyRow,this.enemyCol,1);
		}
		else{																				//Enemy was unable to move towards player
			System.out.println();
			System.out.println("Your enemy is slown by locked doors.");
		}
	}
	
	//Prints out rules for playing the game==============================================================================
	public void printRules(){
		System.out.println();
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
	}
}
