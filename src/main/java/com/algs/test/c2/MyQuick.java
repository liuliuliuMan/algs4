package com.algs.test.c2;

import edu.princeton.cs.algs4.StdRandom;

public class MyQuick extends MyExample {

    public static int partition(Comparable[] a,int lo,int hi){
        int i = lo;
        int j = hi + 1;
        Comparable v = a[lo];
        while(true){
            while(less(a[++i],v)){
                if (i == hi){
                    break;
                }
            }
            while(less(v,a[--j])){
                if (j == lo){
                    break;
                }
            }
            if (i >= j) break;
            exch(a,i,j);
        }
        exch(a,lo,j);   //将v = a[j]放入正确位置
        return j;
    }

    public static void sort(Comparable[] a){
        StdRandom.shuffle(a);
        sort(a,0,a.length-1);
    }

    private static void sort(Comparable[] a,int lo,int hi){
        if (hi <= lo){
            return;
        }
        int j = partition(a,lo,hi);
        sort(a,lo,j-1);
        sort(a,j+1,hi);
    }

    public static void main(String[] args) {
        Integer[] a ={1,99,11,32,5,22,45,12,3,9,6};
        show(a);
        MyQuick.sort(a);
        show(a);
    }
}
