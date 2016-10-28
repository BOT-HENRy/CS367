///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            Twitter
// Files:            Timeline.java
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

import java.util.*;;

/**
 * The Timeline class uses SimpleLinkedList to build a time ordered list of 
 * following tweets. Tweets with smaller Time fields should come earlier in the list.
 */
class Timeline{

    private SimpleLinkedList<Tweet> list;

    /**
     * Constructs an empty timeline
     */
    public Timeline()
    {
    	this.list = new SimpleLinkedList<Tweet>();
    }

    /**
     * Adds a single tweet to the Timeline
     * 
     * @param tweet the tweet to add
     */
    public void add(Tweet tweet)
    {
    	int last = 0;
    	for(int i = 0; i < list.size(); i ++)
    	{
    		
    		if(list.get(i).getTime() > tweet.getTime())
    		{
    			list.add(i, tweet);
    			last ++;
    			break;
    		}
    	}
    	if(last == 0) list.add(tweet);
    }

    /**
     * Adds an ordered list of tweets to the Timeline
     * @param tweets the list of tweets to add
     */
    public void add(List<Tweet> tweets)
    {
    	for(int j = 0; j < tweets.size(); j ++)
    	{
    		for(int i = 0; i < list.size(); i ++)
    		{
        		if(list.get(i).getTime() < tweets.get(j).getTime() && list.get(i+1).getTime() > tweets.get(j).getTime())
        		{
        			list.add(i, tweets.get(j));
        		}
        	} 
    	}   	
    }

    /**
     * Removes all tweets by user from the Timeline
     * 
     * @param user the user whose tweets should be removed
     */
    public void remove(String user)
    {
    	boolean done = false;
    	while(!done)
    	{
    		for(int i = 0; i < list.size(); i ++)
    		{
        		if(list.get(i).getUser().equals(user))
        		{
        			list.remove(i);
        			break;
        		}
        		if(i == list.size() - 1)
        		{
        			done = true;
        		}
        	} 
    	}   	
    }

    /**
     * Create a new Timeline containing only the tweets containing keyword
     * 
     * @param keyword the keyword to search for
     * @return a Timeline of tweets containing keyword
     */
    public Timeline search(String keyword)
    {
    	
    	Timeline searchWord = new Timeline();
    	int keyWordLength = keyword.length();
    	for(int i = 0; i < list.size(); i ++)
		{    		
    		int curr = -1;
    		for(int j = 0; j < list.get(i).getMessage().length() - keyWordLength + 1; j ++)
    		{
    			if(curr == i) continue;
    			if(list.get(i).getMessage().substring(j, j + keyWordLength).equals(keyword))
    			{
    				searchWord.add(list.get(i));    			
    				curr = i;
    				continue;
    			}
    		} 		
    	}    	    	
    	return searchWord;
    }

    /**
     * Print each tweet in the timeline
     */
    public void print()
    {
    	for(int i = 0; i < list.size(); i ++)
		{
    		list.get(i).print();
    	} 
    }   
    
    /**
     * Print each tweet in the timeline since time
     * 
     * @param time the largest time to print tweets
     */
    public void print(int time)
    {
    	for(int i = 0; i < list.size(); i ++)
		{
    		if(list.get(i).getTime() < time)
    		{
    			list.get(i).print();
    		}   		
    	}     	
    }
}
