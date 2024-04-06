package seedu.address.model.person;


import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's allergies in the address book.
 * Guarantees: immutable;
 */
public class Allergies {
    public static final String VALIDATION_REGEX = "[^\\s].*";
    public static final String MESSAGE_CONSTRAINTS = "Allergies can take any values, and it should not be blank";
    private final String allergies;
    /**
     * Constructs an {@code Allergies}.
     *
     * @param allergies A valid allergies.
     */
    public Allergies(String allergies) {
        requireNonNull(allergies);
        checkArgument(isValidAllergies(allergies), MESSAGE_CONSTRAINTS);
        this.allergies = allergies;
    }
    public static boolean isValidAllergies(String test) {
        return test.matches(VALIDATION_REGEX);
    }
    public String getAllergies() {
        return allergies;
    }

    /**
     * Returns given placeholder string if value field is not initialised
     * @param alt
     * @return placeholder string
     */
    public String orElse(String alt) {
        return allergies == null ? alt : allergies;
    }

    @Override
    public String toString() {
        return this.allergies;
    }

    @Override
    public int hashCode() {
        return allergies.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Allergies)) {
            return false;
        }

        Allergies otherAllergies = (Allergies) other;
        return allergies.equals(otherAllergies.allergies);
    }
}
