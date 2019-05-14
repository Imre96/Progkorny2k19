package Model;

import static org.junit.Assert.*;

import java.lang.reflect.Method;

import org.junit.Test;
import org.pmw.tinylog.Logger;

import Model.fibonacci_impl;
public class FibonacciTest {
	
	fibonacci_impl Game = new fibonacci_impl();
	
	@Test
	public void test() {
		newGameTest();
		MoveTest();
		MergeTest();
		StateTest();
		//fail("Not yet implemented");
	}
	
	void newGameTest() {
		Game.newGame();
		int db=0;
		for(int i=0; i<4;i++) {
			for(int j=0; j<4; j++) {
				db+=Game.getCell(i, j);
			}
		}
	 	assertEquals(2,db);
	}
	
	void MoveTest() {
		Game.newGame();
		Game.leftMove();
		Game.downMove();
		Game.rightMove();
		Game.upMove();
	}
	
	void MergeTest() {
		try {
		Method method = fibonacci_impl.class.getDeclaredMethod("MergeFields", int[][].class);
		method.setAccessible(true);
		int[][] testsor= {{1,1,2,2},{1,2,1,1},{0,1,1,0},{0,0,1,1}};
		int[][] testedmatrix = (int[][]) method.invoke(Game, testsor);
		int[][] tmp = {{2,2,2,0},{3,2,0,0},{0,2,0,0},{0,0,2,0}};
		int[] fifthcase= {0,0,0,0};
		for(int i=0; i<4; i++)
			assertArrayEquals(tmp[i],testedmatrix[i]);
		testsor[0]=fifthcase;
		testedmatrix = (int[][]) method.invoke(Game, testsor);
		assertArrayEquals(fifthcase,testedmatrix[0]);
		}catch(Exception e) {
			Logger.warn(e.getMessage());
			Logger.warn(e.toString());
		}
	}
	
	void StateTest() {
		Game.newGame();
	 	assertTrue(!Game.getLose());
	 	assertTrue(!Game.getwin());
	 	Game.fields[0][0]=12;
	 	try {
		Method method = fibonacci_impl.class.getDeclaredMethod("checkstate");
		method.setAccessible(true);
		method.invoke(Game);
	 	assertTrue(Game.getwin());
		Method putnew = fibonacci_impl.class.getDeclaredMethod("PutNew",int[][].class);
		int[][] fullmatrix = {{1,1,1,1},{1,1,1,1},{1,1,1,1},{1,1,1,1}};
		int[][] fullmatrix2 = {{1,1,0,1},{1,1,0,1},{1,1,0,1},{1,1,0,1}};
		putnew.setAccessible(true);
		putnew.invoke(Game,Game.fields);
		assertTrue(!Game.getLose());
		putnew.invoke(Game,fullmatrix);
	 	assertTrue(Game.getLose());
	 	}catch(Exception e) {
			Logger.warn(e.getMessage());
			Logger.warn(e.toString());

	 	}
	}
}
