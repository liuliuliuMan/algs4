package com.algs.test.c4;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class MyDigraphDFS {
    private boolean[] marked;

    public MyDigraphDFS(MyDigraph g,int s){
        marked = new boolean[g.V()];
        dfs(g,s);
    }

    public MyDigraphDFS(MyDigraph g,Iterable<Integer> sourse){
        marked = new boolean[g.V()];
        for (int s:sourse) {
            if (!marked(s)){
                dfs(g,s);
            }
        }
    }

    private void dfs(MyDigraph g,int v){
        marked[v] = true;
        for (int w:g.adj(v)) {
            if (!marked[w]){
                dfs(g,w);
            }
        }
    }

    public boolean marked(int v){
        return marked[v];
    }

    public static void main(String[] args) {

        // read in digraph from command-line argument
        In in = new In("tinyDG.txt");
        MyDigraph G = new MyDigraph(in);

        // read in sources from command-line arguments
        Bag<Integer> sources = new Bag<Integer>();
        sources.add(1);
        sources.add(2);
        sources.add(6);


        // multiple-source reachability
        MyDigraphDFS dfs = new MyDigraphDFS(G, sources);

        // print out vertices reachable from sources
        for (int v = 0; v < G.V(); v++) {
            if (dfs.marked(v)) StdOut.print(v + " ");
        }
        StdOut.println();
    }
}
