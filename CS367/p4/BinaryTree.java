///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            GuessingGame
// Files:            BinaryTree.java
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

/**
 * store the information used by the program
 * Each node of the tree that is not a leaf is a question.  
 * used for both storage of the information, and navigation of the tree. 
 * hold not only a reference to the root of the tree, 
 * but also a reference to the current tree node for use navigating the tree.
 * 
 * @author Yizhe Qu, Xuxiang Wu
 */
public class BinaryTree<E>{
	private String data;
	private BinaryTreenode<E> root;
	private BinaryTreenode<E> curr;
	
	//Constructs an empty BinaryTree with a null root.	
	public BinaryTree()
	{
		root = curr = null;
	}
	
	//Constructs a BinaryTree with data stored in its root.
	public BinaryTree(E data) 
	{
		root = new BinaryTreenode<E>(data);
	} 
	
	//Starts the current reference at the root of the tree 
	//to begin navigation of the tree.
	public void start()
	{
		curr = root;
	}
	
	/*Returns the data stored in the current node in navigation. 
	 * Throws an IllegalBinaryTreeOpException if there is no current node in navigation.*/
	public E getCurrent() throws IllegalBinaryTreeOpException
	{
		if (this.curr.getData() == null) 
		{
			throw new IllegalBinaryTreeOpException("Cannot get data; there is no current node");
		}
		return this.curr.getData();		
	}
	
	/*Moves the current reference to the left child of the current node in navigation.
	 *Throws an IllegalBinaryTreeOpException if the current node does not have a left child.*/
	public void goLeft() throws IllegalBinaryTreeOpException
	{
		if(this.curr.getLeft() == null)
		{
			throw new IllegalBinaryTreeOpException("Cannot go left; "
					+ "there is no left child of this node.");
		}
		this.curr = this.curr.getLeft();
	}
	
	/*Moves the current reference to the right child of the current node in navigation. 
	 *Throws an IllegalBinaryTreeOpException if the current node does not have a right child.*/
	public void goRight() throws IllegalBinaryTreeOpException
	{
		if(this.curr.getRight() == null)
		{
			throw new IllegalBinaryTreeOpException("Cannot go left; "
					+ "there is no right child of this node.");
		}
		this.curr = this.curr.getRight();
	}
	
	/*Returns true if the current node in navigation is a leaf (i.e., has no children).*/
	public boolean isLeaf() throws IllegalBinaryTreeOpException
	{
		if(this.curr == null)
		{
			throw new IllegalBinaryTreeOpException("Cannot get data; "
					+ "there is no current node");
		}
		if(this.curr.getRight() == null && this.curr.getLeft() == null)
		{
			return true;
		}else
		{
			return false;
		}		
	}
	
	//Changes the data held by the current node in navigation to the specified data.
	public void changeCurrent(E data) throws IllegalBinaryTreeOpException
	{
		if(this.curr == null)
		{
			throw new IllegalBinaryTreeOpException("Cannot change data; "
					+ "there is no current node");
		}
		this.curr.setData(data);
	}
	
	/*Adds a node with the specified child as the right child of the current node in navigation. 
	 *Throws an IllegalBinaryTreeOpException if the current node already has a right child.*/
	public void addRightChild(E child) throws IllegalBinaryTreeOpException
	{
		if(this.curr.getRight() != null)
		{
			throw new IllegalBinaryTreeOpException("Cannot add right; "
					+ "the current node already has a right child");
		}else
		{
			this.curr.setRight(child);
		}
	}
	
	/*Adds a node with the specified child as the left child of the current node in navigation.
      Throws an IllegalBinaryTreeOpException if the current node already has a left child.*/
	public void addLeftChild(E child) throws IllegalBinaryTreeOpException
	{
		if(this.curr.getLeft() != null)
		{
			throw new IllegalBinaryTreeOpException("Cannot add left; "
					+ "the current node already has a left child");
		}else
		{
			this.curr.setLeft(child);
		}
	}
	
	//Pre-order prints the tree
	public void print()
	{
		printTree(this.root, 0);		
	}
	/**
	 * use recursive method to print the tree 
	 * and differ different levels by spaces
	 */
	private void printTree(BinaryTreenode<E> root, int spaces)
	{
		if(root == null) return;
		for(int i = 0; i < spaces; i++)
		{
			System.out.print("   ");
		}
		System.out.println(root.getData());
		printTree(root.getLeft(), spaces + 1);
		printTree(root.getRight(), spaces + 1);
	}
}


