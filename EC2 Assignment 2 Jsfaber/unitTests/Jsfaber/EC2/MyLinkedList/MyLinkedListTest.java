package Jsfaber.EC2.MyLinkedList;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MyLinkedListTest {

    MyLinkedList<String> myList;
    String s1;
    String s2;
    String s3;
    String s4;
    String s5;
    String[] array1;
    String[] array2;

    @Before
    public void setUp() throws Exception {
        myList = new MyLinkedList<String>();
        s1 = "0";
        s2 = "1";
        s3 = "2";
        s4 = "3";
        s5 = "4";
        array1 = new String[]{s1, s2, s3, s4, s5};
        array2 = new String[]{"A", "B", "C", "D"};

    }

    @Test
    public void constructorTest(){
        Assert.assertTrue(myList.first == null);
        Assert.assertTrue(myList.last == null);
        Assert.assertTrue(myList.size == 0);
    }

    @Test
    public void getSize() {
        Assert.assertEquals(myList.size, myList.getSize());
    }

    @Test
    public void addNoIndexSpecified() {
        myList.add(s1);
        Assert.assertEquals(s1, myList.get(0));

        myList.add(s2);
        Assert.assertEquals(s2, myList.get(1));
    }

    @Test
    public void addBecomesFirst() {
        myList.addAll(array2);
        int expectedSize = array2.length;

        myList.add(0, s1);
        expectedSize++;
        Assert.assertEquals(expectedSize, myList.getSize());
        Assert.assertEquals(s1, myList.get(0));
    }

    @Test
    public void addBecomesSecond() {
        myList.addAll(array2);
        int expectedSize = array2.length;

        myList.add(1, s2);
        expectedSize++;
        Assert.assertEquals(expectedSize, myList.getSize());
        Assert.assertEquals(s2, myList.get(1));
    }

    @Test
    public void addBecomesSecondToLast() {
        myList.addAll(array2);
        int expectedSize = array2.length;

        myList.add(myList.getSize()-1, s3);
        expectedSize++;
        Assert.assertEquals(expectedSize, myList.getSize());
        Assert.assertEquals(s3, myList.get(myList.getSize()-2));
    }

    @Test
    public void addLastElement() {
        myList.addAll(array2);
        int expectedSize = array2.length;

        myList.add(myList.getSize(), s4);
        expectedSize++;
        Assert.assertEquals(expectedSize, myList.getSize());
        Assert.assertEquals(s4, myList.get(myList.getSize()-1));
    }

    @Test
    public void addOutOfBounds() {
        myList.add(1, s1);
        try{
            myList.add(200, s5);
            Assert.fail();
        }catch(IndexOutOfBoundsException e){}
    }



    @Test
    public void removeFirstElement() {
        //test remove first element (index: 0)
        myList.addAll(array1);
        myList.remove(0);
        Assert.assertEquals(array1.length - 1, myList.getSize());
        Assert.assertTrue(array1[1] == myList.get(0));
    }

    @Test
    public void removeSecondElement() {
        //test remove 2nd element(index: 1)
        myList.addAll(array1);
        myList.remove(1);
        Assert.assertEquals(array1.length - 1, myList.getSize());
        Assert.assertTrue(array1[0] == myList.get(0));
        Assert.assertTrue(array1[2] == myList.get(1));
    }

    @Test
    public void removeSecondToLastElement() {

        //test remove next to last element(index: size-2)
        myList.addAll(array1);
        myList.remove(myList.size - 2);
        Assert.assertEquals(array1.length - 1, myList.getSize());
        Assert.assertTrue(array1[array1.length - 3] == myList.get(myList.getSize() - 2));
        Assert.assertTrue(array1[array1.length - 1] == myList.get(myList.getSize() - 1));
    }

    @Test
    public void removeLastElement() {
        myList.addAll(array1);
        myList.remove(myList.size - 1);
        Assert.assertEquals(array1.length - 1, myList.getSize());
        Assert.assertEquals(array1[array1.length - 2], myList.get(myList.getSize() - 1));
    }

    @Test
    public void removeOutOfBounds() {
        myList.addAll(array1);
        try {
            myList.remove(-1);
            Assert.fail();
        } catch (IndexOutOfBoundsException e) {
        }

        try {
            myList.remove(myList.getSize());
            Assert.fail();
        } catch (IndexOutOfBoundsException e) {
        }

        try {
            myList.remove(myList.getSize() + 1);
            Assert.fail();
        } catch (IndexOutOfBoundsException e) {
        }

        Assert.assertEquals(array1.length, myList.getSize());
    }

    @Test
    public void addAll() {
        //test for adding all in empty list
        myList.addAll(array1);
        for(int i = 0; i < array1.length; i++ ){
            Assert.assertEquals(array1[i], myList.get(i));
        }

        //test for adding all in non-empty list
        myList.addAll(array2);
        for(int j = 0; j < array2.length; j++){
            Assert.assertEquals(array2[j], myList.get(array1.length + j));
        }

    }

    @Test
    public void addAllWithIndex() {
        int startIndex = 1;
        myList.addAll(array1);
        myList.addAll(startIndex, array2);

        for(int i = 0; i < array2.length; i++){
            Assert.assertEquals(array2[i], myList.get(i + startIndex));
        }
    }

    @Test
    public void removeAll() {
        //test removeAll resulting in non-empty array

        myList.addAll(array1);
        myList.addAll(array2);

        myList.removeAll(0, array1.length - 1);
        Assert.assertEquals(array2.length, myList.getSize());

        //test removeAll clearing array
        myList.removeAll(0, array2.length-1);
        Assert.assertEquals(0, myList.getSize());

    }

    @Test
    public void clear() {
        myList.addAll(array1);
        myList.clear();

        Assert.assertTrue(myList.first == null);
        Assert.assertTrue(myList.last == null);
        Assert.assertTrue(myList.size == 0);
    }

    @Test
    public void get() {

        //test get first
        myList.add(s1);
        Assert.assertEquals(s1, myList.get(0));

        //test get last
        myList.add(s2);
        Assert.assertEquals(s2, myList.get(1));

        //test get out of bounds
        myList.clear();
        try{
            myList.get(0);
            Assert.fail();
        }catch(IndexOutOfBoundsException e){
            //if error caught, success.
        }
    }

    @Test
    public void toList() {
        List<String> testList = new ArrayList<>();
        testList.add(s1);
        testList.add(s2);
        testList.add(s3);

        myList.add(s1);
        myList.add(s2);
        myList.add(s3);

        Assert.assertEquals(testList, myList.toList());

    }

}