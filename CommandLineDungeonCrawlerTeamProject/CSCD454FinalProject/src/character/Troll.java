/* Team: Larfleeze
 * Members: Nathan Graham, Matt Wilhelm, Brandon Fowler
 * Final project
 */

package character;

import java.util.Random;

import combat.behaviors.Evade;
import combat.behaviors.TrollSpit;
import combat.behaviors.TrollWhack;

public class Troll extends Bad {

	public Troll(int floorMod){
		this.name = "Troll";
		this.difMultiplier = (0.5 * (floorMod+1));
		this.health = (20 * this.difMultiplier);
		this.curHealth = this.health;
		this.attackPwr = (8 * this.difMultiplier);
		this.speed = (12 * this.difMultiplier);
		this.armorVal = (5);
		this.defend = new Evade();
		this.experienceWorth = (70 * this.difMultiplier);
	}
	
	public void setAttackBehavior(){
		Random rand = new Random();
		double chance = (rand.nextInt(100) + 1);
		if(chance <= 70){
			this.attack = new TrollWhack();
		}
		else{
			this.attack = new TrollSpit();
		}
	}

}
