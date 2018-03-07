package Jsfaber.EC2.MyCircularList;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ListNodeTest {

    ListNode<Integer> node1;
    ListNode<Integer> node2;

    @Test
    public void getData() {
        int value = 1;
        node1 = new ListNode<>(value, null);

        Assert.assertEquals(value, (int)node1.getData());
    }

    @Test
    public void setData() {
        node1 = new ListNode<>(0, null );
        node1.setData(1);
        assertEquals(1, (int)node1.getData());
    }

    @Test
    public void getNext() {
        node2 = new ListNode<>(2, null);
        node1 = new ListNode<>(1, node2);

        Assert.assertEquals(node2, node1.getNext());
    }

    @Test
    public void setNext() {

        node1 = new ListNode<>(1, null);
        node2 = new ListNode<>(2, null);
        node1.setNext(node2);

        Assert.assertEquals(node2, node1.getNext());
    }
}