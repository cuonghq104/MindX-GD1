/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package day7.hashmap;

/**
 *
 * @author dangvu.vn
 */
public class HashMapOpenAddressing {

    class Item {

        int key;
        String val;

        Item(int key, String val) {
            this.key = key;
            this.val = val;
        }
    }

    Item[] items;
    int capacity;
    int size;

    public void init() {
        this.capacity = 8;
        this.size = 0;
        
        items = new Item[capacity];
    }

    private boolean isExist(Item item) {
        return (item != null && item.key != -1 && !item.val.equals(""));
    }

    public void insert(int key, String val) {
        int count = 0;
        int hashVal = -1;
        Item item = null;

        do {
            count += 1;
            hashVal = hash(key, count);

            item = items[hashVal];
            if (isExist(item) && item.key == key) {
                updateVal(hashVal, val);
                return;
            }

        } while (isExist(item));

        items[hashVal] = new Item(key, val);
        
        size += 1;
        checkAndGrowHashMap();
    }

    public boolean delete(int key) {

        int count = 0;
        int hashVal = -1;
        Item item = null;

        do {
            count += 1;
            hashVal = hash(key, count);

            item = items[hashVal];
            if (isExist(item) && item.key == key) {
                
                // Put delete flag on item
                updateVal(hashVal, "");
                updateKey(hashVal, -1);
                
                // Shrink hashmap if its size is too small
                size -= 1;
                checkAndShrinkHashMap();
                
                return true;
            }
        } while (isExist(item));

        return false;
    }

    public String search(int key) {

        int count = 0;
        int hashVal = -1;
        Item item = null;

        do {
            count += 1;
            item = items[hash(key, count)];

            if (isExist(item) && item.key == key) {

                return item.val;
            }
        } while (isExist(item));

        return "";
    }

    private void relocateHashMap(Item[] copied) {
        size = 0;
        this.items = new Item[capacity];
        
        for (Item item : copied) {
            if (isExist(item)) {
                
                insert(item.key, item.val);
            }
        }
    }
    
    private void checkAndGrowHashMap() {
        if (size <= capacity / 2)
            return;
        
        Item[] backup = this.items;
        this.capacity = capacity * 2;
        relocateHashMap(backup);
    }
    
    private void checkAndShrinkHashMap() {
        if (capacity == 8 || size >= capacity / 4)
            return;
        
        Item[] backup = this.items;
        this.capacity = capacity / 2;
        relocateHashMap(backup);
        
    }
    private void display() {

        for (int i = 0; i < capacity; i += 1) {
            System.out.print((items[i] == null || items[i].key == -1) ? "[N, N]" : "[" + items[i].key + ", " + items[i].val + "] ");
        }
        System.out.println("");
    }

    private void updateVal(int hashVal, String val) {
        items[hashVal].val = val;
    }

    private void updateKey(int hashVal, int key) {
        items[hashVal].key = key;
    }

    private int subHash1(int key) {
        return (key % capacity);
    }

    private int subHash2(int key) {
        return (key * 2) - 1;
    }

    private int hash(int key, int count) {
        return (subHash1(key) + count * subHash2(key)) % capacity;
    }

}
