package Inventory.Consumables;

public class Potion extends Consumable {
	private int healthRestore = 30;
	
	public Potion(){
		this.name = "Potion";
	}
	
	public int consume() {
		return healthRestore;
	}

	@Override
	public String getType() {
		return this.name;
	}
}
