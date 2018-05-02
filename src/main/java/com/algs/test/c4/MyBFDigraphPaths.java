package com.algs.test.c4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingDeque;

public class MyBFDigraphPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private int s;

    public MyBFDigraphPaths(MyDigraph g,int s){
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        dfs(g,s);
    }

    private void dfs(MyDigraph g,int s){
        Queue<Integer> queue = new LinkedBlockingDeque<Integer>();
        marked[s] = true;
        queue.add(s);
        while (!queue.isEmpty()){
            int v = queue.remove();
            for (int w:g.adj(v)){
                if (!marked[w]){
                    marked[w] = true;
                    edgeTo[w] = v;
                    queue.add(w);
                }
            }
        }
    }

    public boolean hasPathTo(int v){
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v){
        if (!hasPathTo(v)){
            return null;
        }
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s ; x = edgeTo[x]) {
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
        MyBFDigraphPaths dfs = new MyBFDigraphPaths(G, s);

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
