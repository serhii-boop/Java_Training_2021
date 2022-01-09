package binariks;

import org.junit.jupiter.api.Assertions;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {



    @org.junit.jupiter.api.Test
    void testFib_1() {
        int n = 1;
        long[] memo = new long[n+1];
        Arrays.fill(memo,-1);

        Assertions.assertEquals(1, Main.fibonaci(n,memo));
    }

    @org.junit.jupiter.api.Test
    void testFib_2() {
        int n = 2;
        long[] memo = new long[n+1];
        Arrays.fill(memo,-1);

        assertEquals(1,Main.fibonaci(n,memo));
    }

    @org.junit.jupiter.api.Test
    void testFib_10() {
        int n = 10;
        long[] memo = new long[n+1];
        Arrays.fill(memo,-1);

        assertEquals(55,Main.fibonaci(n,memo));
    }
}