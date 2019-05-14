package model;

import java.util.LinkedList;

/**
 * Implementation of Fibonacci Interface. Contains the Logic of the game.
 * @author szabo
 *
 */
public class Fibonacci_impl implements Fibonacci {
	/**
	 * represents the cells of the gameboard.
	 */
	public int fields[][];
	/**
	 * Has the victory condition been met?
	 */
	private boolean won = false;
	/**
	 * Has the game been lost?
	 */
	private boolean lost = false;
	/**
	 * Implements The interface's NewGame() method.
	 */
	public final void newGame() {
		fields = new int[4][4];
		for(int i =0; i<4; i++)
			for(int j=0; j<4; j++) {
				fields[i][j]=0;
			}
		int firstX=(int)(Math.random()*4);
		int firstY=(int)(Math.random()*4);
		int secondX;
		int secondY;
		do {
			secondX=(int)(Math.random()*4);
			secondY=(int)(Math.random()*4);
		}while((firstX==secondX) && (firstY==secondY));
		fields[secondX][secondY]=1;
		fields[firstX][firstY]=1;
		won=false;
		lost = false;
	}
	/**
	 * Rotates the Ma matrix clockwise No times;
	 * @param Ma The Matrix to be rotated.
	 * @param No The number of times that it needs to be rotated.
	 * @return The rotated matrix.
	 */
	private int[][] RotateMatrix(int[][]Ma, int No){

		for	(int n=0; n<No; n++)
	 for (int x = 0; x < 2; x++) 
	 { 
		 for (int y = x; y < 4-x-1; y++) 
		 	{ 
			 	int temp = Ma[x][y]; 
			 	Ma[x][y] = Ma[y][3-x];
			 	Ma[y][3-x] = Ma[3-x][3-y]; 
			 	Ma[3-x][3-y] = Ma[3-y][x]; 	 
			 	Ma[3-y][x] = temp; 
		 	}
	 }
		return Ma;
	}
	/**
	 * Ads filler 0s to the List to the indicated size;
	 * @param line The list to be lengthened.
	 * @param s the desired list length.
	 */
	private static void FillUpLine(LinkedList<Integer> line, int s) {
		while (line.size() != s) {
			line.addLast(0);
	 }
	}
	/**
	 *Pushes all valid value to the right side of the matrix 
	 * @param Mat The matrix to be modified.
	 * @return The matrix with all non0 values on the right side
	 */
	private int[][] PushField(int Mat[][]){
		int temp[][]=new int[4][4];
		for(int i=0; i<4 ; i++) {
			LinkedList<Integer> line = new LinkedList<Integer>();
			for	(int j=0; j<4; j++) {
				if(Mat[i][j]>0) {
					line.addLast(Mat[i][j]);
				}
			}
			FillUpLine(line, 4);
			for (int j = 0; j < 4; j++) {
				temp[i][j] = line.removeFirst();
			}
		}
		return temp;
	}
	/**
	 * Check if 2 cells can be merged
	 * @param a the value of one cell.
	 * @param b The value of the other cell.
	 * @return true if can merge, false if not.
	 */
	private boolean CanMerge(int a, int b) {
		if(a*b==0)
			return false;
		else if(a*b==1)
			return true;
		else if(a==b)
			return false;
		else if(Math.abs(a-b)==1)
			return true;
		else
		return false;
	}
	/**
	 * MErges the fields.
	 * @param Mat The Matrix to be transformed.
	 * @return The Transformed matrix.
	 */
	private int[][] MergeFields(int Mat[][]){
		int temp[][]=new int[4][4];
		for(int i=0; i<4 ; i++) {
			LinkedList<Integer> line = new LinkedList<Integer>();
			if(CanMerge(Mat[i][0],Mat[i][1])) {
				line.add(Math.max(Mat[i][0],Mat[i][1])+1);
				if(CanMerge(Mat[i][2],Mat[i][3])) {
					line.add(Math.max(Mat[i][2],Mat[i][3])+1);
				}else {
					line.add(Mat[i][2]);
					line.add(Mat[i][3]);
				}
			}else {
				line.add(Mat[i][0]);
				if(CanMerge(Mat[i][1],Mat[i][2])) {
					line.add(Math.max(Mat[i][1],Mat[i][2])+1);
					line.add(Mat[i][3]);
				}else {
					line.add(Mat[i][1]);
					if(CanMerge(Mat[i][2],Mat[i][3])) {
						line.add(Math.max(Mat[i][2],Mat[i][3])+1);
					}else {
						line.add(Mat[i][2]);
						line.add(Mat[i][3]);
					}
				}
			}
			
			FillUpLine(line, 4);
			for (int j = 0; j < 4; j++) {
				temp[i][j] = line.removeFirst();
			}
		}		
		return temp;
	}
	/**
	 * Places a new '1' value on the board.
	 * @param Mat The Matrix representing the current gamestate
	 * @return The Matrix wit one '1' cell added. 
	 */
	private int[][] PutNew(int Mat[][]){
		boolean vanures=false;
		for(int i=0; i<4; i++)
			for(int j=0; j<4; j++)
				if(Mat[i][j]==0)
					vanures=true;
		if(vanures) {
			int newX;
			int newY;
			do {
				newX=(int)(Math.random()*4);
				newY=(int)(Math.random()*4);
			}while(Mat[newX][newY]!=0);
				Mat[newX][newY]=1;
				return Mat;
		}else {
			lost=true;
			return Mat;
		}
		
	}
	/**
	 * Executes Cell-moving and merging transformations
	 * @param Mat The Matrix representing the current game-state
	 * @return The altered Matrix
	 */
	private int[][] MoveTrasformations(int Mat[][]){
		int tmp[][]=Mat;
		tmp=PushField(tmp);
		tmp=MergeFields(tmp);
		return tmp;
	}
	/**
	 * Check if victory condition has been met and set the won variable accordingly.
	 */
	private void checkstate() {
		if(!won)
		for(int i =0; i<4; i++)
			for(int j=0; j<4; j++) {
				if(fields[i][j]==12)
					won=true;
			}
	}
	
	public void rightMove() {
		
		int tmp[][]=fields;
		tmp=RotateMatrix(tmp,2);
		if(tmp!=MoveTrasformations(tmp)) {
			tmp=MoveTrasformations(tmp);
			tmp=PutNew(tmp);
		}
		tmp=RotateMatrix(tmp,2);
		fields=tmp;
		checkstate();
	}

	public void leftMove() {
		int tmp[][]=fields;
		if(tmp!=MoveTrasformations(tmp)) {
			tmp=MoveTrasformations(tmp);
			tmp=PutNew(tmp);
		}		
		fields=tmp;
		checkstate();
	}

	public void upMove() {
		int tmp[][]=fields;
		tmp=RotateMatrix(tmp,1);
		if(tmp!=MoveTrasformations(tmp)) {
			tmp=MoveTrasformations(tmp);
			tmp=PutNew(tmp);
		}
		tmp=RotateMatrix(tmp,3);
		fields=tmp;
		checkstate();
	}

	public void downMove() {
		int tmp[][]=fields;
		tmp=RotateMatrix(tmp,3);
		if(tmp!=MoveTrasformations(tmp)) {
			tmp=MoveTrasformations(tmp);
			tmp=PutNew(tmp);
		}
		tmp=RotateMatrix(tmp,1);
		fields=tmp;
		checkstate();
	}

	public int getCell(int i, int j) {
		return fields[i][j];
	}

	public boolean getwin() {
		return won;
	}
	
	public boolean getLose() {
		return lost;
	}

}

