package Challenges;// you can also use imports, for example:
import java.util.*;

// you can write to stdout for debugging purposes, e.g.
// System.out.println("this is a debug message");

class HouseStoreDistance {
    public int[] solution(int[] stores, int[] houses) {
        int[] ans = new int[houses.length];

        // sort the stores so we can access by ascending distance and pick the shortest one
        Arrays.sort(stores);

        // find the store of shortest distance to each house
        for (int j = 0; j < houses.length; j++) {
            for (int i = 0; i < stores.length; i++) {

                // case 1: a store exists that is equidistant to the house
                if (stores[i] == houses[j]) {
                    ans[j] = stores[i];
                    break;
                }
                // case 2: no equidistant store and stores exist further than the house
                else if (stores[i] > houses[j]) {

                    // the further store is the first store in the array
                    if (i == 0) {
                        ans[j] = stores[i];
                        break;
                    }
                    // if stores exist before and after the house, determine the closest
                    else {
                        int distAfter = stores[i] - houses[j];
                        int distBefore = houses[j] - stores[i - 1];

                        // the stores are equidistant or the one before is , pick the smaller one
                        if (distAfter < distBefore) {
                            ans[j] = stores[i];
                            break;
                        }
                        // the stores are equidistant or the one before is closer, pick the one before
                        else {
                            ans[j] = stores[i - 1];
                            break;
                        }
                    }
                }
                // case 3: no stores exist further than the house
                else if (i == stores.length - 1) {
                    ans[j] = stores[i];
                    break;
                }
            }
        }
        return ans;
    }
}