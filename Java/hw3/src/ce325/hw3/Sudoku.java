/*  Authors:    Patsianotakis Charalampos cpatsianotakis@inf.uth.gr
*               Axelou Olympia            oaxelou@inf.uth.gr
*
*	package ce325.hw3, Sudoku Project
*   InputReader class:
*
*	Main class of project. Includes main function as main frame.
*
*	Implements ActionListener
*
*/

package ce325.hw3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import java.io.*;
import javax.swing.filechooser.*;
import java.lang.*;
import java.util.*;
import javax.swing.border.*;

public class Sudoku implements ActionListener{
  	private static final int WIDTH = 400;
  	private static final int HEIGHT = 1000;
  	private static final String LINK_IMAGES = "../images/";

	private static int BUT_INITIALIZE     = 0;
	private static int BUT_PUSHED         = 1;
	private static int BUT_NUMPUSHED      = 2;
	private static int BUT_SHOWSOL        = 3;

  	private JFrame frame;
  	private Panel boxArea, choisesArea; 

  	private JButton [][][][] button;
  	private JButton [] numButton;
  	private JButton rubikButton, eraserButton, undoButton;
  	private JCheckBox verifySolution;

  	private JMenuBar bar;
  	private JMenu NewGameMenu;
  	private JMenuItem EasyMenu, InterMediateMenu, ExpertMenu;

  	private String [] input;
  	private InputReader inReader;

  	private boolean [][] defaultVal;

  	private String [] solutionTable;

  	private Deque<SudokuAction> actionsDeque;

  	private int [] selectedButton;

	public Sudoku(){

		frame = new JFrame("Sudoku");

	    frame.setSize(WIDTH, HEIGHT);

	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	    frame.setJMenuBar(createMenu());

	    boxArea = new Panel();
	    choisesArea = new Panel();

	    frame.add(boxArea, BorderLayout.CENTER);
	    frame.add(choisesArea, BorderLayout.SOUTH);

	    boxArea.setLayout(new BorderLayout());

	    button = new JButton[3][3][3][3];
	    createChoisesArea();
	    createBoxArea();

	    selectedButton = new int[4];
 		selectedButton[0] = -1;
 		selectedButton[1] = -1;
 		selectedButton[2] = -1;
 		selectedButton[3] = -1;
	    
	    setDisabled();

	    frame.setVisible(true);

	    frame.pack();


	}

	private void createBoxArea(){

		JPanel [][] panel = new JPanel[3][3];
		Border lineBorder = new LineBorder(Color.CYAN);

		boxArea.setLayout( new GridLayout(3,3) );

		for(int i=0; i < 3; i++){
 			for(int j=0; j< 3; j++){
 				
 				//Make panels and put them in boxArea grid
 				panel[i][j] = new JPanel();
 				panel[i][j].setLayout( new GridLayout(3,3) );
 				panel[i][j].setBorder(lineBorder);
 				boxArea.add(panel[i][j]);

 				for(int k=0; k<3; k++){
 					for(int m=0; m<3; m++){

 						//Make buttons and put them in panels grid
 						button[i][j][k][m] = new JButton(" ");
 						panel[i][j].add(button[i][j][k][m]);
 						button[i][j][k][m].setBackground(Color.WHITE);
		 				button[i][j][k][m].addActionListener(this);

					}
 				}
 			}
	 		
	 	}
	}

	private  void createChoisesArea(){

		int IMAGE_WIDTH = 17;
		int IMAGE_HEIGHT = 12;

		int i;
		Image tempImage;
		ImageIcon tempIcon;
		
		tempIcon = new ImageIcon( LINK_IMAGES + "rubik.png");
		tempImage = tempIcon.getImage().getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT, Image.SCALE_DEFAULT);
		rubikButton = new JButton( new ImageIcon(tempImage) );
		rubikButton.setSize(20,10);
		rubikButton.addActionListener(this);

