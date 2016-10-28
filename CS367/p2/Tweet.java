///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            Twitter
// Files:            Tweet.java
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
 * Stores the time, message and user of a Tweet
 */
class Tweet{
	private int time;
	private String message;
	private String user;
    /**
     * Constructs a Tweet for user with message at time. 
     * Throws a TweetTooLongException if message over 140 characters.
     * 
     * @param time time at which tweet occured
     * @param message message of the tweet, <=140 characters
     * @param user the person who tweeted the tweet 
     * @throws TweetTooLongException if message over 140 characters 
     */
    public Tweet(int time, String message, String user) throws TweetTooLongException{ 
    	if(message.length() > 140)
    	{
    		throw new TweetTooLongException("message is too long!");
    	}
    	this.time = time;
    	this.message = message;
    	this.user = user;
    }

    /** 
     * Returns the stored message of the Tweet
     * @return the message
     */
    public String getMessage()
    {
    	return this.message;
    }
    
    /** 
     * Returns the stored time of the Tweet
     * @return the stored time
     */
    public int getTime()
    {
		return this.time;
    }

    /** 
     * Returns the user who tweeted the Tweet
     * @return the user
     */
    public String getUser()
    {
    	return this.user;
    }

    /** 
     * Print the Tweet with the following format: <TIME> <USER>:<MESSAGE>
     */
    public void print()
    {
    	System.out.println(this.time + " " + this.user + ":" + this.message);
    }
}

