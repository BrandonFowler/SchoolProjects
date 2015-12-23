
//Authors: Brandon Fowler, James White, Zach Lontz
//Class CSCD350
//Quarter: Spring 2014
//Group Project

package TriviaMaze_4F_CSCD350;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

//Tests Manager class set up values and methods that run without user input
public class MangerTest {

	Manager test;
	
	@Before
	public void setUp() throws Exception {
		
		test = new Manager("S","Bob");
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testPlayerPosition() {
		assertEquals(0,test.playerRow);
		assertEquals(0,test.playerRow);
	}
	
	@Test
	public void testEnemyPosition() {
		assertEquals(0,test.enemyRow);
		assertEquals(2,test.enemyCol);
		test = new Manager("M","Bob");
		assertEquals(0,test.enemyRow);
		assertEquals(3,test.enemyCol);
		test = new Manager("L","Bob");
		assertEquals(0,test.enemyRow);
		assertEquals(4,test.enemyCol);
	}
	
	@Test
	public void testUsedIDsInitialized() {
		for(int i = 0; i < test.usedIDs.length; i++){
			assertEquals(0,test.usedIDs[i]);
		}
	}
	
	@Test
	public void testPlayerNameSet() {
		assertEquals(test.player.getName(),"Bob");
	}
	
	@Test
	public void testMethods() {
		test.moveEnemy();
		test.printRules();
	}
}
