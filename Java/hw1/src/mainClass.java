/*  Authors:    Patsianotakis Charalampos cpatsianotakis@inf.uth.gr
*               Axelou Olympia            oaxelou@inf.uth.gr
*
*   This is the main class of hw1 of course Object Oriented Programming
*/

import java.util.Scanner;
import java.io.PrintWriter;

//***********************************************************************************************************************//
//***********************************************************************************************************************//
//***********************************************************************************************************************//
//****************************************************MAIN FUNCTION******************************************************//
public class mainClass {
    
    public static void main(String[] args){
        
        String Input = ReadLine();
        
        if(!firstCheckValidInput(Input)){
            System.out.println("Invalid Input!");
            return;
        }
        
        Input = makeWhiteSpaces(Input);
               
        String[] SetInputs = Input.split(" ");
        for(int i = 0; i < SetInputs.length ; i++)
            System.out.println(SetInputs[i]);
        
        if(!secondCheckValidInput(SetInputs)){
            System.out.println("Invalid Input!");
            return;
        }

        //=========================================================//
		
		Scanner scan = new Scanner(System.in);
        
        Tree arithmeticTree = new Tree();
        arithmeticTree.insert();
		
        System.out.println("\n\n------- toDotString -------");
		PrintWriter writer = null;
		try {        
			PrintWriter pfile = new PrintWriter("ArithmeticExpression.dot");
			pfile.println(arithmeticTree.dotString("ArithmeticExpression"));
			pfile.close();
			System.out.println("PRINT DOT FILE OK!");
         
			Process p = Runtime.getRuntime().exec("dot -Tpng ArithmeticExpression.dot " + "-o ArithmeticExpression.png");
			p.waitFor();
			System.out.println("PRINT PNG FILE OK!");
		} catch(Exception ex) {
			System.err.println("Unable to write dotString!!!");
			ex.printStackTrace();
			System.exit(1);
		}

        System.out.println("\n\n-------- toString --------");
        System.out.println(arithmeticTree.toString());
        
        System.out.println("\n\n-------- Calculate --------");
        System.out.println(arithmeticTree.calculate());
    }

    //***********************************************************************************************************************//
    //***********************************************************************************************************************//
    //***********************************************************************************************************************//
    //***********************************************************************************************************************//
    //***********************************************************************************************************************//
    //***********************************************************************************************************************//
    
    /*String ReadLine function
    *Makes String the input till new line user gives
    *Returns the string made
    */
    private static String ReadLine() {
    java.util.Scanner sc = new java.util.Scanner(System.in);
    System.out.print("Enter math expression: ");
    String line = sc.nextLine();
    System.out.println("Math expression is: "+line);
    
    return line;
  }
    
   /*Boolean firstCheckValidInput function:
    *   
    *Input:         String
    *Functionality: Checks if all chars in String are Valid for
    *               math expression and if '.' is correct placed
    *Returns:       true if String Input is Valid, else false
    *
    *Example:       String str = "1/3-(3+2)*8*2"
    *               checkValidInput(str);
    *               Returns true
    *
    *               String str = "1/3-(3+-2)9*8*2"
    *               checkValidInput(str);
    *               Returns true
    *   
    *               String str = ".1+3-(3+2+)a"
    *               checkValidInput(str);
    *               Returns false
    */
   private static boolean firstCheckValidInput(String Input){
       
       char tempChar;
      // int parenthesisCounter = 0;
       int i,j;
       
       for(i = 0; i < Input.length() ; i++){
           tempChar = Input.charAt(i);
           
            if(tempChar == '.'){

                if(i == 0 || i == Input.length() - 1){
                    System.out.println("'.' must have digits at the left or right side!");
                    return false;
                }

                if(!Character.isDigit( Input.charAt(i-1) ) || !Character.isDigit( Input.charAt(i+1) )){
                    System.out.println("'.' must have digits at the left or right side!");
                    return false;
                }

            }
            
            //If it is incompatible char
            else if( !(Character.isWhitespace(tempChar) || Character.isDigit(tempChar) || isOperator(tempChar) || tempChar == '(' || tempChar == ')') ){
                System.out.println("Available characters (0,1,2,3,4,5,6,7,8,9,.,(,),+,-,*,/,^) !");
                return false;
            }
        }
       
       return true;
   }
   
   /*Boolean isOperator function
    *   
    *Input:         char
    *Functionality: Checks if char input represents an operator
    *Returns:       true if input represents operator, else false
    *
    *Example:   char a = '+';
    *           isOperator(a);
    *           Returns true
    *
    *           char a = '4';
    *           isOperator(a);
    *           Returns false
    */
   private static boolean isOperator(char a){
       if(a == '/' || a == '*' || a == '+' || a == '-' || a == '^')
           return true;
       else
           return false;
   }


