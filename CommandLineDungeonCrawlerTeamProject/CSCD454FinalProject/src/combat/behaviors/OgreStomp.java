/* Team: Larfleeze
 * Members: Nathan Graham, Matt Wilhelm, Brandon Fowler
 * Final project
 */

package combat.behaviors;

import java.util.Random;

public class OgreStomp implements AttackBehavior{
	public double attack(double atkPower, String atName, String dfName){
		System.out.println(atName+" attempts to smash "+dfName);
		Random rand = new Random();
		if(rand.nextInt(100) + 1 < 20){
			System.out.println("The attack misses!");
			return 0;
		}
		return (rand.nextInt(9) + 1) + atkPower;
	}
}
