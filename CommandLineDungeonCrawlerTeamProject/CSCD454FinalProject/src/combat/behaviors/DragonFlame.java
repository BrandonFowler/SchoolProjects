/* Team: Larfleeze
 * Members: Nathan Graham, Matt Wilhelm, Brandon Fowler
 * Final project
 */

package combat.behaviors;

import java.util.Random;

public class DragonFlame implements AttackBehavior{
	public double attack(double atkPower, String atName, String dfName){
		System.out.println(atName+" blows a mighty breath if molten flame at "+dfName);
		Random rand = new Random();
		if(rand.nextInt(100) + 1 < 20){
			System.out.println("The attack misses!");
			return 0;
		}
		return (rand.nextInt(15) + 1) + atkPower;
	}
}
