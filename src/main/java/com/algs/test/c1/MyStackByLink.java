package com.algs.test.c1;

import java.util.Iterator;

public class MyStackByLink<Item> implements Iterable<Item>{

    private Node<Item> first;

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

    private class Node<Item>{
        Item item;
        Node<Item> next;
    }

    public MyStackByLink(){
        first = null;
        n = 0;
    }

    public void push(Item item){
        Node oldFirst = first;
        first = new Node();
        first.item = item;
        first.next = oldFirst;
        n++;
    }

    public Item pop(){
        Item item = first.item;
        first = first.next;
        n--;
        return item;
    }

    public boolean isEmpty(){
        return first == null;
    }

    public int size(){
        return n;
    }

    public static void main(String[] args) {
        MyStackByLink stack = new MyStackByLink();
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
