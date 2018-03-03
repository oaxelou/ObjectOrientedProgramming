/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author oaxel
 */
public class Tree {
    TreeNode root;
    
    
    public static final int LEFT = 0;
    public static final int RIGHT = 1;
    
    public Tree(){
        root = new TreeNode();
    }
    
    //mallon den to xreiazomaste alla why not. pantou uparxei mia isEmpty
    public boolean isEmpty(){
        return root == null;
    }
    
    // function to insert an operator node
    public TreeNode insert(TreeNode node, int child, int operatorValue){
        if(node == null){
            System.out.println("Node is null. Should not have happened");
            return null;
        }
        else{
            if(child == LEFT){
                node.left = new TreeNode(operatorValue);
            }
            else{
                node.right = new TreeNode(operatorValue);
            }
        }
        return node;
    }
    
    // function to insert a number node
    public TreeNode insert(TreeNode node, int child, double numberValue){
        if(node == null){
            System.out.println("Node is null. Should not have happened");
            return null;
        }
        else{
            if(child == LEFT){
                node.left = new TreeNode(numberValue);
            }
            else{
                node.right = new TreeNode(numberValue);
            }
        }
        return node;
    }
    
    //mallon den to xreiazomaste
    public int countNodes(){
        return countNodes(root);
    }
    
    // function to count nodes recursively //mallon den to xreiazomaste
    public int countNodes(TreeNode r){
        if(r == null){
            return 0;
        }
        else{
            int l = 1;
            l += countNodes(r.getRight());
            l += countNodes(r.getLeft());
            return l;
        }
    }
    
    //mallon den to xreiazomaste
    public void postorder(){
        postorder(root);
    }
    
    //mallon den to xreiazomaste
    public void postorder(TreeNode r){
        if(r != null){
            postorder(r.getLeft());
            postorder(r.getRight());
            if(r.type == TreeNode.OPERATOR){
                System.out.print("+" + " ");
            }
            else{
                System.out.print(r.getValue() + " ");
            }    
            
        }
    }
    
    // EDW THA MPEI H CALCULATE
    public double calculate(){
        return calculate(root);
    }
    
    public double calculate(TreeNode r){
        if(r == null){
            return 0;
        }
        else if(r.right == null && r.left == null){ // IT'S A NUMBER
            if(r.type == TreeNode.NUMBER) // AN EXEI HDH ELEGXTHEI H if AYTH DEN XREIAZETAI. THA EINAI SIGOURA NUMBER
                return r.getValue();
        }
        else if(r.right == null || r.left == null){
            System.out.println("Error. Not supposed to happen.");
            return -1; //vres exit sunarthsh
        }
        // else.. IT'S AN OPERATOR (for now I assume it's a '+')
        return calculate(r.left) + calculate(r.right);
    }
    
    // tha mpei mia sunarthsh typou insert edw pou tha kalei thn hdh ulopoihmenh insert
    // H domh ths omws eksartatai apo to pws diaxeirizomaste ta strings
}
