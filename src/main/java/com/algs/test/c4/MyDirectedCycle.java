package com.algs.test.c4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Stack;

public class MyDirectedCycle {
    private boolean[] marked;
    private int[] edgeTo;
    private boolean[] onStack;
    private Stack<Integer> cycle;

    public MyDirectedCycle(MyDigraph g){
        onStack = new boolean[g.V()];
        edgeTo = new int[g.V()];
        marked = new boolean[g.V()];
        for (int v = 0;v < g.V();v++){
            if (!marked[v]){
                dfs(g,v);
            }
        }
    }

    private void dfs(MyDigraph g,int v){
        marked[v] = true;
        onStack[v] = true;
        for (int w:g.adj(v)){
            if (this.hasCycle()){
                return;
            }else if (!marked[w]){
                edgeTo[w] = v;
                dfs(g,w);
            }else if(onStack[w]){
                cycle = new Stack<Integer>();
                for (int x = v;x != w;x = edgeTo[x]){
                    cycle.push(x);
                }
                cycle.push(w);
                cycle.push(v);
            }
        }
        onStack[v] = false;
    }

    public boolean hasCycle(){
        return cycle != null;
    }

    public Iterable<Integer> cycle(){
        return cycle;
    }

    public static void main(String[] args) {
        In in = new In("tinyDG.txt");
        MyDigraph G = new MyDigraph(in);

        MyDirectedCycle finder = new MyDirectedCycle(G);
        if (finder.hasCycle()) {
            StdOut.print("Directed cycle: ");
            for (int v : finder.cycle()) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        }

        else {
            StdOut.println("No directed cycle");
        }
        StdOut.println();
    }
}
