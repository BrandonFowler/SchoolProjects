package Tests;

import combat.Combat;

import Party.Party;
import Party.PartyFactory;

public class TestPartyCombat {

	public static void main(String[] args) {
		PartyFactory factory = new PartyFactory();
		Party goodGuys = factory.makeGoodParty();
		Party badGuys = factory.makeBossParty(1, "dragon");
		
		Combat combat = new Combat(goodGuys,badGuys);
		boolean win = combat.fight();
		
		if(win){
			System.out.println("You win!");
		}
		else{
			System.out.println("Game Over!");
		}

	}

}
