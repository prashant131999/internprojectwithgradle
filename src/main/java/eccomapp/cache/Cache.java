package eccomapp.cache;

import java.util.*;

public class Cache {
    Set<String> cache;
    Map<String, Object> m= new HashMap<String,Object>();
    int capacity;

    public Cache(int capacity) {
        this.cache = new LinkedHashSet<String>(capacity);
        this.capacity = capacity;
    }

    public Object get(String key) {
        if (!cache.contains(key))
            return null;
        cache.remove(key);
        cache.add(key);

        return m.get(key);
    }
    public void delete(String key){
        if(cache.contains(key)) {
            cache.remove(key);
        }
    }
    public boolean contains(String key) {
        if (!cache.contains(key))
            return false;
        return  true;
    }

    public void display() {
        for (Map.Entry<String,Object> entry : m.entrySet())
            System.out.println("Key = " + entry.getKey() +
                    ", Value = " + entry.getValue());
    }

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