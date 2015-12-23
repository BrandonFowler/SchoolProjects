/* Team: Larfleeze
 * Members: Nathan Graham, Matt Wilhelm, Brandon Fowler
 * Final project
 */

package dungeon;

import dungeon.DungeonBuilderInterface;

public class DunBuilderDirector {
	public Dungeon construct(int difficulty) {
		DunBuilderFactory factory = new DunBuilderFactory();
		DungeonBuilderInterface dungeonBuilder = factory.createBuilder(difficulty);
		
		dungeonBuilder.buildFloors();
		dungeonBuilder.placeEntrance();
		dungeonBuilder.placeLastBossPos();
		dungeonBuilder.closeDoors();
		dungeonBuilder.verifyDungeon();
		Dungeon dungeon = dungeonBuilder.getDungeon();
		dungeon.setPlayerFloor(0);
		
		return dungeon;
	}
}
