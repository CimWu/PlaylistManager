/* I hereby declare upon my word of honor that I have neither given nor 
 * received unauthorized help on this work.
 *  */
package com.mycompany.playlistmanager;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * DoubleList of Type Song, this constrains the types in this
 * list to only Song and allows me to use Song methods
 * @author whitw
 */
class DoubleList<Type extends Song> {

    /**
     * nested class to instantiate doubly ll
     */
    private class Node {
        public Type data; // holds song object
        public Node next; // next node in list
        public Node prev; // previous node in list
    }

    private Node head; // first node in ll
    private Node tail; // last node in ll

    /**
     * constructor
     */
    public DoubleList() {
        head = null;
        tail = null;
    }

    /**
     * add to start of list
     * @param item 
     */
    public void addStart(Type item) {
        Node newNode = new Node();
        newNode.data = item;
        newNode.next = head;
        newNode.prev = null;
        if (tail == null) {
            tail = newNode;
        } else {
            head.prev = newNode;
        }
        head = newNode;
    }

    /**
     * add to end of list
     * @param item 
     */
    public void addEnd(Type item) { 
        Node newNode = new Node();
        newNode.data = item;
        newNode.prev = tail;
        newNode.next = null;
        if (head == null) {
            head = newNode;
        } else {
            tail.next = newNode;
        }
        tail = newNode;
    }

    /**
     * prints the playlist from head to tail
     */
    public void print() {
        Node current = head;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
            
        }
    }

    /**
     * print list backwards
     * iteratively prints each node from tail to head
     */
    public void printBackwards() {
        Node current = tail;
        
        while (current != null) {
            System.out.println(current.data);
            current = current.prev;
            
        }
        
    }
    
    /**
     * Save method
     * Walks through the dll, starting at head, and writes each non-null node
     * to the users chosen .txt file
     * @param input 
     */
    public void save(String input) {
        String filename = input;

        try (FileWriter writer = new FileWriter(filename)) {
            Node current = head;
            while (current != null) {
                String artist = current.data.getArtist();
                String title = current.data.getTitle();
                String line = artist + " - " + title + "\n";
                
                writer.write(line);
                current = current.next;
                
            }

            System.out.println("Playlist saved to " + filename);
        } catch (IOException e) {
            System.out.println("Failed to save playlist " + e.getMessage());
        }
    }

    /**
     * removes a song
     * start at the head, if the current node doesn't equal the removed
     * item, move to the next node. if the current node equals the chosen
     * song to be removed, update the previous and next node in the list to
     * point to the correct node
     * handles song not found
     * @param item 
     */
    public void remove(Type item) {
        Node current = head;
        boolean found = false;

        while (current != null) {
            
            if (current.data.equals(item)) {
                
                // check if head and point nodes accordingly
                if (current.prev != null) {
                    current.prev.next = current.next;                    
                } else {
                    head = current.next;                    
                }
                
                // check if tail and point nodes accordinggly
                if (current.next != null) {
                    current.next.prev = current.prev;
                    
                } else {
                    tail = current.prev;                    
                }
                
                found = true;
                System.out.println("\nRemoved song: " + item + "\n");
                // stop after first instance incase of duplicates
                break;
            }

            current = current.next;
        }
        
        if (!found) {
            System.out.println("\nNo song " + item + " found.");
        }
    }

    /**
     * returns size of doubly ll
     * @return count
     */
    public int size() {
        int count = 0;
        Node current = head;
        
        while (current != null) {
            count++;
            current = current.next;
            
        }
        
        return count;
    }

    /**
     * "shuffles" (randomizes) the list
     * starts at the head of the dll and iterates through each node
     * sequentially. randomizes a number in the range of the dll size
     * and swaps the current node with that one
     */
    public void shuffle() {
        Random rng = new Random();

        int size = size();
        Node current = head;

        for (int i = 0; i < size; i++) {
            int randomIndex = rng.nextInt(size);
            Node node = getIndex(randomIndex);

            // swap the randomized node with the current node
            Type tempNode = current.data;
            current.data = node.data;
            node.data = tempNode;
            current = current.next;
            
        }
        
    }
    
    /**
     * helper method for shuffle()
     * retrieves node index
     * @param index
     * @return 
     */
    private Node getIndex(int index) {
        
        // just in case
        if (index < 0 || index >= size()){
            System.out.println("Index out of range (" + index + ")");
            return null;
        }

        Node current = head;
        int count = 0;

        while (count < index) {
            current = current.next;
            count++;

        }

        return current;
    }

    /**
     * reverses the list
     * create a temporary node that facilitates swapping nodes
     */
    public void reverse() {
        Node tempNode = null;
        Node current = head;

        while (current != null) {
            tempNode = current.prev;
            current.prev = current.next;
            current.next = tempNode;
            current = current.prev;
            
        }

        if (tempNode != null) {
            head = tempNode.prev;
            
        }
        
    }

}
