package Jsfaber.EC2.MyLinkedList;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MyLinkedList<T>{

    ListNode first;
    ListNode last;
    int size;

    public MyLinkedList(){
        size = 0;
        first = null;
        last = null;

    }

    public int getSize(){ return size; }

    //if no index specified, add to end of list.
    public void add(T data){
        add(size , data);
    }

    //insert element at specified index, 'nudging' all elements
    public void add(int index, T data){
        ListNode newNode;
        if (size == 0) {
            first = last = new ListNode(data, null);
            size++;
        }

        //if the new node is going to be the first node.
        else if(index == 0) {
            //get previous first node
            ListNode newNext = getNode(index);

            first = new ListNode(data, newNext);
            size++;
        }
        //if new node is going to be anything but the last node or first node
        else if (index < size) {
            ListNode newPrevious = getNode(index - 1);
            ListNode newNext = getNode(index);

            newNode = new ListNode(data, newNext);
            newPrevious.setNext(newNode);
            size++;
        }

        //if the new node is going to be the last node
        else if (index == size) {
            newNode = new ListNode(data, null);
            last.setNext(newNode);
            last = newNode;
            size++;
        }
        else{
            throw new IndexOutOfBoundsException("Index out of bounds when adding at index: " + index);
        }
    }

    public void remove(int index) {
        //if removing the first node.
        if(index == 0){
            first = first.getNext();
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
            newLast.setNext(null);
            size--;
        }
        //if removing a non-existent element(out of bounds)
        else {
            throw new IndexOutOfBoundsException("Index out of bounds when removing at index: " + index);
        }
    }

    public void addAll(T[] dataArray) {
        addAll(size, dataArray);
    }

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

    public void removeAll(int indexStart, int indexEnd) {
        for(int i = indexStart; i <= indexEnd; i++){
            remove(indexStart);
        }
    }

    public void clear() {
        //leave all elements to garbage collection by removing any external reference.
        first = null;
        last = null;
        size = 0;
    }

    public T get(int index) {

        return (T) getNode(index).getData();
    }

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

    public Object[] toArray(){
        Object[] returnArray = new Object[size];
        ListNode currentNode = first;

        for(int i = 0; i < size; i++){
            returnArray[i] = currentNode.getData();
            currentNode = currentNode.getNext();
        }
        return returnArray;

    }

    ListNode getNode(int index){
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
