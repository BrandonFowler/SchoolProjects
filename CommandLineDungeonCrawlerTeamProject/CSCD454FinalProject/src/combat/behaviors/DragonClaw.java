/* Team: Larfleeze
 * Members: Nathan Graham, Matt Wilhelm, Brandon Fowler
 * Final project
 */

package combat.behaviors;

import java.util.Random;

public class DragonClaw implements AttackBehavior{
	public double attack(double atkPower, String atName, String dfName){
		System.out.println(atName+" swings a razor tipped claw at "+dfName);
		Random rand = new Random();
		if(rand.nextInt(100) + 1 < 10){
			System.out.println("The attack misses!");
			return 0;
		}
		return (rand.nextInt(10) + 1) + atkPower;
	}
}
