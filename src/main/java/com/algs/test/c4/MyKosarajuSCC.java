package com.algs.test.c4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

public class MyKosarajuSCC {

    private boolean[] marked;
    private int[] id;
    private int count;

    public MyKosarajuSCC(MyDigraph g){
        marked = new boolean[g.V()];
        id = new int[g.V()];
        MyDFOrder order = new MyDFOrder(g.reverse());
        for (int s:order.reversePost()){
            if (!marked[s]){
                dfs(g,s);
                count++;
            }
        }
    }

    public void dfs(MyDigraph g,int v){
        marked[v] = true;
        id[v] = count;
        for (int w:g.adj(v)){
            if (!marked[w]){
                dfs(g,w);
            }
        }
    }

    public boolean stronglyConnected(int v,int w){

        return id[v] == id[w];
    }

    public int id(int v){
        return id[v];
    }

    public int count(){
        return count;
    }


    public static void main(String[] args) {
        In in = new In("tinyDG.txt");
        MyDigraph G = new MyDigraph(in);
        MyKosarajuSCC scc = new MyKosarajuSCC(G);

        // number of connected components
        int m = scc.count();
        StdOut.println(m + " strong components");

        // compute list of vertices in each strong component
        Queue<Integer>[] components = (Queue<Integer>[]) new Queue[m];
        for (int i = 0; i < m; i++) {
            components[i] = new Queue<Integer>();
        }
        for (int v = 0; v < G.V(); v++) {
            components[scc.id(v)].enqueue(v);
        }

        // print results
        for (int i = 0; i < m; i++) {
            for (int v : components[i]) {
                StdOut.print(v + " ");
            }
            StdOut.println();
        }

    }
}
