package Inventory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import Inventory.Consumables.Consumable;
import Inventory.Equipables.Sets.*;

public class RandomItemFactory implements ItemFactory {
	private EquipmentFactory equipmentFactory;
	private ConsumableFactory consumableFactory;
	private static Random randomSeed;
	private List<String> sets;
	private List<String> consumables;
	private List<String> armors;
	
	public RandomItemFactory(){
		this.equipmentFactory = new EquipmentFactory();
		this.consumableFactory = new ConsumableFactory();
		this.randomSeed = new Random();
		this.sets = new ArrayList<String>();
		this.consumables = new ArrayList<String>();
		this.armors = new ArrayList<String>();
		//TODO there is probably a better way of doing this but for now . . . -[mw]
		populateSets(this.sets);
		populateArmors(this.armors);
		populateConsumables(this.consumables);
	}	
	
	
	public Item generateItem(String... properties) {
		if(randomSeed.nextInt() % 2 == 0){
			return consumableFactory.generateItem(consumables.get(Math.abs(randomSeed.nextInt() % consumables.size())));
		} else {			
			return equipmentFactory.generateItem(armors.get(Math.abs(randomSeed.nextInt() % armors.size())), 
					sets.get(Math.abs(randomSeed.nextInt() % sets.size())));
		}
		
	}
	
	private void populateSets(List<String> sets){
		this.sets.add("Ogre");
		this.sets.add("Dragon");
		this.sets.add("Goblin");
		this.sets.add("Troll");
	}
	
	private void populateArmors(List<String> armors){
		this.armors.add("Boots");
		this.armors.add("ChestPiece");
		this.armors.add("Legs");
		this.armors.add("Gloves");
		this.armors.add("Helmet");
	}
	
	private void populateConsumables(List<String> consumables){
		this.consumables.add("Potion");
		this.consumables.add("SuperPotion");
		this.consumables.add("UltimatePotion");
	}

}
