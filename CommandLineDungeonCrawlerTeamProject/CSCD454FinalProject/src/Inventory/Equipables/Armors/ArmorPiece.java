package Inventory.Equipables.Armors;

import Inventory.Item;
import Inventory.Equipables.IEquipable;
import Inventory.Equipables.Sets.Set;

public abstract class ArmorPiece extends Item implements IEquipable {
	protected double armorRating;
	protected Set set;
	
	public int getArmorValue(){
		return (int)(armorRating + set.getBaseArmorValue());
	}
	
	public String getItemType(){
		return name;
	}
	
	public String getItemName(){
		return set.getSetName() + " " + name;
	}
	
	public String getSetName(){
		return set.getSetName();
	}
}
