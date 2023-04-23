package com.kevin.javaDemo.algorithm;

public class Node<V>{
    private Node<Integer> parent;
    private Node<Integer> left;
    private Node<Integer> right;
    private V v;

    public static void main(String[] args) {
        Node<Integer> parent = new Node();
        parent.v = 0;
        createTree(2,3,parent);
    }

    static void createTree(int h,int height,Node<Integer> parent){
        if(height < 2){
            return;
        }
        while(h <= height){
            parent.left = createTree();
        }
    }

    public Node getParent() {
        return parent;
    }

    public void setParent(Node parent) {
        this.parent = parent;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
