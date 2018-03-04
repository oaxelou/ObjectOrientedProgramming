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
        sampleTree.insert();
		
        System.out.println("\n\n------- toDotString -------");
		sampleTree.toDotString();

        System.out.println("\n\n-------- toString --------");
        System.out.println(sampleTree.toString());
        
        System.out.println("\n\n-------- Calculate --------");
        System.out.println(sampleTree.calculate());
    }
}
