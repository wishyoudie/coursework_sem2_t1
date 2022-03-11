/* FILE NAME: Book.java
 * PROGRAMMER: Ilyin Vladimir, group 3530901/10005, GitHub: @wishyoudie
 * DATE: 03/11/2022
 * PURPOSE: Implementation of Address book class.
 *          Realisation module.
 */


package coursework.sem2.t1;

import java.util.*;

/**
 * Address book implementation class
 */
public class Book implements AddressBook {

    /**
     * Address implementation class
     */
    public static class Address {

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


    /* Fields */
    private final Map<String, Address> book; // Basic container

    /* Constructors */

    /**
     * Default void constructor
     */
    public Book() {
        this.book = new HashMap<>();
    }

    /**
     * Constructor from map
     * @param b Map to construct Book from
     * @throws NullPointerException if map is null
     */
    public Book(Map<String, Address> b) {
        if (b == null) throw new NullPointerException("Null map");
        this.book = new HashMap<>();
        this.book.putAll(b);
    }


    /* Methods */

    /**
     * Private method. Returns true if this map contains a mapping for the specified key.
     * @param person key whose presence in this map is to be tested
     * @return true if this map contains a mapping for the specified key
     * @throws NullPointerException if the specified key is null
     */
    private boolean contains(String person) {
        if (person == null) throw new NullPointerException("Null person");
        return this.book.containsKey(person);
    }

    /**
     * Associates the specified value with the specified key in this book
     * @param person key with which the specified value is to be associated
     * @param ad value to be associated with the specified key
     * @return true if added successfully, false otherwise
     * @throws NullPointerException if the specified key is null
     */
    public boolean add(String person, Address ad) {
        if (this.contains(person))
            return false;
        this.book.put(person, ad);
        return true;
    }

    /**
     * Removes the mapping for a key from this book if it is present
     * @param person key whose mapping is to be removed from the map
     * @return true if deleted successfully, false otherwise
     * @throws NullPointerException if the specified key is null
     */
    public boolean delete(String person) {
        if (!this.contains(person))
            return false;
        this.book.remove(person);
        return true;
    }

    /**
     * Replaces the entry for the specified key only if it is currently mapped to some value.
     * @param person key with which the specified value is associated
     * @param ad value to be associated with the specified key
     * @return true if replaced successfully, false otherwise
     * @throws NullPointerException if the specified key is null
     */
    public boolean replace(String person, Address ad) {
        if (!this.contains(person))
            return false;
        this.book.replace(person, ad);
        return true;
    }

    /**
     * Returns the value to which the specified key is mapped, or null if this book contains no mapping for the key.
     * @param person the key whose associated value is to be returned
     * @return the value to which the specified key is mapped, or null if this book contains no mapping for the key
     * @throws NullPointerException if the specified key is null
     */
    public Address getAddress(String person) {
        if (person == null) throw new NullPointerException("Null person");
        return this.book.get(person);
    }

    /**
     * Returns set of keys whose values' streets match the specified one.
     * @param street the street whose villagers are to be found
     * @return set of keys whose values' streets match the specified one
     */
    public Set<String> getPeople(String street) {
        Set<String> res = new HashSet<>();

        for (Map.Entry<String, Address> entry : this.book.entrySet()) {
            if (street.equals(entry.getValue().getStreet()))
                res.add(entry.getKey());
        }

        return res;
    }

    /**
     * Returns set of keys whose values' streets and house match the specified ones.
     * @param street the street whose villagers are to be found
     * @param house the house whose villagers are to be found
     * @return set of keys whose values' streets and house match the specified ones
     */
    public Set<String> getPeople(String street, int house) {
        Set<String> res = new HashSet<>();

        for (Map.Entry<String, Address> entry : this.book.entrySet()) {
            if (street.equals(entry.getValue().getStreet()) &&
                    house == entry.getValue().getHouse()
            )
                res.add(entry.getKey());
        }

        return res;
    }

    /**
     * Returns the number of key-value mappings in this book.
     * @return the number of key-value mappings in this book
     */
    public int size() {
        return this.book.size();
    }


    /* Default override methods */

    @Override
    public String toString() {
        return this.book.toString();
    }

    @Override
    public int hashCode() {
        return this.book.hashCode();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book that = (Book) o;
        return Objects.equals(this.book, that.book);
    }
}
