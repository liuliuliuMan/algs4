package com.algs.test.c4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class MyDepthFirstSearsh {

    private boolean[] marked;
    private int count;

    public MyDepthFirstSearsh(MyGraph g,int s){
        marked = new boolean[g.V()];
        dfs(g,s);
    }

    public void dfs(MyGraph g,int v){
        marked[v] = true;
        count++;
        for (int w:g.adj(v)) {
            if (!marked[w]){
                dfs(g,w);
            }
        }
    }

    public boolean marked(int v){
        return marked[v];
    }

    public int count(){
        return count;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        MyGraph G = new MyGraph(in);
        int s = Integer.parseInt(args[1]);
        MyDepthFirstSearsh search = new MyDepthFirstSearsh(G, s);
        for (int v = 0; v < G.V(); v++) {
            if (search.marked(v))
                StdOut.print(v + " ");
        }

        StdOut.println();
        if (search.count() != G.V()) StdOut.println("NOT connected");
        else                         StdOut.println("connected");
    }
}
