package deque;

public interface Deque <anyType> {
    public void addFirst(anyType item);
    public void addLast(anyType item);
    default public boolean isEmpty() {
        boolean result = false;
        if (size() == 0) {
            result = true;
        } return result;
    }

    public int size();
    public void printDeque();
    public anyType removeFirst();
    public anyType removeLast();
    public anyType get(int index);
}
