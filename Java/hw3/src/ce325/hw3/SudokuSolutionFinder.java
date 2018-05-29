/*  Authors:    Patsianotakis Charalampos cpatsianotakis@inf.uth.gr
*               Axelou Olympia            oaxelou@inf.uth.gr
*
*	package ce325.hw3, Sudoku Project
*   SudokuSolutionFinder class:
*
*	Class which finds solution os Sudoku game.
*	Constructor's arguments are:
*		-> JButton [][][][] button (boxes from Sudoku main class)
*		-> boolean [][] defaultVal (shows where values are filled from input)
*
*	Constructor finds the solution and saves it in String [] mode.
*
*	* String getSolution() function returns solution in the same formula
*	of input given from the web.
*   
*/

package ce325.hw3;

import javax.swing.*;

public class  SudokuSolutionFinder{

	private String [] finalSolution;

	private String [][][][] strSolution;
	private boolean [][] defaultVal;

	public SudokuSolutionFinder(JButton [][][][] button, boolean [][] defaultVal){

		char counter = '1';

		this.defaultVal = defaultVal;

		//Making it in an easier to work data structure
		strSolution = new String[3][3][3][3];
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++){
				for(int k=0; k<3; k++){
					for(int m=0; m<3; m++){
						strSolution[i][j][k][m] = button[i][j][k][m].getText();
					}
				}
			}
		}
		//call recursive function
		solveTable(0, 0, 0, 0);
			
		//make solution in correct formula
		makefinalSolution();
		
	}

	private void makefinalSolution(){

		finalSolution = new String[9];
		String temp0, temp1, temp2;

		for(int i=0; i < 3; i++){
 			for(int j=0; j< 3; j++){
 				for(int k=0; k<3; k++){
 					for(int m=0; m<3; m++){

 						if(strSolution[i][j][k][m] == " ")
		 					strSolution[i][j][k][m] = ".";

					}
 				}
 			}
	 		
	 	}

	 	for(int i=0; i<3; i++){
	 		for(int k = 0; k <3; k++){

	 			temp0 = strSolution[i][0][k][0] + strSolution[i][0][k][1] + strSolution[i][0][k][2];
	 			temp1 = strSolution[i][1][k][0] + strSolution[i][1][k][1] + strSolution[i][1][k][2];
	 			temp2 = strSolution[i][2][k][0] + strSolution[i][2][k][1] + strSolution[i][2][k][2];
	 			finalSolution[i*3 + k] = temp0 + temp1 + temp2; 
	 		}
	 	}
	}

	private boolean solveTable(int iPos, int jPos, int kPos, int mPos){

		if(mPos == 3){
			
			mPos = 0;
			if(++kPos == 3){

				kPos = 0;
				if(++jPos == 3){

					jPos = 0;
					if(++iPos == 3){
						return true;
					}
				}
			}
		}

		//This content should not be changed
		if(defaultVal[iPos*3 + kPos][jPos*3 + mPos])
			return solveTable(iPos, jPos, kPos, mPos+1);

		for( char counterVal = '1'; counterVal <= '9'; counterVal++){

			if(isLegal(counterVal, iPos, jPos, kPos, mPos)){

				//New entry should be legal
				strSolution[iPos][jPos][kPos][mPos] = counterVal + "";
				if( solveTable(iPos, jPos, kPos, mPos+1) ){
					return true;
				}
			}

		}
		//If none entry is legal, we should change values of pre boxes
		strSolution[iPos][jPos][kPos][mPos] = " ";
		return false;
		

		
	}

	private boolean isLegal(char value, int iPos, int jPos, int kPos, int mPos){

		if(value == ' ' || value == '.') return true;

		//check in same box
		for(int k = 0; k <3; k++){
			for(int m = 0; m < 3; m++){
				if(value == strSolution[iPos][jPos][k][m].charAt(0) && !(k == kPos && m == mPos) ) 
					return false;
			}
		}

		//check in same line
		for(int i = 0; i <3; i++){
			for(int k = 0; k < 3; k++){
				if(value == strSolution[i][jPos][k][mPos].charAt(0) && !(i == iPos && k == kPos) )
					return false;
			}
		}

		//check in same column
		for(int j = 0; j <3; j++){
			for(int m = 0; m < 3; m++){
				if(value == strSolution[iPos][j][kPos][m].charAt(0) && !(j == jPos && m == mPos))
					return false;
			}
		}

		return true;

	}

	public String [] getSolution(){
		return finalSolution;
	}
}