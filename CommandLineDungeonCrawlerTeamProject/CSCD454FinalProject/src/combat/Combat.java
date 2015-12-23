/* Team: Larfleeze
 * Members: Nathan Graham, Matt Wilhelm, Brandon Fowler
 * Final project
 */

package combat;

import character.Bad;
import character.Character;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import main.GameCore;

import Party.*;

public class Combat {
	private Party goodGuys;
	private Party badGuys;
	private CombatQueue combatQueue;
	
	public Combat(Party goodGuys, Party badGuys) {
		this.goodGuys = goodGuys;
		this.badGuys = badGuys;
		this.combatQueue = new CombatQueue(this.goodGuys, this.badGuys);
	}
	
	public boolean fight() {
		ArrayList<Character> attackers;
		while(goodGuys.surviving() && badGuys.surviving()) {
			
			attackers = this.combatQueue.tick();
			
			for(Character attacker: attackers){
				if(goodGuys.surviving() && badGuys.surviving()){
					if(attacker instanceof Bad){
						boolean needValidTarget = true;
						Random rand = new Random();
						int chance = 0;
						while(needValidTarget){
							chance = (rand.nextInt(3));
							if(goodGuys.getCharacter(chance).isAlive()){
								needValidTarget = false;
							}
						}
						attacker.attack(goodGuys.getCharacter(chance));
					}
					else{
						int combatChoice = getCombatChoice(attacker.getName());
						if(combatChoice == 1){
							int targetChoice = getTargetChoice();
							attacker.attack(badGuys.getCharacter(targetChoice));
						}
						else if(combatChoice == -42){
							System.out.println("Your party members have all inexplicably slipped and fallen on their weapons...");
							return false;
						}
						else if(combatChoice == 42){
							System.out.println("You have destroyed all enemies in a brilliant flash of light, cheater!");
							goodGuys.addEXP(badGuys.getEXP());
							return goodGuys.surviving();
						}
						else{
							attacker.combatUseItem();
						}
					}
				}
			}
		}
		goodGuys.addEXP(badGuys.getEXP());
		return goodGuys.surviving();
	}
	
	private int getCombatChoice(String name){
		@SuppressWarnings("resource")
		Scanner getChoice = new Scanner(System.in);
		int choice = -1;
		System.out.println(name+"'s turn!");
		System.out.println("Your options are:");
		System.out.println("1. Attack");
		System.out.println("2. Use Item");
		
		while(choice < 1 || choice > 2){
			System.out.print("Choose the number of your option:");
			try{
				choice = getChoice.nextInt();
				System.out.println();
				if(choice == 42){
					return choice;
				}
				if(choice == -42){
					return choice;
				}
			}
			catch(Exception e){//Bad input
				getChoice.next();//Clear buffer
				choice = -1000;//Cause invalid message to re-prompt input
			}
			
			if(choice < 1  || choice > 2){
				System.out.println("Invalid choice. Try again!");
			}
		}
		return choice;
	}
	
	private int getTargetChoice(){
		@SuppressWarnings("resource")
		Scanner getChoice = new Scanner(System.in);
		int choice = -1;
		int targetCount = 1;
		System.out.println("What would you like to attack?");
		System.out.println("Your options are: ");
		for(Character target: badGuys){
			System.out.println(targetCount+". "+target.getName());
			targetCount++;
		}
		while(choice < 1 || choice > targetCount-1 || !(badGuys.getCharacter(choice-1).isAlive())){
			System.out.print("Choose the number of your option:");
			try{
				choice = getChoice.nextInt();
				System.out.println();
			}
			catch(Exception e){//Bad input
				getChoice.next();//Clear buffer
				choice = -1000;//Cause invalid message to re-prompt input
			}
			
			if(choice < 1  || choice > targetCount-1 || !(badGuys.getCharacter(choice-1).isAlive())){
				System.out.println("Invalid choice. Try again!");
			}
		}
		return (choice-1); //For index reference
	}
	
	public static boolean chanceFight(Party goodGuys, int curFloor) {
		Random rand = new Random();
		if(rand.nextInt(4) == 0) {
			System.out.println("You are under attack!  Prepare for combat!");
			PartyFactory factory = new PartyFactory();
			Combat curFight = new Combat(goodGuys, factory.makeBadParty(curFloor));
			if(curFight.fight()) {
				System.out.println("You have defeated your enemies!");
				return true;
			} else {
				GameCore gameCore = GameCore.getGameCore();
				gameCore.endGameKilled();
				return false;
			}
		}
		return true;
	}
}
