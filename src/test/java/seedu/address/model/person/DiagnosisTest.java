package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class DiagnosisTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Diagnosis(null));
    }

    @Test
    public void constructor_invalidSymptom_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> new Diagnosis(""));
        assertThrows(IllegalArgumentException.class, () -> new Diagnosis(" "));
    }
    @Test
    public void isValidDiagnosis_invalidDiagnosis_returnsFalse() {
        // null diagnosis
        assertThrows(NullPointerException.class, () -> Diagnosis.isValidDiagnosis(null));
        // invalid diagnoses
        assertFalse(Diagnosis.isValidDiagnosis("")); // empty string
        assertFalse(Diagnosis.isValidDiagnosis(" ")); // spaces only
    }
    @Test
    public void isValidDiagnosis_validDiagnosis_returnsTrue() {
        // valid diagnoses
        assertTrue(Diagnosis.isValidDiagnosis("Valid Diagnosis"));
        assertTrue(Diagnosis.isValidDiagnosis("-")); // one character
        assertTrue(Diagnosis.isValidDiagnosis("Longggggggggggg Didagnosis")); // long diagnosis
    }

    @Test
    public void equals() {
        Diagnosis diagnosis = new Diagnosis("Valid Diagnosis");

        // same values -> returns true
        assertTrue(diagnosis.equals(new Diagnosis("Valid Diagnosis")));

        // same object -> returns true
        assertTrue(diagnosis.equals(diagnosis));

        // null -> returns false
        assertFalse(diagnosis.equals(null));

        // different types -> returns false
        assertFalse(diagnosis.equals(5.0f));

        // different values -> returns false
        assertFalse(diagnosis.equals(new Diagnosis("Other Valid Diagnosis")));
    }
}
