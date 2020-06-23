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
public class HeapSort {
    
    void swap(int[] items, int i, int j) {
        int tmp = items[i];
        items[i] = items[j];
        items[j] = tmp;
    }
    
    void makeMinHeap(int[] items) {
        for (int i = items.length / 2; i >= 0; i -= 1) {
            siftDown(items, i, items.length);
        }
    }
    
    void siftDown(int[] items, int position, int size) {
        
        if (position >= size / 2) {
            return;
        }
        
        int leftChild = 0;
        int rightChild = 0;
        
        if (position * 2 + 2 == size) {
            leftChild = position * 2 + 1;
            if (items[leftChild] < items[position]) {
                swap(items, leftChild, position);
            }
            return;
        }
        
        leftChild = 2 * position + 1;
        rightChild = 2 * position + 2;
        
        if (items[position] > items[leftChild] || items[position] > items[rightChild]) {
            
            int swapPosition;
            if (items[leftChild] >= items[position]) {
                swapPosition = rightChild;
            } else if (items[rightChild] >= items[position]) {
                swapPosition = leftChild;
            } else {
                swapPosition = (items[leftChild] > items[rightChild]) ? rightChild : leftChild;
            }
            swap(items, position, swapPosition);
            siftDown(items, swapPosition, size);
        }
    }
    
    
    void sort(int[] items) {
        int largestPosition = items.length - 1;
        
        makeMinHeap(items);
        for (int i = 0; i < items.length - 1; i += 1) {
            swap(items, 0, largestPosition);
            siftDown(items, 0, largestPosition - 1);
            largestPosition -= 1;
        }
    }
    
}
