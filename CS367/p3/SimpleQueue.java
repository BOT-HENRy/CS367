///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            Train
// Files:            SimpleQueue.java
// Semester:         CS367 Fall 2014
//
// Author:           Yizhe Qu
// Email:            qu23@wisc.edu
// CS Login:         qu
// Lecturer's Name:  Jim Skrentny
// 
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ////////////////////
//
// Pair Partner:     Xuxiang Wu
// Email:            xwu247@wisc.edu
// CS Login:         xuxiang
// Lecturer's Name:  Jim Skrentny
//
//                   STUDENTS WHO GET HELP FROM ANYONE OTHER THAN THEIR PARTNER
// Credits:          none
//////////////////////////// 80 columns wide //////////////////////////////////

public class SimpleQueue<E> implements QueueADT<E> {
	private int capacity;  // initial array size
    private E[] items; // the items in the queue
    private int numItems;   // the number of items in the queue
    
    //create a Queue which can hold "capacity" values
    public SimpleQueue(int capacity) {
    	this.capacity = capacity;
    	items = (E[])(new Object[this.capacity]);
        numItems = 0;
    }
        
    //add ob to the rear of the Queue 
    //(error if the Queue is full)
    public void enqueue(E ob) throws FullQueueException {
    	if (items.length == numItems) 
    	{
    		throw new FullQueueException();
    	}
    	else
    	{
    		items[numItems] = ob;
    	    numItems ++;
    	}
    }

    //remove and return the item from the front of the Queue
    //(error if the Queue is empty)
    public E dequeue() throws EmptyQueueException {
    	if (items.length == 0) 
    	{
    		throw new EmptyQueueException();
    	}
    	else
    	{
    		E head = items[0];
    		for(int i = 0; i < numItems - 1; i ++)
    		{
    			items[i] = items[i + 1];
    		}
    	    numItems --;
    	    return head;
    	}
    }
    
    //return the front item of the Queue, but do not remove it
    //(error if the Stack is empty)
    public E peek() throws EmptyQueueException{
    	if (items.length == 0) 
    	{
    		throw new EmptyQueueException();
    	}
    	else
    	{
    		return items[0];
    	}
    }

    //return true iff the Queue is empty
    public boolean isEmpty() {
    	if (numItems == 0) 
    	{
            return true;
        }
    	return false;
    }
    
    //return true iff the Queue is full
    public boolean isFull() {
    	if (numItems == capacity) 
    	{
            return true;
        }
    	return false;
    }
}
