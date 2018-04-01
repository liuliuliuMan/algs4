package com.algs.test.c1;

import java.util.Iterator;

public class MyQueue<Item> implements Iterable<Item>{

    private class Node<Item>{
        Item item;
        Node<Item> next;
    }

    private Node<Item> first;

    private Node<Item> last;

    private int n;

    @Override
    public Iterator<Item> iterator() {
        return new ListIterator();
    }

    private class ListIterator implements Iterator<Item>{

        private Node<Item> current = first;
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }
    }

    public boolean isEmpty(){
        return first == null ;
    }

    public int size(){
        return n;
    }

    public void enqueue(Item item){
        Node oldlast = last;
        last = new Node();
        last.item = item;
        last.next = null;
        if(isEmpty()){
            first = last;
        }else{
            oldlast.next = last;
        }
        n++;
    }

    public Item dequeue(){
        Item item = first.item;
        first = first.next;
        if (isEmpty()){
            last = null;
        }
        n--;
        return item;
    }

    public static void main(String[] args) {
        MyQueue queue = new MyQueue<String>();
        queue.enqueue("111");
        queue.enqueue("222");
        queue.enqueue("333");
        queue.enqueue("444");
        queue.enqueue("555");
        queue.enqueue("666");
        queue.enqueue("777");

        Iterator i = queue.iterator();
        while (i.hasNext()){
            System.out.println(i.next());
        }


    }
}
