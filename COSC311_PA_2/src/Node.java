public class Node {

    private String key;
    private int where;
    private Node next;
    private Node previous;

    public Node(String k, int w) {
        where = w;
        key = k;
        next = null;
        previous = null;
    }

    public String toString() {
        return " " + key + " ";
    }


    public void setNext(Node n) {
        next = n;
    }

    public Node getNext() {
        return next;
    }

    public Node getPrevious() {
        return previous;
    }

    public void setPrevious(Node previous) {
        this.previous = previous;
    }

    public int getWhere() {
        return where;
    }

    public String getKey(){
        return key;
    }

    public int compareTo(Node otherNode){ return(key.compareTo(otherNode.key)); }


}
