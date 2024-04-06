package seedu.address.model.person;


import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's condition in the address book.
 * Guarantees: immutable;
 */
public class Condition {
    /*
     * The first character of the address must not be a whitespace,
     * otherwise " " (a blank string) becomes a valid input.
     */
    public static final String VALIDATION_REGEX = "[^\\s].*";
    public static final String MESSAGE_CONSTRAINTS = "Conditions can take any values, and it should not be blank";
    private final String condition;

    /**
     * Constructs an {@code Condition}.
     *
     * @param condition A valid condition.
     */
    public Condition(String condition) {
        requireNonNull(condition);
        checkArgument(isValidCondition(condition), MESSAGE_CONSTRAINTS);
        this.condition = condition;
    }
    /**
     * Returns true if a given string is a valid condition.
     */
    public static boolean isValidCondition(String test) {
        return test.matches(VALIDATION_REGEX);
    }


    public String getCondition() {
        return condition;
    }

    /**
     * Returns given placeholder string if value field is not initialised
     * @param alt
     * @return placeholder string
     */
    public String orElse(String alt) {
        return condition == null ? alt : condition;
    }

    @Override
    public String toString() {
        return this.condition;
    }

    @Override
    public int hashCode() {
        return condition.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Condition)) {
            return false;
        }

        Condition otherCondition = (Condition) other;
        return condition.equals(otherCondition.condition);
    }
}
