/* Team: Larfleeze
 * Members: Nathan Graham, Matt Wilhelm, Brandon Fowler
 * Final project
 */

package combat.behaviors;

import java.util.Random;

public class GiantSmash implements AttackBehavior{
	public double attack(double atkPower, String atName, String dfName){
		System.out.println(atName+" attempts massive a two handed smash on "+dfName);
		Random rand = new Random();
		if(rand.nextInt(100) + 1 < 15){
			System.out.println("The attack misses!");
			return 0;
		}
		return (rand.nextInt(12) + 1) + atkPower;
	}
}
