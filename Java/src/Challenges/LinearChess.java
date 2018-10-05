package Challenges;

/*        A simplified version of chess, “Linear chess”, consists of only pawns lined up in a single row.
        Note that since they are in a single row, they cannot capture or jump over one another.
        The board is represented by a string where “R” is a pawn that can only move right, and “L” is a pawn that can only
        move left, and “ “ is an empty space on the board. Given two strings, start & finish, return true if
        finish is a valid outcome given the board starting state.

        solve(“ R _ _ L ”, “ _ _ R L ”) == true
        solve(“ _ R L _ “, “ L R _ _ “) == false
        solve(“ _ R “, “ R _ “) == false

        */

import java.util.*;

public class LinearChess {
    public static boolean solution(String start, String finish) {
        char[] startArr = start.toCharArray();
        char[] finishArr = finish.toCharArray();

        ArrayList<Integer> lArrS = new ArrayList<>();
        ArrayList<Integer> rArrS = new ArrayList<>();
        StringBuilder rlArrS = new StringBuilder();

        for (int i = 0; i < start.length(); i++) {
            if (startArr[i] == 'R') {
                rArrS.add(i);
                rlArrS.append('R');
            } else if (startArr[i] == 'L') {
                lArrS.add(i);
                rlArrS.append('L');
            }
        }
        System.out.printf("start = %s finish = %s %n lArrS = %s %n rArrS = %s %n rlArrS = %s %n", start, finish, lArrS, rArrS, rlArrS);

        ArrayList<Integer> lArrF = new ArrayList<>();
        ArrayList<Integer> rArrF = new ArrayList<>();
        StringBuilder rlArrF = new StringBuilder();

        for (int i = 0; i < finish.length(); i++) {
            if (finishArr[i] == 'R') {
                rArrF.add(i);
                rlArrF.append('R');
            } else if (finishArr[i] == 'L') {
                lArrF.add(i);
                rlArrF.append('L');
            }
        }

        System.out.printf(" lArrF = %s %n rArrF = %s %n rlArrF = %s %n", lArrF, rArrF, rlArrF);

        for (int i = 0; i < rArrS.size(); i++) {
            if (rArrF.get(i) < rArrS.get(i)) {
                return false;
            }
        }

        for (int i = 0; i < lArrS.size(); i++) {
            if (lArrF.get(i) > lArrS.get(i)) {
                return false;
            }
        }

        return rlArrS.toString().equals(rlArrF.toString());
    }
}