		tempIcon = new ImageIcon( LINK_IMAGES + "undo.png");
		tempImage = tempIcon.getImage().getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT, Image.SCALE_DEFAULT);
		undoButton = new JButton(new ImageIcon(tempImage));
		undoButton.setSize(20,10);
		undoButton.addActionListener(this);

		verifySolution = new JCheckBox("Verify against solution");
		verifySolution.addActionListener(this);

		tempIcon = new ImageIcon( LINK_IMAGES + "eraser.png");
		tempImage = tempIcon.getImage().getScaledInstance(IMAGE_WIDTH, IMAGE_HEIGHT, Image.SCALE_DEFAULT);
		eraserButton = new JButton(new ImageIcon(tempImage));
		eraserButton.setSize(20,10);
		eraserButton.addActionListener(this);

		numButton = new JButton[9];
		for(i = 1; i < 10; i++){
			numButton[i-1] = new JButton( String.valueOf(i) );
			numButton[i-1].setSize(20,10);
			numButton[i-1].addActionListener(this);
		}

		choisesArea.setLayout(new FlowLayout(FlowLayout.RIGHT));

		for(i = 0; i < 9; i++ ){
			choisesArea.add(numButton[i]);
		}

		choisesArea.add(eraserButton);
		choisesArea.add(undoButton);
		choisesArea.add(verifySolution);
		choisesArea.add(rubikButton);
	}

	private JMenuBar createMenu(){

		bar = new JMenuBar();

    	NewGameMenu = new JMenu("New Game");

	    EasyMenu = new JMenuItem("Easy");
	    InterMediateMenu = new JMenuItem("Intermediate");
	    ExpertMenu = new JMenuItem("Expert");

	    EasyMenu.addActionListener(this);
	    InterMediateMenu.addActionListener(this);
	    ExpertMenu.addActionListener(this);

	    NewGameMenu.add(EasyMenu);
	    NewGameMenu.add(InterMediateMenu);
	    NewGameMenu.add(ExpertMenu);

	    bar.add(NewGameMenu);

	    return bar;

  	}

  	//Calls for every object placed in frame setEnabled(true)
  	//Objects must have been created!
  	private void setEnabled(){

  		for(int i=0; i < 3; i++){
 			for(int j=0; j< 3; j++){
 				for(int k=0; k<3; k++){
 					for(int m=0; m<3; m++){
						button[i][j][k][m].setEnabled(true);
					}
 				}
 			}
	 	}
  		for(int i=0; i<9; i++){
			numButton[i].setEnabled(true);
		}
		rubikButton.setEnabled(true);
		eraserButton.setEnabled(true); 
		undoButton.setEnabled(true);
  		verifySolution.setEnabled(true);
  	}

  	//Calls for every object placed in frame setEnabled(false)
  	//Objects must have been created!
  	private void setDisabled(){
  		for(int i=0; i < 3; i++){
 			for(int j=0; j< 3; j++){
 				for(int k=0; k<3; k++){
 					for(int m=0; m<3; m++){
						button[i][j][k][m].setEnabled(false);
					}
 				}
 			}
	 	}
  		for(int i=0; i<9; i++){
			numButton[i].setEnabled(false);
		}
		rubikButton.setEnabled(false);
		eraserButton.setEnabled(false); 
		undoButton.setEnabled(false);
  		verifySolution.setEnabled(false);
  	}

  	public void actionPerformed(ActionEvent e) {

	    if(e.getSource().equals(EasyMenu) ) {

	    	initializeGame(InputReader.EASY);

	    }

	    else if(e.getSource().equals(InterMediateMenu) ) {
	     
	     	initializeGame(InputReader.INTERMEDIATE);

	    }


	    else if(e.getSource().equals(ExpertMenu) ) {
	     
	     	initializeGame(InputReader.EXPERT);

	    }
		

		for(int i=0; i < 9; i++){
	    	if(e.getSource().equals(numButton[i])){
	    		numPushed(i+1);
	    		break;
	    	}
	    }

	    for(int i=0; i<3; i++){
			for(int j=0; j<3; j++){
				for(int k=0; k<3; k++){
					for(int m=0; m<3; m++){

						if(e.getSource().equals(button[i][j][k][m])){
	    					buttonPushed(i,j,k,m);
	    					break;
	    				}

					}
				}
			}
		}

		if(e.getSource().equals(undoButton) ) {
	     	
	     	try{
	     		SudokuAction lastAction = actionsDeque.pop();
		     	int [] dim = lastAction.getDimensions();
		     	String value = lastAction.getPreValue();

		     	selectedButton[0] = dim[0];
		     	selectedButton[1] = dim[1];
		     	selectedButton[2] = dim[2];
		     	selectedButton[3] = dim[3];

		     	if(value != " "){
		     		button[dim[0]][dim[1]][dim[2]][dim[3]].setText(value);
		     		drawButtons(BUT_NUMPUSHED);
		     	}
		     	else{
		     		button[dim[0]][dim[1]][dim[2]][dim[3]].setText(" ");
		     		drawButtons(BUT_PUSHED);
		     	}

		     	if(verifySolution.isSelected())
		     		verifySolutionChecker();
		     	
		    }
		    catch(NoSuchElementException ex){}
	    }

	    else if(e.getSource().equals(verifySolution)){
	    	if(verifySolution.isSelected())
	    		verifySolutionChecker();
	    	else{
	    		drawButtons(BUT_NUMPUSHED);
	    	}
	    }
	    else if(e.getSource().equals(rubikButton)){
	    	drawButtons(BUT_SHOWSOL);
	    	
	    }
	    else if(e.getSource().equals(eraserButton)){

	 		if(selectedButton[0] != -1){

		 		String preValueStr = button[selectedButton[0]][selectedButton[1]][selectedButton[2]][selectedButton[3]].getText();
		 		SudokuAction action = new SudokuAction(selectedButton[0], selectedButton[1], selectedButton[2], selectedButton[3], preValueStr, " ");

		 		button[selectedButton[0]][selectedButton[1]][selectedButton[2]][selectedButton[3]].setText(" ");
		 		drawButtons(BUT_PUSHED);

		 		if(verifySolution.isSelected()){
		        	verifySolutionChecker();	
	       		}


		 		try{
		 			actionsDeque.push(action);
		 		}
		 		catch(Exception ex){}

 			}
	    }
   
 	}

 	//For each valued box, checks if the value equals to value of solution
 	//If the 2 values are not equal, background of box is blue 
 	private void verifySolutionChecker(){

	    for(int i=0; i<3; i++){
			for(int j=0; j<3; j++){
				for(int k=0; k<3; k++){
					for(int m=0; m<3; m++){

						if(button[i][j][k][m].getText() != " "){
	    					if(button[i][j][k][m].getText().charAt(0) != solutionTable[3*i + k].charAt(3*j + m)){

								button[i][j][k][m].setBackground(Color.BLUE);

	    					}
	    				}

					}
				}
			}
		}
 	}


 	//Sets new selected button
 	private void buttonPushed(int iPos, int jPos, int kPos, int mPos){

 		if(!defaultVal[iPos*3 + kPos][jPos*3 +mPos]){
	 		
	 		selectedButton = new int[4];
	 		selectedButton[0] = iPos;
	 		selectedButton[1] = jPos;
	 		selectedButton[2] = kPos;
	 		selectedButton[3] = mPos;
	 		drawButtons(BUT_PUSHED);
	 	}
 	}


 	//Gives new value to box, which user gives
 	private void numPushed(int num){

 		String valueStr, preValueStr;

 		if(selectedButton[0] != -1){

	 		Integer numObj = new Integer(num);
	 		valueStr = numObj.toString();

	 		preValueStr = button[selectedButton[0]][selectedButton[1]][selectedButton[2]][selectedButton[3]].getText();
	 		SudokuAction action = new SudokuAction(selectedButton[0], selectedButton[1], selectedButton[2], selectedButton[3], preValueStr, valueStr);

	 		button[selectedButton[0]][selectedButton[1]][selectedButton[2]][selectedButton[3]].setText(valueStr);
	 		drawButtons(BUT_NUMPUSHED);

	 		if(verifySolution.isSelected()){
	        	verifySolutionChecker();	
       		}


	 		try{
	 			actionsDeque.push(action);
	 		}
	 		catch(Exception e){}

 		}

 	}

 	//Checks if values at iPos, jPos, kPos, mPos is Legal according values excisting the same time
 	private boolean isLegal(int iPos, int jPos, int kPos, int mPos){

 		char value = button[iPos][jPos][kPos][mPos].getText().charAt(0);
 		if(value == ' ') return true;

 		//Checks at same panel
		for(int k = 0; k <3; k++){
			for(int m = 0; m < 3; m++){
				if(value == button[iPos][jPos][k][m].getText().charAt(0) && !(k == kPos && m == mPos) ) 
					return false;
			}
		}

		//Checks at same line.
		for(int i = 0; i <3; i++){
			for(int k = 0; k < 3; k++){
				if(value == button[i][jPos][k][mPos].getText().charAt(0) && !(i == iPos && k == kPos) )
					return false;
			}
		}	

		//Checks at same column.
		for(int j = 0; j <3; j++){
			for(int m = 0; m < 3; m++){
				if(value == button[iPos][j][kPos][m].getText().charAt(0) && !(j == jPos && m == mPos))
					return false;
			}
		}

		return true;

	}

	/*
	Function called when 1 of three JMenuItem is called. 
	int mode represents the mode of game user demands to play.
	Value of mode can be one of below:
		-> InputReader.EASY: 		 	game mode is easy.
		-> InputReader.INTERMEDIATE: 	game mode is intermediate.
		-> InputReader.EXPERT: 		 	game mode is for experts.
	*/
 	private void initializeGame(int mode){

 		inReader = new InputReader(mode);
 		input = inReader.getInput();
	    setDefault();
	    drawButtons(BUT_INITIALIZE);
	    selectedButton[0] = -1;
 		selectedButton[1] = -1;
 		selectedButton[2] = -1;
 		selectedButton[3] = -1;
 		actionsDeque = new LinkedList<SudokuAction>();
	    findSolution();
	    setEnabled();
 	}

 	//Function called while game is initialized.
 	//Checks input and sets default values, which should NOT
 	//be modified during game.
 	private void setDefault(){

 		defaultVal = new boolean[9][9];


 		for(int i=0; i < 9; i++){
 			for(int j=0; j< 9; j++){

 				if(input[i].charAt(j) != '.')
 					defaultVal[i][j] = true;
 				else 
 					defaultVal[i][j] = false;

 			}
 		}
 	}

 	/*
	Function called when we want to redraw the boxArea. 
	Argument int state represents the state we are and
	in which way boxArea should be drawn.
	Value of state can be one of below:
		-> BUT_INITIALIZE: 		 	Game just started. Sets text also.

		-> BUT_SHOWSOL: 			User gives up. Shows the values of solutionTable.
									Disables frame objects. 

		-> BUT_PUSHED: 			 	Selected button just changed. Makes yellow selected
									box and if it has value, makes yellow each box with
									same value. 

		-> BUT_NUMPUSHED: 		 	New value is given at box. If value is legal, makes 
									yellow each box with same value. If value is illegal
									makes selected box red.
	*/
 	private void drawButtons(int state){

 		if(state == BUT_INITIALIZE){
	 		for(int i=0; i < 3; i++){
	 			for(int j=0; j< 3; j++){
	 				for(int k=0; k<3; k++){
	 					for(int m=0; m<3; m++){

			 				if(input[i*3 + k].charAt(j*3 + m) != '.'){
			 					button[i][j][k][m].setBackground(Color.LIGHT_GRAY);
			 					button[i][j][k][m].setText( new String( "" + input[i*3 + k].charAt(j*3 + m) ) );
			 				}
			 				else{
			 					button[i][j][k][m].setBackground(Color.WHITE);
			 					button[i][j][k][m].setText(" ");
			 				}

						}
	 				}
	 			}
	 		}
	 	}
	 	if(state == BUT_SHOWSOL){
	 		for(int i=0; i < 3; i++){
	 			for(int j=0; j< 3; j++){
	 				for(int k=0; k<3; k++){
	 					for(int m=0; m<3; m++){

			 				if(solutionTable[i*3 + k].charAt(j*3 + m) != '.'){
			 					button[i][j][k][m].setBackground(Color.LIGHT_GRAY);
			 					button[i][j][k][m].setText( new String( "" + solutionTable[i*3 + k].charAt(j*3 + m) ) );
			 				}
			 				else{
			 					button[i][j][k][m].setBackground(Color.WHITE);
			 					button[i][j][k][m].setText(" ");
			 				}

						}
	 				}
	 			}
	 		}
	 		setDisabled();
	 	}
		else if(state == BUT_PUSHED){
	 		if(button[selectedButton[0]][selectedButton[1]][selectedButton[2]][selectedButton[3]].getText() != " "){

		 		for(int i=0; i < 3; i++){
		 			for(int j=0; j< 3; j++){
		 				for(int k=0; k<3; k++){
		 					for(int m=0; m<3; m++){

	 							if(button[i][j][k][m].getText().charAt(0) == button[selectedButton[0]][selectedButton[1]][selectedButton[2]][selectedButton[3]].getText().charAt(0)){
	 								button[i][j][k][m].setBackground(new Color(255, 255, 200) );
	 							}
	 							else if( defaultVal[i*3+k][j*3+m])
	 								button[i][j][k][m].setBackground(Color.LIGHT_GRAY);
	 							else
	 								button[i][j][k][m].setBackground(Color.WHITE);
			 				}
				 				
						}
 					}
	 			}

	 		}
	 		else{

	 			for(int i=0; i < 3; i++){
		 			for(int j=0; j< 3; j++){
		 				for(int k=0; k<3; k++){
		 					for(int m=0; m<3; m++){

	 							if(i == selectedButton[0] && j == selectedButton[1] && k == selectedButton[2] && m == selectedButton[3])
	 								button[i][j][k][m].setBackground(new Color(255, 255, 200) );
	 							else if( defaultVal[i*3+k][j*3+m])
	 								button[i][j][k][m].setBackground(Color.LIGHT_GRAY);
	 							else
	 								button[i][j][k][m].setBackground(Color.WHITE);
			 				}
				 				
						}
 					}
	 			}
	 		}
	 	}
	 	else if(state == BUT_NUMPUSHED){

		 	if(isLegal(selectedButton[0], selectedButton[1], selectedButton[2], selectedButton[3])){

		 		int counter = 0;

		 		for(int i=0; i < 3; i++){
		 			for(int j=0; j< 3; j++){
		 				for(int k=0; k<3; k++){
		 					for(int m=0; m<3; m++){

	 							if(button[i][j][k][m].getText().charAt(0) == button[selectedButton[0]][selectedButton[1]][selectedButton[2]][selectedButton[3]].getText().charAt(0) ){
	 								button[i][j][k][m].setBackground(new Color(255, 255, 200) );
	 							}
	 							else{
	 								if( defaultVal[i*3+k][j*3+m])
	 									button[i][j][k][m].setBackground(Color.LIGHT_GRAY);
	 								else
	 									button[i][j][k][m].setBackground(Color.WHITE);
	 							}

	 							if (button[i][j][k][m].getText().charAt(0) != ' ') counter++;
			 				}
				 				
						}
 					}
	 			}
	 			//After adding new LEGAL value, if none empty boxes are remained, game just finished ;) 
	 			if(counter == 81) 
	 				winGame();
			}
 			else{
 				for(int i=0; i < 3; i++){
		 			for(int j=0; j< 3; j++){
		 				for(int k=0; k<3; k++){
		 					for(int m=0; m<3; m++){

 								if( defaultVal[i*3+k][j*3+m])
 									button[i][j][k][m].setBackground(Color.LIGHT_GRAY);
 								else
 									button[i][j][k][m].setBackground(Color.WHITE);

	 						}
			 			}		
					}
 				}
  				button[selectedButton[0]][selectedButton[1]][selectedButton[2]][selectedButton[3]].setBackground(Color.RED);
 			}
	
	 	}
 	}

 	//Returns solution to solutionTable
 	private void findSolution(){
 		SudokuSolutionFinder finder = new SudokuSolutionFinder(button, defaultVal);
 		solutionTable = finder.getSolution();
 	}

 	//Called when game just finished and makes winGame frame
 	private void winGame(){

 		setDisabled();
 		WinGameFrame winGameFr = new WinGameFrame();
 		
 		System.out.println("YOU WON THE GAME!");
 	}

	public static void main(String args[]){

		Sudoku game = new Sudoku();
	}
}