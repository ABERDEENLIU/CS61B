package deque;

import deque.ArrayDeque;

import java.util.Comparator;

public class MaxArrayDeque<anyT> extends ArrayDeque<anyT> {

    public static class namecomparator<anyT> implements Comparator<anyT> {
        public int compare(anyT a, anyT b) {
            return a.toString().compareTo(b.toString());
        }
    }

    public static class numbercomparator<anyT> implements Comparator<anyT> {
        public int compare(anyT a, anyT b) {
            return (Integer) a - (Integer) b;
        }
    }

    Comparator<anyT> x;

    public MaxArrayDeque(Comparator<anyT> c) {
        super();
        Comparator<anyT> x= c;
    }

    public anyT max() {
        if (this.size() == 0) {
            return null;
        }
        int maxIndex = 0;
        for (int i = 0; i < this.size(); i += 1) {
            if (x.compare(this.get(i), this.get(maxIndex)) > 0) {
                maxIndex = i;
            }
        }
        return this.get(maxIndex);
    }


    public anyT max(Comparator<anyT> x) {
        if (this.size() == 0) {
            return null;
        }
        int max = 0;
        for (int i= 0; i<this.size(); i+=1) {
            if (x.compare(this.get(i), this.get(max)) > 0) {
                max = i;
            }
        }
        return this.get(max);
    }
}

