/* Team: Larfleeze
 * Members: Nathan Graham, Matt Wilhelm, Brandon Fowler
 * Final project
 */

package main;

import java.util.Observable;
import java.util.Observer;

import Party.Party;
import Party.PartyFactory;
import dungeon.DunBuilderDirector;
import dungeon.Dungeon;
import dungeon.Point;
import dungeon.Room;

public class GameCore implements Observer {
	private static GameCore gameCore;
	private Observable dungeon;
	private Room curRoom;
	private Party goodGuys;
	private Point playerFloorPos;
	private int playerFloorNum;
	private boolean gameOver;
	private boolean roomChanged;

	public static GameCore getGameCore(Observable dungeon) {
		if (gameCore == null) {
			PartyFactory partyFactory = new PartyFactory();
			Party goodGuys = partyFactory.makeGoodParty();
			gameCore = new GameCore(dungeon, goodGuys);
		}
		return gameCore;
	}

	public static GameCore getGameCore() {
		if (gameCore == null) { // if for some reason the dungeon is not
								// instantiated yet, give an easy dungeon
			DunBuilderDirector dunCreator = new DunBuilderDirector();
			Dungeon dungeon = dunCreator.construct(1);
			PartyFactory partyFactory = new PartyFactory();
			Party goodGuys = partyFactory.makeGoodParty();
			gameCore = new GameCore(dungeon, goodGuys);
		}
		return gameCore;
	}

	private GameCore(Observable dungeon, Party goodGuys) {
		this.dungeon = dungeon;
		this.dungeon.addObserver(this);
		this.goodGuys = goodGuys;
		this.gameOver = false;
		this.roomChanged = false;
	}

	// entry point
	public void play() {
		Dungeon temp = (Dungeon) dungeon;
		temp.setPlayerFloor(0);
		this.curRoom = temp.getCurRoom();
		curRoom.entered(this.goodGuys, this.playerFloorNum);

		do {
			if (this.roomChanged) {
				this.roomChanged = false;
				this.curRoom.entered(this.goodGuys, this.playerFloorNum);
			} else {
				System.out.println(this.curRoom);
				Command command = new Command(this.curRoom);
				command.execute();
			}
		} while (!this.gameOver);
	}

	public void update(Observable o, Object arg) {
		if (o instanceof Dungeon) {
			Dungeon curDungeon = (Dungeon) o;
			this.playerFloorNum = curDungeon.getCurPlayerFloor();
			this.playerFloorPos = curDungeon.getPlayerPos();
			this.curRoom = curDungeon.getCurRoom(this.playerFloorNum, this.playerFloorPos);
			this.roomChanged = true;
		}
	}

	private void setGameOver(boolean gameOver) {
		this.gameOver = true;
	}

	public void endGameVictory() {
		System.out
				.println("You have claimed victory over your enemies and laid waste to all in your path!");
		setGameOver(true);
	}

	public void endGameKilled() {
		System.out
				.println("You have falled in battle.  Your adventure is at an end and no one will remember your name.");
		setGameOver(true);
	}

	public void endGameExit() {
		System.out.println("Quitting?  Coward!");
		setGameOver(true);
	}
}
