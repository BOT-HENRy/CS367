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

import java.util.*;

/**
 * The Movie class represents a single movie that keeps track of its
 * title and cast of actors.
 * 
 * @author CS 367 copyright 2014
 */
public class Movie {

    private String title;       // the movie title
    private List<String> cast;  // the list of actors in the movie
    
    /**
     * Constructs a movie with the given title and an empty cast.
     * 
     * @param title The title of this movie.
     */
    public Movie(String title) {
        this.title = title;
        cast = new ArrayList<String>();
    }
    
    /**
     * Returns the title of this movie.
     * 
     * @return The title of this movie.
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * Returns the cast of actors in this movie.
     * 
     * @return The cast for this movie.
     */
    public List<String> getCast() {
        return cast;
    }
}