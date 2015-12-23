package Inventory;

import Inventory.Equipables.Sets.SetFactory;
import Inventory.Equipables.Armors.*;

public class EquipmentFactory implements ItemFactory {
	//TODO Consider using IOC [mw]
	private SetFactory setFactory;
	
	public EquipmentFactory(){
		setFactory = new SetFactory();
	}
	
	public ArmorPiece generateItem(String... properties) {
		//TODO Refactor this using reflection to gather class instances [mw]
		if(properties.length != 2) throw new IllegalArgumentException("Equippable Items require two components; a set and a name.");
		String itemType = properties[0];
		String setType = properties[1];
		if(itemType.equals("Boots")){
			return new Boots(setFactory.generateSet(setType));
		} else if(itemType.equals("Legs")){
			return new Legs(setFactory.generateSet(setType));
		} else if(itemType.equals("Gloves")){
			return new Gloves(setFactory.generateSet(setType));
		} else if(itemType.equals("Helmet")){
			return new Helmet(setFactory.generateSet(setType));
		} else {		
			return new ChestPiece(setFactory.generateSet(setType));
		}
	}


}
