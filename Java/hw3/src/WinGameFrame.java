/*  Authors:    Patsianotakis Charalampos cpatsianotakis@inf.uth.gr
*               Axelou Olympia            oaxelou@inf.uth.gr
*
*	package ce325.hw3, Sudoku Project
*   WinGameFrame class:
*
*	Class representing frame occures when user wins the game.
*	Frame includes label with text "YOU WON THE GAME!" and button OK
*	which disposes the frame when being clicked.
*
*	Extends JFrame
*	Implements ActionListener
*   
*/

package ce325.hw3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class WinGameFrame extends JFrame implements ActionListener{

	JLabel winGameLabel;
	JButton okButton;

	public WinGameFrame(){
		super();
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);

 		winGameLabel = new JLabel("YOU WON THE GAME!");
 		okButton     = new JButton("OK");

 		okButton.addActionListener(this);

 		add(winGameLabel, BorderLayout.NORTH);
 		add(okButton, BorderLayout.SOUTH);

 		setVisible(true);
 		pack();
	}

	public void actionPerformed(ActionEvent e) {

	    if(e.getSource().equals(okButton) ) {

	    	setVisible(false);
	    	dispose();     

	    }
	}
}