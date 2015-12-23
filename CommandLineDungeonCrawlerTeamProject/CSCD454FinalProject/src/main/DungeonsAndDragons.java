/* Team: Larfleeze
 * Members: Nathan Graham, Matt Wilhelm, Brandon Fowler
 * Final project
 */

package main;

import java.util.InputMismatchException;
import java.util.Scanner;

import dungeon.DunBuilderDirector;
import dungeon.Dungeon;

public class DungeonsAndDragons {

	public static void main(String[] args) {
			int difficulty = obtainDifficulty();
			DunBuilderDirector dunCreator = new DunBuilderDirector();
			Dungeon dungeon = dunCreator.construct(difficulty);
			GameCore curGame = GameCore.getGameCore(dungeon);
			curGame.play();
			new Scanner(System.in).nextLine(); //pause the game before it just exits.
	}

	private static boolean askKeepPlaying() {
		Scanner cin = new Scanner(System.in);
		
		System.out.print("If you would like to play another game enter continue.  Enter anything else to quit.");
		if(cin.nextLine().equalsIgnoreCase("continue")) {
			return true;
		}
		
		return false;
	}

	private static int obtainDifficulty() {
		@SuppressWarnings("resource")
		Scanner cin = new Scanner(System.in);
		boolean cInput = false;
		int choice = 0;

		while (!cInput) {
			System.out
					.println("Enter a number associated with a difficulty from the list  below.");
			System.out.println("\n1. Easy\n2. Medium\n3. Normal\n4. Hard");
			try {
				choice = cin.nextInt();
			} catch (InputMismatchException e) {
				System.out
						.println("You must enter a number corresponding to a listed difficulty!");
			}
			if (choice > 0 && choice < 5) {
				cInput = true;
			} else {
				System.out
						.println("You must enter a number corresponding to a listed difficulty!");
			}
		}
		return choice;
	}
}
