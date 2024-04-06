package seedu.address.model.person;


import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's diagnosis in the address book.
 * Guarantees: immutable;
 */
public class Diagnosis {
    public static final String VALIDATION_REGEX = "[^\\s].*";
    public static final String MESSAGE_CONSTRAINTS = "Diagnosis can take any values, and it should not be blank";
    private final String diagnosis;

    /**
     * Constructs a {@code Diagnosis}.
     *
     * @param diagnosis A valid diagnosis.
     */
    public Diagnosis(String diagnosis) {
        requireNonNull(diagnosis);
        checkArgument(isValidDiagnosis(diagnosis), MESSAGE_CONSTRAINTS);
        this.diagnosis = diagnosis;
    }
    public static boolean isValidDiagnosis(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    /**
     * Returns given placeholder string if value field is not initialised
     * @param alt
     * @return placeholder string
     */
    public String orElse(String alt) {
        return diagnosis == null ? alt : diagnosis;
    }

    @Override
    public String toString() {
        return this.diagnosis;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Diagnosis)) {
            return false;
        }

        Diagnosis otherDiagnosis = (Diagnosis) other;
        return diagnosis.equals(otherDiagnosis.diagnosis);
    }

    @Override
    public int hashCode() {
        return diagnosis.hashCode();
    }
}
