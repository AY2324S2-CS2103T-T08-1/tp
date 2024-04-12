package seedu.address.model.visit;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DateOfVisitTest {
    @Test
    public void isValidDateOfVisit() {
        // invalid addresses
        assertFalse(DateOfVisit.isValidDateOfVisit("")); // empty string
        assertFalse(DateOfVisit.isValidDateOfVisit(" ")); // spaces only
        assertFalse(DateOfVisit.isValidDateOfVisit("20231213")); // numbers only
        assertFalse(DateOfVisit.isValidDateOfVisit("2023-1213")); // one slash
        assertFalse(DateOfVisit.isValidDateOfVisit("202312-13")); // one slash
        assertFalse(DateOfVisit.isValidDateOfVisit("2023-1-3")); // shortened date and month
        assertFalse(DateOfVisit.isValidDateOfVisit("13-12-2023")); // reverse format

        // valid addresses
        assertTrue(DateOfVisit.isValidDateOfVisit("2023-12-13"));
    }

    @Test
    public void equals() {
        DateOfVisit date = new DateOfVisit("2020-12-05");

        // same values -> returns true
        assertTrue(date.equals(new DateOfVisit("2020-12-05")));

        // same object -> returns true
        assertTrue(date.equals(date));

        // null -> returns false
        assertFalse(date.equals(null));

        // different types -> returns false
        assertFalse(date.equals(5.0f));

        // different values -> returns false
        assertFalse(date.equals(new DateOfVisit("2023-12-13")));
    }
}
