/* Team: Larfleeze
 * Members: Nathan Graham, Matt Wilhelm, Brandon Fowler
 * Final project
 */

package Party;

import java.util.ArrayList;

import character.*;
import character.Character;

public class BossParty extends Party{

	public BossParty(int floorMod, String type){
		members = new ArrayList<Character>();
		this.size = 1;
		type = type.toLowerCase();
		if(type.compareTo("giant") == 0){
			members.add(new Giant(floorMod));
		}
		else{
			members.add(new Dragon(floorMod));
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
