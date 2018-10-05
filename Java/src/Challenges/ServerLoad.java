package Challenges;// you can also use imports, for example:
import java.util.*;

import static java.lang.Math.abs;

/*  distribute server load  */

class ServerLoad {
    public int solution(int[] A) {
        Arrays.sort(A);

        for(int i = 0; i < A.length / 2; i++)
        {
            int temp = A[i];
            A[i] = A[A.length - i - 1];
            A[A.length- i - 1] = temp;
        }

        int server1 = 0;
        int server2 = 0;

        for (int load : A) {
            if (server1 > server2) { server2 += load; }
            else if (server2 > server1) { server1 += load; }
            else { server1 += load; }
        }

        return abs(server1 - server2);

    }
}