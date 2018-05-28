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

  	private static int MODE_EASY         = 0;
	private static int MODE_INTERMEDIATE = 1;
	private static int MODE_EXPERT       = 2;

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

  	private TerminalSudokuPrinter terminalPrinter;

	public Sudoku(){

		frame = new JFrame("Hello");

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

		Border lineBorder = new LineBorder(Color.CYAN);

		JPanel north = new JPanel();
		JPanel south = new JPanel(); 

		JPanel west = new JPanel(); 
		JPanel center = new JPanel();
		JPanel east = new JPanel(); 

		JPanel wNorth = new JPanel();
		JPanel cNorth = new JPanel();
		JPanel eNorth = new JPanel();

		JPanel wSouth = new JPanel();
		JPanel cSouth = new JPanel();
		JPanel eSouth = new JPanel();

		north.setLayout(new BorderLayout());
		south.setLayout(new BorderLayout());

		west.setLayout(new BorderLayout());
		center.setLayout(new BorderLayout());
		east.setLayout(new BorderLayout());

		wNorth.setLayout(new BorderLayout());
		cNorth.setLayout(new BorderLayout());
		eNorth.setLayout(new BorderLayout());

		wSouth.setLayout(new BorderLayout());
		cSouth.setLayout(new BorderLayout());
		eSouth.setLayout(new BorderLayout());

		west.setBorder(lineBorder);
		center.setBorder(lineBorder);
		east.setBorder(lineBorder);

		wNorth.setBorder(lineBorder);
		cNorth.setBorder(lineBorder);
		eNorth.setBorder(lineBorder);

		wSouth.setBorder(lineBorder);
		cSouth.setBorder(lineBorder);
		eSouth.setBorder(lineBorder);

		boxArea.add(north, BorderLayout.NORTH);
		boxArea.add(south, BorderLayout.SOUTH);

		north.add(wNorth, BorderLayout.WEST);
		north.add(cNorth, BorderLayout.CENTER);
		north.add(eNorth, BorderLayout.EAST);

		boxArea.add(west, BorderLayout.WEST);
		boxArea.add(center, BorderLayout.CENTER);
		boxArea.add(east, BorderLayout.EAST);

		south.add(wSouth, BorderLayout.WEST);
		south.add(cSouth, BorderLayout.CENTER);
		south.add(eSouth, BorderLayout.EAST);

		createBoxes(wNorth, 0, 0);
		createBoxes(cNorth, 1, 0);
		createBoxes(eNorth, 2, 0);

		createBoxes(west,   0, 1);
		createBoxes(center, 1, 1);
		createBoxes(east,   2, 1);

		createBoxes(wSouth, 0, 2);
		createBoxes(cSouth, 1, 2);
		createBoxes(eSouth, 2, 2);

		for(int i=0; i < 3; i++){
 			for(int j=0; j< 3; j++){
 				for(int k=0; k<3; k++){
 					for(int m=0; m<3; m++){

 						button[i][j][k][m].setBackground(Color.WHITE);
		 				button[i][j][k][m].addActionListener(this);

					}
 				}
 			}
	 		
	 	}

	}
	
	private void createBoxes(JPanel panel, int line, int col){

		JPanel northPanel = new JPanel();
		JPanel southPanel = new JPanel(); 

		button[col][line][0][0] = new JButton(" "); 
		button[col][line][0][1] = new JButton(" ");
		button[col][line][0][2] = new JButton(" ");
		 
		button[col][line][1][0] = new JButton(" ");
		button[col][line][1][1] = new JButton(" ");
		button[col][line][1][2] = new JButton(" ");

		button[col][line][2][0] = new JButton(" ");
		button[col][line][2][1] = new JButton(" "); 
		button[col][line][2][2] = new JButton(" ");

		button[col][line][0][0].setSize(20,10);
		button[col][line][1][0].setSize(20,10);
		button[col][line][2][0].setSize(20,10);

		button[col][line][0][1].setSize(20,10); 
		button[col][line][1][1].setSize(20,10);
		button[col][line][2][1].setSize(20,10); 

		button[col][line][0][2].setSize(20,10);
		button[col][line][1][2].setSize(20,10);
		button[col][line][2][2].setSize(20,10);

		panel.add(northPanel, BorderLayout.NORTH);
		panel.add(southPanel, BorderLayout.SOUTH);
		
		northPanel.add(button[col][line][0][0], BorderLayout.WEST);
		northPanel.add(button[col][line][0][1], BorderLayout.NORTH);
		northPanel.add(button[col][line][0][2], BorderLayout.EAST);

		panel.add(button[col][line][1][0], BorderLayout.WEST);
		panel.add(button[col][line][1][1], BorderLayout.CENTER);
		panel.add(button[col][line][1][2], BorderLayout.EAST);

		southPanel.add(button[col][line][2][0], BorderLayout.WEST);
		southPanel.add(button[col][line][2][1], BorderLayout.SOUTH);
		southPanel.add(button[col][line][2][2], BorderLayout.EAST);



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

	public JMenuBar createMenu(){

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

  	public void setEnabled(){

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

  	public void setDisabled(){
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
	     
	    	inReader = new InputReader(MODE_EASY);
	    	initializeGame();

	    }

	    else if(e.getSource().equals(InterMediateMenu) ) {
	     
	     	inReader = new InputReader(MODE_INTERMEDIATE);
	     	initializeGame();

	    }


	    else if(e.getSource().equals(ExpertMenu) ) {
	     
	     	inReader = new InputReader(MODE_EXPERT);
	     	initializeGame();

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

 	private boolean isLegal(int iPos, int jPos, int kPos, int mPos){

 		char value = button[iPos][jPos][kPos][mPos].getText().charAt(0);
 		if(value == ' ') return true;

		for(int k = 0; k <3; k++){
			for(int m = 0; m < 3; m++){
				if(value == button[iPos][jPos][k][m].getText().charAt(0) && !(k == kPos && m == mPos) ) 
					return false;
			}
		}

		for(int i = 0; i <3; i++){
			for(int k = 0; k < 3; k++){
				if(value == button[i][jPos][k][mPos].getText().charAt(0) && !(i == iPos && k == kPos) )
					return false;
			}
		}

		for(int j = 0; j <3; j++){
			for(int m = 0; m < 3; m++){
				if(value == button[iPos][j][kPos][m].getText().charAt(0) && !(j == jPos && m == mPos))
					return false;
			}
		}

		return true;

	}

 	private void initializeGame(){

 		setEnabled();

 		input = inReader.getInput();
	    setDefault();
	    drawButtons(BUT_INITIALIZE);
	    selectedButton[0] = -1;
 		selectedButton[1] = -1;
 		selectedButton[2] = -1;
 		selectedButton[3] = -1;
 		actionsDeque = new LinkedList<SudokuAction>();
   	    
	    terminalPrinter = new TerminalSudokuPrinter(button, defaultVal);
	    terminalPrinter.printTable();
	    terminalPrinter.toFile("initialTable.txt");

/*	    findSolution();
	    TerminalSudokuPrinter terminalPrinterSolution = new TerminalSudokuPrinter(solutionTable, defaultVal);
	    terminalPrinterSolution.printTable();*/

 	}


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

	 							if(button[i][j][k][m].getText() == button[selectedButton[0]][selectedButton[1]][selectedButton[2]][selectedButton[3]].getText())
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

 	private void findSolution(){
 		SudokuSolutionFinder finder = new SudokuSolutionFinder(button, defaultVal);
 		solutionTable = finder.getSolution();
 	}

 	private void winGame(){
 		setDisabled();
 		System.out.println("YOU WON THE GAME!");
 	}

	public static void main(String args[]){

		Sudoku game = new Sudoku();
	}
}