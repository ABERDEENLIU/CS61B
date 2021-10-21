package deque;
import java.util.Iterator;

public class ArrayDeque<anyT> implements Deque<anyT>, Iterable<anyT>  {
    anyT[] ArrayList;
    int size;
    int theFirst;
    int theLast;
    int ALength;

    public Iterator<anyT> iterator() {
        return new ArrayDequeIterator();
    }

    public class ArrayDequeIterator implements Iterator<anyT> {
        int currentIndext;

        public ArrayDequeIterator() {
            currentIndext = 0;
        }

        @Override
        public boolean hasNext(){
            return currentIndext < size;
        }

        @Override
        public anyT next(){
            anyT returnitem = (anyT) ArrayList[currentIndext];
            currentIndext += 1;
            return returnitem;
        }
    }


    //Firstly Implement a list without resizing, and only works for int
    public ArrayDeque() {
        ArrayList = (anyT[]) new Object[8];
        size = 0;
        ALength = ArrayList.length;
        theFirst = 0;
        theLast = ALength -1;
    }

    @Override
    public void addFirst(anyT item) {
        if (size == ALength) {
            resize(ALength*2);
        }
        theFirst = (theFirst + ALength -1)%ALength;
        ArrayList [theFirst] = item;
        size +=1;
    }

    @Override
    public void addLast(anyT item) {
        if (size == ALength) {
            resize(ALength*2);
        }
        theLast = (theLast + 1)%ALength;
        ArrayList [theLast] = item;
        size += 1;
    }

//    @Override
//    public boolean isEmpty() {
//        return (size == 0);
//    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for (int i=0; i<size; i++) {
            System.out.print(get(i) + " ");
        } System.out.println();
    }

    @Override
    public anyT removeFirst() {
        if (size ==0) {
            return null;
        }
        anyT theFirstItem = ArrayList[theFirst];
        theFirst = (theFirst + 1) % ALength;
        size -= 1;
        return theFirstItem;
    }

    @Override
    public anyT removeLast() {
        if (size ==0) {
            return null;
        }
        anyT theLastItem = ArrayList[theLast];
        theLast = (theLast + ALength -1) % ALength;
        size -= 1;
        return theLastItem;
    }

    @Override
    public anyT get(int index) {
        return ArrayList[(index + theFirst) % ALength];
    }

    public void resize(int capacity) {
        anyT[] temp = (anyT[]) new Object[capacity];
        System.arraycopy(ArrayList, theFirst, temp, 0, size-theFirst);
        System.arraycopy(ArrayList, 0, temp, size-theFirst, theFirst);
        ArrayList = temp;
        theFirst = 0;
        theLast = size-1;
        ALength = ArrayList.length;
    }

}
