package ua.edu.ucu.collections.immutable;

import org.junit.Test;
import static org.junit.Assert.*;

public class ImmutableLinkedListTest {

    @Test
    public void testAppend() {
        ImmutableList list = new ImmutableLinkedList().add(1);
        assertArrayEquals(new Object[] {1}, list.toArray());
    }

    @Test
    public void testInsert() {
        ImmutableList list = new ImmutableLinkedList().add(1).add(2).add(3).add(2, 4);
        assertArrayEquals(new Object[] {1, 2, 4, 3}, list.toArray());
    }

    @Test
    public void testAppendAll() {
        ImmutableList list = new ImmutableLinkedList().add(1).add(2).add(3).addAll(new Object[] {4, 5, 6});
        assertArrayEquals(new Object[] {1, 2, 3, 4, 5, 6}, list.toArray());
        /* Test with an empty array */
        list = list.clear().addAll(new Object[] {4, 5, 6});
        assertArrayEquals(new Object[] {4, 5, 6}, list.toArray());
    }

    @Test
    public void testInsertAll() {
        ImmutableList list = new ImmutableLinkedList().add(1).add(2).add(3).addAll(2, new Object[] {4, 5, 6});
        assertArrayEquals(new Object[] {1, 2, 4, 5, 6, 3}, list.toArray());
    }

    @Test
    public void testGet() {
        ImmutableList list = new ImmutableLinkedList().add(1);
        assertEquals(1, list.get(0));
    }

    @Test
    public void testRemove() {
        /* Test with the last element */
        ImmutableList list = new ImmutableLinkedList().add(1).add(2).add(3).add(4).remove(3);
        assertArrayEquals(new Object[] {1, 2, 3}, list.toArray());
        /* Test with the first element */
        list = list.remove(0);
        assertArrayEquals(new Object[] {2, 3}, list.toArray());
        /* Test with two elements */
        list = list.remove(1);
        assertArrayEquals(new Object[] {2}, list.toArray());
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testRemoveWithEmptyList() {
        ImmutableList list = new ImmutableLinkedList();
        list.remove(0);
    }

    @Test
    public void testSet() {
        ImmutableList list = new ImmutableLinkedList().add(1).add(2).add(3).set(2, 4);
        assertArrayEquals(new Object[] {1, 2, 4}, list.toArray());
    }

    @Test
    public void testIndexOf() {
        ImmutableList list = new ImmutableLinkedList().add(1).add(2).add(3);
        assertEquals(2, list.indexOf(3));
        /* Test with an absent value */
        assertEquals(-1, list.indexOf(4));
    }

    @Test
    public void testSize() {
        ImmutableList list = new ImmutableLinkedList().add(1).add(2).add(3);
        assertEquals(3, list.size());
    }

    @Test
    public void testClear() {
        ImmutableList list = new ImmutableLinkedList().add(1).add(2).add(3).clear();
        assertArrayEquals(new Object[] {}, list.toArray());
    }

    @Test
    public void testIsEmpty() {
        ImmutableList list = new ImmutableLinkedList();
        assertTrue(list.isEmpty());
    }

    @Test
    public void testToString() {
        ImmutableList list = new ImmutableLinkedList().add(1).add(2).add(3);
        assertEquals("1 2 3 ", list.toString());
    }
}
