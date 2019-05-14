package model;
/**
 * Interface for the 2048 Fibonacci game.
 * @author Szabo Imre
 *
 */
public interface Fibonacci {
	/**
	 * Sets the game to the start state.
	 */
	void newGame();
	/**
	 * executes move to the Right direction.
	 */
	void rightMove();
	/**
	 * executes move to the left direction.
	 */
	void leftMove();
	/**
	 * executes move to the up direction.
	 */
	void upMove();
	/**
	 * executes move to the down direction.
	 */
	void downMove();
	/**
	 * Returns the value of the indicated cell.
	 * @param i the x parameter [0,3]
	 * @param j the y parameter [0,3]
	 * @return the value of the cell
	 */
	int getCell(int i, int j);
	/**
	 * Checks if the condition for victory has been met.
	 * @return true if victory have been achieved, false otherwise;
	 */
	boolean getwin();
	/**
	 * Checks if the game has been lost.
	 * @return true if the game is lost, false otherwise;
	 */
	boolean getLose();
}
