/*  Authors:    Patsianotakis Charalampos cpatsianotakis@inf.uth.gr
*               Axelou Olympia            oaxelou@inf.uth.gr
*
*   The class for the tree as a unit.
*	The input of the constructor is CORRECT and stored in an array of strings.
*	The error checking has been done in main.
*/

package ce325.hw1;
 
public class Tree {
    public TreeNode root;
    
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
	
    public Tree(String[] SetInputs){
        root = makeTree(root, SetInputs, 0, SetInputs.length -1);
    }
	/* Recursive method.
	 * The basic method that finds all the components of the arithmetic expression
	 */
	public TreeNode makeTree(TreeNode r, String[] SetInputs, int left, int right){

		if(left == right){ // it's a number
			r = new TreeNode(Double.parseDouble(SetInputs[left]));
		}
		else{
			int OperatorPosition = -1; // initializations for this check
			int OperatorPriority = -1; 
			int tempPriority = -1;
			int inParenthesis = 0; //counts the level of the parenthesis in which we are
			
			for(int i = left; i <= right ; i++){
				if(SetInputs[i].charAt(0) == '('){
					inParenthesis++;
				}
				else if(SetInputs[i].charAt(0) == ')'){
					inParenthesis--;
				}
				else if(inParenthesis == 0){ 
					// only if we are outside of every parenthesis checks for operator
					if(isOperator(SetInputs[i].charAt(0))){
						tempPriority = testOperPriority(SetInputs[i].charAt(0));
						if(tempPriority > OperatorPriority){ // find the operator
							OperatorPosition = i;			 // w/ max priority
							OperatorPriority = tempPriority;
						}
					}
					// if it reaches here means that it's a number! 
				}
			}
			
			if(OperatorPosition == -1){ // no operators: either everything 
										// in a big parenthesis or is just a number
				r = makeTree(r, SetInputs, left + 1, right -1);
			}else{	// we split the process  
				r = new TreeNode(SetInputs[OperatorPosition].charAt(0));
				// analyze everything left of the operator and in the right respectively
				r.right = makeTree(r, SetInputs, OperatorPosition + 1, right);
				r.left = makeTree(r, SetInputs, left, OperatorPosition - 1);
			}
		}
		return r;
	}
	
	/* Static method: Returns a number to indicate the priority of the operator.
	 * The value doesn't mean anything in the general process of the program.
	 */
	private static int testOperPriority(char a){
       switch(a){
			case '+':
				return 4;
			case '-':
				return 3;
			case '*':
				return 2;
			case '/':
				return 1;
			case '^':
				return 0;
			default:
				return -1; //not an operator
		}
   }
   
   private static boolean isOperator(char a){
       if(a == '/' || a == '*' || a == '+' || a == '-' || a == '^')
           return true;
       else
           return false;
   }
	
	/* The public method that is called from the main program.
	 * Calls the other calculate 
	 */
    public double calculate(){
        return root.calculate();
    }

	/* Returns the complete string of the altered 
	 * (more parenthesis) arithmetic expression.
	 * Calls the TreeNode.toString from the root.
	 */
	public String toString(){
		return root.toString() + '\n';
	}
	
	/* Returns a string with the complete info needed for the dot file.
	 * Calls the TreeNode.toDotString from the root and collects the node info
	 */
	public String toDotString(String dotFileName){
		String returnedStr;
		
		returnedStr = "digraph " + dotFileName + "Tree{\n";
		returnedStr += "forntcolor=\"navy\";\n";
		returnedStr += "fontsize=20;\n";
		returnedStr += "labelloc=\"t\";\n";
		returnedStr += "label=\"Arithmetic Expression\"";
		
		returnedStr += root.toDotString();
		
		return returnedStr + "}\n";
	}
}
