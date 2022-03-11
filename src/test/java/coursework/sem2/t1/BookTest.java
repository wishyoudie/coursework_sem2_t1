/* FILE NAME: BookTest.java
 * PROGRAMMER: Ilyin Vladimir, group 3530901/10005, GitHub: @wishyoudie
 * DATE: 03/11/2022
 * PURPOSE: Implementation of Address book class.
 *          Test module.
 */

package coursework.sem2.t1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;


/**
 * Address book test class
 */
class BookTest {

    @Test
    void add() {
        Book book = new Book();
        Address address = new Address("a", 1, 1);

        assertEquals(0, book.size()); // Check that initial size is 0
        assertTrue(book.add("A", address)); // Returns true if added successfully
        assertEquals(1, book.size()); // Now size is 1
        assertFalse(book.add("A", address)); // Returns false if already contains the same entry

        Address secondAddress = new Address("a", 1 ,2);
        assertFalse(book.add("A", secondAddress)); // Returns false if already contains the same key
    }

    @Test
    void constructorFromMap() {
        Book expectedBook = new Book();
        Address address = new Address("a", 1, 1);
        expectedBook.add("A", address);

        Map<String, Address> testMap = new HashMap<>();
        testMap.put("A", address);
        Book testBook = new Book(testMap); // 'testBook' is made from 'testMap'

        assertEquals(expectedBook, testBook); // True if constructor from map works
        assertThrows(NullPointerException.class, () -> new Book(null)); // Check map nullability
    }

    @Test
    void delete() {
        Book book = new Book();
        assertFalse(book.delete("B")); // Returns false if it doesn't contain the specified key

        Address address = new Address("a", 1, 1);
        book.add("A", address);

        assertTrue(book.delete("A")); // Returns true if deleted successfully
        assertEquals(0, book.size()); // If really did delete, size should be 0
        assertFalse(book.delete("A")); // Should return false, "A" already deleted
    }

    @Test
    void replace() {
        Book book = new Book();
        Address address = new Address("a", 1, 1);

        assertFalse(book.replace("A", address)); // Returns false if it doesn't contain the specified key

        Address secondAddress = new Address("a", 1, 2);
        book.add("A", address);

        assertTrue(book.replace("A", secondAddress)); // Returns true if replaced successfully
        assertEquals(book.getAddress("A"), secondAddress); // Check address change
        assertThrows(NullPointerException.class, () -> book.replace(null, address)); // Check person nullability
    }

    @Test
    void getAddress() {
        Book book = new Book();
        Address address = new Address("a", 1, 1);
        book.add("A", address);

        assertEquals(address, book.getAddress("A")); // Should be equal
        assertNull(book.getAddress("B")); // Returns null if it doesn't contain the specified key

        book.delete("A");
        assertNull(book.getAddress("A")); // Doesn't contain "A" anymore
        assertThrows(NullPointerException.class, () -> book.getAddress(null)); // Check person nullability
    }

    @Test
    void getPeopleOnlyByStreet() {
        Book book = new Book();
        Address address = new Address("a", 1, 1);
        book.add("A", address);
        book.add("B", address);
        book.add("C", address);

        Set<String> testSet = new HashSet<>();
        testSet.add("A");
        testSet.add("B");
        testSet.add("C");

        assertEquals(testSet, book.getPeople("a")); // Should be equal

        book.delete("C");
        testSet.remove("C");

        assertEquals(testSet, book.getPeople("a")); // Should still be equal

        testSet.add("D");
        assertNotEquals(testSet, book.getPeople("a")); // Now it shouldn't
        assertEquals(new HashSet<String>(), book.getPeople("b")); // Should return empty set: no people on street "b"
    }

    @Test
    void getPeopleByStreetAndHouse() {
        Book book = new Book();

        Address address = new Address("a", 1, 1);
        book.add("A", address);

        Address addressWithDifferentFlat = new Address("a", 1, 2);
        book.add("B", addressWithDifferentFlat);

        Address addressWithDifferentHouse = new Address("a", 2, 1);
        book.add("C", addressWithDifferentHouse);

        Set<String> sameHouseSet = new HashSet<>();
        sameHouseSet.add("A");
        sameHouseSet.add("B");
        assertEquals(sameHouseSet, book.getPeople("a", 1)); // Should be equal (same house)

        Set<String> differentHouseSet = new HashSet<>();
        differentHouseSet.add("C");

        assertEquals(differentHouseSet, book.getPeople("a", 2)); // Only "C" in house #2

        sameHouseSet.addAll(differentHouseSet);
        assertEquals(sameHouseSet, book.getPeople("a")); // All on same street

        assertEquals(new HashSet<>(), book.getPeople("a", 3)); // None in this house
    }

    @Test
    void size() {
        Book book = new Book();
        Address address = new Address("a", 1, 1);

        assertEquals(0, book.size()); // Initial size

        book.add("a", address);
        assertEquals(1, book.size()); // Now 1

        book.add("a", address);
        assertEquals(1, book.size()); // Still 1: already contains "a"

        book.add("b", address);
        assertEquals(2, book.size()); // 2 people now
    }

    @Test
    void testEquals() {
        Book firstBook = new Book();
        Book secondBook = new Book();
        Address firstAddress = new Address("a", 1, 1);
        Address secondAddress = new Address("a", 1, 2);

        firstBook.add("a", firstAddress);
        secondBook.add("a", firstAddress);

        assertEquals(firstBook, secondBook); // Should be equal

        secondBook.add("b", secondAddress);
        assertNotEquals(firstBook, secondBook); // Now secondBook has different set of entries

        assertNotEquals(firstBook, null); // Check nullability
    }
}