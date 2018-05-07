package com.algs.test.c4;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Queue;

public class MyLazyPrimMST {

    private boolean[] marked;
    private Queue<MyEdge> mst; //最小生成树的边
    private MinPQ<MyEdge> pq;   //横切边

    public MyLazyPrimMST(MyEdgeWeightedGraph G){
        pq = new MinPQ<MyEdge>();
        mst = new Queue<MyEdge>();
        marked = new boolean[G.V()];

        visit(G,0);   //假设G是连通的

        while (!pq.isEmpty()){
            MyEdge edge = pq.delMin();  //得到权重最小的边

            int v = edge.either();
            int w = edge.other(v);

            if (marked[v] && marked[w]){   //无效边跳过
                continue;
            }

            mst.enqueue(edge);   //将边添加到生成树中

            if (!marked[v]){   //将顶点v添加到树中
                visit(G,v);
            }

            if (!marked[w]){
                visit(G,w);
            }

        }
    }

    private void visit(MyEdgeWeightedGraph G,int v){
        //标记V并将所有连接v的和未标记的边加入
        marked[v] = true;
        for (MyEdge edge:G.adj(v)){
            if (!marked[edge.other(v)]){
                pq.insert(edge);
            }
        }

    }

    public Iterable<MyEdge> edges(){
        return mst;
    }
}
