public class BST {
    private Node top;
    private Node rover;

    public BST() {
        top = null;
        rover = null;
    }

    //compares the newNode to the current and then checks to see which direction it should go down the tree.
    //When the current is null it uses parent to connect the newNode
    public void insert(Node newNode) {

        Node prev = null;
        Node current = top;
        int compVal = 0;

        if (top == null) {
            top = newNode;
        } else {

            while (current != null) {
                compVal = newNode.getKey().compareTo(current.getKey());
                prev = current;
                if (compVal <= 0) {
                    current = current.getLeft();
                } else {
                    current = current.getRight();
                }
            }
            if (compVal <= 0) {
                prev.setLeft(newNode);
            } else {
                prev.setRight(newNode);
            }
        }
    }


    //traverses the tree to find the Node
    public Node find(String key) {
        rover = top;
        int compVal;
        while (rover != null) {
            compVal = rover.getKey().compareTo(key);
            if (compVal == 0) {
                break;
            } else if (compVal < 0) {
                rover = rover.getRight();
            } else {
                rover = rover.getLeft();
            }
        }
        return rover;
    }

    //traverses the tree to return the where value
    public int findW(String key) {
        rover = top;
        int compVal;
        while (rover != null) {
            compVal = rover.getKey().compareTo(key);
            if (compVal == 0) {
                break;
            } else if (compVal < 0) {
                rover = rover.getRight();
            } else {
                rover = rover.getLeft();
            }
        }
        return (rover !=null) ? rover.getWhere() : -1;
    }



    public boolean delete(String key, int w) {
        Node current = top;
        Node parent = top;
        boolean isLeftChild = true;

        //looking to see if the node/record exists if not returns false
        while (current.getKey() != key && current.getWhere() != w) {
            parent = current;

            if (current.getKey().compareTo(key)>0) {
                isLeftChild = true;
                current = current.getLeft();
            } else {
                isLeftChild = false;
                current = current.getRight();
            }

            if (current == null) {
                return false;
            }
        }
            //if current is the top just remove it else remove the parents connection to the current
            if (!current.rightHasNext() && !current.leftHasNext()) {
                if (current == top) {
                    top = null;
                } else if (isLeftChild) {
                    parent.setLeft(null);
                } else {
                    parent.setRight(null);
                }

                //if there is no right child, replace with left side
            } else if (!current.rightHasNext()) {
                if (current == top) {
                    top = current.getLeft();
                } else if (isLeftChild) {
                    parent.setLeft(current.getLeft());
                } else {
                    parent.setRight(current.getLeft());
                }
            } else if (!current.leftHasNext()) {
                if (current == top) {
                    top = current.getRight();
                } else if (isLeftChild) {
                    parent.setLeft(current.getRight());
                } else {
                    parent.setRight(current.getRight());
                }
                //two children, so replace with inorder successor
            } else {
                Node successor = getSuccessor(current);

                //connect parent to successor instead
                if (current == top) {
                    top = successor;
                } else if (isLeftChild) {
                    parent.setLeft(successor);
                } else {
                    parent.setRight(successor);
                }
                successor.setLeft(current.getLeft());
            }

        return true;
    }

    //finds the inorder Successor
    private Node getSuccessor(Node delNode) {
        Node successorParent = delNode;
        Node successor = delNode;
        Node current = delNode.getRight();

        while (current.leftHasNext()) {
            successorParent = successor;
            successor = current;
            current = current.getLeft();
        }
        if (successor != delNode.getRight()) {
            successorParent.setLeft(successor.getRight());
            successor.setRight(delNode.getRight());
        }
        return successor;
    }

    //controls the print functions
    public void traverse(int t, DataBaseRecord[] arr){
        switch(t){
            case 1: ascending(top, arr);
                    break;
            case 2: descending(top, arr);
                    break;
        }
    }


    public void ascending(Node current, DataBaseRecord[] arr) {
        if (current != null) {
            ascending(current.getLeft(), arr);
            System.out.println(arr[current.getWhere()]);
            ascending(current.getRight(), arr);
        }
    }
    public void descending(Node current, DataBaseRecord[] arr) {
        if (current != null) {
            descending(current.getRight(), arr);
            System.out.println(arr[current.getWhere()]);
            descending(current.getLeft(), arr);
        }
    }


}