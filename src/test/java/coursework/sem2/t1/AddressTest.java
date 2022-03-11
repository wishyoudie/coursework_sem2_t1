/* FILE NAME: Book.java
 * PROGRAMMER: Ilyin Vladimir, group 3530901/10005, GitHub: @wishyoudie
 * DATE: 03/11/2022
 * PURPOSE: Implementation of Address book class.
 *          Realisation module.
 */

package coursework.sem2.t1;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * Address test class
 */
class AddressTest {
    @Test
    public void addressStreet() {
        String expectedStreet = "a";
        Address address = new Address("a", 1, 1);
        assertEquals(expectedStreet, address.getStreet());
    }

    @Test
    public void nullAddressStreet() {
        assertThrows(IllegalArgumentException.class, () -> new Address(null, 1, 1));
    }

    @Test
    public void addressHouse() {
        int expectedHouse = 1;
        Address address = new Address("a", 1, 1);
        assertEquals(expectedHouse, address.getHouse());
    }

    @Test
    public void nonPositiveAddressHouse() {
        assertThrows(IllegalArgumentException.class, () -> new Address("null", -1, 1));
    }

    @Test
    public void addressFlat() {
        int expectedFlat = 2;
        Address address = new Address("a", 1, 2);
        assertEquals(expectedFlat, address.getFlat());
    }

    @Test
    public void nonPositiveAddressFlat() {
        assertThrows(IllegalArgumentException.class, () -> new Address("null", 1, -1));
    }
}