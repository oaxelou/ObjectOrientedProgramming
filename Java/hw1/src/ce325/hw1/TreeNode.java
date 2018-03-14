/*  Authors:    Patsianotakis Charalampos cpatsianotakis@inf.uth.gr
*               Axelou Olympia            oaxelou@inf.uth.gr
*
*   The class of the node of the tree. 
*   Every method and variable specifically refers to the node it is called for.
*/
 
package ce325.hw1;

public class TreeNode {
    public int type; 				/* values: 0 OPERATOR, 1 NUMBER        */
    public char operatorValue;		/* values: '+' , '-' , '*' , '/' , '^' */
    public double numberValue;
    public TreeNode left;
    public TreeNode right;
    
    public static final int OPERATOR = 0;
    public static final int NUMBER = 1;
	
	private static int nodeID = 0;		  	/* unique node id for toDotString */
	private static String toDotStr = "\n";	/*    the returned string         */
    
    /* Constructor for nodes with operators 
	 */
	TreeNode(char operatorValue){
        type = OPERATOR;
        this.operatorValue = operatorValue;
        right = null;
        left = null;
    }
    
	/* Constructor for nodes with numbers 
	 */
    TreeNode(double numberValue){
        type = NUMBER;
        this.numberValue = numberValue;
        right = null;
        left = null;
    }
    
    public TreeNode getRight(){
        return right;
    }
    
    public TreeNode getLeft(){
        return left;
    }
    
	/* Simple method. 
	 * Returns a double. Specifically, returns the value 
	 * of the number stored in the node.
	 * We assume that it is called correctly: for a node that has numberValue
	 */
    public double getNumberValue(){
        if(type == NUMBER) 
            return numberValue;
		System.out.println("Should never reach this (getNumberValue)");  
        return -1;        
    }
	    
	/* Recursive method.
	 * Calls the TreeNode.toDotString from the root and collects the node info
	 */
    public double calculate(){
        if(right == null && left == null){ // IT'S A NUMBER
            return getNumberValue();
        }
        // else.. it's an operator
        return chooseOperator(left.calculate(), operatorValue, right.calculate());
    }
	
	/* Static method: Returns the result (double) of the inputs.
	 * Performs a single calculation
	 */
	private static double chooseOperator(double leftSide, char operatorToCheck, double rightSide){
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
				System.out.println("Not an operator (should never reach this case)");
				return -1;
		}
    }
	 
	/* Simple method. 
	 * Returns a string with the information of the node.
	 * Either the operator or the number.
	 */
	public String getStringValue(){
        if(type == NUMBER) 
            return ""+numberValue;
        else{ // type == OPERATOR
            return ""+operatorValue;   
		}			
    }
	
	/* Recursive method. 
	 * Returns a string with the arithmetic expression.
	 * It adds parenthesis around a basic calculation 
	 *                     AND both sides of a number.
	 */
	public String toString(){
		String s;
      
        if(left != null){
            return "(" + left.toString() + getStringValue() + right.toString() + ")"; //recursion
        }
        else{
            s =  "(" + getStringValue() + ")";
        }
		
        return s;
	}
	
	/* Recursive method. 
	 * Returns a string with the info for the nodes for the dot file.
	 */
	public String toDotString(){
		int myID = nodeID;
		
		toDotStr += myID + " [label=\"" + getStringValue() + "\", shape=circle, color=black]\n";
		
		if(left != null){
			nodeID++;
			toDotStr += myID + " -> " + nodeID + "\n";
			left.toDotString();
		}
		
		if(right != null){
			nodeID++;
			toDotStr += myID + " -> " + nodeID + "\n";
			right.toDotString();
		}
		return toDotStr;
	}
}