package Heap;

/**
 *
 * @author dangvu.vn
 */
public class PriorityQueue {

    class Item {
        String value;
        int priority;

        Item(int priority, String value) {
            this.value = value;
            this.priority = priority;
        }
    }

    Item[] items;
    int size;
    int capacity;

    final int MIN_CAPACITY = 8;
    final double EXPAND_THRESHOLD = 0.5;
    final double SHRINK_THRESHOLD = 0.75;

    PriorityQueue() {
        this.capacity = MIN_CAPACITY;
        items = new Item[this.capacity];
    }

    public void insert(int priority, String value) {
        items[size] = new Item(priority, value);
        int checkPos = size;
        while (checkPos > 0) {
            shiftUp(checkPos);
            checkPos = (checkPos - 1) / 2;
        }
        size += 1;
        if (capacity * EXPAND_THRESHOLD < size) {
            expand();
        }
    }

    public String extract() {
        if (size == 0) {
            return "";
        }

        Item head = items[0];
        items[0] = items[size - 1];
        size -= 1;

        int checkPos = 0;
        while (checkPos <= size / 2 && checkPos != -1) {
            checkPos = shiftDown(checkPos);
        }

        if (capacity * SHRINK_THRESHOLD > size) {
            shrink();
        }
        return head.value;
    }

    private void shrink() {
        if (capacity == MIN_CAPACITY) {
            return;
        }
        items = cloneHeap(size / 2);
    }

    private void expand() {
        items = cloneHeap(size * 2);
    }

    private Item[] cloneHeap(int newSize) {
        Item[] newValues = new Item[newSize];
        for (int i = 0; i < size; i += 1) {
            newValues[i] = items[i];
        }
        return newValues;
    }

    /**
     * shift the item at the check position to one of its child position
     * if its priority is smaller than one of them, then return the shifted
     * position, else return -1.
     */
    private int shiftDown(int checkPos) {
        int leftChildIdx = 2 * checkPos + 1;
        int rightChildIdx = 2 * checkPos + 2;

        /**
         * Check whether current check position has child node.
         */
        if (leftChildIdx >= size)
            return -1;
        
        /**
         * Check whether current check position has right child node.
         */
        if (rightChildIdx == size - 1) {
            if (items[leftChildIdx].priority > items[checkPos].priority) {
                swap(leftChildIdx, checkPos);
                return leftChildIdx;
            }

            return -1;
        }

        /**
         * Check if current check position has priority smaller than one of 
         * its child position.
         */
        if (items[checkPos].priority < items[leftChildIdx].priority
                || items[checkPos].priority < items[rightChildIdx].priority) {

            int shiftDownIdx = (items[leftChildIdx].priority > items[rightChildIdx].priority)
                    ? leftChildIdx : rightChildIdx;

            swap(checkPos, shiftDownIdx);
            return shiftDownIdx;
        }

        return -1;
    }

    private void shiftUp(int checkPos) {
        int parentPriority = items[parentIdx(checkPos)].priority;
        int currentPriority = items[checkPos].priority;

        if (currentPriority > parentPriority) {
            swap(checkPos, parentIdx(checkPos));
        }
    }

    private void swap(int idxI, int idxJ) {
        Item tmp = items[idxI];
        items[idxI] = items[idxJ];
        items[idxJ] = tmp;
    }

    private int parentIdx(int idx) {
        return (idx - 1) / 2;
    }
}
