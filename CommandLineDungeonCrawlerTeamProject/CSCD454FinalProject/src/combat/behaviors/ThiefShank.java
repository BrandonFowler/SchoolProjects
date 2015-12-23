/* Team: Larfleeze
 * Members: Nathan Graham, Matt Wilhelm, Brandon Fowler
 * Final project
 */

package combat.behaviors;

import java.util.Random;

public class ThiefShank implements AttackBehavior{
	public double attack(double atkPower, String atName, String dfName){
		System.out.println(atName+" stabs in rapid succession with impossible speed at "+dfName);
		Random rand = new Random();
		if(rand.nextInt(100) + 1 < 30){
			System.out.println("The attack misses!");
			return 0;
		}
		return (rand.nextInt(11) + 1) + atkPower;
	}
}
