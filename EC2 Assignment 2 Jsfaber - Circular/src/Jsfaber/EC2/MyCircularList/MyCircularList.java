package Jsfaber.EC2.MyCircularList;

import java.util.ArrayList;
import java.util.List;

public class MyCircularList<T>{

    ListNode first;
    ListNode last;
    int size;


    /**
     * Construct an empty CircularList
     */
    public MyCircularList(){
        size = 0;
        first = null;
        last = null;

    }

    /**
     * @return the number of elements in the list
     */
    public int getSize(){ return size; }

    /**
     * Inserts an element with the given data-value to the end of the list
     * @param data The value of the element to be added
     */
    public void add(T data){
        add(size , data);
    }

    /**
     * Insert an element with the given data-value, at the given index
     * @param index The index where the element should be added
     * @param data The value of the element to be added
     */
    public void add(int index, T data){
        //make circular - convert out-of-bounds index to index within list.
        if(size != 0) index = index % (size+1);

        ListNode newNode;
        if (size == 0) {
            first = last = new ListNode(data, null);
            last.setNext(first);
            size++;
        }

        //if the new node is going to be the first node.
        else if(index == 0) {
            //get previous first node
            ListNode newNext = getNode(index);
            first = new ListNode(data, newNext);

            //make circular
            last.setNext(first);

            size++;
        }
        //if new node is going to be anything but the last node or first node
        else if (index < size) {
            ListNode newPrevious = getNode(index-1); // HERE
            ListNode newNext = getNode(index);

            newNode = new ListNode(data, newNext);
            newPrevious.setNext(newNode);
            size++;
        }

        //if the new node is going to be the last node
        else if (index == size) {
            newNode = new ListNode(data, first);
            last.setNext(newNode);
            last = newNode;
            size++;
        }
    }

    /**
     * Removes the element currently occupying the given index
     * @param index the index of the element to be removed.
     */
    public void remove(int index) {
        //make circular - convert out-of-bounds index to index within list.
        if(size != 0) index = index % (size+1);

        //if removing the first node.
        if(index == 0){
            first = first.getNext();
            last.setNext(first);

            size--;
        }
        //if removing anything but the last or first node
        else if(index < (size - 1)) {
            ListNode previousNode = getNode(index-1);
            ListNode nextNode = getNode(index+1);
            previousNode.setNext(nextNode);
            size--;
        }

        //if removing the last node
        else if(index == size - 1){
            ListNode newLast = getNode(index-1);
            last = newLast;
            last.setNext(first);
            size--;
        }
    }

    /**
     * Adds all values of the given array to the end of the list in sequential order.
     * @param dataArray the array of values to be added.
     */
    public void addAll(T[] dataArray) {
        addAll(size, dataArray);
    }

    /**
     * Adds all values of the given array to array in sequential order. Elements will be added from the given index.
     * @param startIndex The index where the first element of the array should be placed
     * @param dataArray The array of values to be added
     */
    public void addAll(int startIndex, T[] dataArray) {
        int numAdditions = dataArray.length;

        int newNodeIndex;
        T newNodeData;
        for(int i = 0; i < numAdditions; i++){
            newNodeIndex = startIndex +i;
            newNodeData = dataArray[i];
            add(newNodeIndex, newNodeData);
        }
    }

    /**
     * Removes all elements between the two given indexes.
     * @param indexStart the start index of the list of elements to be removed.
     * @param indexEnd the end index of the list of elements to be removed
     */
    public void removeAll(int indexStart, int indexEnd) {
        for(int i = indexStart; i <= indexEnd; i++){
            remove(indexStart);
        }
    }

    /**
     * Completely clears the array of all elements.
     */
    public void clear() {
        //leave all elements to garbage collection by removing any external reference.
        first = null;
        last = null;
        size = 0;
    }

    /**
     * @param index the index of the element to return
     * @return the value of the element at the given index
     */
    public T get(int index) {
        return (T) getNode(index).getData();
    }

    /**
     * Returns a list of all elements in the CircularList. The returned list retains object-types.
     * @return a list of all elements in the CircularList in identical order.
     */
    public List<T> toList() {

        //declare a list with a fitting capacity to reduce arraylist shenanigans
        List<T> returnArray = new ArrayList<>(size);

        ListNode currentNode = first;

        for(int i = 0; i < size; i++){
            returnArray.add((T) currentNode.getData());
            currentNode = currentNode.getNext();
        }
        return returnArray;
    }

    /**
     * Returns an array of all elements in the CircularList. The returned array is of type Object[]. Call
     * Call .toList() if you want to retain value-types.
     * @return an array of all values in the CircularList in identical order.
     */
    public Object[] toArray(){
        Object[] returnArray = new Object[size];
        ListNode currentNode = first;

        for(int i = 0; i < size; i++){
            returnArray[i] = currentNode.getData();
            currentNode = currentNode.getNext();
        }
        return returnArray;

    }

    /* Class-scope method used for fetching the node at a given index.
     * Positive out-of-bounds indexes will be converted to fit the list */
    private ListNode getNode(int index){
        //make circular - convert out-of-bounds index to index within list.
        if(size != 0) index = index % (size+1);

        if(index > 0 && index < size -1){
            ListNode currentNode = first;

            for(int i = 0; i < index; i++){
                currentNode = currentNode.getNext();
            }
            return currentNode;

        }
        else if(index == 0 && size != 0){
            return first;
        }
        else if(index == size -1){
            return last;
        }
        else{
            throw new IndexOutOfBoundsException("Out of bounds during internal search at index: " + index);
        }

    }
}
