/* Team: Larfleeze
 * Members: Nathan Graham, Matt Wilhelm, Brandon Fowler
 * Final project
 */

package character;

import java.util.Random;

import combat.behaviors.Block;
import combat.behaviors.OgreClub;
import combat.behaviors.OgreStomp;

public class Ogre extends Bad {
	
	public Ogre(int floorMod){
		this.name = "Ogre";
		this.difMultiplier = (0.5 * (floorMod+1));
		this.health = (35 * this.difMultiplier);
		this.curHealth = this.health;
		this.attackPwr = (10 * this.difMultiplier);
		this.speed = (8 * this.difMultiplier);
		this.armorVal = (6);
		this.defend = new Block();
		this.experienceWorth = (100 * this.difMultiplier);
	}
	
	public void setAttackBehavior(){
		Random rand = new Random();
		double chance = (rand.nextInt(100) + 1);
		if(chance <= 70){
			this.attack = new OgreClub();
		}
		else{
			this.attack = new OgreStomp();
		}
	}

}
