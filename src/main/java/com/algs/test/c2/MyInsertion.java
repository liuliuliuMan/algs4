package com.algs.test.c2;

public class MyInsertion extends MyExample {

    public static void sort(Comparable[] a){
        int n = a.length;
        for (int i = 0; i < n ; i++) {
            for (int j = i; j >0 && less(a[j],a[j-1]) ; j--) {
                exch(a,j,j-1);
            }
        }
    }

    public static void sortX(Comparable[] a){
        int n = a.length;
        for (int i = 0; i < n ; i++) {
            Comparable c = a[i];
            int j = i-1;
            for (; j >= 0&&less(c,a[j]); j--) {
                    a[j+1] = a[j];
            }
            a[j+1] = c;
        }
    }

    public static void main(String[] args) {
        Integer[] a ={1,99,11,32,5,22,45,12,3,9,6};
        show(a);
        MyInsertion.sortX(a);
        show(a);

    }
}
