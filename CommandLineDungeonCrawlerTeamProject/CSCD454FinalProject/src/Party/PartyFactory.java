/* Team: Larfleeze
 * Members: Nathan Graham, Matt Wilhelm, Brandon Fowler
 * Final project
 */

package Party;

import java.util.*;

public class PartyFactory{
	
	public GoodParty makeGoodParty(){
		int [] choices = chooseMembers();
		GoodParty party = new GoodParty(choices);
		return party;
	}
	
	public BadParty makeBadParty(int floorMod){
		BadParty party = new BadParty(floorMod);
		return party;
	}
	
	public BossParty makeBossParty(int floorMod, String type){
		BossParty party = new BossParty(floorMod, type);
		return party;
	}
	
	public int[] chooseMembers(){
		
		int[] choices = new int[3];
		@SuppressWarnings("resource")
		Scanner getChoice = new Scanner(System.in);
		int choice = 0;
		
		System.out.println("You may choose any three of the following heros:");
		System.out.println("1. Warrior");
		System.out.println("2. Wizzard");
		System.out.println("3. Thief");
		System.out.println("4. Shamman");
		System.out.println("5. Paladin");
		
		int i = 0;
		while(i < choices.length){
			System.out.print("Choose the number of your hero(example 2 = Wizzard): ");
			try{
				choice = getChoice.nextInt();
				System.out.println();
			}
			catch(Exception e){//Bad input
				getChoice.next();//Clear buffer
				choice = -1000;//Cause invalid message to re-prompt input
			}
			
			if(choice < 1  || choice > 5){
				System.out.println("Invalid choice. Try again!");
			}
			else{
				choices[i] = choice;
				i++;
				choice = 0;
			}
		}
		return choices;
	}
}
