/* Team: Larfleeze
 * Members: Nathan Graham, Matt Wilhelm, Brandon Fowler
 * Final project
 */

package combat.behaviors;

import java.util.Random;

public class Block implements DefenseBehavior{
	public boolean defend(double speed, String name){
		Random rand = new Random();
		if((rand.nextInt(1000) + 1) + speed/2 > 850){
			System.out.println(name+" has blocked the attack!");
			return true;
		}
		return false;
	}
}
