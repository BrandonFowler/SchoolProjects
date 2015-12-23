
//Authors: Brandon Fowler, James White, Zach Lontz
//Class CSCD350
//Quarter: Spring 2014
//Group Project

package TriviaMaze_4F_CSCD350;

import static org.junit.Assert.assertNotNull;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

//Test if a trivia question can be created by accessing database
public class TriviaQuestionTest {

	TriviaQuestion question ;
	int[] temp;
	
	@Before
	public void setUp() throws Exception {
		
		temp = new int[40];
		for(int i = 0; i < temp.length; i++){
			temp[i] = 0;
		}
	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void test() {
		question = new TriviaQuestion(3,temp);
		assertNotNull(question.question);
		assertNotNull(question.o1);
		assertNotNull(question.o2);
		assertNotNull(question.o3);
		assertNotNull(question.o4);
		assertNotNull(question.answer);
	}

}
