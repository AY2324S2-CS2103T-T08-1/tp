package seedu.address.model.visit;

import java.util.function.Predicate;

import seedu.address.commons.util.StringUtil;
import seedu.address.commons.util.ToStringBuilder;

/**
 * Tests that a {@code Visit}'s {@code Nric} matches any of the keywords given.
 */
public class VisitContainsNricPredicate implements Predicate<Visit> {
    private final String keywords;

    public VisitContainsNricPredicate(String keywords) {
        this.keywords = keywords;
    }

    @Override
    public boolean test(Visit visit) {
        return StringUtil.containsWordIgnoreCase(visit.getNric().toString(), keywords);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof VisitContainsNricPredicate)) {
            return false;
        }

        VisitContainsNricPredicate otherVisitContainsNricPredicate = (VisitContainsNricPredicate) other;
        return keywords.equals(otherVisitContainsNricPredicate.keywords);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this).add("keywords", keywords).toString();
    }
}

