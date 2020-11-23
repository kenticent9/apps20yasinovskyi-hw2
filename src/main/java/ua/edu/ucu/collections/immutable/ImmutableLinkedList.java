package ua.edu.ucu.collections.immutable;

public final class ImmutableLinkedList implements ImmutableList {
    Node head;
    int len;

    static class Node {
        Object data;
        Node next;

        Node(Object data) {
            this.data = data;
            next = null;
        }

        public Object getData() {
            return data;
        }

        public void setData(Object data) {
            this.data = data;
        }

        public Node getNext() {
            return next;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        /* Analog of Python's deepcopy */
        public Node myCopy() {
            Node newNode = new Node(data);

            Node curNode1 = newNode;
            Node curNode2 = next;
            while (curNode2 != null) {
                curNode1.setNext(new Node(curNode2.getData()));
                curNode1 = curNode1.getNext();
                curNode2 = curNode2.getNext();
            }
            return newNode;
        }
    }

    public ImmutableLinkedList() {
        head = null;
        len = 0;
    }

    public ImmutableLinkedList(Node head, int len) {
        this.head = head;
        this.len = len;
    }

    @Override
    public ImmutableList add(Object e) {
        return addAll(len, new Object[] {e});
    }

    @Override
    public ImmutableList add(int index, Object e) {
        return addAll(index, new Object[] {e});
    }

    @Override
    public ImmutableList addAll(Object[] c) {
        return addAll(len, c);
    }

    static Node insert(Node node, Object data)
    {
        Node temp = new Node(data);
        Node curNode;
        if (node == null) {
            node = temp;
        } else {
            curNode = node;
            while (curNode.next != null)
                curNode = curNode.next;
            curNode.next = temp;
        }
        return node;
    }

    private Node arrayToList(Object[] c) {
        Node head = null;
        for (Object o : c) {
            head = insert(head, o);
        }
        return head;
    }

    @Override
    public ImmutableList addAll(int index, Object[] c) {
        if (index < 0 || index > len) {
            throw new IndexOutOfBoundsException();
        }
        Node newHead;
        if (head == null) {
            newHead = arrayToList(c);
        } else {
            newHead = head.myCopy();
            Node curNode = newHead;
            while (--index > 0) {
                curNode = curNode.getNext();
            }
            Node temp = curNode.getNext();
            curNode.setNext(arrayToList(c));
            while (curNode.getNext() != null) {
                curNode = curNode.getNext();
            }
            curNode.setNext(temp);
        }
        return new ImmutableLinkedList(newHead, len+c.length);
    }

    @Override
    public Object get(int index) {
        if (index < 0 || index >= len) {
            throw new IndexOutOfBoundsException();
        }
        Node curNode = head;
        while (index-- > 0) {
            curNode = curNode.getNext();
        }
        return curNode.getData();
    }

    @Override
    public ImmutableList remove(int index) {
        if (index < 0 || index >= len) {
            throw new IndexOutOfBoundsException();
        }
        Node newHead = head.myCopy();
        if (index == 0) {
            newHead = newHead.getNext();
        } else {
            Node curNode = newHead;
            while (--index > 0) {
                curNode = curNode.getNext();
            }
            curNode.setNext(curNode.getNext().getNext());
        }
        return new ImmutableLinkedList(newHead, len-1);
    }

    @Override
    public ImmutableList set(int index, Object e) {
        if (index < 0 || index >= len) {
            throw new IndexOutOfBoundsException();
        }
        Node newHead = head.myCopy();
        Node curNode = newHead;
        while (index-- > 0) {
            curNode = curNode.getNext();
        }
        curNode.setData(e);
        return new ImmutableLinkedList(newHead, len);
    }

    @Override
    public int indexOf(Object e) {
        int i = 0;
        Node curNode = head;
        while (curNode != null && !curNode.getData().equals(e)) {
            curNode = curNode.getNext();
            i++;
        };
        return (curNode != null) ? i : -1;
    }

    @Override
    public int size() {
        return len;
    }

    @Override
    public ImmutableList clear() {
        return new ImmutableLinkedList();
    }

    @Override
    public boolean isEmpty() {
        return len == 0;
    }

    @Override
    public Object[] toArray() {
        Object[] array = new Object[len];
        Node curNode = head;
        for (int i = 0; i < len; i++) {
            array[i] = curNode.getData();
            curNode = curNode.getNext();
        }
        return array;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node curNode = head;
        while (curNode != null) {
            sb.append(curNode.getData())
                    .append(" ");
            curNode = curNode.getNext();
        }
        return sb.toString();
    }

    public ImmutableList addFirst(Object e) {
        return add(0, e);
    }

    public ImmutableList addLast(Object e) {
        return add(len, e);
    }

    public Object getFirst() {
        return get(0);
    }

    public Object getLast() {
        return get(len-1);
    }

    public ImmutableList removeFirst() {
        return remove(0);
    }

    public ImmutableList removeLast() {
        return remove(len-1);
    }
}
