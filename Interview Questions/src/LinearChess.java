/*        A simplified version of chess, “Linear chess”, consists of only pawns lined up in a single row.
        Note that since they are in a single row, they cannot capture or jump over one another.
        The board is represented by a string where “R” is a pawn that can only move right, and “L” is a pawn that can only
        move left, and “ “ is an empty space on the board. Given two strings, start & finish, return true if
        finish is a valid outcome given the board starting state.

        solve(“ R _ _ L ”, “ _ _ R L ”) == true
        solve(“ _ R L _ “, “ L R _ _ “) == false
        solve(“ _ R “, “ R _ “) == false

        */
public class LinearChess {
    public static boolean solution(String start, String finish){
            char[] startArr = start.toCharArray();
            char[] finishArr = finish.toCharArray();

            int lInd = 0;
            int[] lArr = new int[startArr.length];
            int rInd = 0;
            int[] rArr = new int[startArr.length];


            for (int i = 0; i < start.length(); i++) {
                    if (startArr[i] == 'R') {
                            rArr[rInd] =
                    }
                    else if (startArr[i] == 'L') {

                    }
            }
    }
}
