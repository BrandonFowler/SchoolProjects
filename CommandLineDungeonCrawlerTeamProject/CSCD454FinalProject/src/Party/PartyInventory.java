/* Team: Larfleeze
 * Members: Nathan Graham, Matt Wilhelm, Brandon Fowler
 * Final project
 */

package Party;

import java.util.ArrayList;
import java.util.Scanner;

import Inventory.ConsumableFactory;
import Inventory.EquipmentFactory;
import Inventory.Item;
import Inventory.ItemFactory;
import Inventory.Consumables.Consumable;
import Inventory.Equipables.Armors.ArmorPiece;

public class PartyInventory {
	
	private static PartyInventory inventory = null;
	
	int maxItems;
	int itemCount;
	int consumableCount;
	int armorCount;
	Party party;
	ArrayList<Consumable> consumables;
	ArrayList<ArmorPiece> armors;
	
	
	public static  PartyInventory getInventory(Party party){
		if(inventory == null){
			inventory = new PartyInventory(party);
		}
		return inventory;
	}
	
	public static  PartyInventory getInventory(){
		return inventory;
	}
	
	private PartyInventory(Party party){
		this.maxItems = 50;
		this.itemCount = 0;
		this.consumableCount = 0;
		this.armorCount = 0;
		this.consumables = new ArrayList<Consumable>();
		this.armors = new ArrayList<ArmorPiece>();
		this.party = party;
		ItemFactory consume = new ConsumableFactory();
		ItemFactory equip = new EquipmentFactory();
		this.addItem(consume.generateItem("Potion"));
		this.addItem(consume.generateItem("Potion"));
		this.addItem(equip.generateItem("Helmet","Goblin"));
	}
	
	public void useArmor(character.Character target){
		if(armors.isEmpty()){
			System.out.println("You have nothing to equip!");
		}
		else{
			System.out.println("What would you like to equip?:");
			displayArmor();
			System.out.println("0. Exit Inventory");
			@SuppressWarnings("resource")
			Scanner getChoice = new Scanner(System.in);
			int choice = -1;
			
			while(choice < 0 || choice > this.armorCount){
				System.out.println();
				System.out.print("Please choose the number of the item you would like to use(example: 1 = "+armors.get(0).getItemName()+"): ");
				try{
					choice = getChoice.nextInt();
					System.out.println();
				}
				catch(Exception e){//Bad input
					getChoice.next();//Clear buffer
					choice = -1000;//Cause invalid message to re-prompt input
				}
				
				if(choice < 0  || choice > this.armorCount){
					System.out.println("Invalid choice. Try again!");
				}
				else{
					if(choice != 0){
						choice = choice - 1;//For list index retrieval
						target.equipArmorPiece(armors.get(choice));
						this.armors.remove(choice);
						this.armorCount--;
						this.itemCount--;
					}
				}
			}
		}
	}

	public void useConsumable(character.Character target){
		if(consumables.isEmpty()){
			System.out.println("You have nothing to consume!");
		}
		else{
			System.out.println("What would you like the consume?:");
			displayConsumables();
			System.out.println("0. Exit Inventory");
			@SuppressWarnings("resource")
			Scanner getChoice = new Scanner(System.in);
			int choice = -1;
			
			while(choice < 0 || choice > this.consumableCount){
				System.out.println();
				System.out.print("Please choose the number of the item you would like to use(example: 1 = "+consumables.get(0).getType()+"): ");
				try{
					choice = getChoice.nextInt();
					System.out.println();
				}
				catch(Exception e){//Bad input
					getChoice.next();//Clear buffer
					choice = -1000;//Cause invalid message to re-prompt input
				}
				
				if(choice < 0  || choice > this.consumableCount){
					System.out.println("Invalid choice. Try again!");
				}
				else{
					if(choice != 0){
						choice = choice - 1;//For list index retrieval
						target.setCurHealth(target.getCurHealth() + consumables.get(choice).consume());
						if(target.getCurHealth() > target.getHealth()){
							target.setCurHealth(target.getHealth());
						}
						this.consumables.remove(choice);
						this.consumableCount--;
						this.itemCount--;
					}
				}
			}
		}
	}
	
	public void displayArmor(){
		int i = 1;
		for(ArmorPiece item:armors){
			System.out.println(i+". "+item.getItemName());
			i++;
		}
	}
	
	public void displayConsumables(){
		int i = 1;
		for(Consumable item:consumables){
			System.out.println(i+". "+item.getType());
			i++;
		}
	}
	
	public void displayInventory(){
		System.out.println("EQUIPABLE ITEMS:");
		displayArmor();
		System.out.println("CONSUMABLE ITEMS:");
		displayConsumables();
		System.out.println("Inventory slots remaining: "+(maxItems-itemCount));
	}
	
	private void addArmor(ArmorPiece item){
		if(itemCount == maxItems){
			System.out.println("No more inventory space!");
		}
		else{
			this.armors.add(item);
			this.itemCount++;
			this.armorCount++;
		}
	}
	
	private void addConsumable(Consumable item){
		if(itemCount == maxItems){
			System.out.println("No more inventory space!");
		}
		else{
			this.consumables.add(item);
			this.itemCount++;
			this.consumableCount++;
		}
	}
	
	public void addItem(Item item){
		if(item instanceof Consumable){
			addConsumable((Consumable)item);
		}
		if(item instanceof ArmorPiece){
			addArmor((ArmorPiece)item);
		}
	}
	
	public Party getParty(){
		return this.party;
	}	
}
