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
        
        Tree sampleTree = new Tree(); //insertion with preorder
        System.out.println("\nCreating a tree.\n********************************\nPRE-ORDER!");
	sampleTree.insert();
	//System.out.println("In Tree constructor: root value = " + sampleTree.root.numberValue);

        System.out.println("The tree");
        System.out.println(sampleTree.toString());
        
        System.out.println("The calculation: " + sampleTree.calculate()); 
    }
}
