//package com.google.challenges;

public class Answer {
    public static String answer(int x, int y) {

        int n =x+y;
        int nPrev = n-1;
        long bunnyNumber = (nPrev*(nPrev-1)/2) + x;
        return String.valueOf(bunnyNumber);

    }
}