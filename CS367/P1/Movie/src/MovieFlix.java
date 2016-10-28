// TODO *** add comments as specified in the commenting guide ***
///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            Movie
// Files:            MovieFlix.java
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
import java.util.*;

/**
 * 
 *The application program, MovieFlix, creates and uses an MovieDatabase 
 *to represent and process information about movies.
 *
 *
 * @author Yizhe Qu, Xuxiang Wu
 */

public class MovieFlix {
	
	/**
	 * change the format of the output
	 * turn first letter of each word capitalized 
	 *
	 */
	public void changeFormat(String name)
	{		
		String[] upperCase = new String[name.length()];
		int afterSpace=0;
		for(int i = 0; i < name.length(); i++)
		{
			if(i == 0)
			{
				upperCase[0] = name.substring(0, 1).toUpperCase();
			}else if(name.substring(i, i + 1).equals(" "))
			{
				afterSpace = i;
				upperCase[i] = name.substring(i, i + 1);
			}else if(i == afterSpace + 1 && i != 1)
			{
				upperCase[i]=name.substring(i, i + 1).toUpperCase();
			}else
			{
				upperCase[i]=name.substring(i, i + 1);
			}
		}
		for(int a =0; a < name.length(); a++){
			System.out.print(upperCase[a]);
		}	
	}	

