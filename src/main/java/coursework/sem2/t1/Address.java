/* FILE NAME: Book.java
 * PROGRAMMER: Ilyin Vladimir, group 3530901/10005, GitHub: @wishyoudie
 * DATE: 03/11/2022
 * PURPOSE: Implementation of Address book class.
 *          Address class realisation module.
 */

package coursework.sem2.t1;

import java.util.Objects;

/**
 * Address implementation class
 */
public class Address {

    /* Fields */
    private final String street; // Street name
    private final int house; // House number
    private final int flat; // Flat number


    /* Constructors */

    /**
     * Default constructor from all components
     * @param street Street
     * @param house House
     * @param flat Flat
     */
    public Address(String street, int house, int flat) {
        if (street == null || street.isEmpty() || house <= 0 || flat <= 0) {
            throw new IllegalArgumentException(String.format("Incorrect address format with params: %s, %d, %d",
                    street, house, flat));
        }
        this.street = street;
        this.house = house;
        this.flat = flat;
    }


    /* Methods */

    /**
     * Get address street method
     * @return Address street name
     */
    public String getStreet() {
        return this.street;
    }

    /**
     * Get address house method
     * @return Address house number
     */
    public int getHouse() {
        return this.house;
    }

    /**
     * Get address flat method
     * @return Address flat number
     */
    public int getFlat() {
        return this.flat;
    }


    /* Default override methods */

    @Override
    public String toString() {
        return String.format("%s, д. %d, кв. %d", this.street, this.house, this.flat);
    }

    @Override
    public int hashCode() {
        return this.street.hashCode() + this.house * 2 + this.flat * 3;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address other = (Address) o;
        return Objects.equals(street, other.street) &&
                Objects.equals(house, other.house) &&
                Objects.equals(flat, other.flat);
    }
}