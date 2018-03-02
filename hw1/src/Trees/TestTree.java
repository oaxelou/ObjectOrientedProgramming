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

public class TestTree {
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        
        Tree sampleTree = new Tree();
        TreeNode current = sampleTree.root;
        
        System.out.println("\nBinary Tree Operations:\n");
        
//        System.out.println("Operator or Number? (0 / 1): ");
//        int nodeChoice = scan.nextInt();
//        if(nodeChoice == 0){
//            System.out.println("Enter integer element to instert:");
//            sampleTree.insert(current, Tree.LEFT, 1);
//        }
//        else{
//            System.out.println("Enter double element to insert:");
//            sampleTree.insert(current, Tree.LEFT, scan.nextDouble());
//        }
        // tha mpei mia sunarthsh insert sto Tree.java
        // H domh ths omws eksartatai apo to pws diaxeirizomaste ta strings
        
        
        System.out.println(sampleTree.calculate()); 
    }
}
