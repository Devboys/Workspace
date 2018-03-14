import java.util.ArrayList;

public class Node<T> {

    private Node parent;
    private ArrayList<Node> children;

    private boolean isOpenLeft
    private boolean isOpenDown
    private boolean isOpenUp;
    private boolean isOpenRight;

    //maybe bad
    int nextIndex;

    private T value;


    public Node(T value, Node parent) {
        this.value = value;
        this.parent = parent;
        nextIndex = 0;
    }

    public void addChild(Node newChild){
        children.add(newChild);
    }

    public Node getNext() {
        if(nextIndex == children.size()) {
            nextIndex = 0;
            return parent;
        }
        else{
            return children.get(nextIndex);
        }
    }

    public T getValue(){
        return value;
    }
}


5 4 6 3 2 1
4 5 6 3 2 1
4 5 3 2 1 6
| 5 3 2 1 6
| 3 5 2 1 6
| 3 2 5 1 6
| 3 2 1 5 6