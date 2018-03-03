/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author oaxel
 */
import java.util.Scanner;

public class Tree {
    TreeNode root;
    
    
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    
    public Tree(){
        //root = new TreeNode();
		
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
	
    // function to insert an operator node
    // public TreeNode insert(TreeNode node, int child, int operatorValue){
        // if(node == null){
            // System.out.println("Node is null. Should not have happened");
            // return null;
        // }
        // else{
            // if(child == LEFT){
                // node.left = new TreeNode(operatorValue);
            // }
            // else{
                // node.right = new TreeNode(operatorValue);
            // }
        // }
        // return node;
    // }
    
    // function to insert a number node
    // public TreeNode insert(TreeNode node, int child, double numberValue){
        // if(node == null){
            // System.out.println("Node is null. Should not have happened");
            // return null;
        // }
        // else{
            // if(child == LEFT){
                // node.left = new TreeNode(numberValue);
            // }
            // else{
                // node.right = new TreeNode(numberValue);
            // }
        // }
        // return node;
    // }
    
    //mallon den to xreiazomaste
    public int countNodes(){
        return countNodes(root);
    }
    
    // function to count nodes recursively //mallon den to xreiazomaste
    public int countNodes(TreeNode r){
        if(r == null){
            return 0;
        }
        else{
            int l = 1;
            l += countNodes(r.getRight());
            l += countNodes(r.getLeft());
            return l;
        }
    }
    /*
    //mallon den to xreiazomaste
    public void postorder(){
        postorder(root);
    }
    
    //mallon den to xreiazomaste
    public void postorder(TreeNode r){
        if(r != null){
            postorder(r.getLeft());
            postorder(r.getRight());
            if(r.type == TreeNode.OPERATOR){
                System.out.print("+" + " ");
            }
            else{
                System.out.print(r.getValue() + " ");
            }    
            
        }
    }
    */
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
	
    // tha mpei mia sunarthsh typou insert edw pou tha kalei thn hdh ulopoihmenh insert
    // H domh ths omws eksartatai apo to pws diaxeirizomaste ta strings
	
	    
    // edw tha mpei h toString 
	public String toString(){
		return toStringInternal(root);
	}
	
	public String toStringInternal(TreeNode r){
		String s;
                if(r == null){ return null;}
                
                if(r.getLeft() != null){
                    return "(" + toStringInternal(r.getLeft()) + r.getStringValue() + toStringInternal(r.getRight()) + ")"; //recursion
                }
                else{
                    s =  "(" + r.getStringValue() + ")";
                }
		
                return s;
		// r.getValue(): TSEKARE TO AYTO -> ALLAGES STO TreeNode.java + isws nea klassh! DONE BUT NOT CHECKED
	}
	
	public String toStringInternal(){
		return toStringInternal(root) + "\n";
	}
    
    // edw tha mpei h toDotString
}
