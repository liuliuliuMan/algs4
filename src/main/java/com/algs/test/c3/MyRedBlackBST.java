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

    // restore red-black tree invariant
    private Node balance(Node h) {
        // assert (h != null);

        if (isRed(h.right))                      h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right))     flipColors(h);

        h.size = size(h.left) + size(h.right) + 1;
        return h;
    }

}
