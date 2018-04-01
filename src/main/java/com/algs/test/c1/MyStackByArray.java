package com.algs.test.c1;

import java.util.Iterator;

public class MyStackByArray<Item> implements Iterable<Item>{

    private Item[] a;

    private int N;

    public MyStackByArray(int cap){
        a = (Item[])new Object[cap];
    }

    //添加元素
    public void push(Item item){
        if (N == a.length){
            resize(N*2);
        }
        a[N++] = item;
    }

    //出栈
    public Item pop(){
        Item item = a[--N];
        a[N] = null;
        if (N>0 && N ==a.length/4){
            resize(a.length/2);
        }
        return item;
    }

    public boolean isEmpty(){
        return N == 0;
    }

    public int size(){
        return N;
    }

    private void resize(int max){
        Item[] temp = (Item[])new Object[max];
        for (int i = 0; i < N ; i++) {
            temp[i] = a[i];
        }
        a = temp;
    }

    @Override
    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    private class ReverseArrayIterator implements Iterator<Item>{

        private int i = N;
        @Override
        public boolean hasNext() {
            return i > 0;
        }

        @Override
        public Item next() {
            return a[--i];
        }
    }


    public static void main(String[] args) {
        MyStackByArray stack = new MyStackByArray<String>(2);
        stack.push("www");
        stack.push("1wwqqq");
        stack.push("2wwqqq");
        stack.push("3wwqqq");
        stack.push("4wwqqq");

        Iterator i = stack.iterator();
        while (i.hasNext()){
            System.out.println(i.next());
        }
    }



}
