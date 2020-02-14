/* *****************************************************************************
 *  Name:    Eli Ji
 *  Date: 10-15-19
 *  Description: Generic Iterable Implementation of a Queue
 **************************************************************************** */

import java.util.Iterator;

public class Queue<Item> implements Iterable<Item> {

    //This covers Queue() constructor
    private Node first;
    private Node last;
    private int size = 0;

    private class Node {
        Item item;
        Node next;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public void enqueue(Item item) {
        //1 in diagram
        Node oldLast = last;
        //2
        last = new Node();
        last.item = item;
        last.next = null;
        //3
        if(isEmpty()) first = last;
        else oldLast.next = last;
        //4
        size++;
    }

    public Item dequeue() {
        //1
        Item item = first.item;
        //2
        first = first.next;
        //4
        size--;
        //3
        return item;
    }

    public int size() {
        return size;
    }

    public Iterator<Item> iterator(){
        return new QueueIterator();
    }

    private class QueueIterator implements Iterator<Item> {

        private Node current = first;

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    /*
     * returns Josephus' position to avoid death
     * positions start at 0 and go to n-1
     * the executioner starts at position 0 and skips s people.
     */
    public int Josephus(int s, int n) {

        System.out.println("Josephus Problem with " + n + " people, skipping " + s + " people at a time." );
        System.out.println("------------------------------------------------------------");
        System.out.println("(Executioner begins at position 0)");

        //queue of remaining people, the first of the queue represents the position of the executioner
        Queue<Integer> myQ = new Queue<Integer>();

        for(int i = 0; i < n; i++){
            myQ.enqueue(i);
        }

        //repeat until only Josephus is left
        while(myQ.size() > 1){
            //makes s skips
            for(int i = 0; i < s; i++){
                myQ.enqueue(myQ.dequeue());
            }
            //execution!
            System.out.println("Person at position " + myQ.dequeue() + " was executed.");
        }

        int JosephusPos = myQ.dequeue();
        System.out.println("Josephus avoided death by sitting at position " + JosephusPos + ".");

        return JosephusPos;
    }
}
