/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author oaxel
 */
import java.util.Scanner; // otan ftiaxtei h insert den tha xreiazetai auto to import 
import java.io.PrintWriter;
import java.io.FileNotFoundException;
import java.io.IOException;

public class Tree {
    TreeNode root;
    
    
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    private static int nodeID = 0;
	
    public Tree(){
        // mporei apo edw na kaleitai h insert me ton pinaka twn string gia orismata
		
    }
    
    //mallon den to xreiazomaste alla why not. pantou uparxei mia isEmpty
    public boolean isEmpty(){
        return root == null;
    }
	
	//this method creates the whole tree
	public void insert(){
		root = insert(root);
	}
	
	public TreeNode insert(TreeNode r){
		Scanner scan = new Scanner(System.in);
		int nodeChoice;
		char operatorChoice;
		double numberChoice;
		
		System.out.println("\nMENU\nChoose from { -1, 0 or (>= 1) } for respectively:\n-1.Exit\n 0.Number\n 1.Operator");
		nodeChoice = scan.nextInt();
		if(nodeChoice == -1){
			System.out.println("End of Insertion");
			return null;
		}
		else if(nodeChoice == 0){ // number
			System.out.print("Enter number: ");
			numberChoice = scan.nextDouble();
			
			// creation of number node
			r = new TreeNode(numberChoice);
			
			return r; // IT HAS NO CHILDREN
		}
		
		// operator
		System.out.print("Enter operator: ");
		operatorChoice = scan.next().charAt(0);
		
		// creation of operator node
		r = new TreeNode(operatorChoice);
		
		r.left = insert(r.left); //vale antistoixa tis getLeft kai getRight
		r.right = insert(r.right);
                
                return r;
    }
	
    // EDW THA MPEI H CALCULATE
    public double calculate(){
        return calculate(root);
    }
    
    public double calculate(TreeNode r){
        if(r == null){
            return 0;
        }
        else if(r.right == null && r.left == null){ // IT'S A NUMBER
            if(r.type == TreeNode.NUMBER) // AN EXEI HDH ELEGXTHEI H if AYTH DEN XREIAZETAI. THA EINAI SIGOURA NUMBER
                return r.getNumberValue();
        }
        else if(r.right == null || r.left == null){
            System.out.println("Error. Not supposed to happen.");
            return -1; //vres exit sunarthsh!
        }
        // else.. IT'S AN OPERATOR (for now I assume it's a '+')
        return chooseOperator(calculate(r.left), r.operatorValue, calculate(r.right));
    }
	
	private double chooseOperator(double leftSide, char operatorToCheck, double rightSide){
		switch(operatorToCheck){
			case '+':
				return leftSide + rightSide;
			case '-':
				return leftSide - rightSide;
			case '*':
				return leftSide * rightSide;
			case '/':
				return leftSide / rightSide;
			case '^':
				return Math.pow(leftSide, rightSide);
			default:
				System.out.println("Should never reach this case. Sth wrong.");
				return -1;
		}
    }
	 
	public String toString(){
		return toString(root) + '\n';
	}
	
	public String toString(TreeNode r){
		String s;
                if(r == null){ return null;}
                
                if(r.getLeft() != null){
                    return "(" + toString(r.getLeft()) + r.getStringValue() + toString(r.getRight()) + ")"; //recursion
                }
                else{
                    s =  "(" + r.getStringValue() + ")";
                }
		
                return s;
		// r.getValue(): TSEKARE TO AYTO -> ALLAGES STO TreeNode.java + isws nea klassh! DONE BUT NOT CHECKED
	}
	
	public void dotString(PrintWriter pfile, String dotFileName){
		String returnedStr;
		
		pfile.println("digraph ArithmeticExpressionTree{");
		pfile.println("forntcolor=\"navy\"");
		pfile.println("fontsize=20;");
		pfile.println("labelloc=\"t\"");
		pfile.println("label=\"Arithmetic Expression\"");
		//***************************
		// preorder printing
		toDotString(pfile, root);
		
		//***************************
		pfile.println("}");
	}

	private void toDotString(PrintWriter w, TreeNode r){
		if(r == null){ return;}
		int myID = nodeID;
		
		w.println(myID + " [label=\"" + r.getStringValue() + "\", shape=circle, color=black]");
		
		if(r.getLeft() != null){
			nodeID++;
			w.println(myID + " -> " + nodeID);
			toDotString(w, r.getLeft());
		}
		
		
		if(r.getRight() != null){
			nodeID++;
			w.println(myID + " -> " + nodeID);
			toDotString(w, r.getRight());
		}
	}
}
