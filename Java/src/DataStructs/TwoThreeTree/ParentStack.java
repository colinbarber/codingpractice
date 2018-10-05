package DataStructs.TwoThreeTree;

public class ParentStack {
    // holds parent Nodes generated by DataStructs.TwoThreeTree.Tree.compareAdd() to be used by DataStructs.TwoThreeTree.Tree.splitPromote().
    Node[] arr = new Node[10000];
    int index = -1;

    // push a parent DataStructs.TwoThreeTree.Node to the stack.
    public void push(Node n) {
        index += 1;
        arr[index] = n;
    }

    // pop a parent DataStructs.TwoThreeTree.Node from the stack.
    public Node pop() {
        if (!isEmpty()) {
            Node n = arr[index];
            index -= 1;
            return n;
        }
        else {
            return null;
        }
    }

    // check if the stack is empty.
    public boolean isEmpty() {
        return (index == -1);
    }
}
