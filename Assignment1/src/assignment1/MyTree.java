/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package assignment1;

/**
 *
 * @author LudeersBärbara
 */
public class MyTree implements A1Tree {
    
    int size;
    MyTreeNode root;
    
    public MyTree(){
        root = null;
        size = 0;
    }
    
    public A1TreeNode root(){
        return root;
    }
    public void addChild(MyTreeNode parent, MyTreeNode child){
        if(parent == null){
            root = child;
        }
        else{
            parent.children.enqueue(child);
            child.parent = parent;
        }
        size++;
    }
    
    public int size(){
        return size;
    }
    
    public void printTree(){
        if(root.hasChildren()){
            System.out.println(root.element);
            printChildren(root, 0);
        }
        else if(!root.hasChildren()){
            System.out.println(root.element);
        }
        else{
            System.out.println("Tree is Empty");
        }
    }
    
    public void printChildren(MyTreeNode n, int indent){
        String indentation = "";
        for(int i = 0; i < indent; i++){
            indentation += "\t";
        }
        
        int length = n.getChildren().length;
        for(int i = 0; i < length; i++){
            MyTreeNode temp = (MyTreeNode)n.getChildren().dequeue();
            if(temp.hasChildren()){
                System.out.println( temp.element);
                printChildren(temp, indent += 1);
            }
            else{
                if(temp.element.equals(":")){
                    System.out.print(temp.element + " ");
                }
                else if(temp.parent.element.equals("{") && temp.element.equals(",")){
                    System.out.println(temp.element);
                }
                
                else if(temp.parent.element.equals("[") && temp.element.equals(",")){
                    if(temp.parent.hasChildren()){
                        MyTreeNode secTemp = (MyTreeNode)n.getChildren().peek();
                        if(secTemp.element.equals("[") || secTemp.element.equals("{")){
                            System.out.print(temp.element + " ");
                        }
                        else{
                            System.out.println(temp.element);
                        }
                    }
                    else{
                        System.out.println(temp.element);
                    }
                }
                
                else if(i == (length - 2) && temp.parent.element.equals("[")){
                    System.out.println(indentation + temp.element);
                }
                else if(i == (length - 2) && temp.parent.element.equals("{")){
                    System.out.println(temp.element);
                }
                        
                else if(temp.element.equals("}")){
                    System.out.print(indentation + temp.element);
                }
                else if(temp.element.equals("]")){
                    System.out.println(indentation + temp.element);
                }
                else{
                    System.out.print(indentation + temp.element);
                }
            }
        }
    }
}
