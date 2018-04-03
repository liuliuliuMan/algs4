package com.algs.test.c2;

public class MyExample {

    public static void sort(Comparable[] a){

    }

    // is v < w ?
    protected static boolean less(Comparable v, Comparable w) {
        return v.compareTo(w) < 0;
    }

    // exchange a[i] and a[j]
    protected static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }

    protected static void show(Comparable[] a) {
        System.out.println();
        for (Comparable c:a) {
            System.out.print(c+" ");
        }
    }

    // is the array a[] sorted?
    protected static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    // is the array sorted from a[lo] to a[hi]
    protected static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }
}
