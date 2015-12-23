package Inventory.Equipables;

import Inventory.Equipables.Armors.*;

public class ArmorSet implements IArmor {
	private double calculatedSetBonus;
	private ChestPiece chestPiece;
	private Helmet helmet;
	private Legs legs;
	private Boots boots;
	private Gloves gloves;
	
	private void updateSetBonus(){
		calculatedSetBonus = getCurrentSetBonus();
	}
	
	private double getCurrentSetBonus(){
		return calculateCoreDefenseBonus() * calculatedSetBonus;
	}
	
	private int calculateCoreDefenseBonus(){
		return chestPiece.getArmorValue() + helmet.getArmorValue()
				+ legs.getArmorValue() + boots.getArmorValue()
				+ gloves.getArmorValue();
	}

	public int getDefenseRating() {
		return calculateCoreDefenseBonus();
	}

	public void equip(IEquipable item) {
		if(item.getItemType() == "ChestPiece"){
			this.chestPiece = (ChestPiece)item;
		} else if(item.getItemType() == "Helmet"){
			this.helmet = (Helmet)item;
		} else if(item.getItemType() == "Gloves") {
			this.gloves = (Gloves)item;
		} else if(item.getItemType() == "Boots") {
			this.boots = (Boots)item;
		} else {
			//equip pants
			this.legs = (Legs)item;
		}
		if(this.chestPiece != null && this.gloves != null && this.helmet != null && this.legs != null && this.boots != null){
			updateSetBonus();
		}
	}
}
