package com.algs.test.c4;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

import java.util.Queue;
import java.util.Stack;
import java.util.concurrent.LinkedBlockingDeque;

public class MyBreadthFirstPaths {

    private boolean[] marked; //到顶点的最短路径已知吗
    private int[] edgeTo;     //到达该顶点的已知路径上的最后的一个顶点
    private int s;          //起点

    public MyBreadthFirstPaths(MyGraph g,int s){
        marked = new boolean[g.V()];
        edgeTo = new int[g.V()];
        this.s = s;
        bfs(g,s);
    }

    private void bfs(MyGraph g,int s){
        Queue<Integer> queue = new LinkedBlockingDeque<Integer>();
        marked[s] = true;
        queue.add(s);
        while (!queue.isEmpty()){
            int v = queue.remove();
            for (int w:g.adj(v)){
                if (!marked[w]){
                    marked[w] = true;
                    edgeTo[w] = v;
                    queue.add(w);
                }
            }
        }
    }

    public boolean hasPathTo(int v){
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v){
        if (!hasPathTo(v)){
            return null;
        }
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s ; x = edgeTo[x]) {
            path.push(x);
        }
        path.push(s);
        return path;
    }

    public static void main(String[] args) {
        In in = new In(args[0]);
        MyGraph G = new MyGraph(in);
        int s = Integer.parseInt(args[1]);
        MyDepthFirstPath dfs = new MyDepthFirstPath(G, s);

        for (int v = 0; v < G.V(); v++) {
            if (dfs.hasPathTo(v)) {
                StdOut.printf(s + " to " + v + " :");
                for (int x : dfs.pathTo(v)) {
                    if (x == s) StdOut.print(x);
                    else        StdOut.print(x + "-");
                }
                StdOut.println();
            }

            else {
                StdOut.printf("%d to %d:  not connected\n", s, v);
            }
        }
    }



}
