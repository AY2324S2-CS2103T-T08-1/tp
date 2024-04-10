package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class CountryTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Country(null));
    }

    @Test
    public void constructor_invalidCountry_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Country(""));
        assertThrows(IllegalArgumentException.class, () -> new Country(" "));
    }

    @Test
    public void isValidCountry_invalidCountry_returnsFalse() {
        // null address
        assertThrows(NullPointerException.class, () -> Country.isValidCountry(null));
        // invalid addresses
        assertFalse(Country.isValidCountry("")); // empty string
        assertFalse(Country.isValidCountry(" ")); // spaces only
    }

    @Test
    public void isValidCountry_validCountry_returnsTrue() {
        // valid addresses
        assertTrue(Country.isValidCountry("Singapore"));
        assertTrue(Country.isValidCountry("-")); // one character
        assertTrue(Country.isValidCountry("United States of America")); // long country
    }

    @Test
    public void equals() {
        Country country = new Country("Valid Country");

        // same values -> returns true
        assertTrue(country.equals(new Country("Valid Country")));

        // same object -> returns true
        assertTrue(country.equals(country));

        // null -> returns false
        assertFalse(country.equals(null));

        // different types -> returns false
        assertFalse(country.equals(5.0f));

        // different values -> returns false
        assertFalse(country.equals(new Country("Other Valid Country")));
    }
}
