import java.util.List;
import java.util.LinkedList;
import java.util.Iterator;
/**
 *
 * A map is a data structure that creates a key-value mapping. Keys are
 * unique in the map. That is, there cannot be more than one value associated
 * with a same key. However, two keys can map to a same value.
 *
 * The SimpleHashMap takes two generic parameters, K
 * and V, standing for the types of keys and values respectively.
 *
 */
public class SimpleHashMap<K extends Comparable<K>,V> implements SimpleMapADT<K , V> {


    private int[] tableSizes = { 11, 23, 47, 97, 197, 397, 797, 1597, 3203, 6421, 12853, 25717, 51437, 102877,
            205759, 411527, 823117, 1646237, 3292489, 6584983, 13169977, 26339969, 52679969, 105359939, 210719881, 
            421439783, 842879579, 1685759167};
    private double lf = 0.75;
    
    /** used to get the size of the table */
    private int sizeIndex = 0;
    /** table used to store data */
    private LinkedList<Entry<K, V>>[] table;
    /** total number of Entries */
    private int numTotal = 0;


        public SimpleHashMap()
        {
        	table = (LinkedList<Entry<K, V>>[]) new LinkedList[tableSizes[sizeIndex]];
        } 
 
    private int hash(K k) {
    	int tableIndex = k.hashCode() % tableSizes[sizeIndex];
    	if(tableIndex < 0) tableIndex = tableIndex + tableSizes[sizeIndex];
		return tableIndex;
    } 
    
    /**
     * return entry which containing the key
	 */
	private Entry<K,V> getEntry(LinkedList<Entry<K,V>>list , K key){
		Iterator<Entry<K,V>> iterator = list.iterator();
		while(iterator.hasNext())
		{
			Entry<K,V> entry = iterator.next();
			if (entry.getKey().equals(key))
			{
				return entry;
			}
		}
		return null;
	}
    
 
    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     *
     * @param key the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or null
     * if this map contains no mapping for the key
     * @throws NullPointerException if the specified key is null
     */

    public V get(K key) {
    	if(key == null)
    	{
    		throw new NullPointerException("key can not be null!");
    	}else
    	{
    		int index = hash(key);
    		if (table[index] == null || table[index].isEmpty())
    		{
    			return null;
    		}else{
    			Entry<K,V> entry = getEntry(table[index],key);
        		return entry.getValue();
    		} 		
    	}
    }   
  
    /**
     * Associates the specified value with the specified key in this map.
     * Neither the key nor the value can be null. If the map
     * previously contained a mapping for the key, the old value is replaced.
     *
     * @param key key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     * @return the previous value associated with key, or
     * null if there was no mapping for key.
     * @throws NullPointerException if the key or value is null
     */

    private V put(K key, V value, LinkedList<Entry<K,V>>[] table){
    	if(key == null || value == null){
    		throw new NullPointerException("key and value can not be null!");
    	}else{
    		int index = hash(key);
    		/** position at table is null, create a new Linkedlist and add value */
    		if (table[index] == null){
    			table[index] = new LinkedList<Entry<K,V>>();
    			table[index].add(new Entry<K,V>(key,value));
    		}else{
    			Entry<K,V> entry = getEntry(table[index],key);
    			if (entry==null)
    			{
    				table[index].add(new Entry<K,V>(key,value));
    			}else
    			{
    				return entry.setValue(value);
    			}
    		}    		   			
    	}
    	return null;
	}
    
    /**
	 * put entry into table
	 */
	private V put(Entry<K,V>entry, LinkedList<Entry<K,V>>[] table){
		return put(entry.getKey(),entry.getValue(),table);
	}

	/**
	 * resize the table if needed 
	 */
	public V put(K key, V value) {
		if (key == null|| value == null) 
		{
			throw new NullPointerException("key and value cannot be null");
		}
		//if table is full, we will resize the table
		this.numTotal = this.numTotal + 1;
		if((this.numTotal)/(double)tableSizes[sizeIndex] > this.lf){			
			if (sizeIndex >= tableSizes.length-1)
			{
				throw new IllegalArgumentException("Table is full!");
			}else
			{
				sizeIndex = sizeIndex + 1;
				LinkedList<Entry<K,V>>[] newTable =
						(LinkedList<Entry<K,V>>[])(new LinkedList[tableSizes[sizeIndex]]);
				//put data into the new table
				for (LinkedList<Entry<K, V>> currlist : table) 
				{
					if (currlist!=null)
					{
						Iterator<Entry<K,V>> iterator = currlist.iterator();
						while(iterator.hasNext())
						{
							put(iterator.next(),newTable);
						}
					}
				}
				table=newTable;
			}
		}
		return put(key,value,table);
	}
 
    /**
     * Removes the mapping for the specified key from this map if present. This
     * method does nothing if the key is not in the map.
     *
     * @param key key whose mapping is to be removed from the map
     * @return the previous value associated with key, or null
     * if there was no mapping for key.
     * @throws NullPointerException if key is null
     */

	public V remove(K key) {
		if (key == null)
		{
			throw new NullPointerException("Key can not be null");
		}
		int index = hash(key);
		Entry<K,V> currEntry = getEntry(table[index], key);
		if(currEntry != null)
		{
			table[index].remove(currEntry);
			return currEntry.getValue();
		}
		return null;
	} 

   /**
     * Returns the greatest key less than or equal to the given key, or null if there is no such key. 
	 * Throws NullPointerException if key is null. 
     * @param key key whose floor should be found
     * @return the largest key smaller than the one passed to it
     * @throws NullPointerException if key is null
     */
	public K floorKey(K key){
		K greatestKey = null;
		for (LinkedList<Entry<K, V>> currlist : table) 
		{
			if (currlist != null)
			{
				Iterator<Entry<K,V>> iterator = currlist.iterator();
				while(iterator.hasNext())
				{
					K newKey = iterator.next().getKey();
					if(newKey.compareTo(key) < 0)
					{
						if (greatestKey == null)
						{
							greatestKey = newKey;
						}
						else if (newKey.compareTo(greatestKey) > 0)
						{
							greatestKey = newKey;
						}
					}
				}
			}
		}
		return greatestKey;
	}
}