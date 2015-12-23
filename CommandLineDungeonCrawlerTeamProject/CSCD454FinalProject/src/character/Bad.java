/* Team: Larfleeze
 * Members: Nathan Graham, Matt Wilhelm, Brandon Fowler
 * Final project
 */

package character;

import Inventory.Equipables.Armors.ArmorPiece;

public abstract class Bad extends Character {
	
	protected double difMultiplier;
	protected double armorVal;
	
	public double getDefenceRating(){
		return this.armorVal;
	}
	
	public boolean defend(){
		return defend.defend(this.speed, this.name);
	}

	public void addEXP(double EXP){
		//Not currently Implemented
	}

	public void combatUseItem(){
		//Not currently Implemented
	}
	
	public void equipArmorPiece(ArmorPiece toEquip){
		//Not currently Implemented
	}
}
