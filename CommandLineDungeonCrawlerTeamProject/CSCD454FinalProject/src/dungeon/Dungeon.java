/* Team: Larfleeze
 * Members: Nathan Graham, Matt Wilhelm, Brandon Fowler
 * Final project
 */

package dungeon;

import java.util.Observable;

public class Dungeon extends Observable {
	private Floor map[];
	private Point entrance, lastBossPos, curPlayerPos;
	private int curPlayerFloor;

	public Dungeon() {
	}
	
	public void setPlayerHorizPos(int x, int y) {
		this.curPlayerPos.setX(x);
		this.curPlayerPos.setY(y);
		playerPosChanged();
	}
	
	public void movePlayerUp(int z) {
		this.curPlayerFloor = z;
		setPlayerHorizPos(this.map[z].getDVine().getX(), this.map[z].getDVine().getY());
	}
	
	public void movePlayerDown(int z) {
		this.curPlayerFloor = z;
		setPlayerHorizPos(this.map[z].getUVine().getX(), this.map[z].getUVine().getY());
	}
	
	private void playerPosChanged() {
		setChanged();
		notifyObservers();
	}
	
	public Room getCurRoom() {
		Room[][] curFloor = this.map[curPlayerFloor].getFloor();
		return curFloor[this.curPlayerPos.getX()][this.curPlayerPos.getY()];
	}
	
	public Floor[] getMap() {
		return this.map;
	}
	public void setMap(Floor[] map) {
		this.map = map;
	}
	public Point getEntrance() {
		return this.entrance;
	}
	public void setEntrance(Point entrance) {
		this.entrance = entrance;
	}
	public Point getLastBossPos() {
		return this.lastBossPos;
	}
	public void setLastBossPos(Point bossPos) {
		this.lastBossPos = bossPos;
	}
	public Room[][] getFloorArray(int floorNum) {
		return this.map[floorNum].getFloor();
	}
	public void replaceFloor(int floorSize, double chestMod, int floorNum) {
		this.map[floorNum] = new Floor(floorSize, chestMod, this);
	}
	public Floor getFloor(int floorNum) {
		return this.map[floorNum];
	}
	public Point getPlayerPos() {
		return this.curPlayerPos;
	}
	public int getCurPlayerFloor() {
		return this.curPlayerFloor;
	}
	public Room getCurRoom(int floorNum, Point floorPos) {
		return this.map[floorNum].getRoom(floorPos);
	}
	public void setPlayerPos(Point point) { //only for use in initialization, use setPlayerHorizPos instead
		this.curPlayerPos = point;
	}
	public void setPlayerFloor(int floorNum) { //only for use in initialization, use setPlayerVertPos instead
		this.curPlayerFloor = floorNum;
	}
}
