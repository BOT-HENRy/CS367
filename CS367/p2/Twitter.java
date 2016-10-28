///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            Twitter
// Files:            Twitter.java
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
// TODO *** add comments as specified in the commenting guide ***

import java.util.*;
import java.io.File;
import java.io.FileNotFoundException;
public class  Twitter{

    public static void main(String[] args) throws TweetTooLongException {

        // TODO *** steps 1 - 3 of the main method ***
        Scanner stdin = new Scanner(System.in);  //for console input
                  	
		Timeline Tlist = new Timeline();	
		ArrayList<Tweet> allTweet = new ArrayList<Tweet>();		
		ArrayList followList = new ArrayList();
		String user = "";
		ArrayList userList = new ArrayList();
				
		for(int a = 0; a < args.length; a ++)
		{
			try {
				File srcFile = new File(args[a]);
				for(int i = 0; i < args[a].length(); i ++)
				{
					if(args[a].charAt(i) == '.')
					{
						user = args[a].substring(0, i);	
						userList.add(user);
						followList.add(user);
					}
				}
			
				Scanner fileIn = new Scanner(srcFile);				
				
				while(fileIn.hasNextLine())
				{
					String currline = fileIn.nextLine();
					int lineLength = currline.length();
					int index = 0;					
					int time = 0;
					String message = "";

					for(int k = 0; k < lineLength; k ++)
					{
						if(currline.charAt(k) == ':' && index == 0)
						{
							time = Integer.parseInt(currline.substring(index, k));
							index = k + 1;
							continue;
						}
						
						if(index != 0)
						{
							message = currline.substring(index, lineLength);
							index = k + 1;
							try{
								Tlist.add(new Tweet(time, message, user));
								allTweet.add(new Tweet(time, message, user));
								break;
							}catch(TweetTooLongException e){								
								break;
							}							
						}
						
					}				
				}
			} catch (FileNotFoundException e) 
			{
				System.out.println("Error: Cannot access input file");			
			}
		}
   
		
        boolean done = false;
        while (!done) {
            System.out.print("Enter option : ");
            String input = stdin.nextLine();

            //only do something if the user enters at least one character
            if (input.length() > 0) {
                String[] commands = input.split(" ");//split on space
                switch(commands[0]){
                    case "list":                    	
                    	if(commands.length != 2)
                    	{
                    		System.out.println("Invalid Command");
                    		break;
                    	}
                    	if(commands[1].equals("users"))
                    	{
                    		for(int i = 0; i < userList.size(); i ++)
                        	{
                        		System.out.println(userList.get(i));
                        	}                    		
                    	}else if(commands[1].equals("following"))
                    	{
                    		for(int i = 0; i < followList.size(); i ++)
                        	{
                        		System.out.println(followList.get(i));
                        	}
                    	}
                    	
                        break;
                    case "follow":
                    	if(commands.length != 2)
                    	{
                    		System.out.println("Invalid Command");
                    		break;
                    	}
                    	if(!userList.contains(commands[1]))
                    	{
                    		System.out.println("Invalid user");
                    	}
                    	else if(followList.contains(commands[1]))
                    	{
                    		System.out.println("Warning: User already followed");
                    	}
                    	else
                    	{
                    		for(int i = 0; i < userList.size(); i ++)
                    		{
                    			if(userList.get(i).equals(commands[1]))
                    			{
                    				followList.add(i, commands[1]);
                    			}
                    		}
                    		for(int j = 0; j < allTweet.size(); j ++)
                    		{
                    			if(allTweet.get(j).getUser().equals(commands[1]))
                    			{
                    				Tlist.add(allTweet.get(j));
                    			}
                    		}
                    	}
                        break;
                    case "unfollow":
                    	if(commands.length != 2)
                    	{
                    		System.out.println("Invalid Command");
                    		break;
                    	}
                    	if(!userList.contains(commands[1]))
                    	{
                    		System.out.println("Invalid user");
                    	}
                    	else if(!followList.contains(commands[1]))
                    	{
                    		System.out.println("Warning: User not followed");
                    	}
                    	else
                    	{
                    		for(int i = 0; i < followList.size(); i ++)
                        	{
                    			if(followList.get(i).equals(commands[1]))
                    			{
                    				followList.remove(i);
                    			}
                        	}
                    		Tlist.remove(commands[1]);
                    	}
                        break;
                    case "search":
                    	if(commands.length != 2)
                    	{
                    		System.out.println("Invalid Command");
                    		break;
                    	}
                    	Tlist.search(commands[1]).print();
                        break;
                    case "print":
                    	if(commands.length > 2)
                    	{
                    		System.out.println("Invalid Command");
                    		break;
                    	}
                    	if(commands.length<2)
                    	{
                    		Tlist.print();
                    	}else if(commands.length == 2)
                    	{
                    		int timeIndex = Integer.parseInt(commands[1]);
                    		Tlist.print(timeIndex);
                    	}                    	
                        break;
                    case "quit":
                        done = true;
                        System.out.println("exit");
                        break;
                    default:  //a command with no argument
                        System.out.println("Invalid Command");
                        break;
                }
            } //end if
        } //end while
    } //end main
}