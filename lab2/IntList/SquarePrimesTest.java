package IntList;

import static org.junit.Assert.*;
import org.junit.Test;

public class SquarePrimesTest {

    /**
     * Here is a test for isPrime method. Try running it.
     * It passes, but the starter code implementation of isPrime
     * is broken. Write your own JUnit Test to try to uncover the bug!
     */
    @Test
    public void testSquarePrimesSimple() {
        IntList lst = IntList.of(14, 15, 16, 17, 18);
        IntList lst2 = IntList.of(11, 2, 5, 17);
        IntList lst3 = IntList.of(4, 6, 8, 10);
        IntList lst4 = IntList.of(0, 6, 5, 12, -2);
        boolean changed = IntListExercises.squarePrimes(lst);
        boolean changed2 = IntListExercises.squarePrimes(lst2);
        boolean changed3 = IntListExercises.squarePrimes(lst3);
        boolean changed4 = IntListExercises.squarePrimes(lst4);
        assertEquals("14 -> 15 -> 16 -> 289 -> 18", lst.toString());
        assertEquals("121 -> 4 -> 25 -> 289", lst2.toString());
        assertEquals("4 -> 6 -> 8 -> 10", lst3.toString());
        assertEquals("0 -> 6 -> 25 -> 12 -> -2", lst4.toString());
        assertTrue(changed);
        assertTrue(changed2);
        assertFalse(changed3);
        assertTrue(changed4);
    }
}
