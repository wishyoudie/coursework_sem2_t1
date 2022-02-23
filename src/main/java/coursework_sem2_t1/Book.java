/* FILE NAME: Book.java
 * PROGRAMMER: Ilyin Vladimir, group 3530901/10005, GitHub: @wishyoudie
 * DATE: 02/23/2022
 * PURPOSE: Implementation of Address book class.
 *          Realisation module.
 */

/* Package */
package coursework_sem2_t1;

/* Imports */
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.HashSet;


/*
 * Address book implementation class
 */
public class Book {

    /*
     * Address implementation class
     */
    public static class Address {

        /* Fields */
        private final String street; // Street name
        private final int house; // House number
        private final int flat; // Flat number


        /* Constructors */
        public Address(String street, int house, int flat) {
            if (street == null || house <= 0 || flat <= 0) {
                throw new IllegalArgumentException("Incorrect address format with params: " + street + ", " + house + ", " + flat);
            }
            this.street = street;
            this.house = house;
            this.flat = flat;
        }


        /* Methods */

        /*
         * Get address street method
         * Params: None
         * Returns: (String) Address street name
         */
        public String getStreet() {
            return this.street;
        }

        /*
         * Get address house method
         * Params: None
         * Returns: (int) Address house number
         */
        public int getHouse() {
            return this.house;
        }

        /*
         * Get address flat method
         * Params: None
         * Returns: (int) Address flat number
         */
        public int getFlat() {
            return this.flat;
        }


        /* Default override methods */

        @Override
        public String toString() {
            return this.street + ", д. " + this.house + ", кв. " + this.flat;
        }

        @Override
        public int hashCode() {
            return this.street.hashCode() + this.house * 2 + this.flat * 3;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj instanceof Address) {
                Address other = (Address) obj;
                return this.street.equals(other.street) &&
                        this.house == other.house &&
                        this.flat == other.flat;
            }
            return false;
        }
    }

    /* Fields */
    private final Map<String, Address> book; // Basic container


    /* Constructors */

    /* Default constructor */
    public Book() {
        this.book = new HashMap<>();
    }

    /* Constructor from Map */
    public Book(Map<String, Address> b) {
        this.book = new HashMap<>();
        this.book.putAll(b);
    }


    /* Methods */

    /* Check if book has specified person
     * Params: (String) person - key to be checked
     * Returns: true if this book doesn't have a specified person
     */
    private boolean hasNo(String person) {
        return this.book.isEmpty() || !this.book.containsKey(person);
    }

    /* Add person to book method
     * Params:
     *  - (String) person
     *      Person (key)
     *  - (Address) ad
     *      Person's address (value)
     * Returns: true if the person was added, false otherwise
     */
    public boolean add(String person, Address ad) {
        if (this.book.containsKey(person))
            return false;
        this.book.put(person, ad);
        return true;
    }

    /* Delete person from book method
     * Params: (String) person - key to be deleted
     * Returns: true if the person was deleted, false otherwise
     */
    public boolean delete(String person) {
        if (this.hasNo(person))
            return false;
        this.book.remove(person);
        return true;
    }

    /* Replace person's address in the book method
     * Params:
     *  - (String) person
     *      Person (key)
     *  - (Address) ad
     *      Person's new address (value)
     * Returns: true if the person's address was replaced, false otherwise
     */
    public boolean replace(String person, Address ad) {
        if (this.hasNo(person))
            return false;
        this.book.replace(person, ad);
        return true;
    }

    /* Get address by person method
     * Params: (String) person - key
     * Returns: (Address) of the person or null if this book contains no mapping for the key
     */
    public Address getAddress(String person) {
        return this.book.get(person);
    }

    /* Get set of people living on the specified street
     * Params: (String) street - the street to be examined
     * Returns: (Set<String>) Set of people who live on the specified street
     */
    public Set<String> getPeople(String street) {
        Set<String> res = new HashSet<>();

        for (String p : this.book.keySet()) {
            Address ad = getAddress(p);
            if (street.equals(ad.getStreet()))
                res.add(p);
        }

        return res;
    }

    /* Get set of people living on the specified street AND in the specified house
     * Params:
     *  - (String) street - the street to be examined
     *  - (int) house - the number of house to be examined
     * Returns: (Set<String>) List of people who live on the specified street AND in the specified house
     */
    public Set<String> getPeople(String street, int house) {
        Set<String> res = new HashSet<>();

        for (String p : this.book.keySet()) {
            Address ad = getAddress(p);
            if (street.equals(ad.getStreet()) && house == ad.getHouse())
                res.add(p);
        }

        return res;
    }

    /* Get book size method
     * Params: None
     * Returns: (int) the size of the book
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
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj instanceof Book) {
            Book other = (Book) obj;
            return this.book.equals(other.book);
        }
        return false;
    }
}
