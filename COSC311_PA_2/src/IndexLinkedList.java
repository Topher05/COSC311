public class IndexLinkedList {

    private Node front, back, rover;
    public IndexLinkedList(){
        front = new Node(" ", -1);// makes it so everything is just put inbetween these two nodes
        back = new Node("~", 1000);
        front.setNext(back);
        back.setPrevious(front);
        rover = null;
    }

    public void insert(Node newNode){

        rover = back;
        while(rover.getPrevious()!=null){
            if(rover.compareTo(newNode)<0){
                break;
            }
            rover = rover.getPrevious();
        }
        //inserts new node infront of rover

        newNode.setPrevious(rover);
        newNode.setNext(rover.getNext());
        rover.getNext().setPrevious(newNode);
        rover.setNext(newNode);
    }

    public void display(){
        rover = front.getNext(); //starts after  the placeholder front
        while(rover !=back){
            System.out.println(rover.toString());
            rover = rover.getNext();
        }
    }


    //linearly searches through the linked list until it stops on the correct node
    //then connects the 2 nodes to each other erasing any refrence to the node
    //otherwise it will change retVal to false and returns it
    public boolean delete(int key){
        boolean retVal = true;
        rover = front.getNext();

        while(rover!=back){
            if(rover.getWhere() == key)break;
            rover = rover.getNext();
        }

        if(rover!=back){
            rover.getPrevious().setNext(rover.getNext());
            rover.getNext().setPrevious(rover.getPrevious());
            rover.setPrevious(null);
            rover.setNext(null);

        } else{
            retVal=false;
        }
        return retVal;
    }

    public int findKey(String key){
        rover = front.getNext();

        while(rover!=back){
            if(rover.getKey().compareTo(key) == 0) break;
            rover = rover.getNext();
        }
        return ((rover.getKey().compareTo(key)==0) ? rover.getWhere() : -1);
    }

    //finds where the node is located in the main database
    public int findWhere(int posInArray){
        rover = front.getNext();
        while(rover!=back){
            if(rover.getWhere() == posInArray) break;
            rover = rover.getNext();
        }
        return (rover !=back) ? rover.getWhere() : -1;
    }

    public void iteratorInitFront(){rover = front.getNext(); }
    public void iteratorInitBack(){rover = back.getPrevious(); }

    public boolean hasNext(){
        return (rover!= back ?true : false);
    }

    public boolean hasPrevious(){return(rover!=front ? true: false); }

    //works simialr to a pop it gives the current position and then moves the iterator
    public int getNext(){
        int curry=rover.getWhere();
        rover = rover.getNext();
        return curry; //curry is the current
    }
    //This is the same as get next but in reverse, except it won't go past the front of the list
    public int getPrevious(){
        int curry = rover.getWhere();
        if (rover!= front) rover = rover.getPrevious();
        return curry;
    }
}
