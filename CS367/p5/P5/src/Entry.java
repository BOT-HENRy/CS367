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
    	}else{
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