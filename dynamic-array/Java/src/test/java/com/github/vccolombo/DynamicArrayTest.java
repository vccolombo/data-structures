package com.github.vccolombo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DynamicArrayTest {
    @Test
    void TestEmptySizeAfterCreation() {
        DynamicArray dynamicArray = new DynamicArray();

        assertEquals(0, dynamicArray.size());
    }

    @Test
    void TestCapacityAfterCreationWithNoCapacity() {
        DynamicArray dynamicArray = new DynamicArray();

        assertEquals(16, dynamicArray.capacity());
    }

    @Test
    void TestCapacityAfterCreationWithSpecifiedCapacity() {
        DynamicArray dynamicArray = new DynamicArray(64);

        assertEquals(64, dynamicArray.capacity());
    }

    @Test
    void TestCapacityMustBePowerOf2_1() {
        DynamicArray dynamicArray = new DynamicArray(3);

        assertEquals(4, dynamicArray.capacity());
    }

    @Test
    void TestCapacityMustBePowerOf2_2() {
        DynamicArray dynamicArray = new DynamicArray(5);

        assertEquals(8, dynamicArray.capacity());
    }

    @Test
    void TestCapacityMustBePowerOf2_3() {
        DynamicArray dynamicArray = new DynamicArray(42);

        assertEquals(64, dynamicArray.capacity());
    }

    @Test
    void TestCapacityMustBePowerOf2_4() {
        DynamicArray dynamicArray = new DynamicArray(1234);

        assertEquals(2048, dynamicArray.capacity());
    }

    @Test
    void TestPushFirstItem() {
        DynamicArray dynamicArray = new DynamicArray();

        dynamicArray.push(42);
        assertEquals(42, dynamicArray.get(0));
    }

    @Test
    void TestPushShouldIncreaseSizeBy1() {
        DynamicArray dynamicArray = new DynamicArray();
        int before = dynamicArray.size();
        dynamicArray.push(42);
        int after = dynamicArray.size();

        assertEquals(1, after-before);
    }

    @Test
    void TestPushMultipleItems() {
        DynamicArray dynamicArray = new DynamicArray();

        dynamicArray.push(1);
        dynamicArray.push(3);
        dynamicArray.push(4);
        dynamicArray.push(2);

        assertEquals(1, dynamicArray.get(0));
        assertEquals(3, dynamicArray.get(1));
        assertEquals(4, dynamicArray.get(2));
        assertEquals(2, dynamicArray.get(3));
    }

    @Test
    void TestPushBeyondCapacityShouldReturnCorrectValue() {
        DynamicArray dynamicArray = new DynamicArray(4);

        dynamicArray.push(1);
        dynamicArray.push(2);
        dynamicArray.push(3);
        dynamicArray.push(4);
        dynamicArray.push(5);

        assertEquals(5, dynamicArray.get(4));
    }

    @Test
    void TestPushBeyondCapacityShouldDoubleTheCapacity() {
        DynamicArray dynamicArray = new DynamicArray(4);

        dynamicArray.push(1);
        dynamicArray.push(2);
        dynamicArray.push(3);
        dynamicArray.push(4);
        dynamicArray.push(5);

        assertEquals(8, dynamicArray.capacity());
    }

    @Test
    void TestPushBeyondCapacityShouldAddOneToSize() {
        DynamicArray dynamicArray = new DynamicArray(4);

        dynamicArray.push(1);
        dynamicArray.push(2);
        dynamicArray.push(3);
        dynamicArray.push(4);
        dynamicArray.push(5);

        assertEquals(5, dynamicArray.size());
    }

    @Test
    void TestGetAboveSizeShouldThrowOutOfBoundsException() {
        DynamicArray dynamicArray = new DynamicArray();

        dynamicArray.push(1);

        Exception thrown = assertThrows(IndexOutOfBoundsException.class, () -> dynamicArray.get(2));

        assertTrue(thrown.getMessage().contains("Index 2 out of range of array of size 1"));
    }

    @Test
    void TestGetNegativeIndexShouldThrowOutOfBoundsException() {
        DynamicArray dynamicArray = new DynamicArray();

        dynamicArray.push(1);

        Exception thrown = assertThrows(IndexOutOfBoundsException.class, () -> dynamicArray.get(-1));

        assertTrue(thrown.getMessage().contains("Index -1 out of range of array of size 1"));
    }

    @Test
    void TestIsEmptyShouldReturnTrueWhenJustCreated() {
        DynamicArray dynamicArray = new DynamicArray();
        assertTrue(dynamicArray.isEmpty());
    }

    @Test
    void TestIsEmptyShouldReturnFalseWhenOneElementIsInArray() {
        DynamicArray dynamicArray = new DynamicArray();

        dynamicArray.push(1);

        assertFalse(dynamicArray.isEmpty());
    }

    @Test
    void TestAtReturnsItemAtIndex() {
        DynamicArray dynamicArray = new DynamicArray();

        dynamicArray.push(13);
        dynamicArray.push(42);
        dynamicArray.push(18);
        dynamicArray.push(1234);
        dynamicArray.push(1998);

        assertEquals(1234, dynamicArray.at(3));
    }

    @Test
    void TestAtInvalidIndexThrowsOutOfBounds() {
        DynamicArray dynamicArray = new DynamicArray();

        dynamicArray.push(13);
        dynamicArray.push(42);

        Exception thrown = assertThrows(IndexOutOfBoundsException.class, () -> dynamicArray.at(3));

        assertTrue(thrown.getMessage().contains("Index 3 out of range of array of size 2"));
    }

    @Test
    void TestAtNegativeIndexThrowsOutOfBounds() {
        DynamicArray dynamicArray = new DynamicArray();

        dynamicArray.push(13);
        dynamicArray.push(42);
        dynamicArray.push(42);

        Exception thrown = assertThrows(IndexOutOfBoundsException.class, () -> dynamicArray.at(-42));

        assertTrue(thrown.getMessage().contains("Index -42 out of range of array of size 3"));
    }

    @Test
    void TestInsertInTheMiddleInsertsAtCorrectIndex() {
        DynamicArray dynamicArray = new DynamicArray();

        dynamicArray.push(18);
        dynamicArray.push(18);
        dynamicArray.push(18);
        dynamicArray.push(18);

        dynamicArray.insert(2, 42);

        assertEquals(42, dynamicArray.get(2));
    }

    @Test
    void TestInsertMoveNextValuesOneUp() {
        DynamicArray dynamicArray = new DynamicArray();

        dynamicArray.push(1);
        dynamicArray.push(2);
        dynamicArray.push(3);
        dynamicArray.push(4);

        dynamicArray.insert(1, 42);

        assertEquals(2, dynamicArray.get(2));
        assertEquals(3, dynamicArray.get(3));
        assertEquals(4, dynamicArray.get(4));
    }

    @Test
    void TestInsertAtBegin() {
        DynamicArray dynamicArray = new DynamicArray();

        dynamicArray.push(1);
        dynamicArray.push(2);
        dynamicArray.push(3);
        dynamicArray.push(4);

        dynamicArray.insert(0, 42);

        assertEquals(42, dynamicArray.get(0));
        assertEquals(1, dynamicArray.get(1));
        assertEquals(2, dynamicArray.get(2));
        assertEquals(3, dynamicArray.get(3));
        assertEquals(4, dynamicArray.get(4));
    }

    @Test
    void TestInsertAtSizePlusOne() {
        DynamicArray dynamicArray = new DynamicArray();

        dynamicArray.push(1);
        dynamicArray.push(2);
        dynamicArray.push(3);
        dynamicArray.push(4);

        dynamicArray.insert(4, 42);

        assertEquals(1, dynamicArray.get(0));
        assertEquals(2, dynamicArray.get(1));
        assertEquals(3, dynamicArray.get(2));
        assertEquals(4, dynamicArray.get(3));
        assertEquals(42, dynamicArray.get(4));
    }

    @Test
    void TestInsertAtEndMoveLastElementCorrectly() {
        DynamicArray dynamicArray = new DynamicArray(4);

        dynamicArray.push(0);
        dynamicArray.push(1);
        dynamicArray.push(2);
        dynamicArray.push(3);

        dynamicArray.insert(3, 42);

        assertEquals(3, dynamicArray.get(4));
        assertEquals(42, dynamicArray.get(3));
        assertEquals(8, dynamicArray.capacity());
        assertEquals(5, dynamicArray.size());
    }

    @Test
    void TestInsertOutOfBounds() {
        DynamicArray dynamicArray = new DynamicArray();

        dynamicArray.push(13);
        dynamicArray.push(42);
        dynamicArray.push(42);

        Exception thrown = assertThrows(IndexOutOfBoundsException.class, () -> dynamicArray.insert(-42, 123));

        assertTrue(thrown.getMessage().contains("Index -42 out of range of array of size 3"));
    }

    @Test
    void TestInsertInTheMiddleIncreaseSizeCorrectly() {
        DynamicArray dynamicArray = new DynamicArray();

        dynamicArray.push(1);
        dynamicArray.push(2);
        dynamicArray.push(3);
        dynamicArray.push(4);

        dynamicArray.insert(2, 42);

        assertEquals(5, dynamicArray.size());
    }

    @Test
    void TestInsertInTheMiddleIncreaseCapacityCorrectly() {
        DynamicArray dynamicArray = new DynamicArray(4);

        dynamicArray.push(1);
        dynamicArray.push(2);
        dynamicArray.push(3);
        dynamicArray.push(4);

        dynamicArray.insert(2, 42);

        assertEquals(8, dynamicArray.capacity());
    }

    @Test
    void TestInsertInTheEndIncreaseSizeCorrectly() {
        DynamicArray dynamicArray = new DynamicArray(4);

        dynamicArray.push(0);
        dynamicArray.push(1);
        dynamicArray.push(2);
        dynamicArray.push(3);

        dynamicArray.insert(3, 42);

        assertEquals(5, dynamicArray.size());
    }

    @Test
    void TestInsertInTheEndIncreaseCapacityCorrectly() {
        DynamicArray dynamicArray = new DynamicArray(4);

        dynamicArray.push(0);
        dynamicArray.push(1);
        dynamicArray.push(2);
        dynamicArray.push(3);

        dynamicArray.insert(3, 42);

        assertEquals(8, dynamicArray.capacity());
    }

    @Test
    void TestPrependInsertAtCorrectIndex() {
        DynamicArray dynamicArray = new DynamicArray();

        dynamicArray.push(1);
        dynamicArray.push(2);
        dynamicArray.push(3);
        dynamicArray.push(4);

        dynamicArray.prepend(42);

        assertEquals(42, dynamicArray.get(0));
    }

    @Test
    void TestPrependMoveValuesUp() {
        DynamicArray dynamicArray = new DynamicArray();

        dynamicArray.push(1);
        dynamicArray.push(2);
        dynamicArray.push(3);
        dynamicArray.push(4);

        dynamicArray.prepend(42);

        assertEquals(1, dynamicArray.get(1));
        assertEquals(2, dynamicArray.get(2));
        assertEquals(3, dynamicArray.get(3));
        assertEquals(4, dynamicArray.get(4));
    }

    @Test
    void TestFindElementExistsInTheMiddle() {

        DynamicArray dynamicArray = new DynamicArray();

        dynamicArray.push(42);
        dynamicArray.push(18);
        dynamicArray.push(1337);
        dynamicArray.push(1234);

        assertEquals(2, dynamicArray.find(1337));
    }

    @Test
    void TestFindElementExistsInTheBegin() {

        DynamicArray dynamicArray = new DynamicArray();

        dynamicArray.push(42);
        dynamicArray.push(18);
        dynamicArray.push(1337);
        dynamicArray.push(1234);

        assertEquals(0, dynamicArray.find(42));
    }

    @Test
    void TestFindElementExistsInTheEnd() {

        DynamicArray dynamicArray = new DynamicArray();

        dynamicArray.push(42);
        dynamicArray.push(18);
        dynamicArray.push(1337);
        dynamicArray.push(1234);

        assertEquals(3, dynamicArray.find(1234));
    }

    @Test
    void TestFindElementDoesNotExist() {

        DynamicArray dynamicArray = new DynamicArray();

        dynamicArray.push(42);
        dynamicArray.push(18);
        dynamicArray.push(1337);
        dynamicArray.push(1234);

        assertEquals(-1, dynamicArray.find(4321));
    }

    @Test
    void TestPopReturnCorrectValue() {
        DynamicArray dynamicArray = new DynamicArray();

        dynamicArray.push(42);
        dynamicArray.push(18);
        dynamicArray.push(1337);
        dynamicArray.push(1234);

        assertEquals(1234, dynamicArray.pop());
    }

    @Test
    void TestPopReduceSizeBy1() {
        DynamicArray dynamicArray = new DynamicArray();

        dynamicArray.push(42);
        dynamicArray.push(18);
        dynamicArray.push(1337);
        dynamicArray.push(1234);

        dynamicArray.pop();

        assertEquals(3, dynamicArray.size());
    }

    @Test
    void TestPopFromEmptyArrayThrowsError() {
        DynamicArray dynamicArray = new DynamicArray();

        Exception thrown = assertThrows(IndexOutOfBoundsException.class, dynamicArray::pop);

        assertTrue(thrown.getMessage().contains("Cannot pop from empty array"));
    }

    @Test
    void TestDeleteElementAtIndexInTheMiddleReallyDeletesIt() {
        DynamicArray dynamicArray = new DynamicArray();

        dynamicArray.push(1);
        dynamicArray.push(2);
        dynamicArray.push(3);
        dynamicArray.push(4);

        dynamicArray.delete(2);

        assertNotEquals(3, dynamicArray.at(2));
    }

    @Test
    void TestDeleteElementAtIndexInTheMiddleCorrectlyMovesOtherElementsOneBehind() {
        DynamicArray dynamicArray = new DynamicArray();

        dynamicArray.push(1);
        dynamicArray.push(2);
        dynamicArray.push(3);
        dynamicArray.push(4);

        dynamicArray.delete(1);

        assertEquals(3, dynamicArray.at(1));
        assertEquals(4, dynamicArray.at(2));
    }

    @Test
    void TestDeleteElementReduceSizeBy1() {
        DynamicArray dynamicArray = new DynamicArray();

        dynamicArray.push(1);
        dynamicArray.push(2);
        dynamicArray.push(3);
        dynamicArray.push(4);

        dynamicArray.delete(1);

        assertEquals(3, dynamicArray.size());
    }

    @Test
    void TestDeleteLastIndexDoesNotBreakEverything() {
        DynamicArray dynamicArray = new DynamicArray();

        dynamicArray.push(1);
        dynamicArray.push(2);
        dynamicArray.push(3);
        dynamicArray.push(4);

        dynamicArray.delete(3);

        assertEquals(1, dynamicArray.at(0));
        assertEquals(2, dynamicArray.at(1));
        assertEquals(3, dynamicArray.at(2));
    }

    @Test
    void TestDeleteNegativeIndexThrowsError() {
        DynamicArray dynamicArray = new DynamicArray();

        dynamicArray.push(1);
        dynamicArray.push(2);
        dynamicArray.push(3);

        Exception thrown = assertThrows(IndexOutOfBoundsException.class, () -> dynamicArray.delete(-42));

        assertTrue(thrown.getMessage().contains("Index -42 out of range of array of size 3"));
    }

    @Test
    void TestDeleteBadIndexThrowsError() {
        DynamicArray dynamicArray = new DynamicArray();

        dynamicArray.push(1);
        dynamicArray.push(2);
        dynamicArray.push(3);

        Exception thrown = assertThrows(IndexOutOfBoundsException.class, () -> dynamicArray.delete(3));

        assertTrue(thrown.getMessage().contains("Index 3 out of range of array of size 3"));
    }

    @Test
    void TestDeleteBadIndexDoesNotChangeCapacity() {
        DynamicArray dynamicArray = new DynamicArray(8);

        dynamicArray.push(1);
        dynamicArray.push(2);
        dynamicArray.push(3);
        dynamicArray.push(4);

        Exception thrown = assertThrows(IndexOutOfBoundsException.class, () -> dynamicArray.delete(-42));

        assertEquals(8, dynamicArray.capacity());
    }

    @Test
    void TestRemoveValueThatAppearsOnlyOnceRemoveIt() {
        DynamicArray dynamicArray = new DynamicArray();

        dynamicArray.push(1);
        dynamicArray.push(2);
        dynamicArray.push(3);
        dynamicArray.push(4);

        dynamicArray.remove(2);

        assertEquals(1, dynamicArray.at(0));
        assertEquals(3, dynamicArray.at(1));
        assertEquals(4, dynamicArray.at(2));
    }

    @Test
    void TestRemoveValueThatAppearsOnlyOnceMoveEveryoneCorrectly() {
        DynamicArray dynamicArray = new DynamicArray();

        dynamicArray.push(1);
        dynamicArray.push(2);
        dynamicArray.push(3);
        dynamicArray.push(4);

        dynamicArray.remove(2);

        assertEquals(3, dynamicArray.at(1));
        assertEquals(4, dynamicArray.at(2));
    }

    @Test
    void TestRemoveValueThatAppearsMoreThanOnceMoveEveryoneCorrectly() {
        DynamicArray dynamicArray = new DynamicArray();

        dynamicArray.push(1);
        dynamicArray.push(2);
        dynamicArray.push(3);
        dynamicArray.push(4);
        dynamicArray.push(1);
        dynamicArray.push(2);
        dynamicArray.push(3);
        dynamicArray.push(4);

        dynamicArray.remove(2);

        assertEquals(1, dynamicArray.at(0));
        assertEquals(3, dynamicArray.at(1));
        assertEquals(4, dynamicArray.at(2));
        assertEquals(1, dynamicArray.at(3));
        assertEquals(3, dynamicArray.at(4));
        assertEquals(4, dynamicArray.at(5));
    }

    @Test
    void TestRemoveValueThatDoesNotExistDoesNothingToTheArray() {
        DynamicArray dynamicArray = new DynamicArray();

        dynamicArray.push(1);
        dynamicArray.push(2);
        dynamicArray.push(3);
        dynamicArray.push(4);

        dynamicArray.remove(-1);

        assertEquals(1, dynamicArray.at(0));
        assertEquals(2, dynamicArray.at(1));
        assertEquals(3, dynamicArray.at(2));
        assertEquals(4, dynamicArray.at(3));
    }

    @Test
    void TestRemoveValueThatDoesNotExistDoesNotChangeItSize() {
        DynamicArray dynamicArray = new DynamicArray();

        dynamicArray.push(1);
        dynamicArray.push(2);
        dynamicArray.push(3);
        dynamicArray.push(4);

        dynamicArray.remove(-1);

        assertEquals(4, dynamicArray.size());
    }

    @Test
    void TestRemoveValueThatDoesNotExistDoesNotChangeItCapacity() {
        DynamicArray dynamicArray = new DynamicArray(8);

        dynamicArray.push(1);
        dynamicArray.push(2);
        dynamicArray.push(3);
        dynamicArray.push(4);

        dynamicArray.remove(-1);

        assertEquals(8, dynamicArray.capacity());
    }

    @Test
    void TestRemoveReturnsNumberOfValuesRemoved() {
        DynamicArray dynamicArray = new DynamicArray(8);

        dynamicArray.push(1);
        dynamicArray.push(2);
        dynamicArray.push(3);
        dynamicArray.push(4);
        dynamicArray.push(1);
        dynamicArray.push(2);
        dynamicArray.push(3);
        dynamicArray.push(4);

        assertEquals(2, dynamicArray.remove(4));
    }

    @Test
    void TestPopBelowOneFourthOfCapacityChangesIt_1() {
        DynamicArray dynamicArray = new DynamicArray(16);

        for (int i = 0; i < 64; i++) {
            dynamicArray.push(42);
        }

        for (int i = 0; i < 50; i++) {
            dynamicArray.pop();
        }

        assertEquals(32, dynamicArray.capacity());
    }

    @Test
    void TestPopBelowOneFourthOfCapacityChangesIt_2() {
        DynamicArray dynamicArray = new DynamicArray(11);

        for (int i = 0; i < 128; i++) {
            dynamicArray.push(42);
        }

        for (int i = 0; i < 127; i++) {
            dynamicArray.pop();
        }

        assertEquals(16, dynamicArray.capacity());
    }

    @Test
    void TestDeleteBelowOneFourthOfCapacityChangesIt_1() {
        DynamicArray dynamicArray = new DynamicArray(16);

        for (int i = 0; i < 64; i++) {
            dynamicArray.push(42);
        }

        for (int i = 0; i < 50; i++) {
            dynamicArray.delete(dynamicArray.size() - 1);
        }

        assertEquals(32, dynamicArray.capacity());
    }

    @Test
    void TestDeleteBelowOneFourthOfCapacityChangesIt_2() {
        DynamicArray dynamicArray = new DynamicArray(11);

        for (int i = 0; i < 128; i++) {
            dynamicArray.push(42);
        }

        for (int i = 0; i < 127; i++) {
            dynamicArray.delete(dynamicArray.size() - 1);
        }

        assertEquals(16, dynamicArray.capacity());
    }

    @Test
    void TestRemoveBelowOneFourthOfCapacityChangesIt_1() {
        DynamicArray dynamicArray = new DynamicArray(16);

        for (int i = 0; i < 64; i++) {
            dynamicArray.push(i);
        }

        for (int i = 0; i < 50; i++) {
            dynamicArray.remove(i);
        }

        assertEquals(32, dynamicArray.capacity());
    }

    @Test
    void TestRemoveBelowOneFourthOfCapacityChangesIt_2() {
        DynamicArray dynamicArray = new DynamicArray(11);

        for (int i = 0; i < 128; i++) {
            dynamicArray.push(i);
        }

        for (int i = 0; i < 127; i++) {
            dynamicArray.remove(i);
        }

        assertEquals(16, dynamicArray.capacity());
    }
}