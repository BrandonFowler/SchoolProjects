package dungeon;

import main.Command;
import Party.Party;

public class StartRoom extends Room {
	private boolean enteredAlready;
	
	public StartRoom(double chestMod, Dungeon dungeon) {
		super(chestMod, dungeon);
		enteredAlready = false;
	}
	
	@Override
	public String toString() {
		String message = "";
		if(!enteredAlready) {
			message = "You arrive in a complex cave network.  You feel" +
				" an unexplainable urge to find the master of these caves and slay it!\n";
			this.enteredAlready = true;
		}
		message += super.toString();
		
		return message;
	}

	@Override
	public void entered(Party goodParty, int curFloor) {
		System.out.println(this);
		Command command = new Command(this);
		command.execute();
	}
}
