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
import java.lang.*;

/**
 * contains methods which can modify the MovieDaraBase
 * can do:
 * create a movie database
 * add movie and actor
 * remove movie and actor
 * check if database contains certain movies/actors
 * get certain movies/actors
 * 
 *
 * @author Yizhe Qu, Xuxiang Wu
 */

public class MovieDatabase {
	public ArrayList<Movie> MovieList;
	int numItems=0;
	
	/**
	 * create a database of movie
	 */
	public void MovieDatabase()
	{
		MovieList = new ArrayList<Movie>();		
	}
	
	/**
	 * add movie with title t in the database
	 */
	public void addMovie(String t)
	{		
		if(!containsMovie(t))
		{
			Movie m = new Movie(t);
			MovieList.add(m);
			numItems++;
		}			
	}
	
	/**
	 * add actor with name n in the movie t
	 */
	public void addActor(String n, String t)
	{
		if(!containsMovie(t))
		{
			throw new IllegalArgumentException("Movie not found");
		}else
		{
			if(!isCast(n,t))
			{
				Iterator<Movie> itr = iterator();
				int currPos = 0;
				while(itr.hasNext())
				{
					Movie nextWord = itr.next();
					if(nextWord.getTitle().equals(t))
					{
						MovieList.get(currPos).getCast().add(n);								
					}
					currPos++;
				}
			}
		}	
	}
	
	/**
	 * remove the moive with title t from the database
	 */
	public boolean removeMovie(String t)
	{
		if(!containsMovie(t))
		{
			return false;
		}				
		int done = 1;
		while(done != 0)
		{
			Iterator<Movie> itr = iterator();
			int index = 0;
			while(itr.hasNext())
			{
				Movie nextWord = itr.next();
				if(nextWord.getTitle().equals(t))
				{
					MovieList.remove(index);	
					numItems--;
					break;
				}
				if(index == MovieList.size() - 1)
				{
					done = 0;
				}
				index++;
			}
		}		
		return true;
	}
	
	/**
	 * check if the database contains the movie with title t
	 */
	public boolean containsMovie(String t)
	{
		if (MovieList == null) return false;
		Iterator<Movie> itr = iterator();
		while(itr.hasNext())
		{
			String nextWord = itr.next().getTitle();
			if(nextWord.equals(t))
			{
				return true;				
			}
		}
    	return false;
	}
	
	/**
	 * check if the database contains the actor with name n
	 */
	public boolean containsActor(String n)
	{
		if (MovieList == null) return false;
		Iterator<Movie> itr = iterator();
		while(itr.hasNext())
		{
			List<String> nextWord = itr.next().getCast();
			if(nextWord.contains(n))
			{
				return true;				
			}
		}
    	return false;
	}
	
	/**
	 * check if the actor with name n is in the movie t
	 */
	boolean isCast(String n, String t)
	{
		if (MovieList == null) return false;
		Iterator<Movie> itr = iterator();
		while(itr.hasNext())
		{
			Movie nextWord = itr.next();
			if(nextWord.getTitle().equals(t))
			{
				if(nextWord.getCast().contains(n))
				{
					return true;
				}								
			}
		}		
		return false;		
	}
	
	/**
	 * create a list of actors in the movie t
	 */
	public List<String> getCast(String t)
	{
		if(containsMovie(t))
		{
			Iterator<Movie> itr = iterator();
			while(itr.hasNext())
			{
				Movie nextWord = itr.next();
				if(nextWord.getTitle().equals(t))
				{
					return nextWord.getCast();								
				}
			}					
		}		
		return null;		
	}
	
	/**
	 * create a list of movies contain actor with name n 
	 */
	public List<String> getMovies(String n)
	{
		if(containsActor(n))
		{
			ArrayList<String> moiveList = new ArrayList<String>();
			Iterator<Movie> itr = iterator();
			while(itr.hasNext())
			{
				Movie nextWord = itr.next();
				if(nextWord.getCast().contains(n))
				{
					moiveList.add(nextWord.getTitle());							
				}
			}		
			return moiveList;
		}
		return null;		
	}
	
	/**
	 * create a iterator for the movie database 
	 */
	Iterator<Movie> iterator()
	{
		Iterator<Movie> itr = MovieList.iterator();		
		return itr;		
	}
	
	/**
	 * return the size of the movie database
	 */
	int size()
	{
		return numItems;		
	}
	
	/**
	 * remove the actor with name n in the movie database
	 */
	public boolean removeActor(String n)
	{
		if(containsActor(n))
		{		
			Iterator<Movie> itr = iterator();
			while(itr.hasNext())
			{
				Movie nextWord = itr.next();
				int index = 0;
				if(nextWord.getCast().contains(n))
				{
					for(int i = 0; i < nextWord.getCast().size(); i++)
					{
						if(nextWord.getCast().get(i).equals(n))
						{
							index = i;
						}
					}
					nextWord.getCast().remove(index);					
				}
			}	
		}else
		{
			return false;	
		}
		return true;
	}
}
