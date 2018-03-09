/*  Authors:    Patsianotakis Charalampos cpatsianotakis@inf.uth.gr
*               Axelou Olympia            oaxelou@inf.uth.gr
*
*   
*/
 
public class Tree {
    TreeNode root;
    
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
	
    public Tree(String[] SetInputs){
        root = makeTree(root, SetInputs, 0, SetInputs.length -1);
    }

	public TreeNode makeTree(TreeNode r, String[] SetInputs, int left, int right){

		if(left == right){ // it's a number
			// System.out.println("Left: " + left + ", Right: " + right + ",It's a number: " + SetInputs[left]);
			r = new TreeNode(Double.parseDouble(SetInputs[left]));
		}
		else{
			int OperatorPosition = -1;
			int OperatorPriority = -1; 
			int tempPriority = -1;
			int inParenthesis = 0;
			
			for(int i = left; i <= right ; i++){
				if(SetInputs[i].charAt(0) == '('){
					inParenthesis++;
				}
				else if(SetInputs[i].charAt(0) == ')'){
					inParenthesis--;
				}
				else if(inParenthesis == 0){
					if(isOperator(SetInputs[i].charAt(0))){
						tempPriority = testOperPriority(SetInputs[i].charAt(0));
						if(tempPriority > OperatorPriority){
							OperatorPosition = i;
							OperatorPriority = tempPriority;
						}
					}
					else{ // an ftasei se auto to shmeio shmainei oti vrhke arithmo! 
						// System.out.println("Left: " + left + ", Right: " + right + ",Must be a number: " + SetInputs[i]);
					} 
				}
			}
			
			if(OperatorPosition == -1){
				// System.out.println("Left: " + left + ", Right: " + right + ",Didn't find an operator");
				r = makeTree(r, SetInputs, left + 1, right -1);
				// System.exit(1);
			}else{
				// System.out.println("OperatorPosition: " + OperatorPosition);
				r = new TreeNode(SetInputs[OperatorPosition].charAt(0));
		
				r.right = makeTree(r, SetInputs, OperatorPosition + 1, right);
				r.left = makeTree(r, SetInputs, left, OperatorPosition - 1);
			}
		}
		return r;
	}
	
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
	
    public double calculate(){
        return calculate(root);
    }
    
    private double calculate(TreeNode r){
        if(r == null){
            return 0;
        }
        else if(r.right == null && r.left == null){ // IT'S A NUMBER
            return r.getNumberValue();
        }
        // else.. it's an operator
        return chooseOperator(calculate(r.left), r.operatorValue, calculate(r.right));
    }
	
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
	 
	public String toString(){
		return root.toString() + '\n';
	}
	
	public String dotString(String dotFileName){
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
