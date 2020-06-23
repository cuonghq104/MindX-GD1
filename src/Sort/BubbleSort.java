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
public class BubbleSort {
    void swap(int[] items, int i, int j) {
        int tmp = items[i];
        items[i] = items[j];
        items[j] = tmp;
    }
    
    void sort(int[] items) {
        
        for (int i = items.length - 1; i > 0; i -= 1) {
            
            for (int j = 0; j < i; j += 1) {
                
                if (items[j] < items[j + 1]) {
                    swap(items, i, j);
                }
            }
        }
    }
    
}
