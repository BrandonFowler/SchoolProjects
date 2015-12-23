
//Authors: Brandon Fowler, James White, Zach Lontz
//Class CSCD350
//Quarter: Spring 2014
//Group Project

package TriviaMaze_4F_CSCD350;

//Contains everything needed to initialize and manipulate a maze============================================
public class Maze {

	private Room[][] maze;
	protected int rows;
	protected int cols;
	private boolean[][] traversalCheck;
	
	//Initialize a maze of given size=======================================================================
	public Maze(String size){
		
		//Check size and set accordingly
		if(size.compareTo("S") == 0){
			this.rows = 3;
			this.cols = 3;
		}
		else if(size.compareTo("M") == 0){
			this.rows = 4;
			this.cols = 4;
		}
		else{
			this.rows = 5;
			this.cols = 5;
		}
		
		//Initialize 2d array of rooms
		this.maze = new Room[rows][cols];
		for(int i = 0; i < this.rows; i++){
			for(int j = 0; j < this.cols; j++){
				this.maze[i][j] = new Room();
			}
		}
		
		this.maze[rows-1][cols-1].end = 1;				//Set end of maze
		this.maze[0][0].occupied = 1;					//Set player start
		this.maze[0][0].visited = 1;					//Set start point as a visited room
	}
	
	//Preps a dummy 2d maze to help with maze traversal check======================================================================
	public boolean startTraversal(int row, int col){
		this.traversalCheck = new boolean[this.rows][this.cols];
		for(int i = 0; i < this.rows; i++){
			for(int j = 0; j < this.cols; j++){
				traversalCheck[i][j] = false;
			}
		}
		return traverse(row, col);
	}
	
	//Allows player to use a key if the direction is valid==================================================================
	public boolean useKey(int row, int col, String d){
		
		//Find room that the player wants to use a key on
		int nextRoomRow = row;
		int nextRoomCol = col;
		if(d.compareTo("N") ==0){
			nextRoomRow--;
		}
		if(d.compareTo("S") ==0){
			nextRoomRow++;
		}
		if(d.compareTo("E") ==0){
			nextRoomCol++;
		}
		if(d.compareTo("W") ==0){
			nextRoomCol--;
		}
		
		//Make sure room exists, then set doors to open
		if(nextRoomRow < this.rows && nextRoomRow >= 0 && nextRoomCol < this.cols && nextRoomCol >= 0){
			if(d.compareTo("N") ==0){
				this.maze[row][col].northDoor = 1;														
				this.maze[nextRoomRow][nextRoomCol].southDoor = 1;
				this.maze[nextRoomRow][nextRoomCol].visited = 1;
			}
			else if(d.compareTo("S") ==0){
				this.maze[row][col].southDoor = 1;
				this.maze[nextRoomRow][nextRoomCol].northDoor = 1;
				this.maze[nextRoomRow][nextRoomCol].visited = 1;
			}
			else if(d.compareTo("E") ==0){
				this.maze[row][col].eastDoor = 1;
				this.maze[nextRoomRow][nextRoomCol].westDoor = 1;
				this.maze[nextRoomRow][nextRoomCol].visited = 1;
			}
			else{
				this.maze[row][col].westDoor = 1;
				this.maze[nextRoomRow][nextRoomCol].eastDoor = 1;
				this.maze[nextRoomRow][nextRoomCol].visited = 1;
			}
			return true;
		}
		else{
			System.out.println("Cannot use a key in that direction.");
		}
		return false;
	}
	
	//Traverses dummy maze while checking real maze values. If maze is not traverse-able return false, else true===================
	public boolean traverse(int row, int col){
		
		boolean solved = false;															//Tracks if end is found yet
		
		this.traversalCheck[row][col] = true;											//Mark that this cell has been checked
		
		if(this.maze[row][col].end == 1){												//If maze end found, return true
			return true;
		}
		
		if (canMove(row,col,"E") && !this.traversalCheck[row][col+1] ) {				//If East movement is valid in real maze and not already checked, traverse it 
		    solved = traverse(row,col+1);
		}
		if (canMove(row,col,"S") && !solved && !this.traversalCheck[row+1][col] ) {		//If South movement is valid in real maze and not already checked, traverse it 
		    solved = traverse(row+1,col);
		}
		if (canMove(row,col,"W") && !solved && !this.traversalCheck[row][col-1] ) {		//If West movement is valid in real maze and not already checked, traverse it 
		    solved = traverse(row,col-1);
		}
		if (canMove(row,col,"N") && !solved && !this.traversalCheck[row-1][col] ) {		//If North movement is valid in real maze and not already checked, traverse it 
		    solved = traverse(row-1,col);
		}
		return solved;
	}
	
