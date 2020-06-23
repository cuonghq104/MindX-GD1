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
public class QuickSort {
    
    public void sort(int[] items, int leftIndex, int rightIndex) {
        if (leftIndex > rightIndex)
            return;
        
        int pivot = partitionDoublePointer(items, leftIndex, rightIndex);
        sort(items, leftIndex, pivot - 1);
        sort(items, pivot + 1, rightIndex);
    }
    
    private int partition(int[] items, int leftIndex, int rightIndex) {
        int idx = leftIndex;
        int firstGreaterIdx = leftIndex;
        
        int pivot = items[rightIndex];
        
        while (idx < rightIndex) {
            if (items[idx] < pivot) {
                swap(items, idx, firstGreaterIdx);
                firstGreaterIdx += 1;
            }
            idx += 1;
        }
        
        swap(items, rightIndex, firstGreaterIdx);
        return firstGreaterIdx;
    }
    
    private int partitionDoublePointer(int[] items, int leftIndex, int rightIndex) {
        int pivot = rightIndex;
        
        while (leftIndex <= rightIndex) {
            if (items[leftIndex] < items[pivot]) {
                leftIndex += 1;
            } else if (items[rightIndex] >= items[pivot]) {
                rightIndex -= 1;
            } else {
                swap(items, leftIndex, rightIndex);
                leftIndex += 1;
                rightIndex -= 1;
            }
        }
        
        swap(items, leftIndex, pivot);
        return leftIndex;
    }
    
    private void swap(int[] items, int i, int j) {
        int tmp = items[i];
        items[i] = items[j];
        items[j] = tmp;
    }
    
}
