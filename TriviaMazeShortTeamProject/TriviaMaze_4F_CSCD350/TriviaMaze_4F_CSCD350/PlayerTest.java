
//Authors: Brandon Fowler, James White, Zach Lontz
//Class CSCD350
//Quarter: Spring 2014
//Group Project

package TriviaMaze_4F_CSCD350;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

//Test player variable set up and gets/sets
public class PlayerTest {

	Player player;
	
	@Before
	public void setUp() throws Exception {
		player = new Player("Bob");
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void test() {
		assertEquals("Bob",player.getName());
		player.setName("Alice");
		assertEquals("Alice",player.getName());
		player.setKeys(5);
		assertEquals(5,player.getKeys());
		player.setLifeLines(5);
		assertEquals(5,player.getLifeLines());
	}

}
