/* Team: Larfleeze
 * Members: Nathan Graham, Matt Wilhelm, Brandon Fowler
 * Final project
 */

package combat.behaviors;

import java.util.Random;

public class GoblinStick implements AttackBehavior{
	public double attack(double atkPower, String atName, String dfName){
		System.out.println(atName+" shoves a rusty knife at "+dfName);
		Random rand = new Random();
		if(rand.nextInt(100) + 1 < 10){
			System.out.println("The attack misses!");
			return 0;
		}
		return (rand.nextInt(5) + 1) + atkPower;
	}
}
