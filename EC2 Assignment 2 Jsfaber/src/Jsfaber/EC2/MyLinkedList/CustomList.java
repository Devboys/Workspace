package Jsfaber.EC2.MyLinkedList;

public interface CustomList<T> {
    void add(T t);
    boolean addAll(T[] t);
    int getSize();
}
