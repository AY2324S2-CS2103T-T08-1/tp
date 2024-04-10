package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's condition in the address book.
 * Guarantees: immutable;
 */
public class Country {
    //making string non-empty because empty input is already represented by null,
    //and if allowed, can cause storage problems
    public static final String VALIDATION_REGEX = "[^\\s].*";
    public static final String MESSAGE_CONSTRAINTS = "Countries can take any values, and it should not be blank."
            + " It is case insensitive.";
    private final String country;

    /**
     * Constructs an {@code Country}.
     *
     * @param country A valid country.
     */

    public Country(String country) {
        requireNonNull(country);
        checkArgument(isValidCountry(country), MESSAGE_CONSTRAINTS);
        this.country = country;
    }

    public static boolean isValidCountry(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Returns given placeholder string if value field is not initialised
     * @param alt
     * @return placeholder string
     */
    public String orElse(String alt) {
        return country == null ? alt : country;
    }

    @Override
    public String toString() {
        return this.country;
    }

    @Override
    public int hashCode() {
        return country.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof Country)) {
            return false;
        }
        Country otherCountry = (Country) other;
        return country.equals(otherCountry.country);
    }
}
