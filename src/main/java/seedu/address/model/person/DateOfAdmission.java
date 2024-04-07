package seedu.address.model.person;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;


/**
 * Represents a Person's date of admission in the address book.
 * Guarantees: immutable;
 */
public class DateOfAdmission {
    public static final String MESSAGE_CONSTRAINTS =
            "Date of admission should be in the format of YYYY-MM-DD. It should be a valid date,"
                    + " and it should not be blank.";

    private final LocalDate dateOfAdmission;

    /**
     * Constructs a {@code DateOfAdmission}.
     *
     * @param dateOfAdmission A valid date of admission.
     */
    public DateOfAdmission(String dateOfAdmission) {
        requireNonNull(dateOfAdmission);
        if (!isValidDateOfAdmission(dateOfAdmission)) {
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
        this.dateOfAdmission = LocalDate.parse(dateOfAdmission);
    }

    /**
     * Returns true if a given string is a valid date of admission.
     */
    public static boolean isValidDateOfAdmission(String test) {
        try {
            LocalDate.parse(test);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * Returns given placeholder string if value field is not initialised
     * @param alt
     * @return placeholder string
     */
    public String orElse(String alt) {
        return dateOfAdmission == null ? alt : dateOfAdmission.toString();
    }

    @Override
    public String toString() {
        return this.dateOfAdmission.toString();
    }

    @Override
    public int hashCode() {
        return dateOfAdmission.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DateOfAdmission)) {
            return false;
        }

        DateOfAdmission otherDateOfAdmission = (DateOfAdmission) other;
        return dateOfAdmission.equals(otherDateOfAdmission.dateOfAdmission);
    }
}
