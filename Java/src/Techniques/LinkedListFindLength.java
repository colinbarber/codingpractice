/*public class LinkeListFindLength {

    static Node head;

    static class Node {

        int data;
        Node next;

        Node(int d) {
            data = d;
            next = null;
        }
    }

    // iterative
    public int length() {
        int count = 0;
        Node current = this.head;
        while (current != null) {
            count++;
            current = current.next();
        }
        return count;
    }

    // recursive
    public int length(Node current){
        if(current == null){
            //base case
            return 0;
            }
            return 1+length(current.next());
    }

}*/