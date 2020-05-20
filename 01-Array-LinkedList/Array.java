/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cuonghq104
 */
public class Array {

    int arr[];
    int length;
    int capacity;

    Array() {
        init();
    }

    void init() {
        arr = new int[1];
        length = 0;
        capacity = 1;
    }

    /**
     * @return number of items are currently stored in array
     */
    int size() {
        return length;
    }

    /**
     * @return number of items array can hold.
     */
    int capacity() {
        return capacity;
    }

    /**
     *
     * @return whether the array is empty
     */
    boolean isEmpty() {
        return size() == 0;
    }

    /**
     * @param index: index of item stored in array
     * @return value of item with given index
     */
    int itemAt(int index) {
        if (index < 0 | index >= size()) {
            return -1;
        }

        return arr[index];
    }

    /**
     * Enlarge array size 2 times
     */
    void growArray() {
        /**
         * Store current elements of arrays
         */
        int[] tmp = new int[length];
        for (int idx = 0; idx < length; idx += 1) {
            tmp[idx] = arr[idx];
        }

        /**
         * Double array capacity, copy back data from backup array
         */
        capacity = capacity * 2;
        arr = new int[capacity];
        for (int idx = 0; idx < length; idx += 1) {
            arr[idx] = tmp[idx];
        }
    }

    /**
     * append given value to the end of array
     *
     * @param value
     */
    boolean append(int value) {
        if (size() == capacity()) {
            growArray();
        }
        arr[size()] = value;
        length += 1;
        return true;
    }

    boolean insert(int value, int position) {
        /**
         * If the input position is larger than the array size or smaller than
         * zero, return false.
         */
        if (position < 0 || position >= size()) {
            return false;
        }

        if (size() == capacity()) {
            growArray();
        }

        if (position == size()) {
            return append(value);
        }

        /**
         * Relocate elements from given position -> end of array to the position
         * after it in the array to spend space for new value in the array.
         */
        for (int idx = length; idx > position; idx -= 1) {
            arr[idx] = arr[idx - 1];
        }
        arr[position] = value;

        length += 1;
        return true;
    }

    /**
     * Reduce capacity of current array to half of its current size.
     */
    void shrinkArray() {
        if (length >= capacity() / 2 || capacity == 1) {
            /**
             * Can't shrink when the current length of array is bigger than a
             * half of its current capacity or reach minimum value of capacity
             * (1)
             */
            return;
        }

        int[] tmp = new int[length];
        for (int idx = 0; idx < length; idx += 1) {
            tmp[idx] = arr[idx];
        }

        capacity = capacity / 2;
        arr = new int[capacity];
        for (int idx = 0; idx < length; idx += 1) {
            arr[idx] = tmp[idx];
        }
    }

    /**
     * Remove last item of array and return its values then shrink array when
     * meet a certain condition.
     *
     * @return Value of last item.
     */
    int pop() {
        if (isEmpty())
            return -1;
        
        int value = arr[size() - 1];

        arr[size() - 1] = 0;
        length -= 1;

        /**
         * When size of the array has been dropped to below a quarter of its
         * capacity, shrink its capacity to half --> To guarantee its average
         * time complexity
         */
        if (size() <= capacity() / 4) {
            shrinkArray();
        }

        return value;
    }

    /**
     * Remove a item in given position from the array and return its values then
     * shrink array when meet a certain condition.
     *
     * @return Value of given position.
     */
    int removeAt(int position) {

        /**
         * return -1 if input position out of array possible position.
         */
        if (position < 0 || position >= size()) {
            return -1;
        }

        int value = arr[position];
        for (int idx = position; idx < size() - 1; idx += 1) {
            arr[idx] = arr[idx + 1];
        }
        length -= 1;

        /**
         * When size of the array has been dropped to below a quarter of its
         * capacity, shrink its capacity to half --> To guarantee its average
         * time complexity
         */
        if (size() < capacity / 4) {
            shrinkArray();
        }

        return value;
    }

    /**
     * Display current elements of array.
     */
    void print() {
        System.out.println("------------------------------");
        System.out.println("Capacity: " + capacity() + " Size: " + size());
        if (size() == 0) {
            System.out.println("Empty");
        } else {
            for (int i = 0; i < size(); i += 1) {
                System.out.print(arr[i] + " ");
            }
            System.out.println("");
        }
    }
}