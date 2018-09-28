// package com.google.challenges;

public class Answer3 {
    public static String answer(int x, int y) {

        /* failed two test cases
        int n =x+y-1;
        int nPrev = n-1;
        long bunnyNumber = (nPrev*(nPrev+1)/2) + x;
        return String.valueOf(bunnyNumber);*/

        long num = 0;
        for (int i=1; i <=x; i++) {
            num += i;
        }
        for (int j=0; j<y-1; j++) {
            num += x+j;
        }
        return String.valueOf(num);

    }
}