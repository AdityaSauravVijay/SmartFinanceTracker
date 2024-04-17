package com.smartfinancetracker.models;

public class CategoryList {

    public class Node {
        Category category;
        Node next;

        public Node(Category category) {
            this.category = category;
            this.next = null;
        }
    }

    private Node head;
    private int size;
    public CategoryList() {
        this.head = null;
        this.size = 0;
    }

    // Add a transaction to the end of the list
    public void add(Category category) {
        Node newNode = new Node(category);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
        size++;
    }

    // Remove a transaction by transactionId
    public boolean remove(int categoryId) {
        if (head == null) {
            return false;
        }
        if (head.category.getCategoryId() == categoryId) {
            head = head.next;
            size--;
            return true;
        }

        Node current = head;
        while (current.next != null) {
            if (current.next.category.getCategoryId() == categoryId) {
                current.next = current.next.next;
                size--;
                return true;
            }
            current = current.next;
        }
        return false;
    }

    // Get transaction by index
    public Category get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index out of range");
        }
        Node current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.category;
    }

    // Get the size of the list
    public int getSize() {
        return size;
    }

    public void printAll() {
        Node current = head;
        while (current != null) {
            System.out.println("Category ID: " + current.category.getCategoryId() +
                    ", Name: " + current.category.getCategoryName() +
                    ", User ID: " + current.category.getUserId() +
                    ", Budget: " + current.category.getBudget());
            current = current.next;
        }
    }
}
