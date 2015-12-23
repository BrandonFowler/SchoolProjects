
//Authors: Brandon Fowler, James White, Zach Lontz
//Class CSCD350
//Quarter: Spring 2014
//Group Project

package TriviaMaze_4F_CSCD350;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

//Tests Maze set up values and methods that do not require user input
public class MazeTest {

	Maze maze;
	
	@Before
	public void setUp() throws Exception {
		
		maze = new Maze("S");
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testSize() {
		assertEquals(3,maze.rows);
		assertEquals(3,maze.cols);
		maze = new Maze("M");
		assertEquals(4,maze.rows);
		assertEquals(4,maze.cols);
		maze = new Maze("L");
		assertEquals(5,maze.rows);
		assertEquals(5,maze.cols);
	}
	
	@Test
	public void testTraversalAndLockDoorsAndKeyUse() {
		assertTrue(maze.startTraversal(0, 0));
		maze.lockDoors(0,0,"E");
		maze.lockDoors(0,0,"S");
		assertFalse(maze.startTraversal(0, 0));
		maze.useKey(0,0,"S");
		assertTrue(maze.startTraversal(0, 0));
	}
	
	@Test
	public void testValidMovement() {
		assertTrue(maze.canMove(0, 0,"E"));
		maze.lockDoors(0,0,"E");
		assertFalse(maze.canMove(0, 0,"E"));
		assertFalse(maze.canMove(0, 0,"W"));
		assertFalse(maze.canMove(0, 2,"E"));
		assertFalse(maze.canMove(2, 0,"S"));
		assertFalse(maze.canMove(0, 0,"N"));
	}
	
	@Test
	public void testEnd() {
		assertTrue(maze.checkEnd(2,2));
		assertFalse(maze.checkEnd(1,2));
	}
	
	@Test
	public void testVisisted() {
		assertFalse(maze.checkVisited(1,1,"E"));
		maze.setVisited(1, 2, 1);
		assertTrue(maze.checkVisited(1,1,"E"));	
	}
	
	@Test
	public void testToString() {
		System.out.println(maze);
	}

}
