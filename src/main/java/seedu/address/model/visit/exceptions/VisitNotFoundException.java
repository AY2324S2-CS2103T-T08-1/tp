package seedu.address.model.visit.exceptions;

/**
 * Signals that the operation is unable to find the specified person.
 */
public class VisitNotFoundException extends RuntimeException {
    public VisitNotFoundException() {
        super("Visit not found");
    }
}
