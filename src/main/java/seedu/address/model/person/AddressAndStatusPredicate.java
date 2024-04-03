package seedu.address.model.person;

import java.util.List;
import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Person}'s {@code Status} matches any of the keywords given.
 */
public class AddressAndStatusPredicate implements Predicate<Person> {
    private final String address;
    private final String status;
    public AddressAndStatusPredicate(String address, String status) {
        this.address = address;
        this.status = status;
    }

    @Override
    public boolean test(Person person) {
        return person.getAddress().toString().toLowerCase().contains(address)
                && person.getStatus().toString().equals(status);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddressAndStatusPredicate)) {
            return false;
        }

        AddressAndStatusPredicate otherAddressAndStatusPredicate =
                (AddressAndStatusPredicate) other;
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
