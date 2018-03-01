package com.company;

//FIRST ELEMENT = ELEMENT 0;
//LAST ELEMENT = ELEMENT SIZE-1.

public class MyLinkedList {

    ListNode first = null;
    ListNode last = null;

    private int size = 0;


    public void add(int index, int data) {
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

    public void add(int data){
        add(size , data);
    }

    //with switch
    //public boolean add()

    public int get(int index) {
        //return node value at index.
        ListNode currentNode = first;

        for(int i = 0; i < index; i++){
            currentNode = currentNode.getNext();
        }
        return currentNode.getData();
    }

    public int[] getAll() {
        int[] returnArray = new int[size];

        ListNode currentNode = first;

        for(int i = 0; i < size; i++){
            returnArray[i] = currentNode.getData();
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
