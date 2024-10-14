# COSC 311

This project is made up of four projects that I completed in my algorithm and data structures class.

## Description

### Stack

This program functions as a simple student database, storing each student's ID, first name, and last name as strings. The main array serves as the database, holding all student records. To efficiently access and manage the data, three index arrays (sorted by ID, first name, and last name) are used. Each index array contains a reference to the corresponding record's position in the main array. A static stack is implemented to reclaim space after a record is deleted, ensuring efficient memory management.

### Linked List

This program manages a student database, storing each student's ID, first name, and last name as strings. The data is organized in a main array, where each entry represents a student record. Three separate index arrays (sorted by ID, first name, and last name) are maintained for efficient data retrieval, with each entry pointing to the corresponding record's position in the main array. Like the stack-based version, a static stack is implemented to reclaim space when records are deleted.

### Binary Search Tree 

This program operates as a student database using binary search trees (BSTs) for efficient data storage and lookup. Each student record, consisting of ID, first name, and last name (all strings), is stored in the main array. For fast access, three BSTs are maintained—one for IDs, one for first names, and one for last names. Each node in the tree contains a reference to the record's position in the main array. A static stack is also implemented here to reclaim space after deletions, ensuring efficient memory utilization.

### Algorithm Speed Tester

This program compares the performance of three different sorting algorithms: **QuickSort**, **HeapSort**, and **MergeSort**. It measures the time taken by each algorithm to sort arrays of integers under three different conditions:

- **Ascending order**: The array is already sorted in increasing order.
- **Descending order**: The array is sorted in decreasing order.
- **Random order**: The array elements are arranged randomly.

The performance of each algorithm is timed using `System.nanoTime()` and printed for each dataset (ascending, descending, and random). After sorting, the program writes the sorted arrays to text files, with separate output files for each algorithm and dataset combination.

## Getting Started

### Dependencies

* Openjdk19
* Windows 10

### Installing

* This project was run using intellj compiler.
* Navigate to intellj's site https://www.jetbrains.com/idea/download/?section=windows and download the compiler

