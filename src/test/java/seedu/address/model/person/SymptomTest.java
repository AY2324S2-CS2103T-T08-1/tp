package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class SymptomTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Symptom(null));
    }

    @Test
    public void constructor_invalidSymptom_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Symptom(""));
        assertThrows(IllegalArgumentException.class, () -> new Symptom(" "));
    }
    @Test
    public void isValidSymptom_invalidSymptom_returnsFalse() {
        // null symptom
        assertThrows(NullPointerException.class, () -> Symptom.isValidSymptom(null));
        // invalid symptoms
        assertFalse(Symptom.isValidSymptom("")); // empty string
        assertFalse(Symptom.isValidSymptom(" ")); // spaces only
    }
    @Test
    public void isValidSymptom_validSymptom_returnsTrue() {
        // valid symptoms
        assertTrue(Symptom.isValidSymptom("Valid Symptom"));
        assertTrue(Symptom.isValidSymptom("-")); // one character
        assertTrue(Symptom.isValidSymptom("Longggggggggggg Symptom")); // long symptom
    }

    @Test
    public void equals() {
        Symptom symptom = new Symptom("Valid Symptom");

        // same values -> returns true
        assertTrue(symptom.equals(new Symptom("Valid Symptom")));

        // same object -> returns true
        assertTrue(symptom.equals(symptom));

        // null -> returns false
        assertFalse(symptom.equals(null));

        // different types -> returns false
        assertFalse(symptom.equals(5.0f));

        // different values -> returns false
        assertFalse(symptom.equals(new Symptom("Other Valid Symptom")));
    }
}
