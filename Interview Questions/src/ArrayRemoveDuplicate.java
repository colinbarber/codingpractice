import java.util.*;

/* Remove duplicates from integer array
 */
public class ArrayRemoveDuplicate {

    // Remove duplicates from integer array without using Collections (writes 0)
    public static void removeDuplicate(int[] arr) {
        System.out.printf("Array with duplicates is %s %n", Arrays.toString(arr));
        // sort array to bring duplicates together
        Arrays.sort(arr);
        int duplicateIndex = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] == arr[duplicateIndex]) {
                arr[i] = 0;
            }
            else {
                duplicateIndex = i;
            }
        }
        System.out.printf("Array without duplicates is %s %n", Arrays.toString(arr));
    }

    // Remove duplicates from integer array without using Collections (returns new array)
    public static void removeDuplicateNew(int[] arr) {
        System.out.printf("Array with duplicates is %s %n", Arrays.toString(arr));
        // sort array to bring duplicates together
        Arrays.sort(arr);
        int duplicateIndex = 1;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] != arr[i-1]) {
                arr[duplicateIndex] = arr[i];
                duplicateIndex++;
            }
        }
        arr = Arrays.copyOfRange(arr,0,duplicateIndex);
        System.out.printf("Array without duplicates is %s %n", Arrays.toString(arr));
    }

    // using HashSet (does not preserve order) and LinkedHashSet (preserves order)
    public static void removeDuplicateHS(int[] prim) {

        // convert primitive int array to Integer object array
        Integer[] arr = Arrays.stream(prim).boxed().toArray(Integer[]::new);

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
