package com.kevin.javaDemo.algorithm;

public class Node<V>{
    private Node<Integer> parent;
    private Node<Integer> left;
    private Node<Integer> right;
    private V v;

    public static void main(String[] args) {
        Node<Integer> parent = new Node();
        parent.v = 0;
        createTree(2,5,parent);
        System.out.println();
    }

    static void createTree(int h,int height,Node<Integer> parent){
        if(height < 2){
            return;
        }
        while(h <= height){
            if(parent.left != null){
                return;
            }
            Node<Integer> right = new Node();
            Node<Integer> left = new Node();
            right.parent = parent;
            right.v = parent.v * 2 + 2;
            left.parent = parent;
            left.v = parent.v * 2 + 1;
            parent.right = right;
            parent.left = left;
            h++;
            createTree(h,height,parent.left);
            createTree(h,height,parent.right);
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
