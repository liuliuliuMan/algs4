package com.algs.test.c3;

public class MyRedBlackBST<Key extends Comparable<Key>, Value> {
    private static final boolean RED   = true;
    private static final boolean BLACK = false;

    private Node root;     // root of the BST

    // BST helper node data type
    private class Node {
        private Key key;           // key
        private Value val;         // associated data
        private Node left, right;  // links to left and right subtrees
        private boolean color;     // color of parent link
        private int size;          // subtree count

        public Node(Key key, Value val, boolean color, int size) {
            this.key = key;
            this.val = val;
            this.color = color;
            this.size = size;
        }
    }

    // is node x red; false if x is null ?
    private boolean isRed(Node x) {
        if (x == null) return false;
        return x.color == RED;
    }

    // number of node in subtree rooted at x; 0 if x is null
    private int size(Node x) {
        if (x == null) return 0;
        return x.size;
    }

    public int size(){
        return size(root);
    }

    public boolean isEmpty() {
        return root == null;
    }

    public void put(Key key,Value val){
        if (key == null) throw new IllegalArgumentException("first argument to put() is null");
        if (val == null) {
            //delete(key);
            return;
        }

        root = put(root,key,val);
        root.color = BLACK;
    }

    private Node put(Node h, Key key, Value val){
        if (h == null){
            return new Node(key,val,RED,1);
        }

        int cmp = key.compareTo(h.key);
        if (cmp < 0){
            h.left = put(h.left,key,val);
        }else if(cmp > 0){
            h.right = put(h.right,key,val);
        }else{
            h.val = val;
        }

        if (!isRed(h.left)&&isRed(h.right)){
            h = rotateLeft(h);
        }
        if (isRed(h.left)&&isRed(h.left.left)){
            h = rotateRight(h);
        }
        if (isRed(h.left)&&isRed(h.right)){
            flipColors(h);
        }

        h.size = size(h.left) + 1 + size(h.right);

        return h;
    }

    private void flipColors(Node h){
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    private Node rotateLeft(Node h){
        Node x = h.right;
        h.right = x.left;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        x.size = h.size;
        h.size = size(h.left) + 1 + size(h.right);

        return x;
    }

    private Node rotateRight(Node h){
        Node x = h.left;
        h.left = x.right;
        x.right = h;
        x.color = h.color;
        h.color = RED;
        x.size = h.size;
        h.size = size(h.left) + 1 + size(h.right);

        return x;
    }
}
