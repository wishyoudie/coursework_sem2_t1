/* FILE NAME: BookTest.java
 * PROGRAMMER: Ilyin Vladimir, group 3530901/10005, GitHub: @wishyoudie
 * DATE: 02/23/2022
 * PURPOSE: Implementation of Address book class.
 *          Test module.
 */

/* Package */
package coursework_sem2_t1;

/* Imports */
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.HashSet;


/*
 * Address book test class
 */
class BookTest {
    @Test
    public void addressStreet() {
        String st = "a";
        Book.Address ad = new Book.Address("a", 1, 1);
        assertEquals(st, ad.getStreet());
    }

    @Test
    public void badAddressStreet() {
        assertThrows(IllegalArgumentException.class, () -> {
            Book.Address bad = new Book.Address(null, 1, 1);
        });
    }

    @Test
    public void addressHouse() {
        int h = 1;
        Book.Address ad = new Book.Address("a", 1, 1);
        assertEquals(h, ad.getHouse());
    }

    @Test
    public void badAddressHouse() {
        assertThrows(IllegalArgumentException.class, () -> {
            Book.Address bad = new Book.Address("null", -1, 1);
        });
    }

    @Test
    public void addressFlat() {
        int fl = 2;
        Book.Address ad = new Book.Address("a", 1, 2);
        assertEquals(fl, ad.getFlat());
    }

    @Test
    public void badAddressFlat() {
        assertThrows(IllegalArgumentException.class, () -> {
            Book.Address bad = new Book.Address("null", 1, -1);
        });
    }


    @Test
    void add() {
        Book a = new Book();
        Book.Address ad = new Book.Address("a", 1, 1);
        assertEquals(0, a.size());
        assertTrue(a.add("A", ad));
        assertEquals(1, a.size());
        assertFalse(a.add("A", ad));
        Book.Address adt = new Book.Address("a", 1 ,2);
        assertFalse(a.add("A", adt));
    }

    @Test
    void secondConstructor() {
        Book a = new Book();
        Book.Address ad = new Book.Address("a", 1, 1);
        a.add("A", ad);
        Map<String, Book.Address> bMap = new HashMap<>();
        bMap.put("A", ad);
        Book b = new Book(bMap);
        assertEquals(a, b);
    }

    @Test
    void delete() {
        Book a = new Book();
        assertFalse(a.delete("B"));
        Book.Address ad = new Book.Address("a", 1, 1);
        a.add("A", ad);
        assertTrue(a.delete("A"));
        assertEquals(0, a.size());
        assertFalse(a.delete("B"));
    }

    @Test
    void replace() {
        Book a = new Book();
        Book.Address ad = new Book.Address("a", 1, 1);
        assertFalse(a.replace("A", ad));
        Book.Address ab = new Book.Address("a", 1, 2);
        a.add("A", ad);
        assertTrue(a.replace("A", ab));
        assertEquals(a.getAddress("A"), ab);
        assertFalse(a.replace("B", ad));
    }

    @Test
    void getAddress() {
        Book a = new Book();
        Book.Address ad = new Book.Address("a", 1, 1);
        a.add("A", ad);
        assertEquals(ad, a.getAddress("A"));
        assertNull(a.getAddress("B"));
        a.delete("A");
        assertNull(a.getAddress("A"));
    }

    @Test
    void getPeople() {
        Book a = new Book();
        Book.Address ad = new Book.Address("a", 1, 1);
        a.add("A", ad);
        a.add("B", ad);
        a.add("C", ad);
        Set<String> GoodGuys = new HashSet<>(); // goodGuys!!
        GoodGuys.add("A");
        GoodGuys.add("B");
        GoodGuys.add("C");
        assertEquals(GoodGuys, a.getPeople("a"));
        a.delete("C");
        GoodGuys.remove("C");
        assertEquals(GoodGuys, a.getPeople("a"));
        GoodGuys.add("D");
        assertNotEquals(GoodGuys, a.getPeople("a"));
        assertEquals(new HashSet<String>(), a.getPeople("b"));
    }

    @Test
    void getPeopleStreetAndHouse() {
        Book a = new Book();
        Book.Address ad = new Book.Address("a", 1, 1);
        a.add("A", ad);
        Book.Address ab = new Book.Address("a", 1, 2);
        a.add("B", ab);
        Book.Address al = new Book.Address("a", 2, 1);
        a.add("C", al);
        Set<String> GoodGuys = new HashSet<>();
        GoodGuys.add("A");
        GoodGuys.add("B");
        assertEquals(GoodGuys, a.getPeople("a", 1));
        Set<String> BadGuys = new HashSet<>();
        BadGuys.add("C");
        assertEquals(BadGuys, a.getPeople("a", 2));
        GoodGuys.addAll(BadGuys);
        assertEquals(GoodGuys, a.getPeople("a"));
        assertEquals(new HashSet<>(), a.getPeople("a", 3));
    }

    @Test
    void size() {
        Book a = new Book();
        Book.Address ad = new Book.Address("a", 1, 1);
        assertEquals(0, a.size());
        a.add("a", ad);
        assertEquals(1, a.size());
        a.add("a", ad);
        assertEquals(1, a.size());
        a.delete("a");
        assertEquals(0, a.size());
    }

    @Test
    void testEquals() {
        Book a = new Book();
        Book b = new Book();
        Book.Address ad = new Book.Address("a", 1, 1);
        Book.Address ab = new Book.Address("a", 1, 2);
        a.add("a", ad);
        b.add("a", ad);
        assertEquals(a, b);
        b.add("b", ab);
        assertNotEquals(a, b);
    }
}