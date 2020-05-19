/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author cuong.hq1
 */
public class DoublyLinkedList {

    /**
     *
     */
    class Node {

        int value;
        Node prev;
        Node next;
    }

    Node head;
    Node tail;
    int size;

    /**
     * Initializing linked list reset head and tail to null;
     */
    void init() {
        head = null;
        tail = null;
        size = 0;
    }

    public DoublyLinkedList() {
        this.init();
    }

    /**
     * Place a new node before the head position of the linked list if linked
     * list is empty, set head and tail to a same node.
     *
     * @param value: value that will be inserted.
     */
    void pushFront(int value) {
        Node node = new Node();
        node.value = value;
        node.next = head;

        if (head != null) {
            head.prev = node;
        }

        head = node;

        if (tail == null) {
            tail = node;
        }

        size += 1;
    }

    /**
     * Place a new node after the tail position of the linked list. if linked
     * list is empty, set head and tail to a same node.
     *
     * @param value: value that will be inserted.
     */
    void pushBack(int value) {
        Node node = new Node();
        node.value = value;
        node.prev = tail;

        if (tail != null) {
            tail.next = node;
        }

        tail = node;

        if (head == null) {
            head = node;
        }

        size += 1;
    }

    /**
     * Display all the value in the linked list by the order from head -> tail
     */
    void print() {

        Node tmp = head;
        if (tmp == null) {
            System.out.println("Linked list: " + "Empty");
        } else {
            System.out.print("Linked list: ");
            while (tmp != null) {
                System.out.print(tmp.value + " ");
                tmp = tmp.next;
            }
            System.out.println("");
        }
    }

    /**
     * @return number of nodes are currently stored in list
     */
    int size() {
        return this.size;
    }

    /**
     * @return value of head node if linked list is empty, return -1;
     */
    int front() {
        return (head == null) ? -1 : head.value;
    }

    /**
     * @return value of tail node if linked list is empty, return -1;
     */
    int back() {
        return (tail == null) ? -1 : tail.value;
    }

    /**
     * Remove head node out of the linked list
     *
     * @return removed node value, if linked list is empty, return -1
     */
    int popFront() {
        Node tmp = head;
        if (tmp == null) {
            return -1;
        }

        head = head.next;

        /**
         * if head == null --> there are no node stored in linked list --> set
         * tail node to null to completely empty linked list
         */
        if (head != null) {
            head.prev = null;
        } else {
            tail = null;
        }

        size -= 1;
        return tmp.value;
    }

    /**
     * Remove tail node out of the linked list
     *
     * @return removed node value, if linked list is empty, return -1
     */
    int popBack() {
        if (tail == null) {
            return -1;
        }

        Node tmp = tail;
        tail = tail.prev;
        /**
         * if tail == null --> there are no node stored in linked list --> set
         * head node to null to completely empty linked list
         */
        if (tail != null) {
            tail.next = null;
        } else {
            head = null;
        }

        size -= 1;
        return tmp.value;
    }

    /**
     * Determine if linked list is empty
     *
     * @return
     */
    boolean isEmpty() {
        return (size() == 0);
    }

    final int FROM_HEAD = 1;
    final int FROM_TAIL = 0;

    /**
     * Get traversal strategy to look forward a node with given index
     *
     * @param index
     * @return 1: From head -> & 0: From tail
     */
    int getTraversalStrategy(int index) {
        return (index > size() / 2) ? FROM_TAIL : FROM_HEAD;
    }

    /**
     * Get value of the node with given index
     *
     * @param index: position of node counting from head node.
     * @return : value of found node, if linked list is empty or index is larger
     * than linked list size, return -1
     */
    int valueAt(int index) {
        if (isEmpty() || index < 0 || index >= size()) {
            return -1;
        }

        int strategy = getTraversalStrategy(index);
        if (strategy == FROM_HEAD) {
            Node tmp = head;
            while (index > 0) {
                tmp = tmp.next;
                index -= 1;
            }
            return tmp.value;
        } else {
            index = size() - index - 1;
            Node tmp = tail;
            while (index > 0) {
                tmp = tmp.prev;
                index -= 1;
            }
            return tmp.value;
        }

    }

    /**
     * Remove a node with given index
     *
     * @param index: position of node counting from head node.
     * @return : value of found node, if linked list is empty or index is larger
     * than linked list size, return -1
     */
    int removeAt(int index) {
        if (isEmpty() || index < 0 || index >= size()) {
            return -1;
        }

        if (index == 0) {
            return popFront();
        }
        if (index == size() - 1) {
            return popBack();
        }

        int strategy = getTraversalStrategy(index);
        Node tmp = null;

        if (strategy == FROM_HEAD) {
            tmp = head;

            // Tranversal to remove position
            while (index > 0) {
                tmp = tmp.next;
                index -= 1;
            }

        } else {
            index = size() - index - 1;
            tmp = tail;

            // Tranversal to remove position
            while (index > 0) {
                tmp = tmp.prev;
                index -= 1;
            }

        }

        Node prev = tmp.prev;
        Node next = tmp.next;

        prev.next = next;
        next.prev = prev;
        size -= 1;
        return tmp.value;
    }

    /**
     * insert a node into the given position
     *
     * @param index: position of node counting from head node.
     */
    boolean insert(int value, int index) {
        if (index < 0 || index >= size()) {
            return false;
        }

        if (index == 0) {
            pushFront(value);
        }
        if (index == size() - 1) {
            pushBack(value);
        }

        // Find prev node and next node of new node.
        Node nodePrev = null;
        Node nodeNext = null;
        int strategy = getTraversalStrategy(index);
        if (strategy == FROM_HEAD) {
            nodePrev = head;
            
            // Tranversal to insert position
            while (index > 0) {
                nodePrev = nodePrev.next;
                index -= 1;
            }
            nodeNext = nodePrev.next;
        } else {
            nodeNext = tail;
            index = size() - index - 1;
            
            // Tranversal to insert position
            while (index > 0) {
                nodeNext = nodeNext.prev;
                index -= 1;
            }
            nodePrev = nodeNext.prev;
        }

        Node node = new Node();
        node.value = value;
        node.next = nodeNext;
        node.prev = nodePrev;
        
        nodeNext.prev = node;
        nodePrev.next = node;
        
        size += 1;
        return true;
    }
}
