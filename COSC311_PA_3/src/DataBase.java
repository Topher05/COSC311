/*
Name: Christopher Martus
Assignment Number: 3
COSC 311 - Winter 2023

I opted for the easier version of the assignment

This Program is designed as a database of students. It holds their ID, first name, and last name all as strings.
The main array stores all three as a database record which is made up of index records. There are three Binary Search trees
that store the ID, first name, and last name with a variable that says where in
the main array it is stored.
I implemented a static stack to reclaim space after a deletion
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class DataBase {

    private DataBaseRecord[] data;
    private deleteStack dStack;
    private BST ID, First, Last;
    private int next = 0;

    public DataBase() {
        ID = new BST();
        First = new BST();
        Last = new BST();
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

        if (!dStack.isEmpty() && ID.findW(id) == -1) {
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

        } else if (ID.findW(id) == -1) { //using the find function to make sure that the ID doesn't exist

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
    //by getting the node from the BST.
    // Then it finds the where in all arrays then deletes them
    public void deleteIt() {
        int where = -1;
        String firstName = " ", lastName = " ";

        Scanner kbin = new Scanner(System.in);
        System.out.println("Enter Key Please: ");

        String key = kbin.nextLine();
        Node temp = ID.find(key);
        if(temp != null){
            where = temp.getWhere();
            firstName = data[where].getFirst();
            lastName = data[where].getLast();
        }

        boolean deleted = false;

        if (where != -1) {
            deleted = ID.delete(key, where);
            deleted = First.delete(firstName, where);
            deleted = Last.delete(lastName, where);
            dStack.push(where);
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

        int where = ID.findW(kbin.nextLine());
        if (where != -1) {
            //System.out.println("find it method pos: " + pos);
            //System.out.println("find it method data[where]: " + data[pos]);
            System.out.println(data[where].toString());
        } else {
            System.out.println("ID not found");
        }

    }

    public void ListByIDAscending() {
        ID.traverse(1, data);
    }

    public void ListByFirstAscending() {
        First.traverse(1, data);
    }

    public void ListByLastAscending() {
        Last.traverse(1, data);
    }

    public void ListByIDDescending() {
        ID.traverse(2, data);
    }

    public void ListByFirstDescending() {
        First.traverse(2, data);
    }

    public void ListByLastDescending() {
        Last.traverse(2, data);
    }

}

