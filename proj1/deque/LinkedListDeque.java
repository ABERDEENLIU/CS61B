package deque;

public class LinkedListDeque<anyType> {
    private class itemNode{
        public anyType item;
        public itemNode Next;
        public itemNode Prev;

        public itemNode(itemNode P, anyType i, itemNode N) {
            Prev = P;
            item = i;
            Next = N;
        }
    }

    public int size;
    public itemNode Sentinel;

    public LinkedListDeque() {
        Sentinel = new itemNode(null,  null, null);
        Sentinel.Next = Sentinel;
        Sentinel.Prev = Sentinel;
        size =0;
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return Sentinel.Next == Sentinel;
    }

    public void addFirst(anyType item) {
        Sentinel.Next = new itemNode(Sentinel, item, Sentinel.Next);
        Sentinel.Next.Next.Prev = Sentinel.Next;
        size +=1;
    }

    public void addLast(anyType item) {
        Sentinel.Prev = new itemNode(Sentinel.Prev, item, Sentinel);
        Sentinel.Prev.Prev.Next = Sentinel.Prev;
        size +=1;
    }

    public anyType removeFirst(){
        if (isEmpty()) {size = 0; return null;}
        anyType removedItem = Sentinel.Next.item;
        Sentinel.Next = Sentinel.Next.Next;
        Sentinel.Next.Prev = Sentinel;
        size -= 1;
        return removedItem;
    }

    public anyType removeLast(){
        if (isEmpty()) {size = 0; return null;}
        anyType removeditem = Sentinel.Prev.item;
        Sentinel.Prev = Sentinel.Prev.Prev;
        Sentinel.Prev.Next = Sentinel;
        size -= 1;
        return removeditem;
    }

    public anyType get(int index) {
        if (isEmpty()) {size = 0; return null;}
        if (index>=size) {return null;}
        itemNode p= Sentinel;
        while (index>=0) {
            p = p.Next;
            index -=1;
        } return p.item;
    }

    public void printDeque() {
        for (int i=0; i<size; i++) {
            System.out.print(this.get(i) + " " );
        }
        System.out.println();
    }

}


