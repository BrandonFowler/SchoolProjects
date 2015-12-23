/* Team: Larfleeze
 * Members: Nathan Graham, Matt Wilhelm, Brandon Fowler
 * Final project
 */

package dungeon;

import Inventory.Item;
import Inventory.RandomItemFactory;
import Inventory.Consumables.Consumable;
import Inventory.Equipables.Armors.ArmorPiece;

public class TreasureChest {
	Item item;
	
	public TreasureChest() {
		RandomItemFactory factory = new RandomItemFactory();
		this.item = factory.generateItem();
	}
	
	public Item getItem(){
		return this.item;
	}
	
	public void printItem(){
		if(item instanceof Consumable){
			System.out.println("You found: "+((Consumable)(this.item)).getType());
		}
		else{
			System.out.println("You found: "+((ArmorPiece)(this.item)).getItemName());
		}
	}
}
