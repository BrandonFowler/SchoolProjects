package Tests;

import Inventory.*;
import Inventory.Consumables.*;
import Inventory.Equipables.Armors.*;

public class ItemFactoryTests {

	//TODO Refactor this into a framework that java supports
	public static void main(String[] args) {
		ItemFactory consume = new ConsumableFactory();
		ItemFactory equip = new EquipmentFactory();
		
		Consumable potion = (Consumable)consume.generateItem("Potion");
		ArmorPiece armor = (ArmorPiece)equip.generateItem("Boots", "Goblin");
		
		assert(armor.getItemType().equals("Boots"));
		assert(potion.getType().equals("Potion"));
	}

}
