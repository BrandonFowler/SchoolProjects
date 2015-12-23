package Inventory.Equipables.Sets;
import Inventory.Equipables.Sets.Set;
import Inventory.Equipables.Sets.SetTypes.*;

//TODO Consider renaming these to TrollSet() (etc.) - [mw]
public class SetFactory {
	public Set generateSet(String name){
		if(name.equals("Goblin")){
			return new Goblin();
		} else if(name.equals("Ogre")){
			return new Ogre();
		} else if(name.equals("Troll")){
			return new Troll();
		} else if(name.equals("Dragon")){
			return new Dragon();
		} else {		
			return new Starter();
		}
	}
}
