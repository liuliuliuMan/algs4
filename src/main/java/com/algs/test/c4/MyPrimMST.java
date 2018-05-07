package com.algs.test.c4;

import edu.princeton.cs.algs4.IndexMaxPQ;
import edu.princeton.cs.algs4.IndexMinPQ;

public class MyPrimMST {

    private MyEdge[] edgeTo;    //距离树最近的边
    private double[] distTo;    //edgeTo[w].weight();
    private boolean[] marked;    //v是否在树中
    private IndexMinPQ pq;  //有效的横切边

    public MyPrimMST(MyEdgeWeightedGraph G){
        edgeTo = new MyEdge[G.V()];
        distTo = new double[G.V()];
        pq = new IndexMinPQ<Double>(G.V());
        marked = new boolean[G.V()];
        for (int v = 0; v < G.V(); v++)
            distTo[v] = Double.POSITIVE_INFINITY;

        distTo[0] = 0.0;
        pq.insert(0,0.0);   //初始化pq
        while (!pq.isEmpty()){
            visit(G,pq.delMin());
        }


    }

    private void visit(MyEdgeWeightedGraph G,int v){
        marked[v] = true;
        for (MyEdge edge:G.adj(v)){
            int w = edge.other(v);
            if (marked[w]){   //v-w失效
                continue;
            }

            if(edge.weight() < distTo[w]){
                //连接w和树的最佳边为edge
                edgeTo[w] = edge;
                distTo[w] = edge.weight();
                if (pq.contains(w)){
                    pq.changeKey(w,distTo[w]);
                }else {
                    pq.insert(w,distTo[w]);
                }

            }
        }
    }
}
