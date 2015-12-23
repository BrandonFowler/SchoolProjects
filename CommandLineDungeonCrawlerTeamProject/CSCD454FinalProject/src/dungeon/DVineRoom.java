package dungeon;

import main.Command;
import Party.Party;
import combat.Combat;

public class DVineRoom extends Room {

	public DVineRoom(double chestMod, Dungeon dungeon) {
		super(chestMod, dungeon);
	}
	
	@Override
	public String toString() {
		String message = super.toString();
		
		message += "There are vines leading to the lower floor here.";
		
		return message;
	}

	@Override
	public void entered(Party goodParty, int curFloor) {
		Combat.chanceFight(goodParty, curFloor);
		System.out.println(this.toString());
		Command command = new Command(this);
		command.execute();
	}
}
