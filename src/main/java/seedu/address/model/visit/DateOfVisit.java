package seedu.address.model.visit;

import java.time.LocalDate;

/**
 * Represents a Person's date of admission in the address book.
 * Guarantees: immutable;
 */
public class DateOfVisit {
    public static final String MESSAGE_CONSTRAINTS =
            "Date of Visit should be in the format of YYYY-MM-DD, and it should not be blank.";

    public static final String VALIDATION_REGEX = "^\\d{4}-(0[1-9]|1[0-2])-(0[1-9]|[12][0-9]|3[01])";

    private final LocalDate dateOfVisit;

    /**
     * Constructs a {@code DateOfBirth}.
     *
     * @param dateOfBirth A valid date of birth.
     */
    public DateOfVisit(String dateOfBirth) {
        if (!isValidDateOfVisit(dateOfBirth)) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
        this.dateOfVisit = LocalDate.parse(dateOfBirth);
    }

    /**
     * Returns true if a given string is a valid date of birth.
     */
    public static boolean isValidDateOfVisit(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return this.dateOfVisit.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DateOfVisit)) {
            return false;
        }

        DateOfVisit otherDateOfBirth = (DateOfVisit) other;
        return dateOfVisit.equals(otherDateOfBirth.dateOfVisit);
    }

    @Override
    public int hashCode() {
        return dateOfVisit.hashCode();
    }
}
