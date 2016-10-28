///////////////////////////////////////////////////////////////////////////////
//                   ALL STUDENTS COMPLETE THESE SECTIONS
// Title:            p5
// Files:            Entry.java
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
     * A map entry (key-value pair).
     */
public class Entry<K, V> {
    private K key;
    private V value;

    /**
     * Constructs the map entry with the specified key and value.
     */
    public Entry(K k, V v) {
    	if(k == null || v == null)
    	{
    		throw new IllegalArgumentException("Can't set data to null!");
    	}else
    	{
    		this.key = k;
    		this.value = v;
    	}
        // TODO
    }

    /**
     * Returns the key corresponding to this entry.
     *
     * @return the key corresponding to this entry
     */
    public K getKey() {
    	if(this.key == null)
    	{
    		throw new IllegalArgumentException("Data is null!");
    	}else
    	{
    		return this.key;
    	}		
    }

    /**
     * Returns the value corresponding to this entry.
     *
     * @return the value corresponding to this entry
     */
    public V getValue() {
    	if(this.value == null)
    	{
    		throw new IllegalArgumentException("Data is null!");
    	}else
    	{
    		return this.value;
    	}
    }

    /**
     * Replaces the value corresponding to this entry with the specified
     * value.
     *
     * @param value new value to be stored in this entry
     * @return old value corresponding to the entry
     */
    public V setValue(V value) {
    	if(value == null)
    	{
    		throw new IllegalArgumentException("Can't set data to null!");
    	}else
    	{
    		return this.value = value;
    	}
    }
}