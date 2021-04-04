# Automatically resizing vector (dynamic array) implementations

## Languages

- [x] Java
- [ ] C

## Operations

- size() - number of items
- capacity() - number of items it can hold
- is_empty()
- at(index) - returns item at given index, blows up if index out of bounds
- push(item)
- insert(index, item) - inserts item at index, shifts that index's value and trailing elements to the right
- prepend(item) - can use insert above at index 0
- pop() - remove from end, return value
- delete(index) - delete item at index, shifting all trailing elements left
- remove(item) - looks for value and removes index holding it (even if in multiple places)
- find(item) - looks for value and returns first index with that value, -1 if not found
- clear()
- resize(new_capacity) // private function
  - when you reach capacity, resize to double the size
  - when popping an item, if size is 1/4 of capacity, resize to half

### Time

- O(1) to add/remove at end (amortized for allocations for more space), index, or update
- O(n) to insert/remove elsewhere

### Space

- contiguous in memory, so proximity helps performance
- space needed = (array capacity, which is >= n) \* size of item, but even if 2n, still O(n)
