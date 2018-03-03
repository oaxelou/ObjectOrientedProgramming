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
        
        Input = makeWhiteSpaces(Input);
               
        String[] SetInputs = Input.split(" ");
        for(int i = 0; i < SetInputs.length ; i++)
            System.out.println(SetInputs[i]);
        
    }
    
    /*String ReadLine function
    *Makes String the input till new line user gives
    *Returns the string made
    */
    private static String ReadLine() {
    java.util.Scanner sc = new java.util.Scanner(System.in);
    System.out.print("Enter math expression: ");
    String line = sc.nextLine();
    System.out.println("Math expresssion is: "+line);
    
    return line;
  }
    
       /*Boolean checkValidInput function
    *Checks if Sting Input is Valid to make arithmetic operations
    *Returns true if it is, else false
    *
    *Example:   String str = "1/3-(3+2)*8*2"
    *           checkValidInput(str);
    *           Returns true
    *
    *           String str = "1+3-(-3-+2+)a"
    *           checkValidInput(str);
    *           Returns false
    */
   private static boolean checkValidInput(String Input){
       
       char tempChar;
       int parenthesisCounter = 0;
       
       for(int i = 0; i < Input.length() ; i++){
           tempChar = Input.charAt(i);
           
            if(tempChar == '('){
                if(isOperator( Input.charAt(i+1) ) )
                        return false;  //Example 8+(-0+3), (Numbers are Positives)
                
                if(i != 0 && Character.isDigit( Input.charAt(i-1) ) )
                        return false;  //Example 0(9+9)
                
                //Add an opened parenthesis
                parenthesisCounter++;
            }
            
            else if(tempChar == ')'){
               if(isOperator( Input.charAt(i-1) ) )
                        return false;  //Example 8+(0+3+)*10
               
               if(i != Input.length() - 1 && Character.isDigit( Input.charAt(i+1) ) )
                        return false;  //Example (9+9)0
               
               /*Check if there is closing parenthesis symbol,
                *without being opened
                */
                if(parenthesisCounter == 0)
                    return false;
                else 
                    //Sub an opened parenthesis
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
            
            //If it is incompatible char
            else if( !Character.isDigit(tempChar) && !Character.isWhitespace(tempChar)){
                return false;
            }
            
            
        }
            
       //Check if there is no-closed parenthesis
       if(parenthesisCounter != 0)
           return false;
       
       return true;
   }
   
   /*Boolean isOperator function
    *Checks if given char is an operator (+,-,*,/,^)
    *Returns true if it is, else false
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

    private static String makeWhiteSpaces(String Input){
        
        StringBuffer StrBuf = new StringBuffer();
        StrBuf.append(Input);
                        
        for(int i = 0; i < StrBuf.length()-1; i++){
            if(Character.isDigit( StrBuf.charAt(i) ) ){
                if(!Character.isDigit( StrBuf.charAt(i+1) ) ){
                    StrBuf.insert(i+1, ' ');
                }
            }
            else if( !Character.isWhitespace(StrBuf.charAt(i) ) ){
                StrBuf.insert(i+1, ' ');
            }
            else //Char at i is whitespace
                if( Character.isWhitespace(StrBuf.charAt(i+1) ) ){
                        StrBuf.deleteCharAt(i--);
                }
        }

        return StrBuf.toString();
    }
    
}




