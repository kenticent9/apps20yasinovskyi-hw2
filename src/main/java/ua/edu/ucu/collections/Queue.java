package ua.edu.ucu.collections;

import ua.edu.ucu.collections.immutable.ImmutableLinkedList;

public class Queue {
    private ImmutableLinkedList list;

    public Queue() {
        list = new ImmutableLinkedList();
    }

    public Object peek() {
        return list.getFirst();
    }

    public Object dequeue() {
        Object e = list.getFirst();
        list = list.removeFirst();
        return e;
    }

    public void enqueue(Object e) {
        list = list.addLast(e);
    }
}
