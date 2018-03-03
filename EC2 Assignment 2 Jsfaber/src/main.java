import java.util.List;
import Jsfaber.EC2.MyLinkedList.*;

public class main {
    public static void main(String [] args){
        MyLinkedList<String> myList = new MyLinkedList<String>();
        myList.add("F");
        myList.add("B");
        myList.add("C");
        myList.add("D");
        myList.add("G");


        myList.remove(0);
        myList.remove(3);

        myList.add(0, "A");
        myList.add(4, "E");

        List<String> outputArray =  myList.toList();

        for(int i = 0; i < outputArray.size(); i++){
            System.out.println(outputArray.get(i));
        }
    }
}
