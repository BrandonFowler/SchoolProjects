package main;

import java.util.Scanner;

import Inventory.Item;
import Party.PartyInventory;
import dungeon.NormalRoom;
import dungeon.DVineRoom;
import dungeon.TreasureChest;
import dungeon.UVineRoom;
import dungeon.Room;
import dungeon.Dungeon;

public class Command {
	private String command;
	private Dungeon curDungeon;
	private PartyInventory inventory;
	private Room curRoom;
	
	public Command(Room curRoom) {
		this.curRoom = curRoom;
		this.command = getCommand();
		this.curDungeon = curRoom.getDungeon();
		this.inventory = PartyInventory.getInventory();
	}
	
	private String getCommand() {
		String commandTest = "";
		@SuppressWarnings("resource")
		Scanner cin = new Scanner(System.in);
		
		System.out.println("Enter your choice, or type help.\n");
		
		while(true) {
			commandTest = cin.nextLine();
			
			if(curRoom.hasEDoor()) {
				if (commandTest.equalsIgnoreCase("e") || commandTest.equalsIgnoreCase("east")) {
					break;
				}
			} 
			if(curRoom.hasNDoor()) {
				if (commandTest.equalsIgnoreCase("n") || commandTest.equalsIgnoreCase("north")) {
					break;
				}
			} 
			if(curRoom.hasSDoor()) {
				if (commandTest.equalsIgnoreCase("s") || commandTest.equalsIgnoreCase("south")) {
					break;
				}
			} 
			if(curRoom.hasWDoor()) {
				if (commandTest.equalsIgnoreCase("w") || commandTest.equalsIgnoreCase("west")) {
					break;
				}
			} 
			if (commandTest.equalsIgnoreCase("help")) {
				break;
			} 
			if (commandTest.equalsIgnoreCase("use")) {
				break;
			}
			if(commandTest.equalsIgnoreCase("exit")) {
				break;
			}
			if(curRoom.getClass() == NormalRoom.class) {  //found at http://stackoverflow.com/questions/4294844/check-if-an-object-belongs-to-a-class-in-java
				NormalRoom temp = (NormalRoom) curRoom;
				if(temp.hasChest()) {
					if(commandTest.equalsIgnoreCase("open")) {
						break;
					}
				}
			}
			if(curRoom.getClass() == DVineRoom.class) {
				if(commandTest.equalsIgnoreCase("down")) {
					break;
				}
			}
			if(curRoom.getClass() == UVineRoom.class) {
				if(commandTest.equalsIgnoreCase("up")) {
					break;
				}
			}
			if(commandTest.equalsIgnoreCase("print")) {
				break;
			}
			System.out.println("You must enter a valid command.  Enter help if needed.\n");
		}
		return commandTest;
	}
	
	public void execute() {
		if (command.equalsIgnoreCase("e")) {
			this.curDungeon.setPlayerHorizPos(this.curDungeon.getPlayerPos().getX() + 1, this.curDungeon.getPlayerPos().getY());
		} else if (command.equalsIgnoreCase("n")) {
			this.curDungeon.setPlayerHorizPos(this.curDungeon.getPlayerPos().getX(), this.curDungeon.getPlayerPos().getY() - 1);
		} else if (command.equalsIgnoreCase("s")) {
			this.curDungeon.setPlayerHorizPos(this.curDungeon.getPlayerPos().getX(), this.curDungeon.getPlayerPos().getY() + 1);
		} else if (command.equalsIgnoreCase("w")) {
			this.curDungeon.setPlayerHorizPos(this.curDungeon.getPlayerPos().getX() - 1, this.curDungeon.getPlayerPos().getY());
		} else if (command.equalsIgnoreCase("help")) {
			printHelp();
		} else if (command.equalsIgnoreCase("use")) {
			this.inventory.getParty().useItem();
		} else if(command.equalsIgnoreCase("exit")) {
			GameCore gameCore = GameCore.getGameCore();
			gameCore.endGameExit();
			new Scanner(System.in).nextLine(); //pause the game before it just exits.
			System.exit(0);
		} else if(command.equalsIgnoreCase("open")) {
			openChest();
		} else if(command.equalsIgnoreCase("down")) {
			this.curDungeon.movePlayerDown(this.curDungeon.getCurPlayerFloor() - 1);
		} else if(command.equalsIgnoreCase("up")) {
			this.curDungeon.movePlayerUp(this.curDungeon.getCurPlayerFloor() + 1);
		} else {  //command = print
			//Room will reprint in gamecore automatically
		}
	}
	
	private void printHelp() {
		System.out.println("\nThe following are commands you can use based on the condition of the room.");
		System.out.println("\nMovement commands:");
		System.out.println("     N - move north");
		System.out.println("     E - move east");
		System.out.println("     S - move south");
		System.out.println("     W - move west");
		System.out.println("     U - move up");
		System.out.println("     D - move down");
		System.out.println("\nOther commands:");
		System.out.println("     Open - open a chest");
		System.out.println("     Use - use an item");
		System.out.println("     Exit - exit the game");
		System.out.println("     Print - Reprint the room message");
		System.out.println("     Help - display this menu\n");
	}
	
	private void openChest() {
		if(curRoom instanceof NormalRoom){
			if(((NormalRoom)curRoom).hasChest()){
				TreasureChest chest = new TreasureChest();
				chest.printItem();
				Item item = chest.getItem();
				inventory.addItem(item);
				((NormalRoom) curRoom).clearChest();
			}
		}
	}
}
