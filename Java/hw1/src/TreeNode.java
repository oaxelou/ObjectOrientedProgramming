/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author oaxel
 */
public class TreeNode {
    int type; // values: 0 OPERATOR, 1 NUMBER
    char operatorValue; // enum.... klp operators klash
    double numberValue;
    TreeNode left;
    TreeNode right;
    
    public static final int OPERATOR = 0;
    public static final int NUMBER = 1;
	
	private static int nodeID = 0;
	private static String toDotStr = "\n";
    
    TreeNode(char operatorValue){
	//System.out.println("In char constructor");
        type = OPERATOR;
        this.operatorValue = operatorValue;
        right = null;
        left = null;
    }
    
    TreeNode(double numberValue){
	//System.out.println("In double constructor");
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
    
    public double getNumberValue(){
        if(type == NUMBER) 
            return numberValue;
        return -1;        
    }
	
	public String getStringValue(){
        if(type == NUMBER) 
            return ""+numberValue;
        else{ // type == OPERATOR
            return ""+operatorValue;   
		}			
    }
	
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
	
	public String toDotString(){
		// if(r == null){ return null;}
		
		int myID = nodeID;
		System.out.println("myID: " + myID);
		
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