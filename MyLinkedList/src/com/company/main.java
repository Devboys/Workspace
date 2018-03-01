package com.company;

public class main {
    public static void main(String [] args){
        MyLinkedList myList = new MyLinkedList();
        myList.add(7);
        myList.add(14);
        myList.add(21);
        myList.add(28);

        int[] outputArray= myList.getAll();

        for(int i = 0; i < 4; i++){
            System.out.println(Integer.toString(outputArray[i]));
        }
    }
}
