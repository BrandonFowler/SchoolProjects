package dungeon;

import combat.Combat;
import main.GameCore;
import Party.Party;
import Party.PartyFactory;

public class FinalBossRoom extends Room {
	
	public FinalBossRoom(double chestMod, Dungeon dungeon) {
		super(chestMod, dungeon);
	}
	
	@Override
	public String toString() {
		String message = super.toString();
		message += "You have finally found the boss of this cave network!\n" +
				"You ready your weapons and charge!\n";
		
		return message;
	}

	@Override
	public void entered(Party goodParty, int curFloor) {
		System.out.println(this);
		GameCore gameCore = GameCore.getGameCore();
		PartyFactory pFactory = new PartyFactory();
		Party bossParty = pFactory.makeBossParty(this.dungeon.getCurPlayerFloor(), "dragon");
		Combat curFight = new Combat(goodParty, bossParty);
		if(curFight.fight()) {
			gameCore.endGameVictory();
		} else {
			gameCore.endGameKilled();
		}
	}
}
