public class Node{
    private Node left;
    private String key;
    private int where;
    private Node right;

    public Node(String k, int w){
        left = null;
        right = null;
        key = new String(k);
        where = w;

    }

    public boolean leftHasNext(){
        return (left == null ? false : true);
    }

    public boolean rightHasNext(){
        return (right == null ? false : true);
    }

    public int compareTo(Node otherNode){ return(key.compareTo(otherNode.key)); }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public int getWhere() {
        return where;
    }

    public void setWhere(int where) {
        this.where = where;
    }

    @Override
    public String toString() {
        return "Node{" +
                "key='" + key + '\'' +
                ", where='" + where + '\'' +
                '}';
    }
}
