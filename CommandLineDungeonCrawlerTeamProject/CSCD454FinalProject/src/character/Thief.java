/* Team: Larfleeze
 * Members: Nathan Graham, Matt Wilhelm, Brandon Fowler
 * Final project
 */

package character;

import java.util.Scanner;
import combat.behaviors.ThiefShank;
import combat.behaviors.ThiefStab;

public class Thief extends Good {
	
	public Thief(String name){
		this.name = name;
		this.health = 35;
		this.curHealth = this.health;
		this.attackPwr = 10;
		this.speed = 10;
		this.level = 1;
		this.armorMultiplier = 0.5;
		this.xp = 0;
		this.nextLevel = 200;
		equipStartingArmor();
	}
	
	public void levelUp(){
		this.health = this.health + 5;
		this.curHealth = this.health;
		this.attackPwr = this.attackPwr +2;
		this.speed = this.speed + 1.5;
		this.level = level + 1;
	}
	
	public void setAttackBehavior(){
		@SuppressWarnings("resource")
		Scanner getChoice = new Scanner(System.in);
		int choice = 0;
		
		System.out.println();
		System.out.println("What attack would do you want you use?:");
		System.out.println("1. Weapon");
		System.out.println("2. Shank");
		
		
		while(choice < 1 || choice > 2){
			System.out.print("Choose your attack(example 2 = Shank): ");
			try{
				choice = getChoice.nextInt();
				System.out.println();
			}
			catch(Exception e){//Bad input
				getChoice.next();//Clear buffer
				choice = -1000;//Cause invalid message to re-prompt input
			}
			
			if(choice < 1  || choice > 2){
				System.out.println("Invalid choice. Try again!");
			}
			else{
				if(choice == 1){
					this.attack = new ThiefStab();
				}
				else{
					this.attack = new ThiefShank();
				}
			}
		}
	}
}
