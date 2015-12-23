package Inventory.Consumables;

public class SuperPotion extends Consumable {
	private int healthRestore = 70;

	public SuperPotion(){
		this.name = "Super Potion";
	}
	
	public int consume() {
		return healthRestore;
	}

	@Override
	public String getType() {
		return this.name;
	}
	
	
}
