package com.algs.test.c2;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.Stopwatch;

public class SortCompare {
   public static double time(String alg,Double[] a){
       Stopwatch timer = new Stopwatch();
       if (alg.equals("insertion")){
           MyInsertion.sort(a);
       }else if (alg.equals("insertionX")){
           MyInsertion.sortX(a);
       }else if (alg.equals("selection")){
           MySelection.sort(a);
       }else if (alg.equals("shell")){
           MyShell.sort(a);
       }

       return timer.elapsedTime();
   }

   public static double timeRandonInput(String alg,int N,int T){
       double total = 0.0;
       Double[] a= new Double[N];
       for (int i = 0; i < T; i++) {
           for (int j = 0; j < N ; j++) {
               a[j] = StdRandom.uniform();
           }
           total += time(alg,a);
       }

       return total;
   }

    public static void main(String[] args) {
        double t1 = timeRandonInput("insertion",100000,2);
        double t2 = timeRandonInput("shell",100000,2);
        System.out.println(t1/t2);
    }
}
