package seedu.address.model.visit;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;

/**
 * Represents a Person's date of admission in the address book.
 * Guarantees: immutable;
 */
public class DateOfVisit {
    //Changed validity check for date format to disallow invalid dates, as not doing so results in parser error
    public static final String MESSAGE_CONSTRAINTS =
            "Date of visit should be in the format of YYYY-MM-DD. It should be a valid date,"
                    + " and it should not be blank.";

    private final LocalDate dateOfVisit;

    /**
     * Constructs a {@code DateOfBirth}.
     *
     * @param dateOfBirth A valid date of birth.
     */
    public DateOfVisit(String dateOfBirth) {
        requireNonNull(dateOfBirth);
        if (!isValidDateOfVisit(dateOfBirth)) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
        this.dateOfVisit = LocalDate.parse(dateOfBirth);
    }

    /**
     * Returns true if a given string is a valid date of birth.
     */
    public static boolean isValidDateOfVisit(String test) {
        try {
            LocalDate.parse(test);
        } catch (Exception e) {
            return false;
        }
        return true;
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
