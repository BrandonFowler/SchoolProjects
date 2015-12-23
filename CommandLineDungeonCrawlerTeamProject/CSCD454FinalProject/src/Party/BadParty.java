/* Team: Larfleeze
 * Members: Nathan Graham, Matt Wilhelm, Brandon Fowler
 * Final project
 */

package Party;

import java.util.ArrayList;
import java.util.Random;
import character.*;

public class BadParty extends Party{

	protected int floorMod;
	
	public BadParty(int floorMod){
		this.members = new ArrayList<character.Character>();
		this.floorMod = floorMod;
		addMonsters();
	}
	
	public void addMonsters(){
		Random rand = new Random();
		this.size = rand.nextInt(3) + 3; //Random group size between 3 and 5
		for(int i = 0; i < this.size; i++){ //Add five random bad guys to party
			
			int pickMonster = rand.nextInt(5) + 1;
			
			if(pickMonster == 1){
				this.members.add(new Goblin(floorMod));
			}
			else if(pickMonster == 2){
				this.members.add(new Slime(floorMod));
			}
			else if(pickMonster == 3){
				this.members.add(new Ogre(floorMod));
			}
			else if(pickMonster == 4){
				this.members.add(new Troll(floorMod));
			}
			else{
				this.members.add(new Zombie(floorMod));
			}
		}
	}
	
	public void useItem(){
		//Not currently Implemented
	}
	
	public void addEXP(double EXP){
		//Not currently Implemented
	}
	
	public double getEXP(){
		double exp = 0;
		for(character.Character creature:this.members){
			exp = exp + creature.getExperienceWorth();
		}
		return exp;
	}
}
