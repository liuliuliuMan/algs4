package com.algs.test.c2;

public class MyMaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;

    private int N = 0;

    public MyMaxPQ(int max){
        pq = (Key[]) new Comparable[max+1];
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public int size(){
        return N;
    }

    public void insert(Key v){
        pq[++N] = v;
        swim(N);
    }

    public Key delMax(){
        Key max = pq[1];
        exch(1,N--);
        pq[N+1] = null;
        sink(1);
        return max;
    }

    private void swim(int k){
        while (k > 1&& less(k/2 ,k)){
            exch(k/2,k);
            k = k/2;
        }
    }

    private void sink(int k){
        while (2*k < N){
            int j = 2*k;
            if(j < N && less(j,j+1)){
                j++;
            }
            if (!less(k,j)){
                break;
            }
            exch(k,j);
            k = j;
        }
    }


    // is pq[i] < pq[j] ?
    protected boolean less(int i,int j) {
        return pq[i].compareTo(pq[j])<0;
    }

    // exchange a[i] and a[j]
    protected void exch(int i, int j) {
        Key t = pq[i];
        pq[i] = pq[j];
        pq[j] = t;
    }

    public static void main(String[] args) {
        MyMaxPQ<String> s = new MyMaxPQ<String>(10);
        s.insert("P");
        s.insert("Q");
        s.insert("E");
        System.out.println(s.delMax());
    }
}
