package edu.isu.cs.cs3308.structures.impl;

import edu.isu.cs.cs3308.structures.Queue;

public class LinkedQueue<E> implements Queue<E> {

    /*
    Fields
     */
    public DoublyLinkedList<E> DLL = new DoublyLinkedList<>();
    /*
    Constructor
     */
    public LinkedQueue(){

    }

    /**
     * @return The number of elements in the queue
     */
    public int size() {
        return DLL.size();
    }

    /**
     * @return tests whether the queue is empty.
     */
    public boolean isEmpty() {
        return DLL.isEmpty();
    }

    /**
     * Inserts an element at the end of the queue.
     *
     * @param element Element to be inserted.
     */
    public void offer(E element) {
        DLL.addLast(element);
    }

    /**
     * @return The value first element of the queue (with out removing it), or
     * null if empty.
     */
    public E peek() {
        return DLL.first();
    }

    /**
     * @return The value of the first element of the queue (and removes it), or
     * null if empty.
     */
    public E poll() {
        return DLL.removeFirst();
    }

    /**
     * Prints the contents of the queue starting at top, one item per line. Note
     * this method should not empty the contents of the queue.
     */
    public void printQueue() {
        DLL.printList();
    }



    /**
     * Merges the contents of the provided queue onto the bottom of this queue.
     * The order of both queues must be preserved in the order of this queue
     * after the method call. Furthermore, the provided queue must still contain
     * its original contents in their original order after the method is
     * complete. If the provided queue is null, no changes should occur.
     *
     * @param from Queue whose contents are to be merged onto the bottom of
     * this queue.
     */
    public void merge(Queue<E> from){
        if (from != null){
            for (int count = 0; count < from.size(); count++){
                from.offer(from.peek());
                offer(from.poll());
            }
        }
    }

}
