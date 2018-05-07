package com.algs.test.c4;


import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.UF;

public class MyKruskalMST {

    private Queue<MyEdge> mst;
    private double weight;                        // weight of MST

    public MyKruskalMST(MyEdgeWeightedGraph G){
       mst = new Queue<MyEdge>();
        MinPQ<MyEdge> pq = new MinPQ<MyEdge>();
        for (MyEdge edge:G.edges()){
            pq.insert(edge);
        }
        UF uf = new UF(G.V());

        while (!pq.isEmpty() && mst.size() < (G.V()-1)){
            MyEdge e = pq.delMin();  //得到pq最小的边和顶点
            int v = e.either();
            int w = e.other(v);
            if (uf.connected(v,w)){  //忽略失效的边
                continue;
            }
            uf.union(v,w);    //合并分量
            mst.enqueue(e);    //将边添加到生成树中

            weight += e.weight();
        }
    }

    public double weight() {
        return weight;
    }

    public Iterable<MyEdge> edges() {
        return mst;
    }
}
