package com.algs.test.c2;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;

public class MySelection extends MyExample {

    public static void sort(Comparable[] a){
        int n = a.length;
        for (int i = 0; i < n ; i++) {
            int min = i;
            for (int j = i+1; j < n ; j++) {
                if (less(a[j],a[min])){
                    min = j;
                }
            }
            exch(a,i,min);
        }
    }

    public static void main(String[] args) {
        Integer[] a ={1,99,11,32,5,22,45,12,3,9,6};
        show(a);
        MySelection.sort(a);
        show(a);

    }

}
