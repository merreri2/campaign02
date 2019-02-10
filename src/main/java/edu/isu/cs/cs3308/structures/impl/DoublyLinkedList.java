package edu.isu.cs.cs3308.structures.impl;

import edu.isu.cs.cs3308.structures.List;

public class DoublyLinkedList<E> implements List<E> {

    //Node class taken from page 136 of textbook
    private static  class Node<E>{
        private E element;
        private Node<E> prev;
        private Node<E> next;
        public Node(E e, Node<E> p, Node<E> n){
            element = e;
            prev = p;
            next = n;
        }

        public E getElement(){
            return element;
        }

        public Node<E> getPrev() {
            return prev;
        }

        public Node<E> getNext() {
            return next;
        }

        public void setPrev(Node<E> p){
            prev = p;
        }

        public void setNext(Node<E> n){
            next = n;
        }
    }

    private Node<E> header;
    private Node<E> trailer;
    private int size = 0;

    public DoublyLinkedList(){
        header = new Node<>(null, null, null);
        trailer = new Node<>(null, null, null);
        header.setNext(trailer);
        trailer.setPrev(header);
    }

    /*
     @return first element in the list or null if the list is empty.
     taken from book's implementation on page 137
    */
    public E first(){
        if (isEmpty()){
            return null;
        }
        return header.getNext().getElement();
    }

    /**
     * @return last element in the list or null if the list is empty.
     * taken from book's implementation on page 137
     */
    public E last(){
        if (isEmpty()){
            return null;
        }
        return trailer.getPrev().getElement();
    }

    /**
     * Adds the provided element to the end of the list, only if the element is
     * not null.
     *
     * @param element Element to be added to the end of the list.
     * taken from book's implementation on page 137
     */
    public void addLast(E element){
        if (element != null) {
            addBetween(element, trailer.getPrev(), trailer);
        }
    }

    /**
     * Adds the provided element to the front of the list, only if the element
     * is not null.
     *
     * @param element Element to be added to the front of the list
     * taken from book's implementation on page 137.
     */
    public void addFirst(E element){
        if (element != null){
            addBetween(element, header, header.getNext());
        }
    }

    /**
     * Removes the element at the front of the list.
     *
     * @return Element at the front of the list, or null if the list is empty.
     * taken from book's implementation on page 137
     */
    public E removeFirst(){
        if (isEmpty()){
            return null;
        }
        return remove(header.getNext());
    }

    /**
     * Removes the element at the end of the list.
     *
     * @return Element at the end of the list, or null if the list is empty.
     * taken from book's implementation on page 137
     */
    public E removeLast(){
        if (isEmpty()){
            return null;
        }
        return remove(trailer.getPrev());
    }

    /**
     * Inserts the given element into the list at the provided index. The
     * element will not be inserted if either the element provided is null or if
     * the index provided is less than 0. If the index is greater than or equal
     * to the current size of the list, the element will be added to the end of
     * the list.
     *
     * @param element Element to be added (as long as it is not null).
     * @param index Index in the list where the element is to be inserted.
     */
    public void insert(E element, int index){
        if (element == null || index < 0){

        }else {

            if (index >= size) {
                addLast(element);
            } else {
                Node<E> temp = header;
                for (int count = 0; count < index; count++) {
                    temp = temp.getNext();
                }
                addBetween(element, temp, temp.getNext());
            }
        }
    }

    /**
     * Removes the element at the given index and returns the value.
     *
     * @param index Index of the element to remove
     * @return The value of the element at the given index, or null if the index
     * is greater than or equal to the size of the list or less than 0.
     */
    public E remove(int index){
        if (index >= size || index < 0){
            return null;
        }

        Node<E> temp = header;
        for (int count = 0; count < index + 1; count++){
            temp = temp.getNext();
        }
        return remove(temp);
    }

    /**
     * Retrieves the value at the specified index. Will return null if the index
     * provided is less than 0 or greater than or equal to the current size of
     * the list.
     *
     * @param index Index of the value to be retrieved.
     * @return Element at the given index, or null if the index is less than 0
     * or greater than or equal to the list size.
     */
    public E get(int index){
        if (index < 0 || index >= size){
            return null;
        }

        Node<E> temp = header;
        for (int count = 0; count < index; count++){
            temp = temp.getNext();
        }
        return temp.getNext().getElement();
    }

    /**
     * @return The current size of the list. Note that 0 is returned for an
     * empty list.
     */
    public int size(){
        return size;
    }

    /**
     * @return true if there are no items currently stored in the list, false
     * otherwise.
     */
    public boolean isEmpty(){
        return size == 0;
    }

    /**
     * Prints the contents of the list each item on its own line
     */
    public void printList(){
        Node<E> temp = header;
        for (int count = 0; count < size; count++){
            temp = temp.getNext();
            System.out.println(temp.getElement().toString());
        }
    }


    /*
    Adds element e to the linked list between the given nodes
    taken from the book's implementation on page 137
     */
    private void addBetween(E e, Node<E> predecessor, Node<E> successor){
        Node<E> newest = new Node<>(e, predecessor, successor);
        predecessor.setNext(newest);
        successor.setPrev(newest);
        size++;
    }

    /*
    Removes the given node from the list and returns its element
    taken from the book's implementation on page 137
     */
    private E remove(Node<E> node){
        Node<E> predecessor = node.getPrev();
        Node<E> successor = node.getNext();
        predecessor.setNext(successor);
        successor.setPrev(predecessor);
        size--;
        return node.element;
    }
}
