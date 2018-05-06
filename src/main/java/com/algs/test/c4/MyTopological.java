package com.algs.test.c4;

public class MyTopological {
    private Iterable<Integer> order;

    public MyTopological(MyDigraph g){
        MyDirectedCycle cycleFinder = new MyDirectedCycle(g);
        if (!cycleFinder.hasCycle()){
            MyDFOrder dfs = new MyDFOrder(g);

            order = dfs.reversePost();
        }
    }

    public Iterable<Integer> order(){
        return order;
    }

    public boolean isDAG(){
        return order != null;
    }
}
