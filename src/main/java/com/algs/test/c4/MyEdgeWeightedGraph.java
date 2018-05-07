package com.algs.test.c4;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class MyEdgeWeightedGraph {

    private final int V;
    private int E;
    private Bag<MyEdge>[] adj;

    public MyEdgeWeightedGraph(int V) {
        if (V < 0) throw new IllegalArgumentException("Number of vertices must be nonnegative");
        this.V = V;
        this.E = 0;
        adj = (Bag<MyEdge>[]) new Bag[V];
        for (int v = 0; v < V; v++) {
            adj[v] = new Bag<MyEdge>();
        }
    }

    public MyEdgeWeightedGraph(In in) {
        this(in.readInt());
        int E = in.readInt();
        if (E < 0) throw new IllegalArgumentException("Number of edges must be nonnegative");
        for (int i = 0; i < E; i++) {
            int v = in.readInt();
            int w = in.readInt();
            validateVertex(v);
            validateVertex(w);
            double weight = in.readDouble();
            MyEdge e = new MyEdge(v, w, weight);
            addEdge(e);
        }
    }

    private void validateVertex(int v) {
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }

    public void addEdge(MyEdge e) {
        int v = e.either();
        int w = e.other(v);
        validateVertex(v);
        validateVertex(w);
        adj[v].add(e);
        adj[w].add(e);
        E++;
    }

    public Iterable<MyEdge> adj(int v) {
        validateVertex(v);
        return adj[v];
    }

    public Iterable<MyEdge> edges() {
        Bag<MyEdge> list = new Bag<MyEdge>();
        for (int v = 0; v < V; v++) {
            int selfLoops = 0;
            for (MyEdge e : adj(v)) {
                if (e.other(v) > v) {
                    list.add(e);
                }
                // add only one copy of each self loop (self loops will be consecutive)
                else if (e.other(v) == v) {
                    if (selfLoops % 2 == 0) list.add(e);
                    selfLoops++;
                }
            }
        }
        return list;
    }

    public int V(){
        return V;
    }

    public int E() {
        return E;
    }
}
