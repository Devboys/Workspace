package Jsfaber.EC2.MyLinkedList;

import java.util.ArrayList;
import java.util.List;

public class MyLinkedList<T> {

    private ListNode first = null;
    private ListNode last = null;

    private int size = 0;

    public int getSize(){ return size; }

    /**
     * Places a new element with a value of Data, at the given Index.
     * If an element exists at the current index, it will be pushed forward one index.
     *
     * @param index The index of the element in the list.
     * @param data The value of the element.
     */
    public void add(int index, T data) {
        //if new node is not a new first or last node
        ListNode newNode;

        if(size > 0 && index < size) {
            ListNode newPrevious = getNode(index-1);
            ListNode newNext = getNode(index);

            newNode = new ListNode(data, newNext);
            newPrevious.setNext(newNode);
        }

        //if the new node is going to be the new last node
        else if(size > 0 && index == size){
            newNode = new ListNode(data, null);
            last.setNext(newNode);
            last = newNode;
        }

        //if the new node is going to be the new first node

        //if
        else if(size == 0) {
            first = last =  new ListNode(data, null);

        }
        //TEMP
        size++;
    }

    /**
     * Adds an element with a given value to the end of the list (i.e. the last index).
     *
     * @param data the desired value of the element
     */
    public void add(T data){
        add(size , data);
    }

    /**
     * @param index The index of the element
     * @return the value (of whatever type) at the given index in the list.
     */
    public T get(int index) {
        //return node value at index.
        ListNode currentNode = first;

        for(int i = 0; i < index; i++){
            currentNode = currentNode.getNext();
        }
        return (T)currentNode.getData();
    }

    /**
     * @return Returns a list with the data-value of every element in the linked list.
     */
    @SuppressWarnings("unchecked")
    public List<T> toList() {

        List<T> returnArray = new ArrayList<T>();

        ListNode currentNode = first;

        for(int i = 0; i < size; i++){
            returnArray.add((T)currentNode.getData());
            currentNode = currentNode.getNext();
        }

        return returnArray;
    }

    private ListNode getNode(int index){
        if(index > 0 && index < size){
            ListNode currentNode = first;

            for(int i = 0; i < index; i++){
                currentNode = currentNode.getNext();
            }
            return currentNode;

        }
        else{
            return null; //måske throw outofbounds
        }

    }

}
