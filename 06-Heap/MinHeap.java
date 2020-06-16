package heap;

/**
 *
 * @author dangvu.vn
 */
public class MinHeap {

    int[] values;
    int size;
    int capacity;

    final int MIN_CAPACITY = 8;
    final double EXPAND_THRESHOLD = 0.5;
    final double SHRINK_THRESHOLD = 0.75;

    MinHeap() {
        this.capacity = MIN_CAPACITY;
        values = new int[this.capacity];
    }

    public void insert(int newValue) {
        values[size] = newValue;
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

    public int extractMin() {
        if (size == 0) {
            return -1;
        }

        int maxValue = values[0];
        values[0] = values[size - 1];
        size -= 1;

        int checkPos = 0;
        while (checkPos <= size / 2 && checkPos != -1) {
            checkPos = shiftDown(checkPos);
        }

        if (capacity * SHRINK_THRESHOLD > size) {
            shrink();
        }
        return maxValue;
    }

    private void shrink() {
        if (capacity == MIN_CAPACITY) {
            return;
        }
        values = cloneHeap(size / 2);
    }

    private void expand() {
        values = cloneHeap(size * 2);
    }

    private int[] cloneHeap(int newSize) {
        int[] newValues = new int[newSize];
        for (int i = 0; i < size; i += 1) {
            newValues[i] = values[i];
        }
        return newValues;
    }

    private int shiftDown(int checkPos) {
        int leftChildIdx = 2 * checkPos + 1;
        int rightChildIdx = 2 * checkPos + 2;

        if (leftChildIdx >= size) {
            return -1;
        }
        
        if (rightChildIdx == size - 1) {
            if (values[leftChildIdx] < values[checkPos]) {
                swap(leftChildIdx, checkPos);
                return leftChildIdx;
            }

            return -1;
        }

        if (values[checkPos] < values[leftChildIdx]
                || values[checkPos] > values[rightChildIdx]) {

            int shiftDownIdx = (values[leftChildIdx] < values[rightChildIdx])
                    ? leftChildIdx : rightChildIdx;

            swap(checkPos, shiftDownIdx);
            return shiftDownIdx;
        }

        return -1;
    }

    private void shiftUp(int checkPos) {
        int parentValue = values[parentIdx(checkPos)];
        int currentValue = values[checkPos];

        if (currentValue < parentValue) {
            swap(checkPos, parentIdx(checkPos));
        }
    }

    private void swap(int idxI, int idxJ) {
        int tmp = values[idxI];
        values[idxI] = values[idxJ];
        values[idxJ] = tmp;
    }

    private int parentIdx(int idx) {
        return (idx - 1) / 2;
    }

}
