package com.algs.test.c3;

import java.util.NoSuchElementException;

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

    public Value get(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to get() is null");
        return get(root, key);
    }

    // value associated with the given key in subtree rooted at x; null if no such key
    private Value get(Node x, Key key) {
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if      (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else              return x.val;
        }
        return null;
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
        h.color = !h.color;
        h.left.color = !h.left.color;
        h.right.color = !h.right.color;
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

    // Assuming that h is red and both h.left and h.left.left
    // are black, make h.left or one of its children red.
    private Node moveRedLeft(Node h){
        //把上面的节点"拉下来"，形成一个大节点
        flipColors(h);
        //h.right为3节点时，借给左边一个
        if (isRed(h.right.left)){
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            //这一行代表借了一个节点之后，再还一个给父节点，防止变成一个大节点
            flipColors(h);
        }
        return h;
    }

    // Assuming that h is red and both h.right and h.right.left
    // are black, make h.right or one of its children red.
    private Node moveRedRight(Node h){
        //变成一个大节点
        flipColors(h);
        if (isRed(h.left.left)) {
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }

    public void deleteMin(){
        //防止root变成4-节点
        if (!isRed(root.left)&&isRed(root.right)){
            root.color = RED;
        }
        root = deleteMin(root);

        if (!isEmpty()){
            root.color = BLACK;
        }
    }

    private Node deleteMin(Node h){
        //删除
        if (h.left == null){
            return null;
        }
        //h.left为2节点
        if (!isRed(h.left)&&!isRed(h.left.left)){
            moveRedLeft(h);
        }

        h.left = deleteMin(h.left);
        return balance(h);
    }

    public void deleteMax(){
        // if both children of root are black, set root to red
        if (!isRed(root.left) && !isRed(root.right))
            root.color = RED;

        root = deleteMax(root);
        if (!isEmpty()) root.color = BLACK;
    }

    private Node deleteMax(Node h){
        //保证方向的一致性,在底层将红色左节点转向右边删除
        if (isRed(h.left)){
            h = rotateRight(h);
        }
        if (h.right == null){
            //到最右边，删除
            return null;
        }
        if (!isRed(h.right)&&!isRed(h.right.left)){
            //h.right为2节点
            h = moveRedRight(h);
        }
        h.right = deleteMax(h.right);
        return balance(h);
    }

    public void delete(Key key){
        if(!isRed(root.left)&&!isRed(root.right)){
            root.color = RED;
        }
        root = delete(root,key);
        if (!isEmpty()){
            root.color = BLACK;
        }
    }

    private Node delete(Node h,Key key){
        if (key.compareTo(h.key) < 0){
            if (!isRed(h.left)&&!isRed(h.left.left)){
                h = moveRedLeft(h);
            }
            h.left = delete(h.left,key);
        }else {
            if (isRed(h.left)){
                h = rotateRight(h);
            }
            if (key.compareTo(h.key)==0&&(h.right == null)){
                return null;
            }
            if (!isRed(h.right)&&!isRed(h.right.left)){
                h = moveRedRight(h);
            }
            if (key.compareTo(h.key)==0){
                Node x= min(h.right);
                h.key = x.key;
                h.val = x.val;
                h.right = deleteMin(h.right);
            }else {
                h.right = delete(h.right,key);
            }
        }
        return balance(h);
    }

    // restore red-black tree invariant
    private Node balance(Node h) {
        // assert (h != null);

        if (isRed(h.right))                      h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right))     flipColors(h);

        h.size = size(h.left) + size(h.right) + 1;
        return h;
    }

    public Key min() {
        if (isEmpty()) throw new NoSuchElementException("calls min() with empty symbol table");
        return min(root).key;
    }

    // the smallest key in subtree rooted at x; null if no such key
    private Node min(Node x) {
        // assert x != null;
        if (x.left == null) return x;
        else                return min(x.left);
    }

    public Key max() {
        if (isEmpty()) throw new NoSuchElementException("calls max() with empty symbol table");
        return max(root).key;
    }

    // the largest key in the subtree rooted at x; null if no such key
    private Node max(Node x) {
        // assert x != null;
        if (x.right == null) return x;
        else                 return max(x.right);
    }

}
