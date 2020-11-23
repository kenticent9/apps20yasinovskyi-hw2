package ua.edu.ucu.collections.immutable;

import java.util.Arrays;

public final class ImmutableArrayList implements ImmutableList {
    private Object[] elems;

    public ImmutableArrayList() {
        elems = new Object[] {};
    }

    public ImmutableArrayList(Object[] elems) {
        this.elems = elems;  // There's no need to clone the passed elements, because
                             // it was already done in the previous methods
    }

    @Override
    public ImmutableArrayList add(Object e) {
        return addAll(elems.length, new Object[] {e});
    }

    @Override
    public ImmutableArrayList add(int index, Object e) {
        return addAll(index, new Object[] {e});
    }

    @Override
    public ImmutableArrayList addAll(Object[] c) {
        return addAll(elems.length, c);
    }

    @Override
    public ImmutableArrayList addAll(int index, Object[] c) {
        if (index < 0 || index > elems.length) {
            throw new IndexOutOfBoundsException();
        }
        Object[] newElems = new Object[elems.length+c.length];
        System.arraycopy(elems, 0, newElems, 0, index);
        System.arraycopy(elems, index, newElems, index+c.length, elems.length-index);
        System.arraycopy(c, 0, newElems, index, c.length);
        return new ImmutableArrayList(newElems);
    }

    @Override
    public Object get(int index) {
        if (index < 0 || index >= elems.length) {
            throw new IndexOutOfBoundsException();
        }
        return elems[index];
    }

    @Override
    public ImmutableArrayList remove(int index) {
        if (index < 0 || index >= elems.length) {
            throw new IndexOutOfBoundsException();
        }
        Object[] newElems = new Object[elems.length-1];
        System.arraycopy(elems, 0, newElems, 0, index);
        /* If the element is not the last */
        if (index != elems.length-1) {
            System.arraycopy(elems, index+1, newElems, index, elems.length-index-1);
        }
        return new ImmutableArrayList(newElems);
    }

    @Override
    public ImmutableArrayList set(int index, Object e) {
        if (index < 0 || index >= elems.length) {
            throw new IndexOutOfBoundsException();
        }
        Object[] newElems = elems.clone();
        newElems[index] = e;
        return new ImmutableArrayList(newElems);
    }

    @Override
    public int indexOf(Object e) {
        for (int i = 0; i < elems.length; i++) {
            if (elems[i].equals(e)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int size() {
        return elems.length;
    }

    @Override
    public ImmutableArrayList clear() {
        return new ImmutableArrayList();
    }

    @Override
    public boolean isEmpty() {
        return elems.length == 0;
    }

    @Override
    public Object[] toArray() {
        return elems.clone();
    }

    @Override
    public String toString() {
        return Arrays.toString(elems);
    }
}
