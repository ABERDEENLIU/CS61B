package hashmap;

import org.checkerframework.checker.units.qual.C;

import java.util.*;

/**
 *  A hash table-backed Map implementation. Provides amortized constant time
 *  access to elements via get(), remove(), and put() in the best case.
 *
 *  Assumes null keys will never be inserted, and does not resize down upon remove().
 *  @author YOUR NAME HERE
 */
public class MyHashMap<K, V> implements Map61B<K, V> {



    /**
     * Protected helper class to store key/value pairs
     * The protected qualifier allows subclass access
     */
    protected class Node {
        K key;
        V value;

        Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    /* Instance Variables */
    private Collection<Node>[] buckets;
    int N;
    int M;
    double MAXLoad;
    // You should probably define some more!

    /** Constructors */
    public MyHashMap() {
        N = 0;
        M = 16;
        MAXLoad = 0.75;
        buckets = new Collection[M];
    }

    public MyHashMap(int initialSize) {
        N = 0;
        M = initialSize;
        MAXLoad = 0.75;
        buckets = new Collection[M];
    }

    /**
     * MyHashMap constructor that creates a backing array of initialSize.
     * The load factor (# items / # buckets) should always be <= loadFactor
     *
     * @param initialSize initial size of backing array
     * @param maxLoad maximum load factor
     */
    public MyHashMap(int initialSize, double maxLoad) {
        N = 0;
        M = initialSize;
        buckets = new Collection[M];
        MAXLoad = maxLoad;
    }

    /**
     * Returns a new node to be placed in a hash table bucket
     */
    private Node createNode(K key, V value) {
        return new Node(key, value);
    }

    /**
     * Returns a data structure to be a hash table bucket
     *
     * The only requirements of a hash table bucket are that we can:
     *  1. Insert items (`add` method)
     *  2. Remove items (`remove` method)
     *  3. Iterate through items (`iterator` method)
     *
     * Each of these methods is supported by java.util.Collection,
     * Most data structures in Java inherit from Collection, so we
     * can use almost any data structure as our buckets.
     *
     * Override this method to use different data structures as
     * the underlying bucket type
     *
     * BE SURE TO CALL THIS FACTORY METHOD INSTEAD OF CREATING YOUR
     * OWN BUCKET DATA STRUCTURES WITH THE NEW OPERATOR!
     */
    protected Collection<Node> createBucket() {
        return new LinkedList<>();
    }

    /**
     * Returns a table to back our hash table. As per the comment
     * above, this table can be an array of Collection objects
     *
     * BE SURE TO CALL THIS FACTORY METHOD WHEN CREATING A TABLE SO
     * THAT ALL BUCKET TYPES ARE OF JAVA.UTIL.COLLECTION
     *
     * @param tableSize the size of the table to create
     */
    private Collection<Node>[] createTable(int tableSize) {
        return null;
    }

    // TODO: Implement the methods of the Map61B Interface below
    // Your code won't compile until you do so!




    /** Removes all of the mappings from this map. */
    @Override
    public void clear(){
        for (int i=0; i<M; i++){
            buckets[i] = null;
        }
        N =0;
    }

    /** Returns true if this map contains a mapping for the specified key. */
    @Override
    public boolean containsKey(K key){
        int index = Math.floorMod(key.hashCode(), M);
        if (buckets[index] == null) {return false;}
        for (Node n: buckets[index]) {
            if (n.key.equals(key)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        int index = Math.floorMod(key.hashCode(), M);
        if (buckets[index] == null) {return null;}
        for (Node n: buckets[index]) {
            if (n.key.equals(key)) {
                return n.value;
            }
        }
        return null;
    }

    /** Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return N;
    }

    /**
     * Associates the specified value with the specified key in this map.
     * If the map previously contained a mapping for the key,
     * the old value is replaced.
     */

    @Override
    public void put(K key, V value){

        if (this.size() / M >= MAXLoad) {
            resize(M*2);
        }

        int index = Math.floorMod(key.hashCode(), M);

        if (buckets[index] == null) {
            buckets[index] = createBucket();
        }

        if (containsKey(key)) {
            for (Node n: buckets[index]) {
                if (n.key.equals(key)) {
                    n.value = value;
                }
            }

        } else {
        Node n = createNode(key, value);
        buckets[index].add(n);
        N += 1;
        }
    }

    private void resize(int capacity) {
        int m = this.M;
        Collection<Node>[] temp = new Collection[capacity];
        for (int i=0; i< m; i++) {
            if (buckets[i] != null) {
                for (Node n : buckets[i]) {
                    if (n != null) {
                        int index = Math.floorMod(n.key.hashCode(), capacity);
                        if (temp[index] == null) {
                            temp[index] = createBucket();
                        }
                        temp[index].add(n);
                    }
                }
            }
        }
        buckets = temp;
        M = capacity;
    }

    /** Returns a Set view of the keys contained in this map. */
    @Override
    public Set<K> keySet() {
        Set<K> allkey = new HashSet<>();
        for (int i=0; i<M; i++) {
            if (buckets[i] != null) {
                for (Node n: buckets[i]) {
                    allkey.add(n.key);
                }
            }
        }
        return allkey;
    }

    /**
     * Removes the mapping for the specified key from this map if present.
     * Not required for Lab 8. If you don't implement this, throw an
     * UnsupportedOperationException.
     */
    @Override
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    /**
     * Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 8. If you don't implement this,
     * throw an UnsupportedOperationException.
     */
    @Override
    public V remove(K key, V value){
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        return new MyHashMapIterator();
    }

    private class MyHashMapIterator implements Iterator<K> {
        int wiz;
        Set<K> allkeys = new HashSet<>();


        public MyHashMapIterator() {
            wiz = 0;
            allkeys = keySet();
        }

        public boolean hasNext() {
            return wiz < M;
        }
        public K next() {
            Iterator<K> allkeysiterator = allkeys.iterator();
            wiz += 1;
            return allkeysiterator.next();

        }

    }


    public static void main (String[] args) {
        MyHashMap<Integer, String> x = new MyHashMap<>(2);
        x.put(1, "chixian");
        x.put(2, "zhuoran");
        x.put(3, "bitcoin");
        boolean y = x.containsKey(1);
        boolean z = x.containsKey(2);
        boolean j = x.containsKey(3);
        String s = x.get(1);
        String s2 = x.get(2);
        System.out.println(s);
        System.out.println(s2);
        System.out.println(j);
        for (Integer k: x.keySet()) {
            System.out.println(k);
        }



    }


}