    public static void main(String[] args) {

        // TODO *** steps 1 - 3 of the main method ***

        Scanner stdin = new Scanner(System.in);  //for console input

        File srcFile = new File(args[0]);
        //File srcFile = new File("actor.txt");
        MovieDatabase m = new MovieDatabase();
        MovieFlix mF = new MovieFlix();
        
		try {
			Scanner fileIn = new Scanner(srcFile);
			m.MovieDatabase();
			
			while(fileIn.hasNextLine())
			{
				String currline = fileIn.nextLine();
				int lineLength = currline.length();
				int index = 0;
				String Actor = "";
				String Movie = "";
				for(int k = 0; k < lineLength; k++)
				{
					if(currline.charAt(k) == ',' && index == 0)
					{
						Actor = currline.substring(index, k).toLowerCase();
						index = k + 1;
						continue;
					}
					
					if(currline.charAt(k) == ',' && index != 0)
					{
						Movie = currline.substring(index, k).toLowerCase();
						m.addMovie(Movie);
						m.addActor(Actor, Movie);
						index = k + 1;
					}
					
					if(k == lineLength-1 && index != 0)
					{
						Movie = currline.substring(index, k + 1).toLowerCase();
						m.addMovie(Movie);
						m.addActor(Actor, Movie);
						index = k + 1;						
					}
				}				
			}
		} catch (FileNotFoundException e) 
		{
			System.out.println("Error: Cannot access input file");			
		}
        
        boolean done = false;
        while (!done) 
        {
            System.out.print("Enter option (cdprswx): ");
            String input = stdin.nextLine();

            //only do something if the user enters at least one character
            if (input.length() > 0) 
            {
                char choice = input.charAt(0); //strip off option character
                String remainder = "";         //will hold the remaining input
                if (input.length() > 1) {      //if there is an argument
                    //trim off any leading or trailing spaces
                    remainder = input.substring(1).trim(); 
                    remainder = remainder.toLowerCase();
                    
                    switch (choice) { //the commands that have arguments
    
                    case 'c':
                        // TODO *** implement this option ***
                    	if(!m.containsMovie(remainder))
                    	{
                    		System.out.println("movie not found");
                    	}else
                    	{
                    		ArrayList<String> Casts = new ArrayList<String>();
                        	Casts = (ArrayList<String>) m.getCast(remainder);
                        	if(Casts.equals(null))
                        	{
                        		System.out.println("none");
                        	}else
                        	{
                        		mF.changeFormat(Casts.get(0));
                        		for(int i = 1;i < Casts.size(); i++)
                        		{
                            		System.out.print(", ");
                            		mF.changeFormat(Casts.get(i));
                            	}
                        		System.out.println();
                        	}
                    	}	
                        break;
    
                    case 'p':
                        // TODO *** implement this option ***
                    	if(!m.containsActor(remainder))
                    	{
                    		System.out.println("actor not found");
                    	}else
                    	{
                    		List<String> moiveList = m.getMovies(remainder);
                    		mF.changeFormat(moiveList.get(0));
                    		for(int i = 1; i < moiveList.size(); i++)
                    		{
                    			System.out.print(", ");
                    			mF.changeFormat(moiveList.get(i));
                    		}
                    		System.out.println();
                    	}
                        break;
    
                    case 'r':
                        // TODO *** implement this option ***
                    	if(!m.containsMovie(remainder))
                    	{
                    		System.out.print("movie not found");
                    	}else
                    	{
                    		m.removeMovie(remainder);
                    		System.out.println("movie removed");
                    	}
                    	
                        break;
    
                    case 's':
                        // The following code reads in a comma-separated sequence 
                        // of strings.  If there are exactly two strings in the 
                        // sequence, the strings are assigned to name1 and name2.
                        // Otherwise, an error message is printed.
                        String[] tokens = remainder.split("[,]+");
                        if (tokens.length != 2) 
                        {
                            System.out.println("need to provide exactly two names");
                        }
                        else 
                        {
                            String name1 = tokens[0].trim();
                            String name2 = tokens[1].trim();
                            
                            // TODO code to implement this option ***
                            List<String> movieList = m.getMovies(name1.toLowerCase());
                            ArrayList<String> movieBoth =new ArrayList<String>();   
                            for(int i = 0; i < movieList.size(); i++)
                            {
                            	if(m.isCast(name2.toLowerCase(), movieList.get(i)))
                            	{
                            		movieBoth.add(movieList.get(i));
                            	}
                    		}                          
                            if(movieBoth.size() > 0)
                            {
                            	mF.changeFormat(movieBoth.get(0));
                            	for(int i = 1; i < movieBoth.size(); i++)
                            	{                
                            		System.out.print(", ");    
                            		mF.changeFormat(movieBoth.get(i));
                        		}
                            }else
                            {
                            	System.out.println("none");
                            	break;
                            }
                            System.out.println("");
                        }
                        break;
    
                    case 'w':
                        // TODO *** implement this option ***
                    	if(!m.containsActor(remainder))
                    	{
                    		System.out.println("actor not found");
                    	}else
                    	{
                    		m.removeActor(remainder);
                    		mF.changeFormat(remainder);
                    		System.out.println(" withdrawn from all movies");
                    	}
                        break;
    
                    default: //ignore invalid commands
                        System.out.println("Incorrect command.");
                        break;
                    
                    } // end switch
                } // end if
                else { //if there is no argument
                    switch (choice) { //the commands without arguments
                    
                    case 'd': 
                        // TODO to implement this option ***
                    	ArrayList<String> actorList = new ArrayList<String>();
                    	for(int i = 0; i < m.numItems; i++)
                    	{
                    		for(int j = 0; j < m.MovieList.get(i).getCast().size(); j++)
                    		{
                    			if(!actorList.contains(m.MovieList.get(i).getCast().get(j)))
                    			{
                    				actorList.add(m.MovieList.get(i).getCast().get(j));
                    			}
                    		}
                    	}
                    	System.out.println("Movies: "+m.numItems+", Actors: "+actorList.size());
                    	
                    	int indexL = 0;
                    	int indexS = 0;
                    	int sum = m.MovieList.get(0).getCast().size();
                    	int MaxA = m.MovieList.get(0).getCast().size();
                    	int MinA = m.MovieList.get(0).getCast().size();
                    	for(int i = 1; i < m.numItems; i++)
                    	{
                    		if(m.MovieList.get(i).getCast().size() > MaxA)
                    		{
                    			MaxA = m.MovieList.get(i).getCast().size();
                    			indexL = i;
                    		}
                    		if(m.MovieList.get(i).getCast().size() < MinA)
                    		{
                    			MinA = m.MovieList.get(i).getCast().size();
                    			indexS = i;
                    		}
                    		sum = sum + m.MovieList.get(i).getCast().size();
                    	}
                    	System.out.println("# of actors/movie: most " + MaxA +", least " + MinA + ", average " + Math.round(((double)sum/m.numItems)));
                    	
                    	sum = 0;
                    	int MaxM = m.getMovies(actorList.get(0)).size();
                    	int MinM = m.getMovies(actorList.get(0)).size();
                    	for(int i = 0; i < actorList.size(); i++)
                    	{
                    		if(m.getMovies(actorList.get(i)).size() > MaxM)
                    		{
                    			MaxM = m.getMovies(actorList.get(i)).size();
                    		}
                    		if(m.getMovies(actorList.get(i)).size() < MinM)
                    		{
                    			MinM = m.getMovies(actorList.get(i)).size();
                    		}
                    		sum = sum + m.getMovies(actorList.get(i)).size();
                    	}
                    	System.out.println("# of movie/actors: most " + MaxM +", least " + MinM + ", average " + Math.round(((double)sum/actorList.size())));
                    	
                    	int ifFirst = 0;
                    	System.out.print("Largest Cast: ");                
                    	for(int i = 0; i < m.numItems; i++)
                    	{
                    		if(m.MovieList.get(i).getCast().size() == MaxA)
                    		{
                    			if(ifFirst == 0)
                    			{
                    				mF.changeFormat(m.MovieList.get(i).getTitle());                   				
                    				ifFirst++;
                    			}else
                    			{
                    				System.out.print(", ");
                    				mF.changeFormat(m.MovieList.get(i).getTitle());
                    			}
                    		}                  
                    	}                   	
                    	System.out.println(" ["+m.MovieList.get(indexL).getCast().size()+"]");
                    	
                    	ifFirst = 0;
                    	System.out.print("Smallest Cast: ");                
                    	for(int i = 0; i < m.numItems; i++)
                    	{
                    		if(m.MovieList.get(i).getCast().size() == MinA)
                    		{
                    			if(ifFirst == 0)
                    			{
                    				mF.changeFormat(m.MovieList.get(i).getTitle());
                    				ifFirst++;
                    			}else
                    			{
                    				System.out.print(", ");
                    				mF.changeFormat(m.MovieList.get(i).getTitle());
                    			}
                    		}                  
                    	}  
                    	System.out.println(" ["+m.MovieList.get(indexS).getCast().size()+"]");
                        
                    	break;
                        
                    case 'x':
                        done = true;
                        System.out.println("exit");
                        break;
                        
                    default:  //a command with no argument
                        System.out.println("Incorrect command.");
                        break;
                    } //end switch
                } //end else  
           } //end if
        } //end while
    } //end main

}