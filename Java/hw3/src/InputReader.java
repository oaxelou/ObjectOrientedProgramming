/*  Authors:    Patsianotakis Charalampos cpatsianotakis@inf.uth.gr
*               Axelou Olympia            oaxelou@inf.uth.gr
*
*	package ce325.hw3, Sudoku Project
*   InputReader class:
*
*	Class which gets input from specified link.
*
*	Constructor's arguments is int level, which can be one of the consts:
*		-> InputReader.EASY: 		 	game mode is easy.
*		-> InputReader.INTERMEDIATE: 	game mode is intermediate.
*		-> InputReader.EXPERT: 		 	game mode is for experts.
*
*	* String[] getInput() function returns String[] which represents input for Sudoku game.
*		->Each content of the table represents a line.
*		->Each content of String represents a content of the specified line.
*		->Contents as digits represent boxes with the same digit in game.
*		->Contents as '.' represent empty boxes.
*
*/

package ce325.hw3;

import java.net.*;
import java.io.*;

public class InputReader{

	public static int EASY         = 0;
	public static int INTERMEDIATE = 1;
	public static int EXPERT       = 2;

	private static String URL_EASY = "http://gthanos.inf.uth.gr/~gthanos/sudoku/exec.php?difficulty=easy";
	private static String URL_INTERMEDIATE = "http://gthanos.inf.uth.gr/~gthanos/sudoku/exec.php?difficulty=intermediate";
	private static String URL_EXPERT = "http://gthanos.inf.uth.gr/~gthanos/sudoku/exec.php?difficulty=expert";

	String [] sudokuInput;

	public InputReader(int level){

		URL url=null;
		String inputLine;

		int i = 0;

		sudokuInput = new String[9];

		if(level == EASY){

			try {   
			      url = new URL(URL_EASY);
			      BufferedReader in = new BufferedReader( new InputStreamReader(url.openStream()) );
			      while ((inputLine = in.readLine()) != null && i < 9) {
			        sudokuInput[i] = inputLine;
			        i++;
			      }
			      in.close();
  			}
  			catch(MalformedURLException ex) {
  				System.out.println("Malformed URL");      
    		}
    		catch(IOException ex) {
      			System.out.println("Error while reading from URL: "+url.toString() );
      		}
      		catch(Exception ex){
      			System.out.println("Other exception occured: " + ex.toString());
      		}
    	}  
		if(level == INTERMEDIATE){

			try {   
			      url = new URL(URL_INTERMEDIATE);
			      BufferedReader in = new BufferedReader( new InputStreamReader(url.openStream()) );
			      while ((inputLine = in.readLine()) != null && i < 9) {
			        sudokuInput[i] = inputLine;
			        i++;
			      }
			      in.close();
  			}
  			catch(MalformedURLException ex) {
  				System.out.println("Malformed URL");      
    		}
    		catch(IOException ex) {
      			System.out.println("Error while reading from URL: "+url.toString() );
      		}
      		catch(Exception ex){
      			System.out.println("Other exception occured: " + ex.toString());
      		}
    	}
		if(level == EXPERT){

			try {   
			      url = new URL(URL_EXPERT);
			      BufferedReader in = new BufferedReader( new InputStreamReader(url.openStream()) );
			      while ((inputLine = in.readLine()) != null && i < 9) {
			        sudokuInput[i] = inputLine;
			        i++;
			      }
			      in.close();
  			}
  			catch(MalformedURLException ex) {
  				System.out.println("Malformed URL");      
    		}
    		catch(IOException ex) {
      			System.out.println("Error while reading from URL: "+url.toString() );
      		}
      		catch(Exception ex){
      			System.out.println("Other exception occured: " + ex.toString());
      		}
    	}
     
	}



	String [] getInput(){

		for(int i=0; i < 9; i++){
			System.out.println(sudokuInput[i]);
		}

		return sudokuInput;
	}
}