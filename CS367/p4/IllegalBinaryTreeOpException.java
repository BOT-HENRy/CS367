///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            GuessingGame
// Files:            IllegalBinaryTreeOpException.java
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
 * a checked exception
 * 
 * @author Yizhe Qu, Xuxiang Wu
 */

public class IllegalBinaryTreeOpException extends Exception{
	public IllegalBinaryTreeOpException(){
		super();
	}
	public IllegalBinaryTreeOpException(String msg){
		super(msg);
	}
}
