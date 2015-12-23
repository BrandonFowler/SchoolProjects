/* Team: Larfleeze
 * Members: Nathan Graham, Matt Wilhelm, Brandon Fowler
 * Final project
 */

package character;

import Inventory.Equipables.Armors.ArmorPiece;
import combat.behaviors.*;

public abstract class Character {
	protected String name;
	protected double health;
	protected double curHealth;
	protected double attackPwr;
	protected double speed;
	protected double experienceWorth = 0;
	protected AttackBehavior attack;
	protected DefenseBehavior defend;
	
	public double getHealth() {
		return this.health;
	}
	
	public double getSpeed() {
		return this.speed;
	}
	
	public double getAttackPwr() {
		return this.attackPwr;
	}
	
	public double getCurHealth() {
		return this.curHealth;
	}
	
	public String getName(){
		if(this.isAlive()){
			return this.name;
		}
		else{
			return this.name+"(Dead)";
		}
	}
	
	public double getExperienceWorth() {
		return this.experienceWorth;
	}
	
	public void setCurHealth(double health) {
		if(health >= 0) {
			this.curHealth = health;
		} else {
			this.curHealth = 0;
		}
	}
	
	public void setHealth(int health) {
		if(health >= 0) {
			this.health = health;
		} else {
			this.health = 0;
		}
	}
	
	public void setAttackPwr(int attackPwr) {
		if(attackPwr >= 0) {
			this.attackPwr = attackPwr;
		} else {
			this.attackPwr = 0;
		}
	}
	
	public void setSpeed(int speed) {
		if(health >= 0) {
			this.speed = speed;
		} else {
			this.speed = 0;
		}
	}
	
	public boolean isAlive(){
		return this.curHealth > 0;
	}
	
	public void attack(character.Character target){
		if(this.isAlive()){
			setAttackBehavior();
			boolean hasDefended = true;
			double unmodifiedDamage = (this.attack.attack(this.attackPwr,this.getName(), target.getName()));
			double damage = unmodifiedDamage - (target.getDefenceRating());
			
			if(unmodifiedDamage <= target.getDefenceRating() && unmodifiedDamage > 0){
				System.out.println(target.name+"'s armor protects him from damage");
			}
			else if(damage > 0){
				hasDefended = target.defend();
			}
			
			if(!hasDefended){
				if(damage > 0){
					System.out.println("The attack is successful, dealing "+String.format( "%.2f", damage)+" damage!");
					target.setCurHealth(target.getCurHealth() - damage);
					System.out.println(target.getName()+" remaining health: "+String.format("%.2f", target.getCurHealth()));
				}
			}
		}
	}
	
	public void printDescription(){
		System.out.println("Name : "+this.name);
		System.out.println("Max Health : "+this.health);
		System.out.println("Current Health : "+this.curHealth);
		System.out.println("Attack Power : "+this.attackPwr);
		System.out.println("Speed : "+this.speed);
	}
	
	public abstract void addEXP(double EXP);
	public abstract boolean defend();
	public abstract void setAttackBehavior();
	public abstract double getDefenceRating();
	public abstract void combatUseItem();
	public abstract void equipArmorPiece(ArmorPiece toEquip);
}
