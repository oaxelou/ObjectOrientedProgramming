/*  Authors:    Patsianotakis Charalampos cpatsianotakis@inf.uth.gr
*               Axelou Olympia            oaxelou@inf.uth.gr
*
*	package ce325.hw3, Sudoku Project
*   SudokuAction class:
*
*	Class representing actions user does in the game.
*	Usage is to store actions, so that they are not wasted.
*
*	Constructor's arguments are:
*		-> int i, j, k, m representing dimensions of box.
*		-> String preVal representing pre value of box where action
*		   occured.
*		-> String nextVal representing next value of box where action
*		   occured.
*
*	* int [] getDimensions() function returns a table of integers,
*	representing dimensions of box  where action occured.
*
*	* String getNextValue() function returns the value added while
*	action occured.
*
*	* String getPreValue() function returns the value replaced while
*	action occured.
*
*/

package ce325.hw3;

public class SudokuAction{

	private int i;
	private int j;
	private int k;
	private int m;
	private String preVal;
	private String nextVal;

	public SudokuAction(int i, int j, int k, int m, String preVal, String nextVal){
		this.i = i;
		this.j = j;
		this.k = k;
		this.m = m;
		this.preVal = preVal;
		this.nextVal = nextVal;
	}

	public int [] getDimensions(){
		int [] dimensions = new int[4];
		dimensions[0] = i;
		dimensions[1] = j;
		dimensions[2] = k;
		dimensions[3] = m;

		return dimensions;
	}

	public String getNextValue(){
		return nextVal;
	}

	public String getPreValue(){
		return preVal;
	}

}