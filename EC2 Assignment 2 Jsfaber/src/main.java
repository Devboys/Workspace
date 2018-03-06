import java.util.ArrayList;
import java.util.List;
import Jsfaber.EC2.MyLinkedList.*;

public class main {
    public static void main(String [] args){
        MyLinkedList<String> myList = new MyLinkedList<String>();
        myList.add("F 0");
        myList.add("B 1");
        myList.add("C 2");
        myList.add("D 3");
        myList.add("G 4");


        ArrayList<String> heyArray = new ArrayList<String>();
        heyArray.add("1");
        heyArray.add("2");

        myList.remove(0);
        myList.remove(3);

        myList.add(0, "A 5");
        myList.add(4, "E 6");

        List<String> outputArray =  myList.toList();

        for(int i = 0; i < outputArray.size(); i++){
            System.out.println(outputArray.get(i));
        }
    }
}
