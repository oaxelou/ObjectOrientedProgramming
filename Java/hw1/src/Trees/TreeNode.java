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
    int operatorValue; // enum.... klp operators klash
    double numberValue;
    TreeNode left;
    TreeNode right;
    
    public static final int OPERATOR = 0;
    public static final int NUMBER = 1;
    
    TreeNode(int operatorValue){
        type = OPERATOR;
        this.operatorValue = operatorValue;
        right = null;
        left = null;
    }
    
    TreeNode(double numberValue){
        type = NUMBER;
        this.numberValue = numberValue;
        right = null;
        left = null;
    }
    
    TreeNode(){
    }
    
    public void setLeft(TreeNode n){
        left = n;
    }
    
    public void setRight(TreeNode n){
        right = n;
    }

    public void setType(double numberValue){
        type = NUMBER;
        this.numberValue = numberValue;
    }
    
    public void setType(int operatorValue){
        type = OPERATOR;
        this.operatorValue = operatorValue;
    }
    
    public TreeNode getRight(){
        return right;
    }
    
    public TreeNode getLeft(){
        return left;
    }
    
    public double getValue(){
        if(type == NUMBER) 
            return numberValue;
        System.out.println("Not a number. Can not return value");
//        java.lang.System.1exit(-1);
        return -1;        
    }
}
