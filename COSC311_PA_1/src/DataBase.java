/*
Name: Christopher Martus
Assignment Number: 1
COSC 311 - Winter 2023

This Program is designed as a database of students. It holds Their id, first name and last name all ass strings.
The main array stores all three as a database record which is made up of index record. There are Three Arrays
 Index arrays (sorted) that store the id, first and last names with a vairable that says where in the array it is
in the main array.

I implemented a static stack to reclaim space after a deletion
*/

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;


public class DataBase {

    private DataBaseRecord[] data;
    private deleteStack dStack;
    private IndexArray ID, First, Last;
    private int next = 0;

    public DataBase(){
        ID =new IndexArray(101);
        First = new IndexArray(101);
        Last = new IndexArray(101);
        data = new DataBaseRecord[101];
        dStack = new deleteStack(101);



        try {
            File fileOfData = new File("data");
            Scanner dataReader = new Scanner(fileOfData);
            while(dataReader.hasNext()){
                String firstName = dataReader.next();
                String lastName = dataReader.next();
                String id = dataReader.next();

                IndexRecord fRec = new IndexRecord(firstName, next);
                First.insert(fRec);
                IndexRecord lRec = new IndexRecord(lastName, next);
                Last.insert(lRec);
                IndexRecord idRec = new IndexRecord(id, next);
                ID.insert(idRec);

                DataBaseRecord newRec = new DataBaseRecord(id, firstName, lastName);
                data[next] = newRec;
                next++;
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public void addIt(){
        Scanner kbin = new Scanner(System.in);

        System.out.println("Enter ID Please: ");
        String id = kbin.nextLine();

        int reSpace;//for reusing space

        if(!dStack.isEmpty() && ID.find(id) == -1){
            reSpace = dStack.pop();
            IndexRecord idRec = new IndexRecord(id, reSpace);
            ID.insert(idRec);

            System.out.println("Enter First Name Please: ");
            String first = kbin.nextLine();
            IndexRecord fNRec = new IndexRecord(first, reSpace);
            First.insert(fNRec);

            System.out.println("Enter Last Name Please: ");
            String last = kbin.nextLine();
            IndexRecord lNRec = new IndexRecord(last, reSpace);
            Last.insert(lNRec);

            DataBaseRecord newRec = new DataBaseRecord(id, first, last);
            data[reSpace] = newRec;

        }else if(ID.find(id) == -1){ //using the find function to make sure that the ID doesn't exist

            IndexRecord idRec = new IndexRecord(id, next);
            ID.insert(idRec);

            System.out.println("Enter First Name Please: ");
            String first = kbin.nextLine();
            IndexRecord fNRec = new IndexRecord(first, next);
            First.insert(fNRec);

            System.out.println("Enter Last Name Please: ");
            String last = kbin.nextLine();
            IndexRecord lNRec = new IndexRecord(last, next);
            Last.insert(lNRec);

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
        int where = ID.find(key);
        boolean deleted = false;

        if(where != -1) {
            int pos = ID.getPosInDB(where);
            deleted = ID.delete(pos);
            deleted = First.delete(pos);
            deleted = Last.delete(pos);
            dStack.push(pos);
        }
        if(deleted){
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

        int where = ID.find(kbin.nextLine());
        if(where != -1){
            int pos = ID.getPosInDB(where);
            //System.out.println("find it method pos: " + pos);
            //System.out.println("find it method data[where]: " + data[pos]);
            System.out.println(data[pos].toString());
        }else{
            System.out.println("ID not found");
        }

    }

    public void ListByIDAscending() {
        ID.iteratorInitFront();
        System.out.println(ID.hasNext());
        while(ID.hasNext()){
            int temp=ID.getPosInDB(ID.getNext()); //finding the location in the main array
            System.out.println(data[temp]);
        }
    }

    public void ListByFirstAscending() {
        First.iteratorInitFront();
        while(First.hasNext()){
            int temp=First.getPosInDB(First.getNext());
            System.out.println(data[temp]);
        }
    }

    public void ListByLastAscending() {
        Last.iteratorInitFront();
        while(Last.hasNext()){
            int temp=Last.getPosInDB(Last.getNext());
            System.out.println(data[temp]);
        }
    }

    public void ListByIDDescending() {
        ID.iteratorInitBack();
        while(ID.hasPrevious()){
            int temp=ID.getPosInDB(ID.getPrevious());
            System.out.println(data[temp]);
        }
    }

    public void ListByFirstDescending() {
        First.iteratorInitBack();
        while(First.hasPrevious()){
            int temp=First.getPosInDB(First.getPrevious());
            System.out.println(data[temp]);
        }
    }

    public void ListByLastDescending() {
        Last.iteratorInitBack();
        while(Last.hasPrevious()){
            int temp=Last.getPosInDB(Last.getPrevious());
            System.out.println(data[temp]);
        }
    }

}
