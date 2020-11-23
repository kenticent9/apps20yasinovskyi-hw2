package ua.edu.ucu.collections;

import org.junit.Test;
import static org.junit.Assert.*;

public class QueueTest {
    
    @Test
    public void testEnqueue() {
        Queue queue = new Queue();
        queue.enqueue(1);
        assertEquals(1, queue.peek());
    }

    @Test
    public void testDequeue() {
        Queue queue = new Queue();
        queue.enqueue(1);
        queue.enqueue(2);
        queue.enqueue(3);
        assertEquals(1, queue.dequeue());
    }
}
