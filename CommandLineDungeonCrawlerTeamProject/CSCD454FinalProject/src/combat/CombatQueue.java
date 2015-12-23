package combat;

import java.util.ArrayList;
import Party.Party;
import character.Character;

public class CombatQueue {
	private ArrayList<SpeedCountWrapper> combatCharacters;
	private ArrayList<Character> attackingChars;
	
	public CombatQueue(Party goodParty, Party badParty) {
		this.combatCharacters = new ArrayList<SpeedCountWrapper>();
		this.attackingChars = new ArrayList<Character>();
		fillChars(goodParty, badParty);
	}
	//helper for constructor
	private void fillChars(Party goodParty, Party badParty) {
		
		for(Character cur: goodParty){
			this.combatCharacters.add(new SpeedCountWrapper(cur));
		}
		for(Character cur: badParty){
			this.combatCharacters.add(new SpeedCountWrapper(cur));
		}
	}
	
	private void addAttackingChar(Character character) {
		this.attackingChars.add(character);
	}
	
	public ArrayList<Character> tick() {
		this.attackingChars.clear();
		while(attackingChars.isEmpty()){
			for(int i = 0; i < this.combatCharacters.size(); i++) {
				this.combatCharacters.get(i).incrementSpeedCount();
			}
		}
		return this.attackingChars;
	}
	
	private class SpeedCountWrapper {
		private Character character;
		private int speedCount;
		private SpeedCountWrapper(Character character) {
			this.character = character;
			this.speedCount = 0;
		}
		
		private void incrementSpeedCount() {
			this.speedCount += this.character.getSpeed();
			if(this.speedCount > 100) {
				this.speedCount = speedCount % 100;
				if(this.character.isAlive()){
					addAttackingChar(this.character);
				}
			}
		}
	}
}
