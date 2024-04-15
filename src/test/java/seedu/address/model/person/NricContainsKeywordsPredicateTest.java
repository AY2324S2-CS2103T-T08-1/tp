package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class NricContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        String firstPredicateKeyword = "first";
        String secondPredicateKeyword = "second";

        NricContainsKeywordsPredicate firstPredicate =
                new NricContainsKeywordsPredicate(firstPredicateKeyword);
        NricContainsKeywordsPredicate secondPredicate =
                new NricContainsKeywordsPredicate(secondPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        NricContainsKeywordsPredicate firstPredicateCopy =
                new NricContainsKeywordsPredicate(firstPredicateKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different NRIC -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_nricContainsKeywords_returnsTrue() {
        // One keyword
        NricContainsKeywordsPredicate predicate =
                new NricContainsKeywordsPredicate("T1234567A");
        assertTrue(predicate.test(new PersonBuilder().withNric("T1234567A").build()));

        // small last letter
        predicate = new NricContainsKeywordsPredicate("T1234567a");
        assertTrue(predicate.test(new PersonBuilder().withNric("T1234567A").build()));

        // small first letter
        predicate = new NricContainsKeywordsPredicate("t1234567A");
        assertTrue(predicate.test(new PersonBuilder().withNric("T1234567A").build()));
    }

    @Test
    public void test_nricDoesNotContainKeywords_returnsFalse() {
        // Non-matching keyword
        NricContainsKeywordsPredicate predicate = new NricContainsKeywordsPredicate("S7654321A");
        assertFalse(predicate.test(new PersonBuilder().withNric("T1234567A").build()));
    }

    @Test
    public void toStringMethod() {
        String keyword = "keyword";
        NricContainsKeywordsPredicate predicate = new NricContainsKeywordsPredicate(keyword);

        String expected = NricContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keyword + "}";
        assertEquals(expected, predicate.toString());
    }
}
