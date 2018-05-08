package com.algs.test.c4;

public class MyDirectedEdge {

    private int v;           //起点
    private int w;           //终点
    private double weight;   //权重

    public MyDirectedEdge(int v,int w,double weight){
        this.v = v;
        this.w = w;
        this.weight = weight;
    }

    public double weight(){
        return weight;
    }

    public int from(){
        return v;
    }

    public int to(){
        return w;
    }

    public String toString() {
        return v + "->" + w + " " + String.format("%5.2f", weight);
    }

}
