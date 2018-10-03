import java.util.*;

/* Before answering the question ask clarifying questions like:
- Does array contains only positive or negative numbers?
- What if the same pair repeats twice, should we print it every time?
- Is reverse of pair is acceptable e.g. can we print both (4,1) and (1,4) if given sum is 5.
- Do we need to print only distinct pair? does (3, 3) is a valid pair forgiven sum of 6?
- How big is the array?
*
*
* Finds pairs of numbers in an array which add up to target sum
* */

// Naive method: looping through two for loops of n (complexity of O(n^2) )

public class ArrayFindPairs {

    // using a HashSet (complexity of O(n) )
    public static void findPair (int[] arr, int sum) {
        // case for array length of 1
        if (arr.length < 2) {
            return;
        }
        Set set = new HashSet(arr.length);
        // generate set of reciprocal values
        for (int num : arr) {
            int recip = sum - num;
            // print any reciprocal pairs
            if (!set.contains(recip)) {
                set.add(num);
            }
            else {
                System.out.printf("(%d, %d) %n", num, recip);
            }
        }
    }

    // in place method using Arrays.sort and two indices
}
