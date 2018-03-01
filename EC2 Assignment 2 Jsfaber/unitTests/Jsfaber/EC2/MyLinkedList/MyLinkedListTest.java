package Jsfaber.EC2.MyLinkedList;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class MyLinkedListTest {

    MyLinkedList<Integer> theList;

    @Before
    public void setUp() throws Exception {
        theList = new MyLinkedList<Integer>();
    }

    @Test
    public void add() {
        int value1 = 1234;
        int value2 = 5678;
        theList.add(0, value1);
        theList.add(1, value2);
        assertEquals(value1, theList.get(0).intValue());
    }

    @Test
    public void addNoIndexSpecified() {
        int value1 = 1234;
        int value2 = 5678;
        theList.add(value1);
        theList.add(value2);

        assertEquals(value2, theList.get(theList.getSize()-1).intValue());
    }

    @Test
    public void get() {
        int value1 = 1234;
        int value2 = 5678;
        theList.add(value1);
        theList.add(value2);

        assertEquals(value1, theList.get(0).intValue());
        assertEquals(value2, theList.get(1).intValue());
    }

    @Test
    public void toList() {
        theList.add(1);
        theList.add(2);
        theList.add(3);
        List<Integer> outputList = theList.toList();

        for(int i = 0; i < 3; i++){
            assertTrue(outputList.get(i) instanceof Integer);
            assertEquals((i+1), outputList.get(i).intValue());
        }
    }
}