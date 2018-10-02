import java.util.*;

/* Remove duplicates from integer array
 */
public class ArrayRemoveDuplicate {

    // Remove duplicates from integer array in place without using Collections
    public static void removeDuplicate(int[] arr) {
        System.out.printf("Array with duplicates is %s %n", Arrays.toString(arr));
        int newIndex = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[i-1]) {
                arr[newIndex] = arr[i];
                newIndex++;
            }
        }
        arr = Arrays.copyOfRange(arr,0,newIndex);
        System.out.printf("Array without duplicates is %s %n", Arrays.toString(arr));
    }

    // using HashSet (does not preserve order) and LinkedHashSet (preserves order)
    public static void removeDuplicateHS(int[] primitive) {

        // convert primitive int array to Integer object array
        Integer[] arr = Arrays.stream(primitive).boxed().toArray(Integer[]::new);

        // Convert Integer array to ArrayList
        List<Integer> duplicateList = Arrays.asList(arr);
        System.out.println("size of ArrayList with duplicates: " + duplicateList.size());
        System.out.println(duplicateList);

        //Convert ArrayList to HashSet to remove duplicates
        HashSet<Integer> hs = new HashSet<>(duplicateList);
        List<Integer> listWithoutDuplicates = new ArrayList<>(hs);

        //Convert ArrayList to LinkedHashSet to remove duplicates (preserves order)
        HashSet<Integer> lhs = new LinkedHashSet<>(duplicateList);
        List<Integer> orderedListWithoutDuplicates = new ArrayList<>(lhs);

        System.out.println("size of ArrayList without duplicates: " + hs.size());
        System.out.println("HashSet: "+listWithoutDuplicates);
        System.out.println("LinkedHashSet: "+orderedListWithoutDuplicates);
    }



}
