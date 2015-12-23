package Inventory.Consumables;

import Inventory.Item;

public abstract class Consumable extends Item {
	public abstract int consume();
	public abstract String getType();
}
