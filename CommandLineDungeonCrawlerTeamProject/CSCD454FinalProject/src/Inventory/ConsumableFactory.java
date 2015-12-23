package Inventory;

import Inventory.Consumables.*;

public class ConsumableFactory implements ItemFactory {

	public Consumable generateItem(String... properties) {
		if(properties.length != 1) throw new IllegalArgumentException("Consumable items require one argument; a name.");
		String itemType = properties[0];
		if(itemType.equals("Potion")){
			return new Potion();
		} else if(itemType.equals("SuperPotion")){
			return new SuperPotion();
		} else {
			return new UltimatePotion();
		}		
	}

}
