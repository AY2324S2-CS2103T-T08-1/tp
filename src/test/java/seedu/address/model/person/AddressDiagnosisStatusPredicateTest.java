package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class AddressDiagnosisStatusPredicateTest {

    @Test
    public void equals() {
        String rightAddress = "Blk 123, Yishun Avenue 4, Singapore 567890";
        String rightDisease = "dengue";
        String rightStatus = "UNWELL";
        AddressDiagnosisStatusPredicate predicate =
                new AddressDiagnosisStatusPredicate(rightAddress, rightDisease, rightStatus);

        // same object -> returns true
        assertTrue(predicate.equals(predicate));

        // same values -> returns true
        AddressDiagnosisStatusPredicate predicateCopy =
                new AddressDiagnosisStatusPredicate(rightAddress, rightDisease, rightStatus);
        assertTrue(predicate.equals(predicateCopy));

        // different types -> returns false
        assertFalse(predicate.equals(1));

        // null -> returns false
        assertFalse(predicate.equals(null));

        // different address -> returns false
        AddressDiagnosisStatusPredicate wrongPredicate = new AddressDiagnosisStatusPredicate(
                "Blk 321, Yishun Avenue 4, Singapore 987650", rightDisease, rightStatus);
        assertFalse(predicate.equals(wrongPredicate));

        // different disease -> returns false
        wrongPredicate = new AddressDiagnosisStatusPredicate(rightAddress, "swine flu", rightStatus);
        assertFalse(predicate.equals(wrongPredicate));

        // different status -> returns false
        wrongPredicate = new AddressDiagnosisStatusPredicate(rightAddress, rightDisease, "PENDING");
        assertFalse(predicate.equals(wrongPredicate));
    }

    @Test
    public void test_containsKeywords_returnsTrue() {
        String address = "Blk 123, Yishun Avenue 4, Singapore 567890";
        String disease = "dengue fever";
        String status = "UNWELL";

        // total address and disease match
        AddressDiagnosisStatusPredicate predicate =
                new AddressDiagnosisStatusPredicate(address, disease, status);
        Person person = new PersonBuilder().withAddress(address).withStatus(status).withDiagnosis(disease).build();
        assertTrue(predicate.test(person));

        // partial address match, total disease match
        predicate = new AddressDiagnosisStatusPredicate("Yishun Avenue", disease, status);
        assertTrue(predicate.test(person));

        // total address match, partial disease match
        predicate = new AddressDiagnosisStatusPredicate(address, "dengue", status);
        assertTrue(predicate.test(person));
    }

    @Test
    public void test_doesNotContainKeywords_returnsFalse() {
        String address = "Blk 123, Yishun Avenue 4, Singapore 567890";
        String disease = "dengue fever";
        String status = "UNWELL";
        Person person = new PersonBuilder().withAddress(address).withStatus(status).withDiagnosis(disease).build();

        // Non-matching address
        AddressDiagnosisStatusPredicate predicate = new AddressDiagnosisStatusPredicate(
                "Blk 10, Sengkang Drive 4, Singapore 192847", disease, status);
        assertFalse(predicate.test(person));

        // Non-matching disease
        predicate = new AddressDiagnosisStatusPredicate(address, "swine flu", status);
        assertFalse(predicate.test(person));
    }

    @Test
    public void toStringMethod() {
        String address = "Blk 123, Yishun Avenue 4, Singapore 567890";
        String disease = "dengue";
        String status = "UNWELL";
        AddressDiagnosisStatusPredicate predicate = new AddressDiagnosisStatusPredicate(address, disease, status);

        String expected = AddressDiagnosisStatusPredicate.class.getCanonicalName()
                + "{address=" + address + ", "
                + "disease=" + disease + ", "
                + "status=" + status + "}";
        assertEquals(expected, predicate.toString());
    }
}
