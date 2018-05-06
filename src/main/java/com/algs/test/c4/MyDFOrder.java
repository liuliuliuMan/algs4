package com.algs.test.c4;


import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.Stack;

public class MyDFOrder {

    private boolean[] marked;
    private Queue<Integer> pre; //所有顶点的前序排列
    private Queue<Integer> post; //所有顶点的后序排列
    private Stack<Integer> reversePost;  //所有顶点的逆后序排列

    public MyDFOrder(MyDigraph g){
        marked = new boolean[g.V()];
        pre = new Queue<Integer>();
        post = new Queue<Integer>();
        reversePost = new Stack<Integer>();
        for (int v = 0;v<g.V();v++){
            if (!marked[v]){
                dfs(g,v);
            }
        }

    }

    private void dfs(MyDigraph g,int v){
        pre.enqueue(v);
        for (int w:g.adj(v)){
            if (!marked[w]){
                dfs(g,w);
            }
        }
        post.enqueue(v);
        reversePost.push(v);
    }

    public Iterable<Integer> pre(){
        return pre;
    }

    public Iterable<Integer> post(){
        return post;
    }

    public Iterable<Integer> reversePost(){
        return reversePost;
    }
}
