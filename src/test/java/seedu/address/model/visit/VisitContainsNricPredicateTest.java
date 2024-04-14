package seedu.address.model.visit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BENSON;
import static seedu.address.testutil.TypicalVisits.VISIT_ALICE;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.VisitBuilder;

class VisitContainsNricPredicateTest {

    @Test
    public void equals() {
        String firstPredicateKeyword = ALICE.getNric().toString();
        String secondPredicateKeyword = BENSON.getNric().toString();

        VisitContainsNricPredicate firstPredicate =
                new VisitContainsNricPredicate(firstPredicateKeyword);
        VisitContainsNricPredicate secondPredicate =
                new VisitContainsNricPredicate(secondPredicateKeyword);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        VisitContainsNricPredicate firstPredicateCopy =
                new VisitContainsNricPredicate(firstPredicateKeyword);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different person -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_visitContainsKeyword_returnsTrue() {
        VisitContainsNricPredicate predicate =
                new VisitContainsNricPredicate(VISIT_ALICE.getNric().toString());
        assertTrue(predicate.test(new VisitBuilder().withNric(ALICE.getNric().toString()).build()));

        predicate = new VisitContainsNricPredicate("T0123456A");
        assertTrue(predicate.test(new VisitBuilder().withNric("T0123456A").build()));

    }

    @Test
    public void test_visitDoesNotContainKeyword_returnsFalse() {
        VisitContainsNricPredicate predicate = new VisitContainsNricPredicate("T0123456A");
        assertFalse(predicate.test(new VisitBuilder().withNric("T0654321A").build()));
    }

    @Test
    void testToString() {
        String keyword = "T0123456A";
        VisitContainsNricPredicate predicate = new VisitContainsNricPredicate(keyword);

        String expected = VisitContainsNricPredicate.class.getCanonicalName() + "{keywords=" + keyword + "}";
        assertEquals(expected, predicate.toString());
    }
}
