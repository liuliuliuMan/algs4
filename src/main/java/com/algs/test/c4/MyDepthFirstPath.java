package com.algs.test.c4;


import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Stack;

public class MyDepthFirstPath {
    private boolean[] marked;
    private int[] edgeTo;
    private int s;

    public MyDepthFirstPath(MyGraph g,int s){
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        this.s = s;
        dfs(g,s);
    }

    private void dfs(MyGraph g,int v){
        marked[v] = true;
        for (int w:g.adj(v)) {
            if (!marked[w]){
                edgeTo[w] = v;
                dfs(g,w);
            }
        }
    }

    public boolean hasPathTo(int w){
        return marked[w];
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
        In in = new In(args[0]);
        MyGraph G = new MyGraph(in);
        int s = Integer.parseInt(args[1]);
        MyDepthFirstPath dfs = new MyDepthFirstPath(G, s);

        for (int v = 0; v < G.V(); v++) {
            if (dfs.hasPathTo(v)) {
                StdOut.printf(s + " to " + v + " :");
                for (int x : dfs.pathTo(v)) {
                    if (x == s) StdOut.print(x);
                    else        StdOut.print(x + "-");
                }
                StdOut.println();
            }

            else {
                StdOut.printf("%d to %d:  not connected\n", s, v);
            }
        }
    }


}