   /*Boolean makeWhiteSpaces function:
    *   
    *Input:         String
    *Functionality: Seperates the components of input String with
    *               WhiteSpace chars. Also if more than one WhiteSpace
    *               excists between components, it deletes to one
    *Returns:       The new String
    *
    *Example:       String str = "1/3-(3+2)*8*2"
    *               checkValidInput(str);
    *               Returns "1 / 3 - ( 3 + 2 ) * 8 * 2"
    *
    *               String str = "1 /3-(      3+2)* 8*2"
    *               checkValidInput(str);
    *               Returns "1 / 3 - ( 3 + 2 ) * 8 * 2"
    *
    *               String str = "1/3-(*3.3+2)* 8.2  *2"
    *               checkValidInput(str);
    *               Returns "1 / 3 - ( * 3.3 + 2 ) * 8.2 * 2"
    */
    private static String makeWhiteSpaces(String Input){
        
        StringBuffer StrBuf = new StringBuffer();
        StrBuf.append(Input);
                        
        for(int i = 0; i < StrBuf.length()-1; i++){
            if(Character.isDigit( StrBuf.charAt(i) ) ){
                if(!Character.isDigit( StrBuf.charAt(i+1) ) && (StrBuf.charAt(i+1) != '.') ){
                    StrBuf.insert(i+1, ' ');
                }
            }
            else if(StrBuf.charAt(i) != '.'){
                if( !Character.isWhitespace(StrBuf.charAt(i) ) ){
                    StrBuf.insert(i+1, ' ');
                }
                else //Char at i is whitespace
                    if( Character.isWhitespace(StrBuf.charAt(i+1) ) ){
                            StrBuf.deleteCharAt(i--);
                }
            }
        }

        return StrBuf.toString();
    }

    /*Boolean secondCheckValidInput function:
    *   
    *Input:         Table of Strings
    *Functionality: Checks if Input components makes sense
    *               as math expression.
    *Returns:       true if String Input is it makes, else false
    *
    *Example:       String[] SetStr = {"1", "/", "3", "-", "(", "3.0", "+", "2", ")", "*", "8", "*", "2.3"}
    *               checkValidInput(str);
    *               Returns true
    *
    *               String[] SetStr = {"1", "/", "3", "3.42435", "-", "(", "3.0", "+", "2", ")", "*", "8", "*", "2.3"}
    *               checkValidInput(str);
    *               Returns false
    *
    *               String[] SetStr = {"1", "/", "3", "-", "(", "(", "3.0", "+", "2", ")", "*", "8", "*", "2.3"}
    *               checkValidInput(str);
    *               Returns false
    *           
    *               String[] SetStr = {"1", "/", "3", "-", "(", "3.0", "+", "2", ")", ")", "*", "8", "*", "2.3"}
    *               checkValidInput(str);
    *               Returns false
    *
    *               String[] SetStr = {"1", "/", "3", "-", "(", "3.0", "+", "2", ")", "*", "+", "8", "*", "2.3"}
    *               checkValidInput(str);
    *               Returns true
    */
    private static boolean secondCheckValidInput(String []SetInputs){

        int i;
        int first_occ;
        int parenthesisCounter = 0;

        char tempChar;


        for(i = 0; i < SetInputs.length; i++){

            //CHECK FOR 2 POINTS IN A NUMBER
            first_occ = SetInputs[i].indexOf('.');
            if(SetInputs[i].indexOf('.', first_occ + 1) > -1){
                System.out.println("Real numbers have only one '.'!");
                return false;
            }
            

            tempChar = SetInputs[i].charAt(0); 

            if(tempChar == '('){

                if(i != SetInputs.length - 1 && (isOperator( SetInputs[i+1].charAt(0) ) ) ){
                    System.out.println("Operator cannot finish or start expression in parenthesis!");
                    return false;  //Example 8+(-0+3), (Numbers are Positives)
                }
                
                if(i != 0 && Character.isDigit( SetInputs[i-1].charAt(0) ) ){
                    System.out.println("Between a parenthesis and a number must be an operator");
                    return false;  //Example 0(9+9)
                }
                
                //Add an opened parenthesis
                parenthesisCounter++;
            }

            else if(tempChar == ')'){

                if(parenthesisCounter == 0){
                    System.out.println("')' detected without openening parenthesis!");
                    return false;
                }
                else 
                    //Sub an opened parenthesis
                    parenthesisCounter--;

                if(isOperator( SetInputs[i-1].charAt(0) ) ){
                    System.out.println("Operator cannot finish or start expression in parenthesis!");
                    return false;  //Example 8+(0+3+)*10
                }


                if(i != SetInputs.length - 1 && ( Character.isDigit( SetInputs[i+1].charAt(0) ) || SetInputs[i+1].charAt(0) == '(' ) ){
                    System.out.println("Between a parenthesis and a number or an other parenthesis must be an operator");
                    return false;  //Example (9+9)0, (8-9)(4/8)
                } 


            }   


            else if(isOperator(tempChar)){
                
                if(i == 0 || i == SetInputs.length - 1){
                    System.out.println("An operator cannot start or finish a math expression");
                    return false;  // Example 4-2+ or -0+3 (Numbers are Positives)
                }
                else{
                    if(isOperator( SetInputs[i+1].charAt(0) ) ){
                        System.out.println("Between two operators must excist a number or a parenthesis!");
                        return false;  //Example 9++3, 5-2+-4
                    }
                }
            }

            else if(Character.isDigit(tempChar)){
                

                if(i < SetInputs.length -1 && Character.isDigit( SetInputs[i+1].charAt(0) ) ){
                    System.out.println("Between two numbers must excist an operator!");
                    return false;  //Example 3+4 5 -1 
                } 
            }



        }//FOR LOOP ENDS

        if(parenthesisCounter != 0){
            System.out.println("You haven't closed all your parenthesis!");
            return false;
        } 

        return true;
    }
    
}






