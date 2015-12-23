package dungeon;

public class DunBuilderFactory {
	public DungeonBuilderInterface createBuilder(int difficulty) {
		DungeonBuilderInterface dungeonBuilder = null;
		if(difficulty == 1) {
			dungeonBuilder  = new EasyDungeonBuilder();
		} else if(difficulty == 2) {
			dungeonBuilder = new MediumDungeonBuilder();
		} else if(difficulty == 3) {
			dungeonBuilder = new NormalDungeonBuilder();
		} else {
			dungeonBuilder = new HardDungeonBuilder();
		}
		return dungeonBuilder;
	}
}
