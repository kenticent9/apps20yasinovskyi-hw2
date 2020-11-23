package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

public class Stack {
    private ImmutableLinkedList list;

    public Stack() {
        list = new ImmutableLinkedList();
    }

    public Object peek() {
        return list.getLast();
    }

    public Object pop() {
        Object e = list.getLast();
        list = list.removeLast();
        return e;
    }

    public void push(Object e) {
        list = list.addLast(e);
    }
}
