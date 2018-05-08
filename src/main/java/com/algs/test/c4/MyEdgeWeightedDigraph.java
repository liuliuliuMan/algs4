package com.algs.test.c4;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class MyEdgeWeightedDigraph {
    private final int V;   //顶点总数
    private int E;       //边的总数
    private Bag<MyDirectedEdge>[] adj; //邻接表

    public MyEdgeWeightedDigraph(int V){
        if (V < 0) throw new IllegalArgumentException("Number of vertices in a Digraph must be nonnegative");
        this.V = V;
        this.E = 0;
        adj = (Bag<MyDirectedEdge>[]) new Bag[V];
        for (int v = 0; v < V; v++)
            adj[v] = new Bag<MyDirectedEdge>();
    }

    public MyEdgeWeightedDigraph(In in) {
        this(in.readInt());
        int E = in.readInt();
        if (E < 0) throw new IllegalArgumentException("Number of edges must be nonnegative");
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            validateVertex(v);
            validateVertex(w);
            double weight = in.readDouble();
            addEdge(new MyDirectedEdge(v, w, weight));
        }
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    public int V(){
        return V;
    }

    public int E(){
        return E;
    }

    public void addEdge(MyDirectedEdge edge){
        adj[edge.from()].add(edge);
        E++;
    }

    public Iterable<MyDirectedEdge> adj(int v){
        return adj[v];
    }

    public Iterable<MyDirectedEdge> edges() {
        Bag<MyDirectedEdge> bag = new Bag<MyDirectedEdge>();
        for (int v = 0; v < V; v++) {
            for (MyDirectedEdge e : adj(v)) {
                bag.add(e);
            }
        }
        return bag;
    }
}
