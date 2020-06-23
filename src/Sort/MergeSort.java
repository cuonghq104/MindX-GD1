/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sort;

/**
 *
 * @author dangvu.vn
 */
public class MergeSort {

    public void sort(int[] items, int left, int right) {

        if (left >= right) {
            return;
        }

        int mid = (left + right) / 2;
        sort(items, left, mid);
        sort(items, mid + 1, right);

        merge(items, left, right);
    }
    
    private void merge(int[] items, int left, int right) {
        if (right - left < 1) {
            return;
        }

        int mid = (left + right) / 2;
        int leftStart = left;
        int leftEnd = mid;

        int rightStart = mid + 1;
        int rightEnd = right;

        int[] merge = new int[right - left + 1];
        int mergeArrSize = 0;

        while (leftStart <= leftEnd || rightStart <= rightEnd) {
            if (leftStart > leftEnd) {
                merge[mergeArrSize++] = items[rightStart++];
            } else if (rightStart > rightEnd) {
                merge[mergeArrSize++] = items[leftStart++];
            } else {
                merge[mergeArrSize++] = (items[leftStart] > items[rightStart])
                        ? items[rightStart++] : items[leftStart++];
            }
        }

        for (int i = 0; i < mergeArrSize; i += 1) {
            items[i + left] = merge[i];
        }
    }

}
