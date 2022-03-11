/* FILE NAME: AddressBook.java
 * PROGRAMMER: Ilyin Vladimir, group 3530901/10005, GitHub: @wishyoudie
 * DATE: 03/11/2022
 * PURPOSE: Implementation of Address book class.
 *          Interface module.
 */

package coursework.sem2.t1;

import java.util.Set;

/**
 * Address book interface
 */
public interface AddressBook {
    boolean add(String person, Book.Address ad);
    boolean delete(String person);
    boolean replace(String person, Book.Address ad);
    Book.Address getAddress(String person);
    Set<String> getPeople(String street);
    Set<String> getPeople(String street, int house);
    int size();
}
