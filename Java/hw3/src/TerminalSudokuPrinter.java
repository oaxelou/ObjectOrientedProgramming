import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.swing.filechooser.*;
import java.lang.*;
import java.util.*;

public class TerminalSudokuPrinter{

	String [][] Strbutton;

	public TerminalSudokuPrinter(JButton [][][][] button, boolean [][] defaultTable){

		resetTable(button, defaultTable);

	}

	public TerminalSudokuPrinter(String [] strNums, boolean [][] defaultTable){

		resetTable(strNums, defaultTable);

	}

	public void resetTable(String [] strNums, boolean [][] defaultTable){

		Strbutton = new String[9][9];

		for(int i=0; i<9; i++){
			for(int j=0; j<9; j++){

				if(defaultTable[i][j])
					Strbutton[i][j] = "//"+strNums[i].charAt(j)+"//";
				else
					Strbutton[i][j] = "  "+strNums[i].charAt(j)+"  ";
			}
		}

	}



	public void resetTable(JButton [][][][] button, boolean [][] defaultTable){

		Strbutton = new String[9][9];

		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++){
				for(int k=0; k<3; k++){
					for(int m=0; m<3; m++){

						if(defaultTable[i*3+k][j*3+m])
							Strbutton[i*3+k][j*3+m] = "//"+button[i][j][k][m].getText()+"//";
						else
							Strbutton[i*3+k][j*3+m] = "  "+button[i][j][k][m].getText()+"  ";
					}
				}
			}
		}

	}

	public void resetTable(JButton [][][][] button, boolean [][] defaultTable, int iMarked, int jMarked, int kMarked, int mMarked){

		Strbutton = new String[9][9];

		for(int i=0; i<3; i++){
			for(int j=0; j<3; j++){
				for(int k=0; k<3; k++){
					for(int m=0; m<3; m++){

						if(defaultTable[i*3+k][j*3+m]){
							
							if(i == iMarked && j == jMarked && k == kMarked && m == mMarked)
								Strbutton[i*3+k][j*3+m] = "/-"+button[i][j][k][m].getText()+"-/";
							else
								Strbutton[i*3+k][j*3+m] = "//"+button[i][j][k][m].getText()+"//";
						}
						else{
							if(i == iMarked && j == jMarked && k == kMarked && m == mMarked)
								Strbutton[i*3+k][j*3+m] = " -"+button[i][j][k][m].getText()+"- ";
							else
								Strbutton[i*3+k][j*3+m] = "  "+button[i][j][k][m].getText()+"  ";
						}
					}
				}
			}
		}

	}

	public void printTable(){
		System.out.println( toString() );
	}

	public void toFile(String fileStr){

		File file = new File(fileStr);

		try (FileWriter fWriter = new FileWriter(file, false)) {
      		fWriter.write(toString());
    	}catch(IOException ex){
    	  System.err.println("Something went wrong with FileWriter");
    	  System.exit(1);
    	}
	}

	public String toString(){

		String string = "";

		for(int j=0; j < 2; j++){
			string += "\\\\";
			for(int i=0 ; i < 72; i++)
				string += "=";
			string += "\\\\ \n";
		}

		for(int i = 0; i < 22; i++) string += " ";
		string += "SUDOKU TABLE PRINTING\n";

		for(int i=0; i<9; i++){

			for(int j=0; j<9; j++){
				string += "_______ ";
				if(j == 2 || j == 5)
					string += "  ";
			}
			string += "\n";

			for(int j=0; j<9; j++){
				string += "|"+Strbutton[i][j]+"| ";
				if(j == 2 || j == 5)
					string += "  ";
			}
			string += "\n";

			for(int j=0; j<9; j++){
				string += "------- ";
				if(j == 2 || j == 5)
					string += "  ";
			}
			string += "\n";
			if(i == 2 || i == 5)
					string += "\n\n";
		}

		for(int j=0; j < 2; j++){
			string += "\\\\";
			for(int i=0 ; i < 72; i++)
				string += "=";
			string += "\\\\ \n";
		}
		string += "\n\n";

		return string;

	}
}