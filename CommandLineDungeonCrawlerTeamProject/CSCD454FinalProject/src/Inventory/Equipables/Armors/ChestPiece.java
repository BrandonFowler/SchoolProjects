package Inventory.Equipables.Armors;

import Inventory.Equipables.Sets.Set;

public class ChestPiece extends ArmorPiece {
	
	public ChestPiece(Set set){
		this.armorRating = 1;
		this.name = "ChestPiece";
		this.set = set;
	}
}
