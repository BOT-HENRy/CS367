///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            Twitter
// Files:            SimpleLinkedList.java
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

public class SimpleLinkedList<E> implements ListADT<E>{
	private DblListnode<E> head;
	private int numItems;
	
	public SimpleLinkedList()
	{
		head = null;
		numItems = 0;
	}
	 
	public void add (E item)
	{
		if(item == null)	
			throw new IllegalArgumentException();
		
		DblListnode<E> newNode = new DblListnode<E>(item);
		
		if(head == null)	head = newNode;
		else
		{			
			DblListnode<E> curr = head;
			while(curr.getNext() != null)
			{
				curr = curr.getNext();
			}
			curr.setNext(newNode);
			newNode.setPrev(curr);
		}
		numItems++;	
	}
    
	public void add(int pos, E item) 
	{
		if(item == null)	
			throw new IllegalArgumentException();
		if(pos < 0 || pos > numItems)
			throw new IndexOutOfBoundsException();
		
	    if (pos == numItems) 
	    {
	        add(item);
	        return;
	    }
	    DblListnode<E> newNode = new DblListnode<E>(item);
	    
	    if(pos ==0)
	    {
	    	newNode.setNext(head);
	    	head.setPrev(newNode);
	    	head = newNode;
	    	return;
	    	
	    }
	    DblListnode<E> curr = head;
	    for(int i = 0; i < pos - 1; i++)
	    {
	    	curr = curr.getNext();
	    }
	    
	    newNode.setNext(curr.getNext());
	    curr.getNext().setPrev(newNode);
	    newNode.setPrev(curr);
	    curr.setNext(newNode);
	    numItems++;
	}
	  
    public boolean contains(E item) 
    {
    	boolean isFound = false;

		if(item == null) 
			return isFound;

		DblListnode<E> curr = head;
		while(curr.getNext() != null)
		{
			curr = curr.getNext();
			if(curr.equals(item))
				isFound = true;
		}
		
		return isFound;
    }
    
    public int size() {
        return numItems;
    }
    
    public boolean isEmpty() {
    	if(numItems == 0)
    	{
    		return true;
    	}
        return false;
    }
    
    public E get(int pos) {
    	if(pos < 0 || pos >= numItems)
			throw new IndexOutOfBoundsException();

		DblListnode<E> curr = head;
		for(int i  = 0; i < pos; i++)
		{
			curr = curr.getNext();
		}

		return curr.getData();
    }
    
    public E remove(int pos) {
    	if(pos < 0 || pos >= numItems)
			throw new IndexOutOfBoundsException();

		DblListnode<E> curr = head;
		for(int i = 0; i < pos; i++)
	    {
	    	curr = curr.getNext();
	    }

		if (pos == numItems-1)
	    {
			curr.getPrev().setNext(null);
	    }
		else
		{
			curr.getNext().setPrev(curr.getPrev());
			curr.getPrev().setNext(curr.getNext());
	    } 
		numItems--;
		return curr.getData();
    }
}
