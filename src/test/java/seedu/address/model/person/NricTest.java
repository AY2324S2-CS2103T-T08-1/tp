package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class NricTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Nric(null));
    }

    @Test
    public void constructor_invalidNric_throwsIllegalArgumentException() {
        String invalidNric = "";
        assertThrows(IllegalArgumentException.class, () -> new Nric(invalidNric));
    }

    @Test
    public void isValidNric_null_throwNullPointerException() {
        // null address
        assertThrows(NullPointerException.class, () -> Nric.isValidNric(null));
    }
    @Test
    public void isValidNric_invalidNric_returnFalse() {

        // invalid addresses
        assertFalse(Nric.isValidNric("")); // empty string
        assertFalse(Nric.isValidNric(" ")); // spaces only
        assertFalse(Nric.isValidNric("0312345")); // numbers only
        assertFalse(Nric.isValidNric("S0312345")); // without last letter
        assertFalse(Nric.isValidNric("0312345A")); // without first letter
        assertFalse(Nric.isValidNric("T03123425A")); // too many numbers
        assertFalse(Nric.isValidNric("T031234A")); // too few numbers
        assertFalse(Nric.isValidNric("D0312345S")); // invalid first number
    }
    @Test
    public void isValidNric_validNric_returnTrue() {
        // valid addresses
        assertTrue(Nric.isValidNric("T0912345A"));
        assertTrue(Nric.isValidNric("S0412345G"));
    }
    @Test
    public void toString_upperCaseNric_returnUpperCase() {
        Nric nric = new Nric("T0412345G");
        assertTrue(nric.toString().equals("T0412345G"));
    }

    @Test
    public void equals() {
        Nric nric = new Nric("T0412345G");

        // same values -> returns true
        assertTrue(nric.equals(new Nric("T0412345G")));

        // same object -> returns true
        assertTrue(nric.equals(nric));

        // null -> returns false
        assertFalse(nric.equals(null));

        // different types -> returns false
        assertFalse(nric.equals(5.0f));

        // different values -> returns false
        assertFalse(nric.equals(new Nric("T0412345H")));
    }
}
