package com.algs.test.c4;

import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Stack;

public class MyDijkstraSP {

    private double[] distTo;          // distTo[v] = distance  of shortest s->v path
    private MyDirectedEdge[] edgeTo;    // edgeTo[v] = last edge on shortest s->v path
    private IndexMinPQ<Double> pq;    // priority queue of vertices

    public MyDijkstraSP(MyEdgeWeightedDigraph G,int s){
        distTo = new double[G.V()];
        edgeTo = new MyDirectedEdge[G.V()];

        pq = new IndexMinPQ<Double>(G.V());


        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;
        distTo[s] = 0.0;

        pq.insert(s, distTo[s]);
        while (!pq.isEmpty()){
            relax(G,pq.delMin());
        }
    }

    private void relax(MyEdgeWeightedDigraph G,int v){
        for (MyDirectedEdge edge:G.adj(v)){
            int w = edge.to();
            if (distTo[w] > distTo[v] + edge.weight()){
                distTo[w] = distTo[v] + edge.weight();
                edgeTo[w] = edge;
                if (pq.contains(w)){
                    pq.changeKey(w,distTo[w]);
                }else {
                    pq.insert(w,distTo[w]);
                }
            }
        }
    }

    public double distTo(int v){
        return distTo[v];
    }

    public boolean hasPathTo(int v){
        return distTo[v] < Double.POSITIVE_INFINITY;
    }

    public Iterable<MyDirectedEdge> pathTo(int v) {
        validateVertex(v);
        if (!hasPathTo(v)) return null;
        Stack<MyDirectedEdge> path = new Stack<MyDirectedEdge>();
        for (MyDirectedEdge e = edgeTo[v]; e != null; e = edgeTo[e.from()]) {
            path.push(e);
        }
        return path;
    }

    private void validateVertex(int v) {
        int V = distTo.length;
        if (v < 0 || v >= V)
            throw new IllegalArgumentException("vertex " + v + " is not between 0 and " + (V-1));
    }
}
