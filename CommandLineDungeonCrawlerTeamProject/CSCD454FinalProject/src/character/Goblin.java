/* Team: Larfleeze
 * Members: Nathan Graham, Matt Wilhelm, Brandon Fowler
 * Final project
 */

package character;

import java.util.Random;

import combat.behaviors.Evade;
import combat.behaviors.GoblinBow;
import combat.behaviors.GoblinStick;

public class Goblin extends Bad {

	public Goblin(int floorMod){
		this.name = "Goblin";
		this.difMultiplier = (0.5 * (floorMod+1));
		this.health = (12 * this.difMultiplier);
		this.curHealth = this.health;
		this.attackPwr = (8 * this.difMultiplier);
		this.speed = (12 * this.difMultiplier);
		this.armorVal = (3);
		this.defend = new Evade();
		this.experienceWorth = (70 * this.difMultiplier);
	}
	
	public void setAttackBehavior(){
		Random rand = new Random();
		double chance = (rand.nextInt(100) + 1);
		if(chance <= 70){
			this.attack = new GoblinStick();
		}
		else{
			this.attack = new GoblinBow();
		}
	}
}
