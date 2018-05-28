import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.swing.filechooser.*;
import java.lang.*;
import java.util.*;

public class  SudokuSolutionFinder{

	private String [] finalSolution;

	private char [][][][] strSolution;
	private boolean [][] defaultVal;

	public SudokuSolutionFinder(JButton [][][][] button, boolean [][] defaultVal){

		char counter = '1';

		this.defaultVal = defaultVal;

		strSolution = new char[3][3][3][3];
		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++){
				for(int k=0; k<3; k++){
					for(int m=0; m<3; m++){
						strSolution[i][j][k][m] = button[i][j][k][m].getText().charAt(0);
					}
				}
			}
		}

		while(!addTable(counter, 0, 0, 0, 0)){
			System.out.println("Returned to begining with counter :" + counter);
			counter++;
		}

		makefinalSolution();
		
	}

	private void makefinalSolution(){

		finalSolution = new String[9];
		String temp0, temp1, temp2;

		for(int i=0; i < 3; i++){
 			for(int j=0; j< 3; j++){
 				for(int k=0; k<3; k++){
 					for(int m=0; m<3; m++){

 						if(strSolution[i][j][k][m] == ' ')
		 					strSolution[i][j][k][m] = '.';

					}
 				}
 			}
	 		
	 	}

	 	for(int i=0; i<3; i++){
	 		for(int k = 0; k <3; k++){

	 			temp0 = "" + strSolution[i][0][k][0] + strSolution[i][0][k][1] + strSolution[i][0][k][2];
	 			temp1 = "" + strSolution[i][1][k][0] + strSolution[i][1][k][1] + strSolution[i][1][k][2];
	 			temp2 = "" + strSolution[i][2][k][0] + strSolution[i][2][k][1] + strSolution[i][2][k][2];
	 			finalSolution[i*3 + k] = temp0 + temp1 + temp2; 
	 		}
	 	}
	}

	private boolean addTable(char value, int iPos, int jPos, int kPos, int mPos){

		char counter = '1';

		//System.out.print("Value: " + value + " , iPos: " + iPos + " , jPos: " + jPos + " , kPos: " + kPos + " , mPos: " + mPos);

		if(!defaultVal[iPos*3+kPos][jPos*3+mPos]){

			//System.out.print(" NOT DEFAULT ");
			if(isLegal(value, iPos, jPos, kPos, mPos)){

				//System.out.println(" IS LEGAL ");
				
				strSolution[iPos][jPos][kPos][mPos] = value;
				

				if(iPos == 2 && jPos == 2 && kPos == 2 && mPos == 2){
					return true;
				}
				else if(mPos < 2){
						while(!addTable(counter, iPos, jPos, kPos, mPos+1)){
							counter++;
							if(counter > '9' ){
								strSolution[iPos][jPos][kPos][mPos] = ' ';
								return false;
							}
						}
						return true;


				}
				else if(kPos < 2){
						while(!addTable(counter, iPos, jPos, kPos+1, 0)){
							counter++;
							if(counter > '9' ){
								strSolution[iPos][jPos][kPos][mPos] = ' ';
								return false;
							}
						}
						return true;
				}
				else if(jPos < 2){
						while(!addTable(counter, iPos, jPos+1, 0, 0)){
							counter++;
							if(counter > '9' ){
								strSolution[iPos][jPos][kPos][mPos] = ' ';
								return false;
							}
						}
						return true;

				}
				else {
					while(!addTable(counter, iPos+1, 0, 0, 0)){
						counter++;
						if(counter > '9' ){
							strSolution[iPos][jPos][kPos][mPos] = ' ';
							return false;
						}
					}
					return true;
				}
			}
			else{
				//System.out.println(" IS NOT LEGAL ");
				return false;
			}
		}
		else{ //IS DEFAULT VALUE CANNOT BE CHANGED

			//System.out.print(" DEFAULT     ");

			//System.out.println(" IS LEGAL ");		

			if(iPos == 2 && jPos == 2 && kPos == 2 && mPos == 2){
				return true;
			}
			else if(mPos < 2){
				while(!addTable(counter, iPos, jPos, kPos, mPos+1) ) {
					counter++;
					if(counter > '9' ){
						return false;
					}
				}
				return true;

			}
			else if(kPos < 2){
				while(!addTable(counter, iPos, jPos, kPos+1, 0)){
					counter++;
					if(counter > '9' ){
						return false;
					}
				}
				return true;

			}
			else if(jPos < 2){
				while(!addTable(counter, iPos, jPos+1, 0, 0)){
					counter++;
					if(counter > '9' ){
						return false;
					}
				}
				return true;

			}
			else {
				while(!addTable(counter, iPos+1, 0, 0, 0)){
					counter++;
					if(counter > '9' ){
						return false;
					}
				}
				return true;

			}
		}
		
	}

	private boolean isLegal(char value, int iPos, int jPos, int kPos, int mPos){

		if(value == ' ') return true;

		for(int k = 0; k <3; k++){
			for(int m = 0; m < 3; m++){
				if(value == strSolution[iPos][jPos][k][m] && !(k == kPos && m == mPos) ) 
					return false;
			}
		}

		for(int i = 0; i <3; i++){
			for(int k = 0; k < 3; k++){
				if(value == strSolution[i][jPos][k][mPos] && !(i == iPos && k == kPos) )
					return false;
			}
		}

		for(int j = 0; j <3; j++){
			for(int m = 0; m < 3; m++){
				if(value == strSolution[iPos][j][kPos][m] && !(j == jPos && m == mPos))
					return false;
			}
		}

		return true;

	}

	public String [] getSolution(){
		return finalSolution;
	}
}