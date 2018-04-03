package com.algs.test.c2;

public class MyShell extends MyExample{
    public static void sort(Comparable[] a){
        int n = a.length;
        int h = 1;
        while (h< n/3){
            h = 3 * h + 1;
        }
        while (h >= 1){
            //将数组变成h有序
            for (int i = h; i < n ; i++) {
                for (int j = i; j >= h && less(a[j],a[j-h])  ; j -= h) {
                    exch(a,j,j-h);
                }
            }
            h = h / 3;
        }
    }

    public static void main(String[] args) {
            Integer[] a ={1,99,11,32,5,22,45,12,3,9,6};
            show(a);
            MyShell.sort(a);
            show(a);
    }
}
