package Tests;

import character.*;

public class CombatBehaviorTest {

	public static void main(String[] args) {
		
		character.Character wizzard = new Wizzard("Simon");
		character.Character warrior = new Warrior("Clide");
		character.Character thief = new Thief("Jimmy");
		character.Character paladin = new Paladin("Morry");
		character.Character shaman = new Shaman("Seamus");
		character.Character zombie = new Zombie(1);
		character.Character ogre = new Ogre(1);
		character.Character slime = new Slime(1);
		character.Character troll = new Troll(1);
		character.Character goblin = new Goblin(1);
		
		System.out.println("wizzard vs zombie");
		System.out.println("-----------------");

		
		while(wizzard.isAlive() && zombie.isAlive()){
			wizzard.attack(zombie);
			
			if(zombie.isAlive()){
				zombie.attack(wizzard);
			}
		}
		
		printWinner(wizzard, zombie);
		
		System.out.println("warrior vs ogre");
		System.out.println("-----------------");
		
		
		
		while(warrior.isAlive() && ogre.isAlive()){
			warrior.attack(ogre);
			
			if(ogre.isAlive()){
				ogre.attack(warrior);
			}
		}
		
		printWinner(warrior, ogre);
		
		System.out.println("thief vs slime");
		System.out.println("-----------------");
		
		
		
		while(thief.isAlive() && slime.isAlive()){
			thief.attack(slime);
			
			if(slime.isAlive()){
				slime.attack(thief);
			}
		}
		
		printWinner(thief, slime);
		
		System.out.println("paladin vs troll");
		System.out.println("-----------------");
		
		
		
		while(paladin.isAlive() && troll.isAlive()){
			paladin.attack(troll);
			
			if(troll.isAlive()){
				troll.attack(paladin);
			}
		}
		
		printWinner(paladin, troll);
		
		System.out.println("shaman vs goblin");
		System.out.println("-----------------");
		
		
		
		while(shaman.isAlive() && goblin.isAlive()){
			shaman.attack(goblin);
			
			if(goblin.isAlive()){
				goblin.attack(shaman);
			}
		}
		
		printWinner(shaman, goblin);
	}
	
	public static void printWinner(character.Character good, character.Character bad){
		if(good.isAlive()){
			System.out.println();
			System.out.println(good.getName()+" Wins!");
			System.out.println();
		}
		else{
			System.out.println();
			System.out.println(bad.getName()+" Wins!");
			System.out.println();
		}
	}

}
