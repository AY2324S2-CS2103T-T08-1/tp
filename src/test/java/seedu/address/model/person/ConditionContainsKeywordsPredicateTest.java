package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class ConditionContainsKeywordsPredicateTest {

    @Test
    public void equals() {
        List<String> firstPredicateKeywordList = Collections.singletonList("first");
        List<String> secondPredicateKeywordList = Arrays.asList("first", "second");

        ConditionContainsKeywordsPredicate firstPredicate =
                new ConditionContainsKeywordsPredicate(firstPredicateKeywordList);
        ConditionContainsKeywordsPredicate secondPredicate =
                new ConditionContainsKeywordsPredicate(secondPredicateKeywordList);

        // same object -> returns true
        assertTrue(firstPredicate.equals(firstPredicate));

        // same values -> returns true
        ConditionContainsKeywordsPredicate firstPredicateCopy =
                new ConditionContainsKeywordsPredicate(firstPredicateKeywordList);
        assertTrue(firstPredicate.equals(firstPredicateCopy));

        // different types -> returns false
        assertFalse(firstPredicate.equals(1));

        // null -> returns false
        assertFalse(firstPredicate.equals(null));

        // different condition -> returns false
        assertFalse(firstPredicate.equals(secondPredicate));
    }

    @Test
    public void test_conditionContainsKeywords_returnsTrue() {
        // One keyword
        ConditionContainsKeywordsPredicate predicate =
                new ConditionContainsKeywordsPredicate(Collections.singletonList("runny nose"));
        assertTrue(predicate.test(new PersonBuilder().withCondition("very runny nose").build()));

        // Multiple keywords
        predicate = new ConditionContainsKeywordsPredicate(Arrays.asList("myopia", "diabetes"));
        assertTrue(predicate.test(new PersonBuilder().withCondition("myopia, diabetes").build()));

        // Only one matching keyword
        predicate = new ConditionContainsKeywordsPredicate(Arrays.asList("myopia", "diabetes"));
        assertTrue(predicate.test(new PersonBuilder().withCondition("severe myopia").build()));

        // Mixed-case keywords
        predicate = new ConditionContainsKeywordsPredicate(Arrays.asList("mYoPiA", "dIaBeTeS"));
        assertTrue(predicate.test(new PersonBuilder().withCondition("myopia, diabetes").build()));
    }

    @Test
    public void test_conditionDoesNotContainKeywords_returnsFalse() {
        // Zero keywords
        ConditionContainsKeywordsPredicate predicate = new ConditionContainsKeywordsPredicate(Collections.emptyList());
        assertFalse(predicate.test(new PersonBuilder().withCondition("myopia").build()));

        // Non-matching keyword
        predicate = new ConditionContainsKeywordsPredicate(Arrays.asList("high blood pressure"));
        assertFalse(predicate.test(new PersonBuilder().withCondition("myopia, diabetes").build()));

        // Keywords match phone and address, but does not match condition
        predicate = new ConditionContainsKeywordsPredicate(Arrays.asList("91234567", "alice@email.com", "myopia"));
        assertFalse(predicate.test(new PersonBuilder().withName("Alice").withPhone("91234567")
                .withAddress("Main Street").withCondition("diabetes").build()));
    }

    @Test
    public void toStringMethod() {
        List<String> keywords = List.of("keyword1", "keyword2");
        ConditionContainsKeywordsPredicate predicate = new ConditionContainsKeywordsPredicate(keywords);

        String expected = ConditionContainsKeywordsPredicate.class.getCanonicalName() + "{keywords=" + keywords + "}";
        assertEquals(expected, predicate.toString());
    }
}
