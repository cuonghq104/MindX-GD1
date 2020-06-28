package Sort;

import java.time.LocalTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Collections;

public class RadixSort {

    int max(Integer[] items) {
        return Collections.max(Arrays.asList(items));
    }

    int countingValue(int value, int exp) {
        return (value % exp) / (exp / 10);
    }

    void transformToPositionArray(int[] count) {
        int position = 0;
        for (int i = 0; i < count.length; i += 1) {
            position = position + count[i];
            count[i] = position;
        }
    }

    void shiftRightCountArray(int[] count) {
        for (int i = count.length - 1; i > 0; i -= 1) {
            count[i] = count[i - 1];
        }
        count[0] = 0;
    }

    void countingSort(Integer[] items, int exp) {
        int[] count = new int[10];
        Arrays.fill(count, 0);

        for (Integer value : items) {
            int countingVal = countingValue(value, exp);
            count[countingVal] += 1;
        }

        transformToPositionArray(count);
        shiftRightCountArray(count);

        int[] sortedItems = new int[items.length];

        for (Integer value : items) {
            int countingVal = countingValue(value, exp);
            sortedItems[count[countingVal]] = value;
            count[countingVal] += 1;
        }

        for (int i = 0; i < items.length; i += 1) {
            items[i] = sortedItems[i];
        }
    }

    void sort(Integer[] items) {
        int max = max(items);
        int numberOfDigits = (max + "").length();

        int exponential = 10;

        for (int i = 0; i < numberOfDigits; i += 1) {
            countingSort(items, exponential);
            exponential = exponential * 10;
        }

    }

}
