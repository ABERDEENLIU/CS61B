package bstmap;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>, Iterable<K>{

    // the instance variable in the class
    int size;
    private Node root;

    /** Represents one BST node in the binary search tree that stores the key-value pairs
     *  in the dictionary and its 2 leafs */
    private class Node {

        private K key;
        private V value;
        private Node left, right;

        public Node(K key, V value){
            this.key = key;
            this.value = value;
        }
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public void clear(){
        size = 0;
        root = null;
    }

    @Override
    public boolean containsKey(K key){
       return ifcontain(root, key);
    }

    private boolean ifcontain(Node bstnode, K key) {
        if (bstnode == null) {
            return false;
        }
        int cmp = key.compareTo(bstnode.key);
        if (cmp > 0) {
            return ifcontain (bstnode.right, key);
        }
        if (cmp < 0) {
            return ifcontain (bstnode.left, key);
        } else {
            return true;
        }

    }


    @Override
    public V get(K key){
        return Nodeget(root, key);
    }

    private V Nodeget(Node bstnode, K key) {
        if (bstnode == null) {
            return null;
        }
        int cmp = key.compareTo(bstnode.key);
        if (cmp > 0) {
            return Nodeget(bstnode.right, key);
        }
        if (cmp < 0) {
            return Nodeget(bstnode.left, key);
        } else {
            return bstnode.value;
        }
    }


    @Override
    public void put(K key, V value) {
        root = Nodeput(root, key, value);
    }

    private Node Nodeput(Node bstnode, K key, V value) {
        if (bstnode == null) {
            size += 1;
            return new Node(key, value);
            }
        else {
            int cmp = key.compareTo(bstnode.key);
            if (cmp > 0) {
                bstnode.right = Nodeput(bstnode.right, key, value);
            }
            if (cmp < 0) {
                bstnode.left = Nodeput(bstnode.left, key, value);
            } else {
                bstnode.value = value;
            }
            return bstnode;
        }
    }




    @Override
    public Set<K> keySet(){
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key){
        throw new UnsupportedOperationException();
    }

    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }


    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }



    public void printInOrder(){

    }

}
