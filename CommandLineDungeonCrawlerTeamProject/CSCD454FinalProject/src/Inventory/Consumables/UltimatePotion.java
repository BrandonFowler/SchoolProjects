package Inventory.Consumables;

public class UltimatePotion extends Consumable{
	private int healthRestore = 500;
	
	public UltimatePotion(){
		this.name = "Ultimate Potion";
	}
	
	public int consume() {
		return healthRestore;
	}

	@Override
	public String getType() {
		return this.name;
	}

}
