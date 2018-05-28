import java.net.*;
import java.io.*;

public class InputReader{

	private static int EASY         = 0;
	private static int INTERMEDIATE = 1;
	private static int EXPERT       = 2;

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