	//Simple check for array out of bounds, and if door is unlocked=====================================================================
	public boolean canMove(int row, int col, String d){
		if(d.compareTo("N") ==0){
			row--;
		}
		if(d.compareTo("S") ==0){
			row++;
		}
		if(d.compareTo("E") ==0){
			col++;
		}
		if(d.compareTo("W") ==0){
			col--;
		}
		
		if(row >= 0 && row < this.rows && col >= 0 && col < this.cols){				//Check if array out of bounds
			if(this.maze[row][col].southDoor == 1 && d.compareTo("N") ==0){			//If moving north check south door
			
				return true;
			}
			if(this.maze[row][col].northDoor == 1 && d.compareTo("S") ==0){			//If moving south check north door
			
				return true;
			}
			if(this.maze[row][col].westDoor == 1 && d.compareTo("E") ==0){			//If moving east check west door
			
				return true;
			}
			if(this.maze[row][col].eastDoor == 1 && d.compareTo("W") ==0){			//If moving west check east door
			
				return true;
			}
		}
		return false;																//Can't move this direction
	}
	
	//Locks doors for rooms based on attempted movement direction========================================================
		public void lockDoors(int row, int col, String d){
											
			if(d.compareTo("N") == 0){							//If moving north lock doors
				this.maze[row-1][col].southDoor = 0;
				this.maze[row][col].northDoor = 0;
			}
			if(d.compareTo("S") == 0){							//If moving south lock doors
				this.maze[row+1][col].northDoor = 0;
				this.maze[row][col].southDoor = 0;	
			}
			if(d.compareTo("E") == 0){							//If moving east lock doors
				this.maze[row][col+1].westDoor = 0;
				this.maze[row][col].eastDoor = 0;	
			}
			if(d.compareTo("W") == 0){							//If moving west lock doors
				this.maze[row][col-1].eastDoor = 0;
				this.maze[row][col].westDoor = 0;	
			}													
		}
	
	//Simply checks to see this room is the end of the maze=================================================
	public boolean checkEnd(int row, int col){
		if(this.maze[row][col].end == 1){
			return true;
		}
		return false;
	}
	
	
	//Checks if player has been in a room that he is trying to move into====================================
	public boolean checkVisited(int row, int col, String d){
		if(d.compareTo("N") ==0){
			row--;
		}
		if(d.compareTo("S") ==0){
			row++;
		}
		if(d.compareTo("E") ==0){
			col++;
		}
		if(d.compareTo("W") ==0){
			col--;
		}
		
		if(this.maze[row][col].visited == 1){
			return true;
		}
		return false;
	}
	
	//Prints entire maze showing doors, enemy, player, and end================================================
	@Override
	public String toString(){
		int row;
		int col;
		String result = "";
		
		//Top of maze
		for(col = 0; col < this.cols; col++){
			result = result+"* * ";					
		}
		result = result+"*"+"\n";
		
		//Body of maze(checks for locked doors and prints accordingly)
		for ( row = 0; row < this.rows-1; row++){
			result = result+"* ";
			for ( col = 0; col < this.cols-1; col++){
				if(this.maze[row][col].eastDoor == 1){
					result = result+this.inRoom(row,col)+" | ";
				}
				else
				{
					result = result+this.inRoom(row,col)+" X ";
				}
			}
			result = result+this.inRoom(row,col)+" *"+"\n"+"*";
			for(col = 0; col < this.cols; col++){
				if(this.maze[row][col].southDoor == 1){	
					result = result+" - *";
				}
				else{
					result = result+" x *";
				}
			}
			result = result+"\n";
		}
		
		//Last row of maze
		result = result+"* ";
		for (col = 0; col < this.cols-1; col++){
			if(this.maze[row][col].eastDoor == 1){
				result = result+this.inRoom(row,col)+" | ";
			}
			else
			{
				result = result+this.inRoom(row,col)+" X ";
			}
		}
		result = result+this.inRoom(row,col)+" *"+"\n";
		
		//Bottom of maze
		for(col = 0; col < this.cols; col++){
			result = result+"* * ";					
		}
		result = result+"*";
		return result;
	}
	
	//Checks what is inside a room and returns a token representation=========================================
	public String inRoom(int row, int col){
		if(this.maze[row][col].enemy == 1){
			return "!";
		}
		if(this.maze[row][col].end == 1){
			return "F";
		}
		if(this.maze[row][col].occupied == 1){
			return "P";
		}
		return " ";
	}
	
	//Set if player has or has not visited a room
	public void setVisited(int row, int col, int v){
		this.maze[row][col].visited = v;
	}
	
	//Set if player is or is not currently occupying a room
	public void setOccupied(int row, int col, int v){
		this.maze[row][col].occupied = v;
	}
	
	//Set position of enemy AI
	public void setEnemy(int row, int col, int v){
		this.maze[row][col].enemy = v;
	}

	//Room class to be used by Maze=========================================================================
	private class Room{
		
		private int visited;								//1 = visited	0 = not visited
		private int northDoor;		    					//1 = unlocked  0 = locked
		private int southDoor;								//		"			"
		private int eastDoor;								//		"			"
		private int westDoor;								//		"			"
		private int end;									//1 = End of the maze
		private int occupied;								//1 = Player is currently here
		private int enemy;									//Enemy present
		
		//Simple initialize Room as unvisited and doors unlocked===========================================
		public Room(){
			this.visited = 0;
			this.northDoor = 1;
			this.southDoor = 1;
			this.eastDoor = 1;
			this.westDoor = 1;
			this.end = 0;
			this.occupied = 0;
			this.enemy = 0;
		}
		
	}
	
}

