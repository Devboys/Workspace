package Jsfaber.EC2.MyLinkedList;

public class ListNode<T> {

    private T data;
    private ListNode next;

    ListNode(T data, ListNode next) {
        this.data = data;
        this.next = next;
    }

    T getData() {
        return data;
    }

    void setData(T data) {
        this.data = data;
    }

    ListNode getNext() {
        return next;
    }

    void setNext(ListNode next) {
        this.next = next;
    }
}
