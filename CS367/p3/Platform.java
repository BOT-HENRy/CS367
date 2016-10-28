///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            Train
// Files:            Platform.java
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

public class Platform implements PlatformADT{
	private SimpleStack<Train> platForm;
	
	//create a Platform which can hold "capacity" trains
	public Platform(int capacity){		
		platForm = new SimpleStack<Train>(capacity);		
	}
	
	//Adds the train Train to the Platform (error if the Platform is Full)
	public void put(Train item) throws FullPlatformException {
		if (platForm.isFull())
		{
			throw new FullPlatformException();
		}
		else
		{
			try{
				platForm.push(item);
			}catch(FullStackException ex){
				System.out.println("The Stack is full");
			}			
		}
	}
	
	//Removes a Train from the Platform and returns it
	public Train get(){
		Train topTrain = null;
		try{
			topTrain = platForm.pop();
		}catch(EmptyStackException ex){
			System.out.println("The Stack is empty");
		}
		return topTrain;
	}
	
	//Return the Train at the exit of the  Platform without removing it
	public Train check(){
		Train topTrain = null;
		try{
			topTrain = platForm.peek();
		}catch(EmptyStackException ex){
			System.out.println("The Stack is empty");
		}
		return topTrain;
	}
	
	//Returns true iff the Platform is empty
	public boolean isEmpty(){
		return platForm.isEmpty();
	}
	
	//Returns true iff the Platform is full
	public boolean isFull(){
		return platForm.isFull();
	}
	
}
