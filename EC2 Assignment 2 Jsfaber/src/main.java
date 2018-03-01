import java.util.List;
import Jsfaber.EC2.MyLinkedList.*;

public class main {
    public static void main(String [] args){
        MyLinkedList<Integer> myList = new MyLinkedList<Integer>();
        myList.add(7);
        myList.add(14);
        myList.add(21);
        myList.add(28);

        List<Integer> outputArray = myList.toList();

        for(int i = 0; i < 4; i++){
            System.out.println(Integer.toString(outputArray.get(i)));
        }
    }
}
