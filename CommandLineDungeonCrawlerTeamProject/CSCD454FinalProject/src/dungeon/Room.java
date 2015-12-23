/* Team: Larfleeze
 * Members: Nathan Graham, Matt Wilhelm, Brandon Fowler
 * Final project
 */

package dungeon;

import Party.Party;

public abstract class Room {
	protected Dungeon dungeon;
	protected boolean nDoor;
	protected boolean eDoor;
	protected boolean sDoor;
	protected boolean wDoor;
	
	public Room(double chestMod, Dungeon dungeon) {
		this.nDoor = true;
		this.eDoor = true;
		this.sDoor = true;
		this.wDoor = true;
		this.dungeon = dungeon;
	}
	
	@Override
	public String toString() {
		String message = "There are exits in the following directions:\n";
		
		if(this.nDoor) {
			message += "     North\n";
		}
		if(this.eDoor) {
			message += "     East\n";
		}
		if(this.sDoor) {
			message += "     South\n";
		}
		if(this.wDoor) {
			message += "     West\n\n";
		}
		
		return message;
	}
	
	public boolean hasNDoor() {
		return nDoor;
	}

	public void setNDoor(boolean nDoor) {
		this.nDoor = nDoor;
	}

	public boolean hasEDoor() {
		return eDoor;
	}

	public void setEDoor(boolean eDoor) {
		this.eDoor = eDoor;
	}

	public boolean hasSDoor() {
		return sDoor;
	}

	public void setSDoor(boolean sDoor) {
		this.sDoor = sDoor;
	}

	public boolean hasWDoor() {
		return wDoor;
	}

	public void setWDoor(boolean wDoor) {
		this.wDoor = wDoor;
	}
	
	public Dungeon getDungeon() {
		return this.dungeon;
	}
	public abstract void entered(Party goodParty, int curFloor);
}
