package seedu.address.model.person;

import java.util.function.Predicate;

import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Status} matches any of the keywords given.
 */
public class AddressDiagnosisStatusPredicate implements Predicate<Person> {
    private final String address;
    private final String disease;
    private final String status;

    /**
     * Constructor for AddressDiagnosisStatusPredicate object
     *
     * @param address
     * @param disease
     * @param status
     */
    public AddressDiagnosisStatusPredicate(String address, String disease, String status) {
        this.address = address;
        this.disease = disease;
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public String getDisease() {
        return disease;
    }

    @Override
    public boolean test(Person person) {
        return person.getAddress().toString().toLowerCase().contains(address)
                && person.getDiagnosis().toString().toLowerCase().contains(disease)
                && person.getStatus().toString().equals(status);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddressDiagnosisStatusPredicate)) {
            return false;
        }

        AddressDiagnosisStatusPredicate otherAddressAndStatusPredicate =
                (AddressDiagnosisStatusPredicate) other;
        return address.equals(otherAddressAndStatusPredicate.address)
                && status.equals(otherAddressAndStatusPredicate.status);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .add("address", address)
                .add("status", status)
                .toString();
    }
}
