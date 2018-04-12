package com.algs.test.c3;

import java.util.NoSuchElementException;

public class MyBST <Key extends Comparable<Key>, Value>{
    private Node root;             // root of BST

    private class Node {
        private Key key;           // sorted by key
        private Value val;         // associated data
        private Node left, right;  // left and right subtrees
        private int size;          // number of nodes in subtree

        public Node(Key key, Value val, int size) {
            this.key = key;
            this.val = val;
            this.size = size;
        }
    }

    public int size(Node x){
        if (x == null){
            return 0;
        }
        return x.size;
    }

    public int size(){
        return size(root);
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public Value get(Key key){
        return get(root,key);
    }

    private Value get(Node x,Key key){
        if (x == null){
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0 ){
            return get(x.left,key);
        }else if(cmp > 0){
            return get(x.right,key);
        }else{
            return x.val;
        }
    }

    public void put(Key key,Value val){
        root = put(root,key,val);
    }

    private Node put(Node x,Key key,Value val){
        if (x == null){
            return new Node(key,val,1);
        }
        int cmp = key.compareTo(x.key);
        if (cmp > 0){
            x.right = put(x.right,key,val);
        }else if(cmp < 0){
            x.left = put(x.left,key,val);
        }else {
            x.val = val;
        }

        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    public Key min(){
        return min(root).key;
    }

    private Node min(Node x){
        if (x.left == null){
            return x;
        }
        return min(x.left);
    }

    public Key max(){
        return max(root).key;
    }

    private Node max(Node x){
        if (x.right == null){
            return x;
        }
        return max(x.right);
    }

    public Key floor(Key key){
        if (key == null) throw new IllegalArgumentException("argument to floor() is null");
        if (isEmpty()) throw new NoSuchElementException("calls floor() with empty symbol table");
        Node x = floor(root, key);
        if (x == null) return null;
        else return x.key;
    }

    private Node floor(Node x,Key key){
        if (x == null){
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0){
            return x;
        }else if (cmp < 0){
            return floor(x.left,key);
        }

        Node t = floor(x.right,key);
        if (t !=null){
            return t;
        }else {
            return x;
        }
    }

    public Key ceiling(Key key) {
        if (key == null) throw new IllegalArgumentException("argument to ceiling() is null");
        if (isEmpty()) throw new NoSuchElementException("calls ceiling() with empty symbol table");
        Node x = ceiling(root, key);
        if (x == null) return null;
        else return x.key;
    }

    private Node ceiling(Node x,Key key){
        if(x == null){
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0){
            return x;
        }else if(cmp > 0){
            return ceiling(x.right,key);
        }

        Node t = ceiling(x.left,key);
        if (t != null){
            return t;
        }else {
            return x;
        }
    }

    public Key select(int k){
        return select(root,k).key;
    }

    private Node select(Node x,int k){
        //返回排名k的节点
        if (x == null){
            return null;
        }

        int t = size(x.left);
        if (t > k){
            return select(x.left,k);
        }else if (t < k){
            return select(x.right,k-t-1);
        }else {
            return x;
        }
    }

    public int rank(Key key){
        return rank(root,key);
    }

    private int rank(Node x,Key key){
        if (x == null){
            return 0;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0){
            return rank(x.left,key);
        }else if(cmp > 0){
            return size(x.left) + 1 + rank(x.right,key);
        }else {
            return size(x.left);
        }
    }

    public void deleteMin(){
        root = deleteMin(root);
    }

    private Node deleteMin(Node x){
        if (x.left == null){
            return x.right;
        }
        x.left = deleteMin(x.left);
        x.size = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void delete(Key key){
        root = delete(root,key);
    }

    private Node delete(Node x,Key key){
        if (x == null){
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0){
            x.left = delete(x.left,key);
        }else if (cmp > 0){
            x.right = delete(x.right,key);
        }else{
            if (x.left == null){
                return x.right;
            }
            if (x.right == null){
                return x.left;
            }
            Node t = x;
            x = min(t.right);
            x.right = deleteMin(t.right);
            x.left = t.left;
        }
        x.size = size(x.left) + 1 + size(x.right);
        return x;
    }

}
