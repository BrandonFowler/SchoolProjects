package Inventory.Equipables.Sets;

public abstract class Set implements ISetBonus{
	protected String setName;
	protected double setBonus;
	protected int baseArmorValue;

	public double getSetArmorBonus() {		
		return setBonus;
	}
	
	public double calculateSetArmorBonus(int numPieces){
		double currentSetBonus = 1;
		if(numPieces > 4){
			currentSetBonus += (setBonus * 1.4);
		} else if(numPieces > 1){
			currentSetBonus += (setBonus * .6);
		}
		return currentSetBonus;
	}
	
	public int getBaseArmorValue(){
		return baseArmorValue;
	}
	
	public String getSetName(){
		return setName;
	}
}
