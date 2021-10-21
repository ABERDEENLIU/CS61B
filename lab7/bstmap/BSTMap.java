package bstmap;

import java.util.Comparator;
import java.util.Iterator;
import java.util.Set;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V>, Iterable<K>{

    // the instance variable in the class
    int size;
    private BST list;

    /** Represents one BST node in the binary search tree that stores the key-value pairs
     *  in the dictionary and its 2 leafs */
    private class BST {

        private K key;
        private V value;
        private BST left;
        private BST right;

        public BST(K k, V v, BST l, BST r){
            this.key = k;
            this.value = v;
            this.left = l;
            this.right = r;
        }

        public BST(K k, V v){
            this.key = k;
            this.value = v;
        }

        public BST find(BST T, K sk) {
            if (T == null) {
                return null;
            }
            if (sk.equals(T.key)) {
                return T;
            }
            else if (sk.compareTo(T.key) <0) {
                return find(T.left, sk);
            }
            else {
                return find(T.right, sk);
            }
        }

        public BST containsKeyhelper(BST T, K sk) {
            if (T == null) {
                return null;}
            if (sk.equals(T.key)){
                return T;}
            else if (sk.compareTo(T.key)<0){
                return containsKeyhelper(T.left, sk);}
            else {
                return containsKeyhelper(T.right, sk);}
        }

        public BST insert(BST T, K ik, V iv) {
            if (T == null) {
                return new BST(ik, iv);
            }
            if (ik.compareTo(T.key)<0) {
                T.left = insert(T.left, ik, iv);
            }
            if (ik.compareTo(T.key)>0) {
                T.right = insert(T.right, ik, iv);
            }
            return T;
        }
    }

    @Override
    public int size(){
        return size;
    }

    @Override
    public void clear(){
        size = 0;
        list = null;
    }

    @Override
    public boolean containsKey(K key){
        if (list == null) {return false;}
        if (list.containsKeyhelper(list, key) == null) {return false;}
        else {return true;}
    }

    @Override
    public V get(K key){
        if (list == null) {
            return null;
        }
        if (list.find(list, key) == null) {
            return null;
        }
        return (list.find(list, key).value);
    }

    @Override
    public void put(K key, V value){
        if (list == null) {
            list = new BST(key, value);
            size += 1;
        }
        else {
        list.insert(list, key, value);
        size += 1;}
        }

    @Override
    public Set<K> keySet(){
        BSTMap temp = new BSTMap();
        for (K key: temp) {

        };
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
        return new BSTIterator();
    }

    public class BSTIterator implements Iterator<K>{
        private int ithNode;
        private BST pointer1 = list;
        public boolean hasNext() {
            return ithNode<size;
        }
        public K next() {
            while (pointer1.left !=null || pointer1.right !=null) {
                if (pointer1.left != null) {pointer1 = pointer1.left;}
                else {pointer1 = pointer1.right;}
            }
            K ithkey = pointer1.key;
            ithNode += 1;
            pointer1 = list.remove(ithkey);
            return ithkey;
        }
    }

    public void printInOrder(){

    }

}
