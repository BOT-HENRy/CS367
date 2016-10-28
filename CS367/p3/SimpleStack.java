///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            Train
// Files:            SimpleStack.java
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

public class SimpleStack<E> implements StackADT<E> {
	private int capacity;  // initial array size
    private E[] items; // the items in the stack
    private int numItems;   // the number of items in the stack

    //create a Stack which can hold "capacity" values
    public SimpleStack(int capacity) {
    	this.capacity = capacity;
    	items = (E[])(new Object[this.capacity]);
        numItems = 0;
    }
    
    //add ob to the top of the Stack (error if the Stack is Full)
    public void push(E ob) throws FullStackException {
    	if (items.length == numItems)
    	{
    		throw new FullStackException();
    	}
    	else
    	{
    		items[numItems] = ob;
    	    numItems++;
    	}
    }

    //remove and return the item from the top of the Stack
    //(error if the Stack is empty)
    public E pop() throws EmptyStackException {
    	if (numItems == 0) 
    	{
            throw new EmptyStackException();
        }
        else 
        {
            numItems--;
            return items[numItems];
        }
    }
  
    //return the item that is on the top of the Stack, but do not remove it
    //(error if the Stack is empty)
    public E peek() throws EmptyStackException {
    	if (numItems == 0) 
    	{
            throw new EmptyStackException();
        }
        else 
        {
            return items[numItems - 1];
        }
    }
    
    //return true iff the Stack is empty
    public boolean isEmpty() {
    	if (numItems == 0) 
    	{
            return true;
        }
    	return false;
    }
    
    ////return true iff the Stack is full
    public boolean isFull() {
    	if (numItems == capacity) 
    	{
            return true;
        }
    	return false;
    }
}
