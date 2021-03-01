package eccomapp.cache;

import java.util.*;

public class Cache {
    Set<String> cache;
    Map<String, Object> m= new HashMap<String,Object>();
    int capacity;

    /** This is a constructor used to initialize capacity
     *
     * @param capacity
     */

    public Cache(int capacity) {
        this.cache = new LinkedHashSet<String>(capacity);
        this.capacity = capacity;
    }

    /** This method used to get object of key
     *
     * @param key for key
     * @return the object
     */
    public Object get(String key) {
        if (!cache.contains(key))
            return null;
        cache.remove(key);
        cache.add(key);

        return m.get(key);
    }

    /**This method deletes the object of respective key from cache
     *
     * @param key for input key
     */
    public void delete(String key){
        if(cache.contains(key)) {
            cache.remove(key);
        }
    }

    /**This method check wheteher it contains key or not
     *
     * @param key for input key
     * @return boolean
     */
    public boolean contains(String key) {
        if (!cache.contains(key))
            return false;
        return  true;
    }


    /**This method put the key in cache and deltes according to lru
     *
     * @param key for key value
     * @param obj for object of given key
     */

    public void put(String key,Object obj) {

        if (cache.size() == capacity) {
            String firstKey = cache.iterator().next();
            cache.remove(firstKey);
            m.remove(firstKey);
        }

        cache.add(key);
        m.put(key,obj);
    }


}