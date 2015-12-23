/* Team: Larfleeze
 * Members: Nathan Graham, Matt Wilhelm, Brandon Fowler
 * Final project
 */

package dungeon;

public class Floor {
	private Room floor[][];
	private Point uVine, dVine;
	
	public Floor(int floorSize, double chestMod, Dungeon dungeon) {
		this.floor = new Room[floorSize][floorSize];
		fillFloor(chestMod, dungeon);
	}
	
	private void fillFloor(double chestMod, Dungeon dungeon) {
		for(int i = 0; i < this.floor.length; i++) {
			for(int j = 0; j < this.floor.length; j ++) {
				this.floor[i][j] = new NormalRoom(chestMod, dungeon);
			}
		}
	}

	public Room[][] getFloor() {
		return this.floor;
	}
	public Point getUVine() {
		return this.uVine;
	}
	public void setUVine(Point point) {
		this.uVine = point;
	}
	public Point getDVine() {
		return this.dVine;
	}
	public void setDVine(Point point) {
		this.dVine = point;
	}
	public Room getRoom(Point floorPos) {
		return this.floor[floorPos.getX()][floorPos.getY()];
	}
}
