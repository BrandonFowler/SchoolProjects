package Tests;

import character.Wizzard;
import Inventory.ConsumableFactory;
import Inventory.EquipmentFactory;
import Inventory.ItemFactory;
import Inventory.Consumables.Consumable;
import Inventory.Equipables.Armors.ArmorPiece;
import Party.Party;
import Party.PartyFactory;
import Party.PartyInventory;

public class PartyInventoryTest {

	public static void main(String[] args) {
		PartyFactory factory = new PartyFactory();
		Party good = factory.makeGoodParty();
		PartyInventory inventory = PartyInventory.getInventory();
		ItemFactory consume = new ConsumableFactory();
		ItemFactory equip = new EquipmentFactory();
		
		Consumable potion = (Consumable)consume.generateItem("SuperPotion");
		ArmorPiece armor = (ArmorPiece)equip.generateItem("Boots", "Goblin");
		
		inventory.addItem(potion);
		inventory.addItem(armor);
		
		potion = (Consumable)consume.generateItem("Potion");
		armor = (ArmorPiece)equip.generateItem("Boots", "Dragon");
		
		inventory.addItem(potion);
		inventory.addItem(armor);
		
		System.out.println("TEST DISPLAY INVENTORY");
		System.out.println();
		inventory.displayInventory();
		
		character.Character wizzard = new Wizzard("Tom");
		
		System.out.println();
		System.out.println("TEST USE POTION");
		System.out.println();
		
		System.out.println("Wizzard starting health: "+wizzard.getCurHealth());
		System.out.println();
		
		wizzard.setCurHealth(3);
		
		System.out.println("Wizzard health after taking some damage: "+wizzard.getCurHealth());
		System.out.println();
		
		wizzard.combatUseItem();//Use a potion
		
		System.out.println();
		System.out.println("Wizzard health after using inventory: "+wizzard.getCurHealth());
		System.out.println();
		
		System.out.println("INVENTORY AFTER USE");
		System.out.println();
		inventory.displayInventory();
		
		System.out.println();
		System.out.println("CREATE A PARTY");
		System.out.println();
		
		for(character.Character cur: good){
			System.out.println(cur.getName()+" armor rating: "+cur.getDefenceRating());
		}
		System.out.println();
		
		good.useItem(); //Equip an armor piece
		
		for(character.Character cur: good){
			System.out.println(cur.getName()+" armor rating: "+cur.getDefenceRating());
		}
		System.out.println();
		
		System.out.println("INVENTORY AFTER USE");
		System.out.println();
		inventory.displayInventory();
	}

}
