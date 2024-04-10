package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class AllergiesTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Allergies(null));
    }

    @Test
    public void constructor_invalidAllergies_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Allergies(""));
        assertThrows(IllegalArgumentException.class, () -> new Allergies(" "));
    }

    @Test
    public void isValidAddress_invalidAllergies_returnsFalse() {
        // null address
        assertThrows(NullPointerException.class, () -> Allergies.isValidAllergies(null));
        // invalid addresses
        assertFalse(Allergies.isValidAllergies("")); // empty string
        assertFalse(Allergies.isValidAllergies(" ")); // spaces only
    }
    @Test
    public void isValidAllergies_validAllergies_returnsTrue() {
        // valid addresses
        assertTrue(Allergies.isValidAllergies("peanut"));
        assertTrue(Allergies.isValidAllergies("-")); // one character
        assertTrue(Allergies.isValidAllergies("Pollen, Cat hair, Dog hair, Dust mites, Mold, Cockroach droppings"));
    }

    @Test
    public void equals() {
        Allergies allergies = new Allergies("Valid Allergy");

        // same values -> returns true
        assertTrue(allergies.equals(new Allergies("Valid Allergy")));

        // same object -> returns true
        assertTrue(allergies.equals(allergies));

        // null -> returns false
        assertFalse(allergies.equals(null));

        // different types -> returns false
        assertFalse(allergies.equals(5.0f));

        // different values -> returns false
        assertFalse(allergies.equals(new Allergies("Other Valid Allergy")));
    }
}
