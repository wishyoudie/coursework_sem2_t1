/* FILE NAME: Book.java
 * PROGRAMMER: Ilyin Vladimir, group 3530901/10005, GitHub: @wishyoudie
 * DATE: 02/23/2022
 * PURPOSE: Implementation of Address book class.
 *          Realisation module.
 */

/* Package */
package coursework_sem2_t1;

/* Imports */
import java.util.*;


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
            if (street == null || street.isEmpty() || house <= 0 || flat <= 0) {
                throw new IllegalArgumentException(String.format("Incorrect address format with params: %s, %d, %d",
                        street, house, flat));
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

    /* Default constructor */
    public Book() {
        this.book = new HashMap<>();
    }

    /* Constructor from Map */
    public Book(Map<String, Address> b) {
        if (b == null) throw new NullPointerException("Null map");
        this.book = new HashMap<>();
        this.book.putAll(b);
    }


    /* Methods */

    /* Check if book has specified person
     * Params: (String) person - key to be checked
     * Returns: true if this book has a specified person
     */
    private boolean contains(String person) {
        if (person == null) throw new NullPointerException("Null person");
        return this.book.containsKey(person);
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
        if (this.contains(person))
            return false;
        this.book.put(person, ad);
        return true;
    }

    /* Delete person from book method
     * Params: (String) person - key to be deleted
     * Returns: true if the person was deleted, false otherwise
     */
    public boolean delete(String person) {
        if (!this.contains(person))
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
        if (!this.contains(person))
            return false;
        this.book.replace(person, ad);
        return true;
    }

    /* Get address by person method
     * Params: (String) person - key
     * Returns: (Address) The address to which the specified key is mapped, or null if this book contains no mapping for the key
     */
    public Address getAddress(String person) {
        if (person == null) throw new NullPointerException("Null person");
        return this.book.get(person);
    }

    /* Get set of people living on the specified street
     * Params: (String) street - the street to be examined
     * Returns: (Set<String>) Set of people who live on the specified street
     */
    public Set<String> getPeople(String street) {
        Set<String> res = new HashSet<>();

        for (Map.Entry<String, Address> entry : this.book.entrySet()) {
            if (street.equals(entry.getValue().getStreet()))
                res.add(entry.getKey());
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

        for (Map.Entry<String, Address> entry : this.book.entrySet()) {
            if (street.equals(entry.getValue().getStreet()) &&
                    house == entry.getValue().getHouse()
            )
                res.add(entry.getKey());
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book that = (Book) o;
        return Objects.equals(this.book, that.book);
    }
}
