package com.github.vccolombo;

public class DynamicArray {
    private int capacity;
    private final int minCapacity;
    private int size;
    private int[] array;

    public DynamicArray(int capacity) {
        // capacity must be a power of two
        int power = (int) Math.ceil(Math.log(capacity) / Math.log(2));
        capacity = Math.max(capacity, (int) Math.pow(2, power));

        this.capacity = capacity;
        this.minCapacity = capacity; // the user decided the initial capacity so he might not want it to go below that
        this.size = 0;
        this.array = new int[this.capacity];
    }

    public DynamicArray() {
        this(16);
    }

    public int size() {
        return this.size;
    }

    public int capacity() {
        return this.capacity;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    public int get(int index) {
        if (index < 0 || index >= this.size) throw new IndexOutOfBoundsException(String.format("Index %d out of range of array of size %d", index, this.size));

        return this.array[index];
    }

    public int at(int index) {
        return this.get(index);
    }

    public void push(int value) {
        if (this.size == this.capacity) doubleCapacity();

        this.array[size] = value;
        this.size++;
    }

    public void insert(int index, int value) {
        if (index < 0 || index > this.size) throw new IndexOutOfBoundsException(String.format("Index %d out of range of array of size %d", index, this.size));

        // a better approach would be to insert while doubling instead of doubling then inserting
        if (this.size == this.capacity) doubleCapacity();

        this.size++;

        int previous = this.array[index];
        int tmp;
        for (int i = index + 1; i < this.size; i++) {
            tmp = this.array[i];
            this.array[i] = previous;
            previous = tmp;
        }

        this.array[index] = value;
    }

    public void prepend(int value) {
        insert(0, value);
    }

    public int pop() {
        if (this.size == 0) throw new IndexOutOfBoundsException("Cannot pop from empty array");

        int popped = this.array[this.size - 1];

        this.size--;

        if (this.capacityMustBeHalved()) this.halveCapacity();

        return popped;
    }

    public void delete(int index) {
        if (index < 0 || index >= this.size) throw new IndexOutOfBoundsException(String.format("Index %d out of range of array of size %d", index, this.size));

        if (this.size - 1 - index >= 0) {
            System.arraycopy(this.array, index + 1, this.array, index, this.size - 1 - index);
        }

        if (this.capacityMustBeHalved()) this.halveCapacity();

        this.size--;
    }

    public int remove(int value) {
        int valuesRemoved = 0;
        // starting from the end fixes the problem of O(n^2) when value is at every index of the array
        for (int i = this.size - 1; i >= 0; i--) {
            if (value == this.array[i]) {
                this.delete(i);
                valuesRemoved++;
            }
        }

        return valuesRemoved;
    }

    public int find(int value) {
        for (int i = 0; i < this.size; i++) {
            if (this.array[i] == value) return i;
        }

        return -1;
    }

    private void resize(int newCapacity) {
        int[] newArray = new int[newCapacity];

        // copy old array to new one
        for (int i = 0; i < this.capacity && i < newCapacity; i++) {
            newArray[i] = this.array[i];
        }

        this.array = newArray;
        this.capacity = newCapacity;
    }

    private void doubleCapacity() {
        resize(this.capacity * 2);
    }

    private boolean capacityMustBeHalved() {
        return this.size < this.capacity / 4;
    }
    private void halveCapacity() {
        int newCapacity = this.capacity / 2;
        if (newCapacity != this.capacity && newCapacity >= this.minCapacity)
            resize(newCapacity);
    }
}
