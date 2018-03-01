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
        
        String[] SetInputs = Input.split(" ");
            
    }
    
    //Method that gets the math expression from user
    public static String ReadLine() {
    java.util.Scanner sc = new java.util.Scanner(System.in);
    System.out.print("Enter math expression: ");
    String line = sc.nextLine();
    System.out.println("Math expresssion is: "+line);
    
    return line;
  }
    
   public static boolean checkValidInput(String Input){
       
       char tempChar;
       int parenthesisCounter = 0;
       
       for(int i = 0; i < Input.length() ; i++){
           tempChar = Input.charAt(i);
           
           switch(tempChar){
               
               case('(') :
                   parenthesisCounter++;
                   break;
                   
                case(')'):
                    /*Check if there is closing parenthesis symbol,
                    *without being opened
                    */ 
                    if(parenthesisCounter == 0)
                        return false;
                    else 
                        parenthesisCounter--;
                    break;
                    
                case()
                    
            }
       }
       //Not every parenthesis has been closed
       if(parenthesisCounter != 0)
           return false;
       
   }
    
}




