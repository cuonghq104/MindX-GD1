

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author dangvu.vn
 */
public class HashMap {

    class Node {
        String val;
        String key;
        Node next;

        Node(String key, String val) {
            this.key = key;
            this.val = val;
        }
    }

    Node[] map;

    int capacity;
    int size;

    int HASH_BASE = 31;
    
    HashMap() {
        init();
    }
    
    public void init() {
        this.capacity = 8;
        this.size = 0;
        this.map = createHashMap(capacity);
    }

    private int hash(String txt) {
        int val = 0;
        for (char c : txt.toCharArray()) {
            val = ((val * HASH_BASE) + (c)) % capacity;
        }
        return val;
    }

    public void insert(String key, String val) {

        if (key.equals("")) {
            System.out.println("Validate error: Key cannot be null");
            return;
        }

        int hashVal = hash(key);

        // Indicator to check if key is exist in map
        Node node = map[hashVal].next;

        // Indicator to find the last node in linked list
        Node prev = map[hashVal];

        while (node != null) {

            if (node.key.equals(key)) {

                updateNode(node, val);
                return;
            }

            prev = node;
            node = node.next;
        }

        Node newNode = new Node(key, val);
        prev.next = newNode;
        System.out.println("Inserted new node with key " + key);

        size += 1;
        /**
         * To ensure time-complexity and reduce collusion in element stored in
         * hashMap, when the size of hashMap reach half of its capacity, we grow
         * it capacity to double.
         */
        if (size == capacity / 2) {
            this.grow();
        }
    }


    public void delete(String key) {

        if (!keyValid(key)) {
            return;
        }

        int hash = hash(key);

        /**
         * In order to remove a node from linked list we must have 2 indicator
         * to locate the target node and the node in the previous the the target
         * node to replace previous node relation to target node relation.
         */
        Node head = map[hash].next;
        Node prev = map[hash];

        boolean deleted = false;
        while (head != null) {
            if (head.key.equals(key)) {
                deleted = true;
                prev.next = head.next;

                System.out.println("Deleted node with key: " + key);
                break;
            }
            prev = head;
            head = head.next;
        }

        if (!deleted) {
            System.out.println("Can't find node with key: " + key
                    + " in current hashmap");
        } else {

            /**
             * Relatively to insert, when total number of items in HashMap reach
             * a quarter of its own capacity, we must shrink it capacity to half
             * of its current one, to avoid waste memory.
             */
            size -= 1;
            if (size <= capacity / 4) {
                shrink();
            }
        }

    }

    public boolean contains(String key) {

        if (!keyValid(key)) {
            return false;
        }

        int hashVal = hash(key);
        Node head = map[hashVal].next;

        while (head != null) {

            if (head.key.equals(key)) {

                System.out.println("Founded node with key: " + head.key + " has value: " + head.val);
                return true;
            }
        }

        System.out.println("Can't find node with key: " + head.key
                + " in current HashMap");
        return false;
    }

    public String find(String key) {

        if (!keyValid(key)) {
            return "";
        }

        int hashVal = hash(key);
        Node head = map[hashVal].next;

        while (head != null) {

            if (head.key.equals(key)) {

                System.out.println("Founded node with key: " + head.key + " has value: " + head.val);
                return head.val;
            }
        }

        System.out.println("Can't find node with key: " + head.key
                + " in current HashMap");
        return "";
    }

    private void updateNode(Node node, String val) {
        node.val = val;
        System.out.println("Node with key " + node.key + " updated");
    }
    
    private boolean keyValid(String key) {
        if (key.equals("")) {
            System.out.println("Validate error: Key cannot be null");
            return false;
        }

        return true;
    }

    private void rebaseHashMap(Node[] currentHashMap) {
        for (Node node : currentHashMap) {

            node = node.next;
            while (node != null) {

                insert(node.key, node.val);
                node = node.next;
            }
        }
    }

    private void shrink() {
        if (this.capacity == 8) {
            // HashMap reached minumum value of its capacity
            // --> Can't shrink anymore
            System.out.println("CAN'T SHRINK : "
                    + "HashMap has reached it minimum capacity");
            return;
        }
        System.out.println("---BEGIN TO SHRINK---------");

        this.capacity = this.capacity / 2;
        Node[] currentHashMap = this.map;
        this.map = createHashMap(this.capacity);

        this.rebaseHashMap(currentHashMap);
        System.out.println("Capacity of hashMap has been shrinked from "
                + this.capacity + " to " + this.capacity / 2);
    }

    private void grow() {
        System.out.println("---BEGIN TO GROW---------");
        this.capacity = this.capacity * 2;
        Node[] currentHashMap = this.map;
        this.map = createHashMap(this.capacity);

        this.rebaseHashMap(currentHashMap);
        System.out.println("Capacity of hashMap has been growth from "
                + this.capacity / 2 + " to " + this.capacity);
    }

    private Node[] createHashMap(int capacity) {
        this.size = 0;
        Node[] map = new Node[capacity];
        for (int i = 0; i < capacity; i += 1) {
            map[i] = new Node("", "");
        }
        return map;
    }

    public static void main(String[] args) {
        HashMap map = new HashMap();
        map.init();

        map.insert("abcdef", "Cuong");
        map.find("abcdef");
        map.insert("abcdef", "Cuong Hoang");
        map.find("abcdef");
        map.insert("aa", "Cuong Hoang");
        map.insert("bb", "Cuong Quoc");
        map.insert("cc", "Cuong Hoang Quoc");
        map.insert("dd", "Cuong Quoc Hoang");
        map.insert("ee", "Hoang Quoc Cuong");
        map.delete("aa");
        map.delete("bb");
        map.delete("cc");
        map.delete("dd");
        map.delete("ee");
    }
}
