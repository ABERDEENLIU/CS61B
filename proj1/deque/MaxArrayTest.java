package deque;
import org.junit.Test;

import java.util.Comparator;

import static org.junit.Assert.*;


public class MaxArrayTest {

    @Test
    public void isconstructorworks() {
        MaxArrayDeque.namecomparator x = new MaxArrayDeque.namecomparator();
        MaxArrayDeque.numbercomparator y = new MaxArrayDeque.numbercomparator();
        MaxArrayDeque<String> test1 = new MaxArrayDeque<>(y);
        test1.addFirst("chixian");
        test1.addFirst("huoran");
        test1.addFirst("123");
        test1.addFirst("xx");
        test1.addFirst("liu");

        MaxArrayDeque<Integer> test2 = new MaxArrayDeque<>(y);
        test2.addFirst(1);
        test2.addFirst(2);
        test2.addFirst(3);
        test2.addFirst(4);
        test2.addFirst(3);


        System.out.println(test2.max(y));
        System.out.println(test1.max(x));

    }




}
