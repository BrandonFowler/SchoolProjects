/* Team: Larfleeze
 * Members: Nathan Graham, Matt Wilhelm, Brandon Fowler
 * Final project
 */

package character;

import java.util.Random;

import combat.behaviors.Block;
import combat.behaviors.GiantCrush;
import combat.behaviors.GiantPunch;
import combat.behaviors.GiantSmash;


public class Giant extends Bad {
	
	public Giant(int floorMod){
		this.name = "Giant";
		this.difMultiplier = (0.5 * (floorMod+1));
		this.health = (150 * this.difMultiplier);
		this.curHealth = this.health;
		this.attackPwr = (15 * this.difMultiplier);
		this.speed = (14 * this.difMultiplier);
		this.armorVal = (8);
		this.defend = new Block();
		this.experienceWorth = (300 * this.difMultiplier);
	}
	
	public void setAttackBehavior(){
		Random rand = new Random();
		double chance = (rand.nextInt(100) + 1);
		if(chance <= 40){
			this.attack = new GiantPunch();
		}
		else if(chance <= 70){
			this.attack = new GiantSmash();
		}
		else{
			this.attack = new GiantCrush();
		}
	}
}
