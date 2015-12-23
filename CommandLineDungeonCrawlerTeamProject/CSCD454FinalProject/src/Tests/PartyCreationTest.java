package Tests;

import Party.Party;
import Party.PartyFactory;

public class PartyCreationTest {

	public static void main(String[] args) {
	
		int dungeonFloor = 1;
		
		PartyFactory factory = new PartyFactory();
		Party goodGuys = factory.makeGoodParty();
		Party badGuys = factory.makeBadParty(dungeonFloor);
		
		System.out.println();
		System.out.println("LEVEL ONE GOOD GUYS:");
		System.out.println();
		
		for(character.Character cur : goodGuys){
			cur.printDescription();
			System.out.println();
		}
		
		System.out.println();
		System.out.println("FLOOR ONE RANDOM BAD GUYS:");
		System.out.println();
		
		for(character.Character cur : badGuys){
			cur.printDescription();
			System.out.println();
		}
		
		//Upgrade monsters
		dungeonFloor = 2;
		badGuys = factory.makeBadParty(dungeonFloor);
		
		//Level up good guys to level 3
		goodGuys.addEXP(500);
		goodGuys.addEXP(750);
		goodGuys.addEXP(1125);
		
		
		System.out.println();
		System.out.println("LEVEL THREE GOOD GUYS:");
		System.out.println();
		
		for(character.Character cur : goodGuys){
			cur.printDescription();
			System.out.println();
		}
		
		System.out.println();
		System.out.println("FLOOR TWO RANDOM BAD GUYS:");
		System.out.println();
		
		for(character.Character cur : badGuys){
			cur.printDescription();
			System.out.println();
		}

	}

}
