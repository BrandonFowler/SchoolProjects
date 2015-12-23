/* Team: Larfleeze
 * Members: Nathan Graham, Matt Wilhelm, Brandon Fowler
 * Final project
 */

package dungeon;

public interface DungeonBuilderInterface {
	public void buildFloors();
	public void placeEntrance();
	public void placeLastBossPos();
	public void closeDoors();
	public void verifyDungeon();
	public Dungeon getDungeon();
}
