package randomizedtest;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void ThreeAddThreeRemove() {
        BuggyAList<Integer> B = new BuggyAList<>();
        AListNoResizing<Integer> S = new AListNoResizing<>();
        B.addLast(3);
        B.addLast(4);
        B.addLast(5);
        S.addLast(3);
        S.addLast(4);
        S.addLast(5);

        assertEquals(B.size(), S.size());

        assertEquals(B.removeLast(), S.removeLast());
        assertEquals(B.removeLast(), S.removeLast());
        assertEquals(B.removeLast(), S.removeLast());
    }

    @Test
    public void randomizedTest() {
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> B = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                B.addLast(randVal);
                System.out.println("addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int sizeL = L.size();
                int sizeB = B.size();
                assertEquals(sizeB, sizeL);
                System.out.println("sizeL: " + sizeL + "sizeB: " + sizeB);
            } else if (operationNumber == 2) {
                // getLast
                if (L.size() == 0) {continue;}
                int lastL = L.getLast();
                int lastB = B.getLast();
                assertEquals(lastB, lastL);
                System.out.println("getLast L: " + lastL + " getLastB: " + lastB);
            } else if (operationNumber ==3 ) {
                // removeLast
                if (L.size() == 0) {continue;}
                int removedlastL = L.removeLast();
                int removedlastB = B.removeLast();
                assertEquals(removedlastB, removedlastL);
                System.out.println("removeL " + removedlastL + "removeB " + removedlastB);
            }
        }
    }

}


