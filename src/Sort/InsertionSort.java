/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sort;

import java.util.Collections;

/**
 *
 * @author dangvu.vn
 */
public class InsertionSort {
    void swap(int[] items, int i, int j) {
        int tmp = items[i];
        items[i] = items[j];
        items[j] = tmp;
    }
    
    void sort(int[] items) {
        for (int i = 1; i < items.length; i += 1) {
            int j = i;
            while (j > 0 && items[j] > items[j - 1]) {
                swap(items, j, j - 1);
                j -= 1;
            }
        }
    }
    
}
