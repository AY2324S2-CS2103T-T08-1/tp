package seedu.address.model.person;

import java.time.LocalDate;

/**
 * Represents a Person's date of admission in the address book.
 * Guarantees: immutable;
 */
public class DateOfBirth {
    public static final String MESSAGE_CONSTRAINTS =
            "Date of birth should be in the format of YYYY-MM-DD. It should be a valid date,"
                    + " and it should not be blank.";

    private final LocalDate dateOfBirth;

    /**
     * Constructs a {@code DateOfBirth}.
     *
     * @param dateOfBirth A valid date of birth.
     */
    public DateOfBirth(String dateOfBirth) {
        if (!isValidDateOfBirth(dateOfBirth)) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
        this.dateOfBirth = LocalDate.parse(dateOfBirth);
    }

    /**
     * Returns true if a given string is a valid date of birth.
     */
    public static boolean isValidDateOfBirth(String test) {
        try {
            LocalDate.parse(test);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return this.dateOfBirth.toString();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DateOfBirth)) {
            return false;
        }

        DateOfBirth otherDateOfBirth = (DateOfBirth) other;
        return dateOfBirth.equals(otherDateOfBirth.dateOfBirth);
    }

    @Override
    public int hashCode() {
        return dateOfBirth.hashCode();
    }
}
