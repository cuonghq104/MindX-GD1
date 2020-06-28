package Sort;

import java.util.Arrays;

public class CountingSort {

    void sort(int[] items, int max) {
        int[] count = new int[max + 1];
        Arrays.fill(count, 0);

        for (int item : items) {
            count[item] += 1;
        }

        int position = 0; // position of next element
        for (int i = 0; i < count.length; i += 1) {
            position = position + count[i];
            count[i] = position;
        }

        // shift each element to the right to transform position of next element ==> first position to place
        // itself in the result array
        for (int i = count.length - 1; i > 0; i -= 1) {
            count[i] = count[i - 1];
        }
        count[0] = 0;

        int[] results = new int[items.length];
        for (int item : items) {
            results[count[item]] = item;
            count[item] += 1;
        }

        for (int i = 0; i < items.length; i += 1) {
            items[i] = results[i];
        }
    }

    public static void main(String[] args) {
        int[] arr = new int[] {1, 2, 3, 2, 1, 3, 2, 3, 2, 1, 1, 1};
        CountingSort mSort = new CountingSort();
        mSort.sort(arr, 3);
        for (int i = 0; i < arr.length; i += 1) {
            System.out.print(arr[i] + " ");
        }
    }
}
