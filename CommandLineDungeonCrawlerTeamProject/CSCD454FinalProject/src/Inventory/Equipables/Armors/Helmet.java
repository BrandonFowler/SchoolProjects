package Inventory.Equipables.Armors;

import Inventory.Equipables.Sets.Set;

public class Helmet extends ArmorPiece {
	
	public Helmet(Set set){
		this.armorRating = .75;
		this.name = "Helmet";
		this.set = set;
	}	
}
