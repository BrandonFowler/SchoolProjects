/* Team: Larfleeze
 * Members: Nathan Graham, Matt Wilhelm, Brandon Fowler
 * Final project
 */

package character;

import java.util.Random;

import combat.behaviors.DragonClaw;
import combat.behaviors.DragonFlame;
import combat.behaviors.DragonTail;
import combat.behaviors.Evade;

public class Dragon extends Bad {
	
	public Dragon(int floorMod){
		this.name = "Dragon";
		this.difMultiplier = (0.5 * (floorMod+1));
		this.health = (150 * this.difMultiplier);
		this.curHealth = this.health;
		this.attackPwr = (15 * this.difMultiplier);
		this.speed = (12 * this.difMultiplier);
		this.armorVal = (10);
		this.defend = new Evade();
		this.experienceWorth = (500 * this.difMultiplier);
	}
	
	public void setAttackBehavior(){
		Random rand = new Random();
		double chance = (rand.nextInt(100) + 1);
		if(chance <= 40){
			this.attack = new DragonClaw();
		}
		else if(chance <= 70){
			this.attack = new DragonTail();
		}
		else{
			this.attack = new DragonFlame();
		}
	}
}
