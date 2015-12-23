
//Authors: Brandon Fowler, James White, Zach Lontz
//Class CSCD350
//Quarter: Spring 2014
//Group Project

package TriviaMaze_4F_CSCD350;

//Contains player attributes and location in the maze================================
public class Player {
	private int score;					//Questions answered correctly
	private int keys;					//Item a player can use to skip a question
	private String name;				//Player's name
	private int lifeLines;
	
	//Constructs simple player object with blank values==============================
	public Player(String name){
		this.score = 0;
		this.keys = 0;
		this.name = name;
		this.lifeLines = 0;
	}
	
	//Constructs a player object with the specified values============================
	public Player(int s, int k, String n){
		this.score = s;
		this.keys = k;
		this.name = n;
	}
	
	//Returns what score the player has==============================================
	public int getScore(){
		return this.score;
	}
	
	//Returns number of keys the player has=========================================
	public int getKeys(){
		return this.keys;
	}
	
	//Returns the player's name=====================================================
	public String getName(){
		return this.name;
	}
	
	//Returns number of life lines remaining========================================
	public int getLifeLines(){
		return this.lifeLines;
	}
	
	//Sets the player's score=======================================================
	public void setScore(int s){
		this.score = s;
	}
	
	//Sets the number of keys the player has=========================================
	public void setKeys(int k){
		this.keys = k;
	}
	
	//Sets the player's name==========================================================
	public void setName(String n){
		this.name = n;
	}
	
	//Set amount of life lines========================================================
	public void setLifeLines(int l){
		this.lifeLines = l;
	}
	
}
