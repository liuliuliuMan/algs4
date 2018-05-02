package com.algs.test.c4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Stack;

public class MyDFDigraphPaths {
    private boolean[] marked;  //到该点的路径已知吗
    private int[] edgeTo;      //到改点的路径的上一个节点
    private int s;            //起点

    public MyDFDigraphPaths(MyDigraph g,int s){
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        this.s = s;
        dfs(g, s);
    }

    private void dfs(MyDigraph g,int v){
        marked[v] = true;
        for (int w:g.adj(v)){
            if (!marked[w]){
                edgeTo[w] = v;
                dfs(g,w);
            }
        }
    }

    public boolean hasPathTo(int v){
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v){
        if(!hasPathTo(v)){
            return null;
        }
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v;x != s;x = edgeTo[x]){
            path.push(x);
        }
        path.push(s);
        return path;
    }

    public static void main(String[] args) {
        In in = new In("tinyDG.txt");
        MyDigraph G = new MyDigraph(in);
        // StdOut.println(G);

        int s = 0;
        MyDFDigraphPaths dfs = new MyDFDigraphPaths(G, s);

        for (int v = 0; v < G.V(); v++) {
            if (dfs.hasPathTo(v)) {
                StdOut.printf("%d to %d:  ", s, v);
                for (int x : dfs.pathTo(v)) {
                    if (x == s) StdOut.print(x);
                    else        StdOut.print( x + "-");
                }
                StdOut.println();
            }

            else {
                StdOut.printf("%d to %d:  not connected\n", s, v);
            }

        }
    }
}
