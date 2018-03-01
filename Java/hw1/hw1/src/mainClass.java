/*  Authors: Patsianotakis Charalampos, Axelou Olympia
*
*   This is the main class of hw1 of course Object Oriented Programming
*/

public class mainClass {
    
    public static void main(String[] args){
        
        String Input = ReadLine();
        
        if(checkValidInput(Input)){
        } else {
            System.out.println("Invalid Input!");
            return;
        }
        
        System.out.println("Valid Input!");
        
        String[] SetInputs = Input.split(" ");
            
    }
    
    //Method that gets the math expression from user
    private static String ReadLine() {
    java.util.Scanner sc = new java.util.Scanner(System.in);
    System.out.print("Enter math expression: ");
    String line = sc.nextLine();
    System.out.println("Math expresssion is: "+line);
    
    return line;
  }
    
   private static boolean checkValidInput(String Input){
       
       char tempChar;
       int parenthesisCounter = 0;
       
       for(int i = 0; i < Input.length() ; i++){
           tempChar = Input.charAt(i);
           
            if(tempChar == '('){
                if(isOperator( Input.charAt(i+1) ) )
                        return false;  //Example 8+(-0+3), (Numbers are Positives)
                parenthesisCounter++;
            }
            
            else if(tempChar == ')'){
               /*Check if there is closing parenthesis symbol,
                *without being opened
                */ 
               if(isOperator( Input.charAt(i-1) ) )
                        return false;  //Example 8+(0+3+)*10
                if(parenthesisCounter == 0)
                    return false;
                else 
                    parenthesisCounter--;
            }
            
            else if(isOperator(tempChar)){
                
                if(i == 0 || i == Input.length() - 1){
                    return false;  // Example 4-2+ or -0+3 (Numbers are Positives)
                }
                else{
                    if(isOperator( Input.charAt(i+1) ) )
                    return false;  //Example 9++3, 5-2+-4
                }
               
            }
            
            else if( !Character.isDigit(tempChar) && !Character.isWhitespace(tempChar)){
                return false;
            }
            
        }
            
           
          
       //Not every parenthesis has been closed
       if(parenthesisCounter != 0)
           return false;
       
       return true;
   }
   
   private static boolean isOperator(char a){
       if(a == '/' || a == '*' || a == '+' || a == '-' || a == '^')
           return true;
       else
           return false;
   }
}




