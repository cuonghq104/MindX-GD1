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
public class SelectionSort {
    void swap(int[] items, int i, int j) {
        int tmp = items[i];
        items[i] = items[j];
        items[j] = tmp;
    }
    
    void sort(int[] items) {
        for (int i = 0; i < items.length - 1; i += 1) {
            
            int maxIdx = i;
            for (int j = i + 1; j < items.length; j += 1) {
                
                if (items[j] > items[maxIdx]) {
                    maxIdx = j;
                }
            }
            
            swap(items, maxIdx, i);
        }
    }
    
}
