package binariks;

import java.util.Arrays;

public class Main {

    public static int i = 1;

    public static void main(String[] args) {
        // write your code here

        int n = 6;//sequence number of Fibonacci
        long[] memo = new long[n+1];
        Arrays.fill(memo,-1);
        System.out.println(fibonaci(n, memo));


    }

    static long fibonaci(int n, long[] memo){
        if (memo[n] != -1)
            return memo[n];

        if (n<=1)
            return n;


        i++;
        long result = fibonaci(n-1, memo) + fibonaci(n-2, memo);
        memo[n] = result;
        return result;
    }

}
