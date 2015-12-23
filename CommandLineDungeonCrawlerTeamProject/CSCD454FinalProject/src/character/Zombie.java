/* Team: Larfleeze
 * Members: Nathan Graham, Matt Wilhelm, Brandon Fowler
 * Final project
 */

package character;

import java.util.Random;

import combat.behaviors.*;

public class Zombie extends Bad {
	
	public Zombie(int floorMod){
		this.name = "Zombie";
		this.difMultiplier = (0.5 * (floorMod+1));
		this.health = (25 * this.difMultiplier);
		this.curHealth = this.health;
		this.attackPwr = (8 * this.difMultiplier);
		this.speed = (8 * this.difMultiplier);
		this.armorVal = (3 * this.difMultiplier);
		this.defend = new Block();
		this.experienceWorth = (70 * this.difMultiplier);
	}
	
	public void setAttackBehavior(){
		Random rand = new Random();
		double chance = (rand.nextInt(100) + 1);
		if(chance <= 70){
			this.attack = new ZombieBash();
		}
		else{
			this.attack = new ZombieBite();
		}
	}
	
}
