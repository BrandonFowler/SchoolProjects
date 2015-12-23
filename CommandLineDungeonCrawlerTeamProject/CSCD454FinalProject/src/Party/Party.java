/* Team: Larfleeze
 * Members: Nathan Graham, Matt Wilhelm, Brandon Fowler
 * Final project
 */

package Party;

import java.util.ArrayList;
import java.util.Iterator;

import Inventory.Item;

public abstract class Party implements Iterable<character.Character>{
	protected int size;
	protected ArrayList<character.Character> members;
	protected PartyInventory inventory; 
	
	
	public Iterator<character.Character> iterator(){
		return new PartyIterator(this.members, this.size);
	}
	
	public class PartyIterator implements Iterator<character.Character>{
		protected ArrayList<character.Character> pMembers;
		protected int cur;
		protected int size;
		
		public PartyIterator(ArrayList<character.Character> pMembers, int size){
			this.pMembers = pMembers;
			this.cur = 0;
			this.size = size;
		}
		
		public boolean hasNext(){
			return cur < this.size;
		}
		
		public character.Character next(){
			return hasNext() 
					? pMembers.get(cur++) 
					: null;
		}
		
		public void remove(){
			//Not supported
		}
	}
	
	public boolean surviving(){
		
		for(character.Character cur: this.members){
			if(cur.isAlive()){
				return true;
			}
		}
		return false;
	}
	
	public character.Character getCharacter(int index){
		return members.get(index);
	}
	
	public void addItem(Item toAdd){
		inventory.addItem(toAdd);
	}
	
	public abstract void useItem();
	public abstract void addEXP(double EXP);
	public abstract double getEXP();
}
