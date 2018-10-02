import java.util.*;

/* find the missing numbers in a given array of n integers, containing integers between 1 to n
 */
public class ArrayMissingNumber {

    // return single missing element in an array of integers (no duplicates)
    public static void missingNumber(int[] arr, int count) {
        int completeTotal = count * (count+1) / 2;
        int arrTotal = 0;
        for (int item : arr) { arrTotal += item; }
        int missingNo = completeTotal - arrTotal;
        System.out.printf("Missing number in integer array %s, with total number %d is %n", Arrays.toString(arr), count);
        System.out.println(missingNo);
    }

    // find multiple missing elements in an array of integers (with duplicates)
    public static void missingNumbers(int[] arr, int count) {

        // initialize a bitset of target array length
        BitSet bs = new BitSet(count);

        // set each array element to true
        for (int item : arr) {
            bs.set(item-1);
        }

        // calculate how many missing numbers
        System.out.printf("Missing numbers in integer array %s, with total number %d is %n", Arrays.toString(arr), count);
        int missingCount = count - arr.length;
        int lastMissingIndex = 0;
        for (int i = 0; i < missingCount; i++) {

            // print each missing number
            lastMissingIndex = bs.nextClearBit(lastMissingIndex);
            System.out.println(++lastMissingIndex); }
    }
}
