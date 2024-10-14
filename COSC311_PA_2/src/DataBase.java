/*
Name: Christopher Martus
Assignment Number: 2
COSC 311 - Winter 2023

This Program is designed as a database of students. It holds their ID, first name, and last name all as strings.
The main array stores all three as a database record which is made up of index records. There are three doubly double
ended linked lists (IndexLinkedList sorted) that store the ID, first name, and last name with a variable that says where in
the main array it is stored.

I implemented a static stack to reclaim space after a deletion
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DataBase {

    private DataBaseRecord[] data;
    private deleteStack dStack;
    private IndexLinkedList ID, First, Last;
    private int next = 0;

    public DataBase() {
        ID = new IndexLinkedList();
        First = new IndexLinkedList();
        Last = new IndexLinkedList();
        data = new DataBaseRecord[101];
        dStack = new deleteStack(101);


        try {
            File fileOfData = new File("data");
            Scanner dataReader = new Scanner(fileOfData);
            while (dataReader.hasNext()) {
                String firstName = dataReader.next();
                String lastName = dataReader.next();
                String id = dataReader.next();

                Node fNode = new Node(firstName, next);
                First.insert(fNode);
                Node lNode = new Node(lastName, next);
                Last.insert(lNode);
                Node idNode = new Node(id, next);
                ID.insert(idNode);

                DataBaseRecord newRec = new DataBaseRecord(id, firstName, lastName);
                data[next] = newRec;
                next++;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void addIt() {
        Scanner kbin = new Scanner(System.in);

        System.out.println("Enter ID Please: ");
        String id = kbin.nextLine();

        int reSpace;//for reusing space

        if (!dStack.isEmpty() && ID.findKey(id) == -1) {
            reSpace = dStack.pop();
            Node idNode = new Node(id, reSpace);
            ID.insert(idNode);

            System.out.println("Enter First Name Please: ");
            String first = kbin.nextLine();
            Node fNode = new Node(first, reSpace);
            First.insert(fNode);

            System.out.println("Enter Last Name Please: ");
            String last = kbin.nextLine();
            Node lNode = new Node(last, reSpace);
            Last.insert(lNode);

            DataBaseRecord newRec = new DataBaseRecord(id, first, last);
            data[reSpace] = newRec;

        } else if (ID.findKey(id) == -1) { //using the find function to make sure that the ID doesn't exist

            Node idNode = new Node(id, next);
            ID.insert(idNode);

            System.out.println("Enter First Name Please: ");
            String first = kbin.nextLine();
            Node fNode = new Node(first, next);
            First.insert(fNode);

            System.out.println("Enter Last Name Please: ");
            String last = kbin.nextLine();
            Node lNode = new Node(last, next);
            Last.insert(lNode);

            DataBaseRecord newRec = new DataBaseRecord(id, first, last);
            data[next] = newRec;
            next++;
        } else {
            System.out.println("ID already exists");
        }

    }

    //First it gets id and then finds the position in the man array
    // then finds the where in all arrays then deletes them
    public void deleteIt() {
        Scanner kbin = new Scanner(System.in);
        System.out.println("Enter Key Please: ");

        String key = kbin.nextLine();
        int where = ID.findKey(key);
        boolean deleted = false;

        if (where != -1) {
            int pos = ID.findWhere(where);
            deleted = ID.delete(pos);
            deleted = First.delete(pos);
            deleted = Last.delete(pos);
            dStack.push(pos);
        }
        if (deleted) {
            System.out.println("Deleted");
        } else {
            System.out.println("ID not found");
        }

    }

    //I first try and find the id if the id exist I get the position in the main
    //array and print it out
    public void findIt() {
        Scanner kbin = new Scanner(System.in);
        System.out.println("Enter Key Please: ");

        int where = ID.findKey(kbin.nextLine());
        if (where != -1) {
            int pos = ID.findWhere(where);
            //System.out.println("find it method pos: " + pos);
            //System.out.println("find it method data[where]: " + data[pos]);
            System.out.println(data[pos].toString());
        } else {
            System.out.println("ID not found");
        }

    }

    public void ListByIDAscending() {
        ID.iteratorInitFront();

        while (ID.hasNext()) {
            int temp = ID.getNext(); //finding the location in the main array
            System.out.println(data[temp]);
        }
    }

    public void ListByFirstAscending() {
        First.iteratorInitFront();
        while (First.hasNext()) {
            int temp = First.getNext();
            System.out.println(data[temp]);
        }
    }

    public void ListByLastAscending() {
        Last.iteratorInitFront();
        while (Last.hasNext()) {
            int temp = Last.getNext();
            System.out.println(data[temp]);
        }
    }

    public void ListByIDDescending() {
        ID.iteratorInitBack();
        while (ID.hasPrevious()) {
            int temp = ID.getPrevious();
            System.out.println(data[temp]);
        }
    }

    public void ListByFirstDescending() {
        First.iteratorInitBack();
        while (First.hasPrevious()) {
            int temp = First.getPrevious();
            System.out.println(data[temp]);
        }
    }

    public void ListByLastDescending() {
        Last.iteratorInitBack();
        while (Last.hasPrevious()) {
            int temp = Last.getPrevious();
            System.out.println(data[temp]);
        }
    }

}

