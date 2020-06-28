package Sort;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class BucketSort {

    void sort(double[] items) {
        List<Double>[] buckets = new List[10];
        for (double num : items) {
            int bucketIdx = (int) (num * 10);
            if (buckets[bucketIdx] == null) {
                buckets[bucketIdx] = new ArrayList<Double>();
            }
            buckets[bucketIdx].add(num);
        }

        int[] count = new int[10];
        for (int i = 0; i < buckets.length; i += 1) {
            count[i] = (buckets[i] == null) ? 0 : buckets[i].size();
        }

        for (int i = count.length - 1; i > 0; i -= 1) {
            count[i] = count[i - 1];
        }
        count[0] = 0;

        int position = 0;
        for (int i = 0; i < count.length; i += 1) {
            position = position + count[i];
            count[i] = position;
        }

        for (int i = 0; i < buckets.length; i += 1) {
            if (buckets[i] != null && buckets[i].size() > 1) {
                buckets[i].sort(Double::compareTo);
            }
        }


        for (int i = 0; i < buckets.length; i += 1) {
            List<Double> values = buckets[i];
            if (values != null) {
                for (int j = 0; j < values.size(); j += 1) {

                    items[count[i]] = values.get(j);
                    count[i] += 1;
                }
            }
        }
    }

}
