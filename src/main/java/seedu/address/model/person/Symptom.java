package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's symptom in the address book.
 * Guarantees: immutable;
 */
public class Symptom {
    //making string non-empty because empty input is already represented by null,
    //and if allowed, can cause storage problems
    public static final String VALIDATION_REGEX = "[^\\s].*";
    public static final String MESSAGE_CONSTRAINTS = "Symptom can take any values, and it should not be blank";
    private final String symptom;

    /**
     * Constructs a {@code Symptom}.
     *
     * @param symptom A valid symptom.
     */
    public Symptom(String symptom) {
        requireNonNull(symptom);
        checkArgument(isValidSymptom(symptom), MESSAGE_CONSTRAINTS);
        this.symptom = symptom;
    }
    public static boolean isValidSymptom(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String getSymptom() {
        return symptom;
    }

    /**
     * Returns given placeholder string if value field is not initialised
     * @param alt
     * @return placeholder string
     */
    public String orElse(String alt) {
        return symptom == null ? alt : symptom;
    }

    @Override
    public String toString() {
        return this.symptom;
    }

    @Override
    public int hashCode() {
        return symptom.hashCode();
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Symptom)) {
            return false;
        }

        Symptom otherSymptom = (Symptom) other;
        return symptom.equals(otherSymptom.symptom);
    }
}
