package com.algs.test.c2;

public class MyMerge extends MyExample {
    
    private static Comparable[] aux;
    
    public static void merge(Comparable[] a,int lo ,int mid,int hi){
        int i = lo;
        int j = mid + 1;
        for (int k = lo; k <= hi ; k++) {
            aux[k] = a[k];
        }
        for (int k = lo; k <= hi; k++) {
            if(i > mid){
                a[k] = aux[j++];
            }else if (j > hi){
                a[k] = aux[i++];
            }else if (less(aux[j],aux[i])){     //
                a[k] = aux[j++];
            }else{
                a[k] = aux[i++];
            }
        }
    }

    public static void sortX(Comparable[] a){
        int N = a.length;
        aux = new Comparable[N];
        for (int sz = 1; sz < N ; sz = sz + sz) {
            for (int lo = 0;lo < N-sz;lo+= sz + sz){
                merge(a,lo,lo+sz-1,Math.min(lo+sz+sz-1,N-1));
            }
        }
    }

    public static void sort(Comparable[] a){
        aux = new Comparable[a.length];
        sort(a,0,a.length-1);
    }


    public static void sort(Comparable[] a,int lo,int hi){
        if (hi <= lo){
            return;
        }
       int mid = lo + (hi - lo)/2;
       sort(a, lo, mid);
       sort(a, mid+1, hi);
       merge(a,lo,mid,hi);
    }

    public static void main(String[] args) {
        String[] a ={"M","E","R","G","E","S","O","R","T","E","X","A","M","P","L","E"};
        Integer[] b ={1,99,11,32,5,22,45,12,3,9,6};
        show(b);
        MyMerge.sortX(b);
        show(b);
    }
}
