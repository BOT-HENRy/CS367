///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            GuessingGame
// Files:            GuessingGame.java
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

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

/**
 * creates and uses a BinaryTree to represent and 
 * navigate through the information used to ask questions and make guesses.
 * Each node of the tree that is not a leaf is a question. 
 * The left child of the tree contains, at its leaves, 
 * all the answers that are true for this question. 
 * The right child of the tree contains, at its leaves, 
 * all the answers that are false for this question. 
 * 
 * 
 * @author Yizhe Qu, Xuxiang Wu
 */
public class GuessingGame {
	
	public static void main(String args[]) throws IllegalBinaryTreeOpException{

		String fileName = args[0];
		String command = "";
		BinaryTree guessGame = new BinaryTree(null);
		
		String questions = "";
		
		File srcFile = new File(fileName);
		Scanner fileIn = null;
		
		try {
			fileIn = new Scanner(srcFile);
		} catch (FileNotFoundException ex) {
			System.out.println("File not found!");
		}
				
		do{	
			System.out.println("Please enter a command (o, p, q, r):");
			command = fileIn.nextLine();	
			/**
			 * preorder print the BinaryTree. If the tree is empty, print "Empty Tree".
			 * 
			 */
			if(command.equals("o"))
			{
				try{
					guessGame.getCurrent();
				}catch(NullPointerException ex){
					System.out.println("Empty Tree");
					continue;
				}		
				guessGame.print();
			}
			/**
			 * Play the game with the program
			 * If the tree is empty, follow the steps for the "r" command
			 * Otherwise, play the game
			 */
			else if(command.equals("p"))
			{
				try{
					guessGame.getCurrent();
				}catch(NullPointerException ex){
					System.out.println("the tree is empty, please build the first question");
					System.out.println("Please enter a question.");
					questions = fileIn.nextLine();
					guessGame = new BinaryTree(questions);
					guessGame.start();
					System.out.println("Please enter something that is true for that question.");
					questions = fileIn.nextLine();
					guessGame.addLeftChild(questions);
					System.out.println("Please enter something that is false for that question.");
					questions = fileIn.nextLine();
					guessGame.addRightChild(questions);		
				}
				
				guessGame.start();
				
				do{
					System.out.println(guessGame.getCurrent());
					String choice = fileIn.nextLine();
					if(choice.equals("y")){
						guessGame.goLeft();
					}
					else if(choice.equals("n")){
						guessGame.goRight();
					}
					else{
						System.out.println("please use 'y' for yes and 'n' for no!");
					}
				}while(!guessGame.isLeaf());
				
				System.out.println("I guess: "+guessGame.getCurrent()+". Was I right?");
				String choice = fileIn.nextLine();
				if(choice.equals("y")){
					System.out.println("I win!");
				}
				else if(choice.equals("n")){
					String answerN = (String) guessGame.getCurrent();
					System.out.println("Darn. Ok, tell me a question that is "
							+ "true for your answer, but false for my guess.");
					String newQuestion = fileIn.nextLine();
					System.out.println("Thanks! And what were you thinking of?");
					String answerY = fileIn.nextLine();
					
					guessGame.changeCurrent(newQuestion);
				    guessGame.addLeftChild(answerY);
					guessGame.addRightChild(answerN);					
				}else{
					System.out.println("please use 'y' for yes and 'n' for no!");
				}
			}
			/**
			 * Reset the game. You should make the current tree completely empty
			 */
			else if(command.equals("r"))
			{
				System.out.println("Please enter a question.");
				questions = fileIn.nextLine();
				guessGame = new BinaryTree(questions);
				guessGame.start();
				System.out.println("Please enter something that is true for that question.");
				questions = fileIn.nextLine();
				guessGame.addLeftChild(questions);
				System.out.println("Please enter something that is false for that question.");
				questions = fileIn.nextLine();
				guessGame.addRightChild(questions);								
			}
		}while(!command.equals("q")); //quit the program
	}
}
